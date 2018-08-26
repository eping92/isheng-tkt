package com.isheng.dao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

@EnableAsync
@EnableScheduling
@EnableDubboConfiguration
@MapperScan(basePackages= {"classpath*:com/isheng/dao/**/*Mapper.xml"})
@SpringBootApplication(scanBasePackages= {"com.isheng.dao"})
public class DaoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DaoApplication.class, args);
	}

}
