package com.example.acm.cache;


import redis.clients.jedis.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description : Redis操作接口，简单操作可以通过调用提供的快速接口实现，复杂的操作自己获取jedis实例，
 *              使用过程中出现异常应调用returnResource方法销毁异常的实例
 *              ，使用完成ying调用returnResource方法归还实例，具体使用方法参考快速接口
 */
public class JedisAPI {
    private static JedisPool pool = null;

    /**
     * 从JedisPool中获取Jedis实例
     * 
     * @return Jedis实例
     * @version:v1.0
     */

    public static Jedis getResource() {
        if (pool == null) {
            synchronized (JedisAPI.class) {
                if (pool == null) {
                    JedisPoolConfig config = new JedisPoolConfig();
                    // 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
                    // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
                    config.setMaxTotal(2000);
                    // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
                    config.setMaxIdle(8);
                    // 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
                    config.setMaxWaitMillis(1000 * 60);
                    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
                    config.setTestOnBorrow(true);
//                    String server = "192.168.188.101";
//                    int port = 6379;
            String server = "localhost";
            int port = 6379;
            String password = "123456";
            pool = new JedisPool(config, server, port, 30 * 1000, password);
                }
            }
        }
        return pool.getResource();
    }

    /**
     * 返还连接池
     *
     * @param jedis jedis实例
     * @version:v1.0
     */
    public static void returnResource(Jedis jedis) {
        if (jedis != null) {
            //pool.returnResource(jedis);
            jedis.close();
        }
    }

    /**
     * 销毁出现异常的jedis实例
     *
     * @param jedis jedis实例
     * @version:v1.0
     */
    public static void brokenResource(Jedis jedis) {
        if (jedis != null) {
//            pool.returnBrokenResource(jedis);
            jedis.close();
            pool = null;
        }
    }

    /**
     * 获取redis并切换数据库
     *
     * @param db 数据库号 0-15
     * @return jedis
     * @version v1.0
     */
    public static Jedis getResourceByDB(int db) {
        Jedis jedis = getResource();
        jedis.select(db);
        return jedis;
    }

    /**
     * 选择数据库
     *
     * @param index 数据库编号
     * @return 成功或失败
     * @version v1.0
     */
    public static String select(Integer index) {
        Jedis jedis = null;
        String statusCode = null;
        try {
            jedis = getResource();
            statusCode = jedis.select(index);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return statusCode;
    }

    /**
     * 给指定的key设置过期时间
     * 
     * @param key
     * @param seconds
     * @version:v1.0
     */
    public static void expire(int db, final String key, final int seconds) {
        Jedis jedis = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            jedis.expire(key, seconds);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
    }

    /**
     * 设置到期时间
     * 
     * @param key
     * @param unixTime
     * @version:v1.0
     */
    public static void expireAt(int db, final String key, final long unixTime) {
        Jedis jedis = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            jedis.expireAt(key, unixTime);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
    }

    /**
     * 快速取String类型数据
     * 
     * @param key
     * @return
     * @version:v1.0
     * @author:张瑞
     * @date:2013-6-25 上午10:38:15
     */
    public static String get(int db, String key) {
        String value = null;
        Jedis jedis = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            value = jedis.get(key);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return value;
    }

    public static List<String> mget(int db, final String... keys) {
        List<String> resList = null;
        Jedis jedis = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            resList = jedis.mget(keys);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
        return resList;
    }

    /**
     * 快速存String类型数据
     * 
     * @param key
     * @param value
     * @return 操作状态
     * @version:v1.0
     * @author:张瑞
     * @date:2013-6-25 上午11:04:54
     */
    public static String set(int db, String key, String value) {
        Jedis jedis = null;
        String statusCode = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            statusCode = jedis.set(key, value);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return statusCode;
    }

    /**
     * 放入信息是否已经存在 1可以存放 0不可以存放,已经有值
     *
     * @param key 
     * @param value
     * @return
     * @version v1.0
     * @author 张瑞
     * @date 2017年6月2日 上午11:38:57
     */
    public static Long setnx(int db, String key, String value) {
        Long result = null;
        Jedis jedis = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            result = jedis.setnx(key, value);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
        return result;
    }

    public static Long incr(int db, String key) {
        Jedis jedis = null;
        Long res = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            res = jedis.incr(key);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 加操作
     *
     * @param key
     * @return
     * @version v1.0
     * @author 张瑞
     * @date 2017年7月21日 下午12:06:05
     */
    public static Long incrBy(int db, String key, Long num) {
        Jedis jedis = null;
        Long res = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            res = jedis.incrBy(key, num);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
        return res;
    }

    /**
     * 减操作
     *
     * @param key
     * @return
     * @version v1.0
     * @author 张瑞
     * @date 2017年7月21日 下午12:06:25
     */
    public static Long decr(int db, String key) {
        Jedis jedis = null;
        Long res = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            res = jedis.decr(key);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
        return res;
    }

    /**
     * 减操作
     *
     * @param key
     * @return
     * @version v1.0
     * @author 张瑞
     * @date 2017年7月21日 下午12:06:25
     */
    public static Long decrBy(int db, String key, Long num) {
        Jedis jedis = null;
        Long res = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            res = jedis.decrBy(key, num);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
        return res;
    }

    /**
     * 存入hash表
     * 
     * @param key hash表名
     * @param field 字段
     * @param value 值
     * @return 更新返回 0,新增返回 1
     * @version:v1.0
     * @author:张瑞
     * @date:2013-6-26 下午2:09:17
     */
    public static long hset(int db, String key, String field, String value) {
        Jedis jedis = null;
        long statusCode = -1;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            statusCode = jedis.hset(key, field, value);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return statusCode;
    }

    /**
     * 从hash表取数据
     * 
     * @param key hash表名
     * @param field 字段
     * @return
     * @version:v1.0
     * @author:张瑞
     * @date:2013-6-26 下午2:18:33
     */
    public static String hget(int db, String key, String field) {
        String value = null;
        Jedis jedis = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            value = jedis.hget(key, field);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return value;
    }

    public static String hmset(int db, final String key,
            final Map<String, String> hash) {
        String value = null;
        Jedis jedis = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            value = jedis.hmset(key, hash);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return value;
    }

    public static List<String> hmget(int db, String key, String... fields) {
        List<String> value = null;
        Jedis jedis = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            value = jedis.hmget(key, fields);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return value;
    }

    /**
     * 获得hash中所有的键值对
     * 
     * @param key
     * @return
     * @version:v1.0
     */
    public static Map<String, String> hgetAll(int db, String key) {
        Map<String, String> map = null;
        Jedis jedis = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            map = jedis.hgetAll(key);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return map;
    }

    /**
     * 获取hash中所有key
     * 
     * @param key
     * @return
     * @version:v1.0
     */
    public static Set<String> hkeys(int db, String key) {
        Set<String> set = null;
        Jedis jedis = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            set = jedis.hkeys(key);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return set;
    }

    /**
     * 批量获取格式下的keys
     *
     * @param pattern 标识 xxx:xxx:*
     * @return 返回的keys
     * @version v1.0
     */
    public static Set<String> keys(int db, String pattern) {
        Set<String> set = null;
        Jedis jedis = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            set = jedis.keys(pattern);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
        return set;
    }

    /**
     * 查询redis 
     *
     * @param cursor 光标位置
     * @return 查询到的key
     */
    public static ScanResult<String> scan(int db, String cursor) {
        ScanResult<String> result = null;
        Jedis jedis = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            result = jedis.scan(cursor);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 查询redis
     *
     * @param cursor 光标
     * @param param 查询配对
     * @return 查询到的key
     * @version v1.0
     */
    public static ScanResult<String> scan(int db, String cursor,
            ScanParams param) {
        ScanResult<String> result = null;
        Jedis jedis = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            result = jedis.scan(cursor, param);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 查询redis
     *
     * @param cursor 光标
     * @param pattern 查询匹配的信息
     * @return 查询到的key
     * @version v1.0
     */
    public static ScanResult<String> scan(int db, String cursor,
            String pattern) {
        ScanResult<String> result = null;
        ScanParams param = new ScanParams();
        param.match(pattern);
        result = scan(db, cursor, param);
        return result;
    }

    /**
     * 查询redis
     *
     * @param pattern 查询匹配的信息
     * @return 查询到的key
     * @version v1.0
     */
    public static List<String> scanAll(int db, String pattern) {
        List<String> list = new LinkedList<String>();
        Integer cursor = 0;
        do {
            //System.out.println("cursor:" + cursor);
            ScanResult<String> result = null;
            ScanParams param = new ScanParams();
            param.match(pattern);
            result = scan(db, cursor.toString(), param);
            cursor = Integer.parseInt(result.getStringCursor());
            if (null != result.getResult() && !result.getResult().isEmpty()) {
                list.addAll(result.getResult());
            }
        } while (!cursor.equals(0));
        return list;
    }

    public static List<String> hvals(int db, String key) {
        List<String> list = null;
        Jedis jedis = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            list = jedis.hvals(key);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return list;
    }

    /**
     * 从hash表取数据
     * 
     * @param key hash表名
     * @return
     * @version:v1.0
     */
    public static Long hdel(int db, String key, String... fields) {
        Long value = null;
        Jedis jedis = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            value = jedis.hdel(key, fields);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return value;
    }

    public static Long hincrBy(int db, String key, String field, int value) {
        Long res = null;
        Jedis jedis = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            res = jedis.hincrBy(key, field, value);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
        return res;
    }

    /**
     * 
     * 
     * @param key
     * @param members
     * @return 添加成功返回0，要添加的对象已存在返回1
     * @version:v1.0
     */
    public static long sadd(int db, String key, String... members) {
        Jedis jedis = null;
        long statusCode = -1;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            statusCode = jedis.sadd(key, members);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return statusCode;
    }

    /**
     * 
     * 
     * @param key
     * @return
     * @version:v1.0
     * @author:张瑞
     * @date:2013-6-26 下午2:32:16
     */
    public static Set<String> smembers(int db, String key) {
        Jedis jedis = null;
        Set<String> members = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            members = jedis.smembers(key);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return members;
    }

    /**
     * 
     * 
     * @param key
     * @return 添加成功返回0，要添加的对象已存在返回1
     * @version:v1.0
     * @author:张瑞
     * @date:2013-6-26 下午2:26:01
     */
    public static long zadd(int db, String key, double score, String member) {
        Jedis jedis = null;
        long statusCode = -1;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            statusCode = jedis.zadd(key, score, member);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return statusCode;
    }

    /**
     * 取得在这些之间的数目
     *
     * @param key
     * @param min 默认包含，不包含则在之前添加(
     * @param max 默认包含，不包含则在之前添加(
     * @return
     * @version v3.1
     * @author 张瑞
     * @date 2016年4月8日 下午7:13:45
     */
    public static long zcount(int db, String key, String min, String max) {
        Jedis jedis = null;
        long statusCode = -1;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            statusCode = jedis.zcount(key, min, max);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return statusCode;
    }

    public static long zrem(int db, String key, String... member) {
        Jedis jedis = null;
        long statusCode = -1;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            statusCode = jedis.zrem(key, member);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return statusCode;
    }

    /**
     * 
     * 
     * @param key
     * @return
     * @version:v1.0
     * @author:张瑞
     * @date:2013-6-26 下午2:32:16
     */
    public static Set<String> zrevrangeByScore(int db, final String key,
            final String max, final String min, final int offset,
            final int count) {
        Jedis jedis = null;
        Set<String> members = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            members = jedis.zrevrangeByScore(key, max, min, offset, count);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return members;
    }

    public static Set<String> zrangeByScore(int db, final String key,
            final long min, final long max) {
        Jedis jedis = null;
        Set<String> members = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            members = jedis.zrangeByScore(key, min, max);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return members;
    }

    public static long zremrangeByScore(int db, final String key,
            final long start, final long end) {
        Jedis jedis = null;
        long value = 0l;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            value = jedis.zremrangeByScore(key, start, end);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 在list头部添加
     * 
     * @param key
     * @param members
     * @return 元素添加完成后list的长度
     * @version:v1.0
     * @author:张瑞
     * @date:2013-6-26 下午2:43:02
     */
    public static long lpush(int db, String key, String... members) {
        Jedis jedis = null;
        long length = -1;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            length = jedis.lpush(key, members);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return length;
    }

    /**
     * 在list尾部取值并删除最后一条
     * 
     * @param key
     * @return 元素添加完成后list的长度
     * @version:v1.0
     */
    public static String rpop(int db, String key) {
        Jedis jedis = null;
        String str = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            str = jedis.rpop(key);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return str;
    }

    /**
     * 删除指定的元素
     *
     * @param key
     * @param value
     * @return
     * @version v1.0
     */
    public static long lrem(int db, String key, String value) {
        Jedis jedis = null;
        long length = -1;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            length = jedis.lrem(key, 0L, value);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return length;
    }

    /**
     * 获取list长度
     *
     * @param key
     * @return
     * @version v1.0
     */
    public static long llen(int db, String key) {
        Jedis jedis = null;
        long length = -1;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            length = jedis.llen(key);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
        return length;
    }

    /**
     * 查询列表的元素
     *
     * @param key
     * @param start
     * @param end
     * @return
     * @version v1.0
     */
    public static List<String> lrange(int db, String key, Long start,
            Long end) {
        Jedis jedis = null;
        List<String> list = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            start = null == start ? 0 : start;
            end = null == end ? -1 : end;
            list = jedis.lrange(key, start, end);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
        return list;
    }

    /**
     * 在list尾部添加
     * 
     * @param key
     * @param members
     * @return 元素添加完成后list的长度
     * @version:v1.0
     */
    public static long rpush(int db, String key, String... members) {
        Jedis jedis = null;
        long length = -1;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            length = jedis.rpush(key, members);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return length;
    }

    /**
     * 是否存在所给的key值
     * 
     * @param key
     * @return 验证结果
     * @version:v1.0
     */
    public static boolean exists(int db, String key) {
        boolean value = false;
        Jedis jedis = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            value = jedis.exists(key);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return value;
    }

    /**
     * 删除所给Keys,如果所给的keys中有不存在的key，则不会对那些不存在的key执行任何操作
     * 
     * @param keys
     * @return 删除key的个数
     * @version:v1.0
     */
    public static long delete(int db, String... keys) {
        long value = 0;
        Jedis jedis = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            value = jedis.del(keys);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return value;
    }

    /**
     * key所对应的存储类型
     * 
     * @param key
     * @return"none"/"string"/"list"/"set"/"zset"/"hash"
     * @version:v1.0
     */
    public static String type(int db, String key) {
        String type = null;
        Jedis jedis = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            type = jedis.type(key);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return type;
    }

    /**
     * 清空数据库
     *
     * @param db 数据库号
     * @return 是否成功
     * @version v1.0
     */
    public static String flushDB(int db) {
        Jedis jedis = null;
        String statusCode = null;
        try {
            jedis = getResourceByDB(db);
            statusCode = jedis.flushDB();
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return statusCode;
    }

    /**
     * 清空全部数据库
     *
     * @version v1.0
     * @date 2018年1月5日 下午12:01:28
     */
    public static void flushDBAll() {
        Jedis jedis = null;
        try {
            jedis = getResource();
            for (int i = 0; i < 16; i++) {
                jedis.select(i);
                jedis.flushDB();
            }
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
    }

    /**
     * 查询key过期时间
     * @param key
     * @return
     * @Author:leining
     * @date:17:02 2018/8/20
     */
    public static Long pttl(Integer db,String key){
        Jedis jedis = null;
        Long time = 0L;
        try {
            jedis = jedis = getResourceByDB(db);;
            time = jedis.pttl(key);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
            return time;
        }
    }

    public static Long setrang(Integer db,String key,String value){
        Jedis jedis = null;
        Long statusCode = null;
        try {
            //jedis = getResource();
            jedis = getResourceByDB(db);
            statusCode = jedis.setrange(key,0L,value);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return statusCode;
    }

    public static void main(String[] args) {
        String b = JedisAPI.select(1);
        System.out.println(b);
        /*JedisAPI.set("test", "1");
        String a = JedisAPI.get("test");
        System.out.println(a);
        /*String c = JedisAPI.get("test");
        System.out.println(c);*/
        /*JedisAPI.select(2);
        String a = JedisAPI.get("test");
        if (a == null) {
            System.out.println("hhaha");
        } else {
            System.out.println(a);
        }*/
        /*String a = "1";
        String b = "2";
        JedisAPI.set("test:1:a", a);
        JedisAPI.set("test:1:b", b);
        Set<String> testList = JedisAPI.keys("test:1*");
        System.out.println("first");
        if (null != testList && !testList.isEmpty()) {
            for (String test : testList) {
                System.out.println(test);
            }
        } else {
            System.out.println("nothing");
        }
        JedisAPI.delete("test:1:b");
        System.out.println("second");
        testList = JedisAPI.keys("test:1*");
        if (null != testList && !testList.isEmpty()) {
            for (String test : testList) {
                System.out.println(test);
            }
        } else {
            System.out.println("nothing");
        }*/
        /*ScanResult<String> c = JedisAPI.scan("0");//0 match test:1:*
        System.out.println(c.getStringCursor());
        List<String> list = c.getResult();
        if (null != list && !list.isEmpty()) {
            for (String result : list) {
                System.out.println(result);
            }
        }
        ScanParams param = new ScanParams();
        param.match("test:1:*");
        c = JedisAPI.scan("0", param);
        System.out.println(c.getStringCursor());
        list = c.getResult();
        if (null != list && !list.isEmpty()) {
            for (String result : list) {
                System.out.println(result);
            }
        }*/

        //scanAll
        /*Long a = JedisAPI.setnx("test:1:c", "1");
        System.out.println(a);
        Long b = JedisAPI.setnx("test:1:c", "1");
        System.out.println(b);*/
        //SETNX
        /*for (int i = 0; i < 5000; i++) {
            JedisAPI.set("test:test1:" + i, "1");
            JedisAPI.set("test:test2:" + i, "1");
        }
        List<String> list = JedisAPI.scanAll("test:test1:*");
        System.out.println(list.size());*/
        //加法测试
        /*JedisAPI.set("test1", "0");
        Long a = JedisAPI.incr("test1");
        Long b = JedisAPI.incr("test1");
        Long c = JedisAPI.incr("test1");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        List<String> list = JedisAPI.scanAll("test*");
        System.out.println(list.get(0));*/
        /*JedisAPI.set("test1", "测试");
        System.out.println(JedisAPI.get("test1"));*/
        /*JedisAPI.lpush("test", "test1");
        JedisAPI.lpush("test", "test2");
        JedisAPI.lpush("test", "test3");
        JedisAPI.lpush("test", "test3");
        JedisAPI.lpush("test", "test5");
        System.out.println(JedisAPI.lrem("test", "test3"));
        List<String> list = JedisAPI.lrange("test", null, null);
        for (String str : list) {
            System.out.println(str);
        }
        System.out.println(JedisAPI.llen("test"));*/
    }
}
