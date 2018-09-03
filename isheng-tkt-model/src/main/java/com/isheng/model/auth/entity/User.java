package com.isheng.model.auth.entity;

import java.util.List;

import com.isheng.common.base.BaseEntity;
import com.isheng.common.enums.Gender;
import com.isheng.model.auth.enums.UserStatus;

/**
 * 后台管理系统用户
 * 
 * @author Administrator
 *
 */
public class User extends BaseEntity {

	private static final long serialVersionUID = -5155241888377506557L;

	private String loginName;

	private String nick;

	private String realName;

	private String headPath;

	private String pwd;

	private String mobile;

	private Gender gender;

	private String companyId;

	private String companyName;

	private String deptId;

	private String deptName;

	private UserStatus userStatus;

	private List<Role> roleList;

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

	public String getHeadPath() {
		return headPath;
	}

	public void setHeadPath(String headPath) {
		this.headPath = headPath;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
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

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
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

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	@Override
	public String toString() {
		return "User [loginName=" + loginName + ", nick=" + nick + ", realName=" + realName + ", headPath=" + headPath
				+ ", pwd=" + pwd + ", mobile=" + mobile + ", gender=" + gender + ", companyId=" + companyId
				+ ", companyName=" + companyName + ", deptId=" + deptId + ", deptName=" + deptName + ", userStatus="
				+ userStatus + ", roleList=" + roleList + "]";
	}

}
