package com.isheng.dao.service.auth;

import java.util.List;
import com.isheng.common.base.BaseDao;
import com.isheng.common.exception.BizException;
import com.isheng.model.auth.entity.UserRole;
import com.isheng.model.auth.request.UserRoleQuery;

/**
 * 用户和角色对应关系
 *
 *
 * @author Administrator
 * @version $Id: UserRoleDao.java 2018年9月14日 下午10:38:30 $
 */
public interface UserRoleDao extends BaseDao<UserRole, UserRoleQuery> {
	
	/**
	 * 批量添加
	 * @param userId
	 * @param roleIds
	 */
	void batchSave(String userId, List<String> roleIds) throws BizException;
	
	/**
	 * 根据用户ID查询
	 * @param userId
	 * @return
	 */
	List<UserRole> listByUserId(String userId) throws BizException;
	
	/**
	 * 是否重复添加用户和角色关系
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 * @throws BizException
	 */
	boolean isRepet(String userId, String roleId) throws BizException;

	

}
