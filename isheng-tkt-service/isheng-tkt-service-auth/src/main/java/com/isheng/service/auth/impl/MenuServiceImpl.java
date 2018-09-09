package com.isheng.service.auth.impl;

import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.isheng.common.base.AbstractBaseService;
import com.isheng.common.base.BaseDao;
import com.isheng.common.enums.ErrMsg;
import com.isheng.common.exception.BizException;
import com.isheng.common.util.ObjUtil;
import com.isheng.dao.service.auth.MenuDao;
import com.isheng.model.auth.entity.Menu;
import com.isheng.model.auth.enums.MenuType;
import com.isheng.model.auth.request.MenuQuery;
import com.isheng.service.auth.MenuService;

@Component("menuService")
@Service(interfaceClass = MenuService.class)
public class MenuServiceImpl extends AbstractBaseService<Menu, MenuQuery> implements MenuService {

	@Reference
	private MenuDao menuDao;
	
	@Override
	protected BaseDao<Menu, MenuQuery> getDao() {
		return menuDao;
	}
	
	@Override
	public String add(Menu entity) throws BizException {
		if (ObjUtil.isNotNull(entity.getUrl())) {
			boolean isExist = super.isExist(entity.getId(), "url", entity.getUrl());
			if (isExist) {
				throw new BizException(ErrMsg.PARAM_ERR.getCode(), "权限地址已存在");
			}
		}
		if (entity.getSort() <= 0) {
			entity.setSort(this.getNextSort(entity.getParentId(), entity.getMenuType()));
		}
		if (ObjUtil.isNotNull(entity.getUrl()) && !"#".equals(entity.getUrl())) {
			String code = entity.getUrl().replaceFirst("/", "").replace("/", ":");//如：/menu/add改成menu:add
			entity.setCode(code);
		} else {
			entity.setCode("#");
		}
		return super.add(entity);
	}

	@Override
	public long getNextSort(String parentId, MenuType menuType) throws BizException {
		MenuQuery query = new MenuQuery();
		query.setParentId(parentId);
		query.setMenuType(menuType);
		return menuDao.countByParam(query) + 1;
	}

}
