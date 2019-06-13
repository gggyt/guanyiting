package com.example.acm.cache;

import redis.clients.jedis.ScanResult;
import com.example.acm.cache.JedisKeys.*;
import java.util.List;

public class JedisFT {

    /**
     * set方法
     *
     * @param key 对应jediskey值
     * @param connectKey 连接key信息
     * @param value 放入值
     * @version v1.0
     */
    public static void set(JedisKeys.JedisKeysEnum key, String connectKey, String value) {
        //JedisAPI.select(key.getCode());
        JedisAPI.set(key.getCode(), key.getKey() + connectKey, value);
    }

    /**
     * set并设置过期时间
     *
     * @param key 对应jediskey值
     * @param connectKey 连接key信息
     * @param value 放入值
     * @param expiretime 过期时间
     * @version v1.0
     */
    public static void set(JedisKeys.JedisKeysEnum key, String connectKey, String value,
                           int expiretime) {
        set(key, connectKey, value);
        if (expiretime > 0) {
            JedisAPI.expire(key.getCode(), key.getKey() + connectKey,
                    expiretime);
        } else {
            JedisAPI.expire(key.getCode(), key.getKey() + connectKey,
                    JedisKeys.ONE_HOUR);
        }
    }

    /**
     * set并设置过期到达时间
     *
     * @param key 对应jediskey值
     * @param connectKey 连接key信息
     * @param value 放入值
     * @param expireUnixtime 过期到达时间
     * @version v1.0
     */
    public static void set(JedisKeys.JedisKeysEnum key, String connectKey, String value,
                           Long expireUnixtime) {
        if (null == expireUnixtime) {
            return;
        }
        set(key, connectKey, value);
        JedisAPI.expireAt(key.getCode(), key.getKey() + connectKey,
                expireUnixtime);
    }

    /**
     * 设置过期时间
     *
     * @param key 对应jediskey值
     * @param connectKey 连接key信息
     * @param expiretime 过期时间
     * @version v1.0
     */
    public static void expire(JedisKeys.JedisKeysEnum key, String connectKey,
                              int expiretime) {
        if (expiretime > 0) {
            JedisAPI.expire(key.getCode(), key.getKey() + connectKey,
                    expiretime);
        } else {
            JedisAPI.expire(key.getCode(), key.getKey() + connectKey,
                    JedisKeys.ONE_HOUR);
        }
    }

    /**
     * 设置过期时间
     *
     * @param key 对应jediskey值
     * @param connectKey 连接key信息
     * @param expireUnixtime 过期到达时间
     * @version v1.0
     */
    public static void expireAt(JedisKeysEnum key, String connectKey,
            Long expireUnixtime) {
        if (null == expireUnixtime) {
            return;
        }
        JedisAPI.expireAt(key.getCode(), key.getKey() + connectKey,
                expireUnixtime);
    }

    /**
     * 锁结构 1可以存放 0不可以存放,已经有值
     *
     * @param key 对应jediskey值
     * @param connectKey 连接key信息
     * @param value 放入值
     * @version v1.0
     */
    public static Long setnx(JedisKeysEnum key, String connectKey,
            String value) {
        //JedisAPI.select(key.getCode());
        return JedisAPI.setnx(key.getCode(), key.getKey() + connectKey, value);
    }

    /**
     * set和incr
     *
     * @param key 对应jediskey值
     * @param connectKey 连接key信息
     * @param num 放入值
     * @return 返回创建完的值
     * @version v1.0
     */
    public static Long setAndIncr(JedisKeysEnum key, String connectKey,
            Long num) {
        Long check = setnx(key, connectKey, num.toString());
        if (check.equals(0L)) {
            return JedisAPI.incrBy(key.getCode(), key.getKey() + connectKey,
                    num);
        } else {
            return num;
        }
    }

    /**
     * set和decr
     *
     * @param key 对应jediskey值
     * @param connectKey 连接key信息
     * @param num 放入值
     * @return 返回创建完的值
     * @version v1.0
     */
    public static Long setAndDecr(JedisKeysEnum key, String connectKey,
            Long num) {
        Long check = setnx(key, connectKey, num.toString());
        if (check.equals(0L)) {
            return JedisAPI.decrBy(key.getCode(), key.getKey() + connectKey,
                    num);
        } else {
            return num;
        }
    }

    /**
     * get方法
     *
     * @param key 对应jediskey值
     * @param connectKey 连接key信息
     * @return 返回的值
     * @version v1.0
     */
    public static String get(JedisKeysEnum key, String connectKey) {
        //JedisAPI.select(key.getCode());
        return JedisAPI.get(key.getCode(), key.getKey() + connectKey);
    }

    /**
     * delete方法
     *
     * @param key 对应jediskey值
     * @param connectKey 连接key信息
     * @version v1.0
     */
    public static void delete(JedisKeysEnum key, String connectKey) {
        //JedisAPI.select(key.getCode());
        JedisAPI.delete(key.getCode(), key.getKey() + connectKey);
    }

    /**
     * lpush方法
     *
     * @param key 对应jediskey值
     * @param connectKey 连接key信息
     * @param members 需要放入的队列
     * @return 返回的值
     * @version v1.0
     */
    public static long lpush(JedisKeysEnum key, String connectKey,
            String... members) {
        //JedisAPI.select(key.getCode());
        return JedisAPI.lpush(key.getCode(), key.getKey() + connectKey,
                members);
    }

    /**
     * rpop方法
     *
     * @param key 对应jediskey值
     * @param connectKey 连接key信息
     * @return 返回的值
     * @version v1.0
     */
    public static String rpop(JedisKeysEnum key, String connectKey) {
        //JedisAPI.select(key.getCode());
        return JedisAPI.rpop(key.getCode(), key.getKey() + connectKey);
    }

    /**
     * 删除指定的元素
     *
     * @param key 对应jediskey值
     * @param connectKey 连接key信息
     * @param value 值
     * @return 删除符合的个数
     * @version v1.0
     */
    public static long lrem(JedisKeysEnum key, String connectKey,
            String value) {
        //JedisAPI.select(key.getCode());
        return JedisAPI.lrem(key.getCode(), key.getKey() + connectKey, value);
    }

    /**
     * 获取list的个数
     *
     * @param key 对应jediskey值
     * @param connectKey 连接key信息
     * @return 个数
     * @version v1.0
     */
    public static long llen(JedisKeysEnum key, String connectKey) {
        //JedisAPI.select(key.getCode());
        return JedisAPI.llen(key.getCode(), key.getKey() + connectKey);
    }

    /**
     * 查询list列表
     *
     * @param key 对应jediskey值
     * @param connectKey 连接key信息
     * @param start 开始
     * @param end 结束
     * @return 列表
     * @version v1.0
     */
    public static List<String> lrange(JedisKeysEnum key, String connectKey,
            Long start, Long end) {
        //JedisAPI.select(key.getCode());
        return JedisAPI.lrange(key.getCode(), key.getKey() + connectKey, start,
                end);
    }

    /**
     * scan方法
     *
     * @param key 对应jediskey值
     * @param connectKey 连接key信息
     * @param cursor 游标
     * @return 返回的值
     * @version v1.0
     */
    public static ScanResult<String> scan(JedisKeysEnum key, String connectKey,
            String cursor) {
        //JedisAPI.select(key.getCode());
        return JedisAPI.scan(key.getCode(), cursor, key.getKey() + connectKey);
    }

    /**
     * scanAll方法
     *
     * @param key 对应jediskey值
     * @param connectKey 连接key信息
     * @return 返回的值
     * @version v1.0
     */
    public static List<String> scanAll(JedisKeysEnum key, String connectKey) {
        //JedisAPI.select(key.getCode());
        return JedisAPI.scanAll(key.getCode(), key.getKey() + connectKey);
    }

    /**
     * 获取过期时间
     * @param key
     * @return
     */
    public static Long getTTL(JedisKeysEnum key,String connectKey){
        return JedisAPI.pttl(key.getCode(),key.getKey()+connectKey);
    }

    public static Long setRang(JedisKeysEnum key, String connectKey, String value){
        return JedisAPI.setrang(key.getCode(),key.getKey()+connectKey,value);
    }

}
