package com.isheng.dao.mapper.auth;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.isheng.common.base.BaseMapper;
import com.isheng.common.exception.BizException;
import com.isheng.model.auth.entity.Menu;
import com.isheng.model.auth.entity.Role;
import com.isheng.model.auth.request.MenuQuery;
/**
 * 权限
 *
 *
 * @author Administrator
 * @version $Id: MenuDao.java 2018年7月28日 上午11:51:50 $
 */
@Mapper
@Repository
public interface MenuMapper extends BaseMapper<Menu, MenuQuery> {
	
	/**
	 * 查询用户的所有权限
	 * 
	 * @param userId
	 * @return
	 * @throws BizException
	 */
	List<Menu> selectByUserId(String userId) throws BizException;

	/**
	 * 查询角色的所有权限
	 * 
	 * @param roleId
	 * @return
	 * @throws BizException
	 */
	List<Menu> selectByRoleId(String roleId) throws BizException;

	/**
	 * 查询多个角色的多个权限
	 * 
	 * @param roles
	 * @return
	 * @throws BizException
	 */
	List<Menu> selectByRoles(@Param("roles")List<Role> roles) throws BizException;
	
}
