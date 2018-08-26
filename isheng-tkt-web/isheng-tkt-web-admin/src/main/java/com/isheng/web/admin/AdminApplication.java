package com.isheng.web.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@SpringBootApplication(scanBasePackages= {"com.isheng.web.admin"})
public class AdminApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}

}