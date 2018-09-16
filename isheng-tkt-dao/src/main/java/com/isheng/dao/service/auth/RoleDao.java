package com.isheng.dao.service.auth;

import java.util.List;
import com.isheng.common.base.BaseDao;
import com.isheng.common.exception.BizException;
import com.isheng.model.auth.entity.Role;
import com.isheng.model.auth.request.RoleQuery;

/**
 * 角色dao
 *
 *
 * @author Administrator
 * @version $Id: RoleDao.java 2018年9月1日 下午7:08:08 $
 */
public interface RoleDao  extends BaseDao<Role, RoleQuery> {
	
	/**
	 * 得到用户的所有角色信息
	 * 
	 * @param userId
	 * @return
	 * @throws BizException
	 */
	List<Role> listByUserId(String userId) throws BizException;

}
