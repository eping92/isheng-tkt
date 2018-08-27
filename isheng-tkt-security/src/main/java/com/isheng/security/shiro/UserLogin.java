package com.isheng.security.shiro;

import java.io.Serializable;

/**
 * 用户登陆封装
 * @author Administrator
 *
 */
public class UserLogin implements Serializable {

	private static final long serialVersionUID = -6126815196050557974L;
	
	/**
	 * 登录名
	 */
	private String loginName;
	
	/**
	 * 密码
	 */
	private String pwd;
	
	/**
	 * 验证码
	 */
	private String verifyCode;
	
	/**
	 * 登陆后跳转地址
	 */
	private String gto;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getGto() {
		return gto;
	}

	public void setGto(String gto) {
		this.gto = gto;
	}

	@Override
	public String toString() {
		return "UserLogin [loginName=" + loginName + ", pwd=" + pwd + ", verifyCode=" + verifyCode + ", gto=" + gto
				+ "]";
	}
	
	

}
