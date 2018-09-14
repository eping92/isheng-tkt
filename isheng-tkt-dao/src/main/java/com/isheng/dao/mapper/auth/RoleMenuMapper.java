package com.isheng.dao.mapper.auth;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.isheng.common.base.BaseMapper;
import com.isheng.common.exception.BizException;
import com.isheng.model.auth.entity.RoleMenu;
import com.isheng.model.auth.request.RoleMenuQuery;
/**
 * 角色权限
 * @author Administrator
 *
 */
@Mapper
@Repository
public interface RoleMenuMapper extends BaseMapper<RoleMenuMapper, RoleMenuQuery> {
	
	/**
	 * 批量插入
	 * @param roleId
	 * @param menuIds
	 * @throws BizException
	 */
	void batchInsert(@Param("roleId")String roleId, @Param("menuIds")List<String> menuIds) throws BizException;
	
	/**
	 * 根据角色id获取所有角色和权限对应关系
	 * @param roleId
	 * @return
	 * @throws BizException
	 */
	List<RoleMenu> selectRoleMenus(@Param("roleId")String roleId) throws BizException;

}
