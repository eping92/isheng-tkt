package com.isheng.service.auth;

import com.isheng.common.base.BaseService;
import com.isheng.common.exception.BizException;
import com.isheng.model.auth.entity.Menu;
import com.isheng.model.auth.enums.MenuType;
import com.isheng.model.auth.request.MenuQuery;

/**
 * 菜单管理
 *
 *
 * @author Administrator
 * @version $Id: MenuService.java 2018年9月8日 上午9:04:38 $
 */
public interface MenuService extends BaseService<Menu, MenuQuery>{

	public long getNextSort(String parentId, MenuType menuType) throws BizException;

}
