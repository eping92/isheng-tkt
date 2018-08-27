package com.isheng.security.shiro;

import java.io.Serializable;

public interface BaseUser extends Serializable {

	/**
	 * 获取会员ID
	 * @return
	 */
	public Long getUserId();
	
	/**
	 * 获取登录名
	 * @return
	 */
	public String getLoginName();
	
	/**
	 * 获取昵称
	 * @return
	 */
	public String getNick();
	
	/**
	 * 获取真实姓名
	 * @return
	 */
	public String getRealName();
	
	/**
	 * 获取图像
	 * @return
	 */
	public String getHeadPath();
	
	/**
	 * 获取手机号
	 * @return
	 */
	public String getMobile();

}
