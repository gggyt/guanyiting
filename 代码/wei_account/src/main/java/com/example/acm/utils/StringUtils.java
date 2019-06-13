package com.example.acm.utils;

import org.apache.ibatis.annotations.Param;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ggg on 2018/11/25.
 */
public class StringUtils {

    public static boolean isNull(String s) {
        if (s == null || "".equals(s)) {
            return true;
        }
        return false;
    }
    public static boolean isConnect(String urlStr) {

        int counts = 0;
        if (isNull(urlStr)) {
            return false;
        }

        while(counts<5) {
            try {
                URL url = new URL(urlStr);
                HttpURLConnection con=(HttpURLConnection)url.openConnection();
                int state = con.getResponseCode();
                if (state==200) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                counts++;
            }

        }
        return false;
    }
    public static String getHtml(String html) {
        html = html.replace("%26", "&");
        html = html.replace("%2B", "\\");

        return html;
    }
    public static void main(String[] args) {
        System.out.println(isConnect("http://www.baidu.com"));
        System.out.println(isConnect("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=2&tn=baiduhome_pg&wd=%E5%88%A4%E6%96%AD%E7%BD%91%E5%9D%80%E6%98%AF%E5%90%A6%E6%9C%89%E6%95%88&oq=antd&rsv_pq=f4cc2a080001059e&rsv_t=2230vugc7AQR6c58AqrK06iidIL5c9FieWij7y0JcfbdjEBm5gwuGH6S41cry5pcgaCt&rqlang=cn&rsv_enter=0&inputT=7218&rsv_sug3=80&rsv_sug1=29&rsv_sug7=100&rsv_sug2=0&rsv_sug4=7219"));
        System.out.println(isConnect("https://blog.csdn.net/cplvfx/article/details/7748321"));
    }
}
