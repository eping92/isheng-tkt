package com.isheng.service.auth;

import java.util.List;

import com.isheng.common.base.BaseQuery;
import com.isheng.common.base.BaseService;
import com.isheng.model.auth.entity.UserRole;

public interface UserRoleService extends BaseService<UserRole, BaseQuery> {
	
	public void batchAdd(String userId, List<String> roleIds);

}
