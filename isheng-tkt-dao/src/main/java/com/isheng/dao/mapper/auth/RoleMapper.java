package com.isheng.dao.mapper.auth;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.isheng.common.base.BaseMapper;
import com.isheng.common.exception.BizException;
import com.isheng.model.auth.entity.Role;
import com.isheng.model.auth.request.RoleQuery;

@Mapper
@Repository
public interface RoleMapper extends BaseMapper<Role, RoleQuery>{

	/**
	 * 得到用户的所有角色信息
	 * 
	 * @param userId
	 * @return
	 * @throws BizException
	 */
	List<Role> selectByUserId(String userId) throws BizException;
	

}
