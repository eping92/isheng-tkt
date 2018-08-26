package com.isheng.model.auth.request;

import com.isheng.model.auth.enums.UserStatus;
import com.isheng.model.common.enums.Gender;
import com.isheng.model.common.request.BaseQuery;

/**
 * user query condition
 *
 *
 * @author Administrator
 * @version $Id: UserQuery.java 2018年8月18日 下午10:47:47 $
 */
public class UserQuery extends BaseQuery {

	/**  */
	private static final long serialVersionUID = 5409207893908956731L;
	
	private String loginName;

	private String nick;

	private String realName;

	private String mobile;

	private Gender gender;

	private String companyName;

	private String deptName;

	private UserStatus userStatus;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	@Override
	public String toString() {
		return "UserQuery [loginName=" + loginName + ", nick=" + nick + ", realName=" + realName + ", mobile=" + mobile
				+ ", gender=" + gender + ", companyName=" + companyName + ", deptName=" + deptName + ", userStatus="
				+ userStatus + "]";
	}
	
}
