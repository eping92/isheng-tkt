package com.isheng.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

@EnableAsync
@EnableScheduling
@EnableDubboConfiguration
@SpringBootApplication(scanBasePackages= {"com.isheng.service.auth"})
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}
}
