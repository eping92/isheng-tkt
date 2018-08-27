package com.isheng.security.shiro;

public class SessionUser extends AbstractUser {
	
	private static final long serialVersionUID = 5399697941852084354L;
	
	private Long userId;
	
	private String loginName;
	
	private String nick;
	
	private String realName;
	
	private String headPath;
	
	private String mobile;

	@Override
	public Long getUserId() {
		return userId;
	}

	@Override
	public String getLoginName() {
		return loginName;
	}

	@Override
	public String getNick() {
		return nick;
	}

	@Override
	public String getRealName() {
		return realName;
	}

	@Override
	public String getHeadPath() {
		return headPath;
	}

	@Override
	public String getMobile() {
		return mobile;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public void setHeadPath(String headPath) {
		this.headPath = headPath;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	

}
