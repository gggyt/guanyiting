package com.example.acm.weiController;

import com.example.acm.authorization.manager.TokenManager;
import com.example.acm.authorization.model.TokenModel;
import com.example.acm.common.ResultBean;
import com.example.acm.common.ResultCode;
import com.example.acm.common.SysConst;
import com.example.acm.entity.User;
import com.example.acm.entity.WeiEntity;
import com.example.acm.service.UserService;
import com.example.acm.utils.StringUtils;
import com.sun.deploy.net.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ggg on 2019/3/10.
 */
@Controller
public class test {

    private String appid="wx5418ed8c3ec757dd";
    private String secret="bd750b6a07ec365a68685eba3d486e9d";

    private static final Logger LOG = LoggerFactory.getLogger(test.class);
    @Autowired
    private UserService userService;

    @Autowired
    private TokenManager tokenManager;
    /*
        https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx5418ed8c3ec757dd&redirect_uri=http://gyt.easy.echosite.cn/sign/test&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect
    */

    //获取用户信息
    @RequestMapping("/wLogin")
    @ResponseBody
    public ResultBean wLogin(@RequestParam(value = "code") String code,
                             HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isNull(code)) {
            return new ResultBean(ResultCode.PARAM_ERROR, "未登录");
        }
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret="
                + secret + "&code=" + code + "&grant_type=authorization_code";
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject = new URL(url);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();

            LOG.info("-----------");
            String s = json.toString();
            LOG.info(s);
            LOG.info("----"+json.toString());
            JSONObject jsStr = JSONObject.parseObject(json.toString());
            String openId = (String) jsStr.get("openid");
            if (openId.equals("")) {
                return new ResultBean(ResultCode.READ_MYSQL_FAILED, "获取openid失败");
            }
            /**/
            //https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
            String url1 = "https://api.weixin.qq.com/sns/userinfo?access_token="+jsStr.get("access_token")
                    +"&openid="+openId+"&lang=zh_CN";
            StringBuilder json1 = new StringBuilder();
            try {
                 urlObject = new URL(url1);
                 uc = urlObject.openConnection();
                 in = new BufferedReader(new InputStreamReader(uc.getInputStream(),"UTF-8"));
                 inputLine = null;
                while ( (inputLine = in.readLine()) != null) {
                    json1.append(inputLine);
                }
                in.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(json1.toString());
            JSONObject jsStr2 = JSONObject.parseObject(json1.toString());
            /**/
            Map<String, Object> map = new HashMap<>();
            map.put("openId", openId);
            map.put("isEffective", SysConst.LIVE);
            LOG.info(openId);
            List<User> users = userService.findUserListByQuery(map);
            if (users.size()>0) {
                User user = users.get(0);
                TokenModel model = tokenManager.createToken(user.getUserId());
                ResultBean b = new ResultBean(ResultCode.SUCCESS, model);
                b.setMsg(openId);

                return b;
            }
            String image = (String)jsStr2.get("headimgurl");
            LOG.info("image:"+image);
            image = image.replace("\\","");
            LOG.info("未存在openid为"+openId+"的用户");
            WeiEntity weiEntity = new WeiEntity(image, openId);
            return new ResultBean(ResultCode.HAS_NO_THIS_USER, weiEntity);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultBean(ResultCode.READ_MYSQL_FAILED, "已进入该页面，无法再次登陆");
        }
        return new ResultBean(ResultCode.READ_MYSQL_FAILED, "获取openid失败");
    }

    //获取用户信息
    @RequestMapping("/wInfo")
    @ResponseBody
    public ResultBean wInfo(@RequestParam(value = "openId") String openId,
                             HttpServletRequest request, HttpServletResponse response) {
        LOG.info(openId);
        Map<String, Object> map = new HashMap<>();
        map.put("openId", openId);
        map.put("isEffective", SysConst.LIVE);
        LOG.info(openId);
        List<User> users = userService.findUserListByQuery(map);
        if (users.size()<1) {
            return new ResultBean(ResultCode.HAS_NO_THIS_USER);


        }
        User user = users.get(0);
        TokenModel model = tokenManager.createToken(user.getUserId());
        return new ResultBean(ResultCode.SUCCESS, model);

    }

    //获取用户信息
    @RequestMapping("/sign")
    @ResponseBody
    public String test(@RequestParam(value = "code") String code,
                       HttpServletRequest request, HttpServletResponse response) {
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="
                +secret+"&code="+code+"&grant_type=authorization_code";
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
            String openId = (String)jsStr.get("openid");
       // String url1 = "https://api.weixin.qq.com/sns/userinfo?access_token="+jsStr.get("access_token")
               // +"&openid="+openId+"&lang=zh_CN";
        //https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
            String url1 = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+jsStr.get("access_token")
                    +"&openid="+openId+"&lang=zh_CN";
        StringBuilder json1 = new StringBuilder();
        try {
            URL urlObject = new URL(url1);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(),"UTF-8"));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json1.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(json1.toString());
        return "xx";
    }

    //获取用户信息
    @RequestMapping("/id")
    @ResponseBody
    public String id(@RequestParam(value = "code") String code,
                       HttpServletRequest request, HttpServletResponse response) {
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="
                +secret+"&code="+code+"&grant_type=authorization_code";
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
        String url2="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=wx5418ed8c3ec757dd&grant_type=refresh_token&refresh_token="+jsStr.get("refresh_token");
        StringBuilder json2 = new StringBuilder();
        try {
            URL urlObject = new URL(url2);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(),"UTF-8"));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json2.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(json2.toString());
        JSONObject jsStr2 = JSONObject.parseObject(json2.toString());
        String openId = (String)jsStr.get("openid");
        // String url1 = "https://api.weixin.qq.com/sns/userinfo?access_token="+jsStr.get("access_token")
        // +"&openid="+openId+"&lang=zh_CN";
        //https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
        String url1 = "https://api.weixin.qq.com/sns/userinfo?access_token="+jsStr2.get("access_token")
                +"&openid="+openId+"&lang=zh_CN";
        StringBuilder json1 = new StringBuilder();
        try {
            URL urlObject = new URL(url1);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(),"UTF-8"));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json1.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(json1.toString());
        return "xx";
    }
    //获取用户信息
    @RequestMapping("/cLogin")
    @ResponseBody
    public String cLogin(@RequestParam(value = "code") String code,
                       HttpServletRequest request, HttpServletResponse response) {
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

        return "xx";
    }

    @RequestMapping("/menu")
    @ResponseBody
    public String menu(@RequestParam(value = "code") String code,
                       HttpServletRequest request, HttpServletResponse response) {
        String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="
                +secret;
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
        //String menu = "{\"button\":[{\"name\":\"菜单\",\"sub_button\":[{\"type\":\"view\",\"name\":\"首页\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx5418ed8c3ec757dd&redirect_uri=http://gyt.easy.echosite.cn/mobileIndex&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect\"}]},{\"name\":\"加入我们\",\"sub_button\":[{\"type\":\"view\",\"name\":\"加入\",\"url\":\"http://gyt.easy.echosite.cn/mobile/home\"}]}]}";
        //String menu = "{\"button\":[{\"name\":\"菜单\",\"sub_button\":[{\"type\":\"view\",\"name\":\"首页\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx5418ed8c3ec757dd&redirect_uri=http://gyt.easy.echosite.cn/mobilelogin&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect\"}]}]}";
        String menu="{\"button\":[{\"name\":\"菜单\",\"sub_button\":[{\"type\":\"view\",\"name\":\"首页\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx5418ed8c3ec757dd&redirect_uri=http://gyt.easy.echosite.cn/mobilelogin&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect\"},{\"type\":\"view\",\"name\":\"关于我们\",\"url\":\"http://gyt.easy.echosite.cn/aboutUs\"}]},{\"name\":\"最新消息\",\"sub_button\":[{\"type\":\"view\",\"name\":\"最新问题\",\"url\":\"http://gyt.easy.echosite.cn/newInvitation\"},{\"type\":\"view\",\"name\":\"最新讲座\",\"url\":\"http://gyt.easy.echosite.cn/newLecture\"}]}]}";
        String url1 = " https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+jsStr.get("access_token");
        StringBuilder json1 = new StringBuilder();
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";

        try {
            URL realUrl = new URL(url1);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //1.获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            //2.中文有乱码的需要将PrintWriter改为如下
            //out=new OutputStreamWriter(conn.getOutputStream(),"UTF-8")
            // 发送请求参数
            out.print(menu);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        System.out.println("post推送结果："+result);
        System.out.println(json1.toString());
        return "xx";
    }

    @RequestMapping("/menuGet")
    @ResponseBody
    public String menuGet(@RequestParam(value = "code") String code,
                       HttpServletRequest request, HttpServletResponse response) {
        String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="
                +secret;
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
        String url1 = " https://api.weixin.qq.com/cgi-bin/menu/get?access_token="+jsStr.get("access_token");
        StringBuilder json1 = new StringBuilder();
        try {
            URL urlObject = new URL(url1);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(),"UTF-8"));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json1.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(json1.toString());
        return "xx";
    }

    @RequestMapping("/menuDelete")
    @ResponseBody
    public String menuDelete(@RequestParam(value = "code") String code,
                          HttpServletRequest request, HttpServletResponse response) {
        String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="
                +secret;
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
        String url1 = " https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+jsStr.get("access_token");
        StringBuilder json1 = new StringBuilder();
        try {
            URL urlObject = new URL(url1);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(),"UTF-8"));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json1.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(json1.toString());
        return "xx";
    }
}
