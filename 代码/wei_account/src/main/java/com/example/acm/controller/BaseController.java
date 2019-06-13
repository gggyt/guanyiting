package com.example.acm.controller;


import com.example.acm.cache.JedisFT;
import com.example.acm.cache.JedisKeys;
import com.example.acm.common.ResultBean;
import com.example.acm.common.ResultCode;
import com.example.acm.common.SysConst;
import com.example.acm.conf.RedisComponent;
import com.example.acm.entity.User;
import com.example.acm.service.UserService;
import com.example.acm.view.UserView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseController {
    private static final Logger LOG = LoggerFactory
            .getLogger(BaseController.class);

    @Autowired
    RedisComponent redisComponent;
    @Autowired
    private UserService userService;
    /**
     * 获取用户信息
     *
     * @param request
     */
    public User getUserIdFromSession(HttpServletRequest request) {
//        String token = request.getHeader(SysConst.TOKEN).substring(7);
//        String userStr = JedisFT.get(JedisKeys.JedisKeysEnum.USERINFO_TOKEN,token);
//        User user = JSONUtils.parse2Bean(userStr,User.class);
        if (request.getHeader(SysConst.TOKEN)==null) {
            return null;
        }
        if (redisComponent.get(request.getHeader(SysConst.TOKEN)) == null) {
            return null;
        }
        int uId = Integer.parseInt(redisComponent.get(request.getHeader(SysConst.TOKEN)));
        Map<String, Object> map = new HashMap<>();
        LOG.info("------uID"+uId);
        map.put("uId", uId);
        map.put("isEffective", SysConst.USE);
        List<User> user = userService.findUserListByQuery(map);
        if (user==null) {
            return null;
        }
        User reU = user.get(0);
        //RedisUser user = (RedisUser) request.getSession().getAttribute("userInfo");
       return reU;
    }

    /**
     * 放入session
     *
     * @param response
     * @param session
     * @param user
     */
    public void setUserSession(HttpServletRequest request, HttpServletResponse response, HttpSession session, User user){
        session.setMaxInactiveInterval(18000);
        session.setAttribute("userInfo", user);
//        //放cookie
//        Cookie cookie = new Cookie("ftSessionID", user.getUserID().toString());
//        cookie.setMaxAge(60 * 60 * 4);// 4小时
//        cookie.setPath("/");
//        response.addCookie(cookie);
    }

    /**
     * 打印cookie信息
     *
     * @param request
     */
    public void printCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(null == cookies || cookies.length == 0){
            //LOG.error(" ############cookie is empty");
        }else {
            //LOG.info(" ############ login cookieInfo: ");
            for(Cookie cookie : cookies){
                LOG.info(" cookie Name:"+cookie.getName() +" cookie value:"+cookie.getValue());
            }
        }
    }
}
