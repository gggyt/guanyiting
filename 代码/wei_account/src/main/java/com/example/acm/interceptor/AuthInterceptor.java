package com.example.acm.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOG = LoggerFactory
            .getLogger(AuthInterceptor.class);

    private static AuthInterceptor authInterceptor;

    @Value("${authUrl}")
    private String excludeAuthUrls;

    private static String[] excludeAuthUrl;

    @PostConstruct //通过@PostConstruct实现初始化bean之前进行的操作
    public void init(){
        authInterceptor = this;
        authInterceptor.excludeAuthUrls = this.excludeAuthUrls;
        excludeAuthUrl = authInterceptor.excludeAuthUrls.split(",");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOG.info("-------------------------"+request.getMethod());
        if (request.getMethod().equals("OPTION")) {
            return false;
        }else{
            return true;
        }
    }

    /**
     * 判断是否是权限url
     *
     * @param url
     * @return
     * @Author:leining
     * @date:14:28 2018/7/18
     */
    private boolean isAuthUri(String url) {
        if (excludeAuthUrl == null || excludeAuthUrl.length <= 0) {
            return false;
        }
        for (String ex : excludeAuthUrl) {
            url = url.trim();
            ex = ex.trim();
            if (compareUrl(url, ex))
                return true;
        }
        return false;
    }

    /**
     * 比较 url
     * @param url 当前访问url
     * @param ex  权限url
     * @return
     * @Author:leining
     * @date:14:28 2018/7/18
     */
    private static boolean compareUrl(String url, String ex) {
        if (ex.endsWith("/*")) {
            String match = ex.replaceAll("/\\*", "/");
            if (url.startsWith(match)) {
                return true;
            } else {
                return false;
            }
        } else {
            if (url.equals(ex)) {
                return true;
            } else {
                return false;
            }
        }
    }
}
