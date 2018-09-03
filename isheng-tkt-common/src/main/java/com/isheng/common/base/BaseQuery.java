package com.isheng.common.base;

import java.io.Serializable;

/**
 * 基础查询条件
 * 
 * @author Administrator
 *
 */
public class BaseQuery implements Serializable {

	private static final long serialVersionUID = 6948265010419452938L;

	private long id;

	private String beginDate;

	private String endDate;

	private String createUser;

	private String updateUser;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

}
