package com.isheng.model.auth.request;

import com.isheng.common.base.BaseQuery;

public class RoleQuery extends BaseQuery {

	/**  */
	private static final long serialVersionUID = 3536055157149987735L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
