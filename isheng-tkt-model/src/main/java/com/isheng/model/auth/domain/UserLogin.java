package com.isheng.model.auth.domain;

import java.io.Serializable;

/**
 * 登录参数类
 *
 *
 * @author Administrator
 * @version $Id: LoginRequest.java 2018年9月9日 上午12:32:46 $
 */
public class UserLogin implements Serializable {

	/**  */
	private static final long serialVersionUID = 3641380076667115737L;
	
	/** 登录名 */
	private String loginName;
	
	/** 手机号 */
	private String mobile;
	
	/** 登录密码 */
	private String pwd;
	
	/**  校验码 */
	private String checkCode;
	
	/** 登陆后要跳转的地址 */
	private String gto;

	/**
	 * Getter method for property <tt>loginName</tt>.
	 * 
	 * @return property value of loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * Setter method for property <tt>loginName</tt>.
	 * 
	 * @param loginName value to be assigned to property loginName
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * Getter method for property <tt>mobile</tt>.
	 * 
	 * @return property value of mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * Setter method for property <tt>mobile</tt>.
	 * 
	 * @param mobile value to be assigned to property mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * Getter method for property <tt>pwd</tt>.
	 * 
	 * @return property value of pwd
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * Setter method for property <tt>pwd</tt>.
	 * 
	 * @param pwd value to be assigned to property pwd
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * Getter method for property <tt>checkCode</tt>.
	 * 
	 * @return property value of checkCode
	 */
	public String getCheckCode() {
		return checkCode;
	}

	/**
	 * Setter method for property <tt>checkCode</tt>.
	 * 
	 * @param checkCode value to be assigned to property checkCode
	 */
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	/**
	 * Getter method for property <tt>gto</tt>.
	 * 
	 * @return property value of gto
	 */
	public String getGto() {
		return gto;
	}

	/**
	 * Setter method for property <tt>gto</tt>.
	 * 
	 * @param gto value to be assigned to property gto
	 */
	public void setGto(String gto) {
		this.gto = gto;
	}

	/** 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Login [loginName=" + loginName + ", mobile=" + mobile + ", pwd=" + pwd + ", checkCode=" + checkCode
				+ ", gto=" + gto + "]";
	}
}
