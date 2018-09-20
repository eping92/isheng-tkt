package com.isheng.web.cms;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

@EnableAsync
@EnableScheduling
@EnableDubboConfiguration
@SpringBootApplication(scanBasePackages= {"com.isheng.web.cms"})
public class CmsApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CmsApplication.class, args);
	}
	
	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(8);  
        executor.setMaxPoolSize(20);  
        executor.setQueueCapacity(1000);  
        executor.setKeepAliveSeconds(3000);  
        executor.setThreadNamePrefix("isheng-tkt Executor-");  
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务  
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行  
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());  
        executor.initialize(); 
        return executor;  
	}
	
	

}