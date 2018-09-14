package com.isheng.dao.mapper.auth;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.isheng.common.base.BaseMapper;
import com.isheng.common.exception.BizException;
import com.isheng.model.auth.entity.UserRole;
import com.isheng.model.auth.request.UserRoleQuery;

/**
 * 用户角色
 * @author Administrator
 *
 */
@Mapper
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole, UserRoleQuery> {
	
	/**
	 * 批量添加
	 * @param userId
	 * @param roleIds
	 */
	void batchInsert(@Param("userId")String userId, @Param("roleIds")List<String> roleIds) throws BizException;
	
	/**
	 * 根据用户ID查询
	 * @param userId
	 * @return
	 */
	List<UserRole> selectUserRoles(@Param("userId")String userId) throws BizException;

}
