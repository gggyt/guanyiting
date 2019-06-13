package com.example.acm.cache;

/**
 * redis key值管理
 *
 */
public class JedisKeys {

    public static final int ONE_HOUR = 1 * 60 * 60;// 1个小时时间
    public static final int FIVE_MINIT= 5 * 60;// 1分钟
    public static final int ONE_MINIT = 1 * 60;
    public static final Long ONE_MINIT_TIME=1L*60L*1000L;
    public static enum JedisKeysEnum {
//        用户存储 auth:userinfo:token:${token}
        USERINFO_TOKEN("auth:userinfo:token:", 0),

        //用户验证码校验 seaeye:message:code:${requestID}
        message_code("seaeye:message:code:",0),

        operateVoucher("auth:voucher:code:", 0);
        // 构造方法
        private JedisKeysEnum(String key, int code) {
            this.key = key;
            this.code = code;
        }

        private final String key;
        private final Integer code;

        public String getKey() {
            return this.key;
        }

        public Integer getCode() {
            return this.code;
        }

    }

    public static void main(String[] args) {

    }

}