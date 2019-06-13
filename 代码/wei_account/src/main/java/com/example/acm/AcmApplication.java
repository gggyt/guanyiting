package com.example.acm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.acm.mapper")

public class AcmApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcmApplication.class, args);
	}
}
