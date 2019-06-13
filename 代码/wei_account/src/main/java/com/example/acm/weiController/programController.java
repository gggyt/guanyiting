package com.example.acm.weiController;

import com.alibaba.fastjson.JSONObject;
import com.example.acm.authorization.manager.TokenManager;
import com.example.acm.authorization.model.TokenModel;
import com.example.acm.common.ResultBean;
import com.example.acm.common.ResultCode;
import com.example.acm.common.SysConst;
import com.example.acm.entity.User;
import com.example.acm.service.UserService;
import com.example.acm.utils.NumUtil;
import com.example.acm.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ggg on 2019/3/31.
 */
@Controller
@RequestMapping("program")
public class programController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenManager tokenManager;

    //获取用户的
    @RequestMapping("/getUId")
    @ResponseBody
    public ResultBean cLogin(@RequestParam(value = "code") String code,
                             HttpServletRequest request, HttpServletResponse response) {
        //https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
        String url="https://api.weixin.qq.com/sns/jscode2session?appid=wx9b2cde0cb873d47e&secret=750a36dd2b8be8f1920b13fe756fc18d&js_code="
                +code+"&grant_type=authorization_code";
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject = new URL(url);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(),"UTF-8"));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(json.toString());
        JSONObject jsStr = JSONObject.parseObject(json.toString());

        return new ResultBean(ResultCode.SUCCESS, jsStr.get("openid"));
    }

    //用户通过unionid登录
    @RequestMapping("/gLogin")
    @ResponseBody
    public ResultBean wInfo(@RequestParam(value = "unionid") String unionid,
                            HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> map = new HashMap<>();
        map.put("unionid", unionid);
        map.put("isEffective", SysConst.LIVE);
        List<User> users = userService.findUserListByQuery(map);
        if (users.size()<1) {
            return new ResultBean(ResultCode.HAS_NO_THIS_USER);
        }
        User user = users.get(0);
        TokenModel model = tokenManager.createToken(user.getUserId());
        return new ResultBean(ResultCode.SUCCESS, model);

    }

    @RequestMapping("/register")
    @ResponseBody
    public ResultBean register(@RequestParam(value = "unionid", required = false, defaultValue = "") String unionid,
                               @RequestParam(value = "mobile", required = true) String mobile,
                               @RequestParam(value = "username", required = true) String username,
                               @RequestParam(value = "grade", required = true) int grade,
                               @RequestParam(value = "studentId", required = true) Long studentId,
                               @RequestParam(value = "realName", required = true) String realName,
                               @RequestParam(value = "image", required = false, defaultValue = "") String image,
                               @RequestParam(value = "openId", required = false, defaultValue = "") String openId,
                               HttpServletRequest request) {
        if (StringUtils.isNull(unionid) || StringUtils.isNull(mobile) || StringUtils.isNull(realName)) {
            return new ResultBean(ResultCode.SYSTEM_FAILED, "参数不为空");
        }
        if (!NumUtil.isPhone(mobile)) {
            return new ResultBean(ResultCode.PARAM_ERROR, "请输入正确的手机号");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("isEffective", SysConst.LIVE);
        List<User> users = userService.findUserListByQuery(map);
        if (users.size()>0) {
            User user = users.get(0);

            if (user.getUnionid()!="") {
                TokenModel model = tokenManager.createToken(user.getUserId());
                return new ResultBean(ResultCode.USER_EXEIST, model);
            }
            user.setUnionid(unionid);
            userService.updateUserByUserId(user.getUserId(), user);
            TokenModel model = tokenManager.createToken(user.getUserId());
            return new ResultBean(ResultCode.USER_EXEIST, model);
        }
        image = image.replace("\\", "");

        User user = new User();
        user.setRealname(realName);
        user.setUsername(username);
        user.setStudentId(studentId);
        user.setUnionid(unionid);
        user.setGrade(grade);
        user.setOpenId(openId);
        user.setMobile(mobile);
        user.setIsEffective(SysConst.LIVE);
        user.setAuth(SysConst.NOT_PASS);
        user.setImage(image);
        user.setCreateDay(new Date());

        userService.addUser(user);
         users = userService.findUserListByQuery(map);
        TokenModel model = tokenManager.createToken(users.get(0).getUserId());
        return new ResultBean(ResultCode.SUCCESS,model );
    }
}
