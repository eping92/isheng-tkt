package com.isheng.web.cms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 解决跨域问题
 *
 *
 * @author Administrator
 * @version $Id: CorsConfig.java 2018年9月25日 下午9:31:00 $
 */
@Configuration
public class CorsConfig extends WebMvcConfigurationSupport{
	
	/**
	 * 重写该方法可以解决无法加载到静态资源的问题
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
	 */
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

	/** 
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry)
	 */
	@Override
	protected void addCorsMappings(CorsRegistry registry) {
		System.out.println("----------------------");
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
	}
	
	

}
