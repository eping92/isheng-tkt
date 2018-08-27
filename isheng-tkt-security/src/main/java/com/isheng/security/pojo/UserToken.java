package com.isheng.security.pojo;

import org.apache.shiro.authc.AuthenticationToken;

import com.isheng.model.auth.entity.User;

public class UserToken implements AuthenticationToken {

	private static final long serialVersionUID = -9215502717079108223L;
	
	private User user;
	
	private String pwd;
	
	public UserToken() {};
	
	public UserToken(User user, String pwd) {
		this.user = user;
		this.pwd = pwd;
	}

	@Override
	public Object getPrincipal() {
		return this.user.getLoginName();
	}

	@Override
	public Object getCredentials() {
		return this.pwd;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	

}
