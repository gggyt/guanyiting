package com.example.acm.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * redis配置类
 *
 * @author zcc ON 2018/3/19
 **/
@Configuration
@EnableCaching//开启注解
public class RedisComponent {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private Logger logger= LoggerFactory.getLogger(RedisComponent.class);
    //RedisTemplate可以进行所有的操作
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;
    public void set(String key,String value){
        ValueOperations<String,String> ops=this.stringRedisTemplate.opsForValue();
        boolean bExistent=this.stringRedisTemplate.hasKey(key);
        if(bExistent){
            logger.info("this key is bExistent!");
        }else{
            ops.set(key,value, 3000);
        }
    }
    public void set(String key, String value, int second) {
        ValueOperations<String,String> ops=this.stringRedisTemplate.opsForValue();
        boolean bExistent=this.stringRedisTemplate.hasKey(key);
        if(bExistent){
            logger.info("this key is bExistent!");
        }else{
            ops.set(key,value, second, TimeUnit.SECONDS);
        }
    }
    public String get(String key){
        return this.stringRedisTemplate.opsForValue().get(key);
    }
    public void del(String key){
        this.stringRedisTemplate.delete(key);
    }

    public void sentinelSet(String key,Object object){
        redisTemplate.opsForValue().set(key,object);

        redisTemplate.opsForValue().set(key,object, 100, TimeUnit.HOURS);
    }
    public String sentinelGet(String key){
        return String.valueOf(redisTemplate.opsForValue().get(key));
    }
    //----------------

}