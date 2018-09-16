package com.isheng.service.auth.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.isheng.common.base.AbstractBaseService;
import com.isheng.common.base.BaseDao;
import com.isheng.common.enums.ErrMsg;
import com.isheng.common.exception.BizException;
import com.isheng.common.util.ObjUtil;
import com.isheng.dao.service.auth.RoleMenuDao;
import com.isheng.model.auth.entity.RoleMenu;
import com.isheng.model.auth.request.RoleMenuQuery;
import com.isheng.service.auth.RoleMenuService;

@Component("roleMenuService")
@Service(interfaceClass = RoleMenuService.class)
public class RoleMenuServiceImpl extends AbstractBaseService<RoleMenu, RoleMenuQuery> implements RoleMenuService {

	@Reference
	private RoleMenuDao roleMenuDao;
	
	@Override
	protected BaseDao<RoleMenu, RoleMenuQuery> getDao() {
		return roleMenuDao;
	}
	
	@Override
	public String add(RoleMenu entity) throws BizException {
		this.dataValid(entity);
		if (roleMenuDao.isRepet(entity.getRoleId(), entity.getMenuId())) {
			throw new BizException(ErrMsg.PARAM_REPET.getCode(), "角色关系已存在");
		}
		try {
			return roleMenuDao.save(entity);
		} catch (Exception e) {
			logger.error("角色权限关系添加异常", e);
			throw new BizException(ErrMsg.EXP_ADD, e);
		}
	}

	@Override
	public int update(RoleMenu entity) throws BizException {
		this.dataValid(entity);
		try {
			return roleMenuDao.update(entity);
		} catch (Exception e) {
			logger.error("角色权限关系更新异常", e);
			throw new BizException(ErrMsg.EXP_UP, e);
		}
	}

	@Override
	public void batchAdd(String roleId, List<String> menuIds) throws BizException {
		if (ObjUtil.isNull(roleId)) {
			throw new BizException(ErrMsg.PARAM_MISS.getCode(), "角色ID不能为空");
		}
		if (null == menuIds || menuIds.isEmpty()) {
			throw new BizException(ErrMsg.PARAM_MISS.getCode(), "请至少选择一个权限信息");
		}
		try {
			roleMenuDao.batchSave(roleId, menuIds);
		} catch (Exception e) {
			logger.error("批量添加角色权限关系异常", e);
			throw new BizException(ErrMsg.EXP_ADD, e);
		}
		
	}

	@Override
	public List<RoleMenu> getListByRoleId(String roleId) throws BizException {
		if (ObjUtil.isNull(roleId)) {
			throw new BizException(ErrMsg.PARAM_MISS.getCode(), "角色ID不能为空");
		}
		try {
			return roleMenuDao.listByRoleId(roleId);
		} catch (Exception e) {
			logger.error("查询角色权限关系异常", e);
			throw new BizException(ErrMsg.EXP_QUERY, e);
		}
	}

	@Override
	protected void dataValid(RoleMenu t) throws BizException {
		if (null == t) {
			throw new BizException(ErrMsg.PARAM_NULL);
		}
		if (ObjUtil.isNull(t.getRoleId())) {
			throw new BizException(ErrMsg.PARAM_MISS.getCode(), "角色ID不能为空");
		}
		if (ObjUtil.isNull(t.getMenuId())) {
			throw new BizException(ErrMsg.PARAM_MISS.getCode(), "权限ID不能为空");
		}
	}


}
