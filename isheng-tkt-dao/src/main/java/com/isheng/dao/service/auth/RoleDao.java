package com.isheng.dao.service.auth;

import java.util.List;

import com.isheng.model.auth.entity.Role;
import com.isheng.model.auth.request.RoleQuery;

public interface RoleDao {
	
	int save(Role entity);
	
	int deleteById(long id);
	
	int update(Role entity);
	
	Role getById(long id);
	
	Role getOne(RoleQuery query);
	
	long countByQuery(RoleQuery query);
	
	List<Role> listByQuery(RoleQuery query);
	
	List<Role> paging(RoleQuery query, int pageNo, int pageSize);
	
	boolean isRepeat(long id, String name);

}
