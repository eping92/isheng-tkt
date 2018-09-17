package com.isheng.web.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import com.isheng.common.base.BaseController;
import com.isheng.common.constant.SysConfig;
import com.isheng.common.exception.BizException;
import com.isheng.common.model.ResultModel;
import com.isheng.model.auth.domain.SessionUser;
import com.isheng.model.auth.entity.Menu;
import com.isheng.model.auth.enums.MenuType;
import com.isheng.service.auth.MenuService;

@Controller
public abstract class AbstractBaseController extends BaseController {
	
	/**
	 * 返回对象
	 * @return
	 */
	protected ResultModel getModel() {
		return new ResultModel();
	}
	
	/**
	 * 数据验证
	 */
	protected ResultModel dataValid(Object object) {
		return null;
	}
	
	/**
	 * 获取当前登录的user
	 * 
	 * @return
	 */
	protected SessionUser getCurrentUser() {
		try {
			return getSessionAttr(SysConfig.SESSION_USER_KEY, SessionUser.class);
		} catch (Exception e) {
			throw new BizException(e);
		}
	}
	
	/**
	 * 初始化菜单
	 */
	@SuppressWarnings("unchecked")
	@Async
	protected void initMenu(MenuService menuService) {
		HttpSession session = getSession();
		if (null == session) {
			return;
		}
		
		List<Menu> roots = null;//一级菜单
		List<Menu> menus = null;//二级菜单
		List<Menu> buttons = null;//三级按钮
		List<Menu> userMenus = null;//用户的所有权限
		
		roots = (List<Menu>) getSessionAttr(SysConfig.MENU_ROOT_KEY);
		if (null != roots && !roots.isEmpty()) {
			return;
		}
		
		List<Menu> all = menuService.getAll();
		if (null != all && !all.isEmpty()) {
			roots = new ArrayList<>();
			menus = new ArrayList<>();
			buttons = new ArrayList<>();
			for (Menu m : all) {
				if (MenuType.ROOT == m.getMenuType()) {
					roots.add(m);
					continue;
				} else if (MenuType.MENU == m.getMenuType()) {
					menus.add(m);
					continue;
				} else if (MenuType.BUTTON == m.getMenuType()) {
					buttons.add(m);
				}
			}
			
			setSessionAttr(SysConfig.MENU_ROOT_KEY, roots);
			setSessionAttr(SysConfig.MENU_MENU_KEY, roots);
			setSessionAttr(SysConfig.MENU_BUTTON_KEY, roots);
		}
		
		userMenus = (List<Menu>) getSessionAttr(SysConfig.MENU_USER_KEY);
		if (null == userMenus || userMenus.isEmpty()) {
			String userId = "";
			SessionUser user = getCurrentUser();
			if (null != user) {
				userId = user.getUserId();
			}
			userMenus = menuService.getListByUserId(userId);
			setSessionAttr(SysConfig.MENU_USER_KEY, userMenus);
		}
	}
}
