package com.example.acm;

import com.example.acm.conf.RedisComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AcmApplicationTests {
	@Autowired
	RedisComponent redisComponent;
	@Test
	public void contextLoads() {
		redisComponent.set("xx", "1", 10);
		System.out.println(redisComponent.get("xx"));
	}

}
