/**
 * Copyright (c) 2011-2016 All Rights Reserved.
 */
package com.isheng.web.cms.security;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.Filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;


/**
 * 
 *
 *
 * @author Administrator
 * @version $Id: WebSecurityConfig.java 2018年9月9日 下午11:19:34 $
 */
@Configuration
public class WebSecurityConfig {
	
	@Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
	
	@Bean(name = "shiroRealm")
	@DependsOn("lifecycleBeanPostProcessor")
    public DefaultUserRealm shiroRealm() {
		DefaultUserRealm realm = new DefaultUserRealm(); 
        return realm;
    }
	
	@Bean(name = "ehCacheManager")
	@DependsOn("lifecycleBeanPostProcessor")
	public EhCacheManager ehCacheManager(){
		EhCacheManager ehCacheManager = new EhCacheManager();
		return ehCacheManager;
	}
	
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager securityManager(){
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(shiroRealm());
		securityManager.setCacheManager(ehCacheManager());
		return securityManager;
	}

	@Order(1)
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(){
		WebSecurityManager securityManager = securityManager();
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		SecurityUtils.setSecurityManager(securityManager);
		
		Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
		LogoutFilter logoutFilter = new LogoutFilter();
		logoutFilter.setRedirectUrl("/");
		filters.put("logout", logoutFilter);
		
		AuthenticationWithLockFilter formFilter = new AuthenticationWithLockFilter();
		formFilter.setMaxLoginAttempts(3);
		filters.put("authc", formFilter);
		shiroFilterFactoryBean.setFilters(filters);
//		formFilter.setValueoptService(valueoptService);
		
		Map<String, String> filterChainDefinitionManager = new LinkedHashMap<String, String>();
		filterChainDefinitionManager.put("/favicon.ico", "anon");
		filterChainDefinitionManager.put("/static/**", "anon");
		filterChainDefinitionManager.put("/", "anon");
		filterChainDefinitionManager.put("/login", "anon");
		filterChainDefinitionManager.put("/logout", "anon");
		
		filterChainDefinitionManager.put("/**", "anon");
		
//		filterChainDefinitionManager.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);
		
		shiroFilterFactoryBean.setLoginUrl("/");
		shiroFilterFactoryBean.setSuccessUrl("/");
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		
		return shiroFilterFactoryBean;
	}

	@Bean
	@ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxy = new DefaultAdvisorAutoProxyCreator();
        proxy.setProxyTargetClass(true);
        return proxy;
    }
	
	@Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor sourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        sourceAdvisor.setSecurityManager(securityManager());
        return sourceAdvisor;
    }
}
