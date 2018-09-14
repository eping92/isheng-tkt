package com.isheng.web.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.isheng.common.constant.SysConfig;
import com.isheng.common.util.WebUtil;
import com.isheng.model.auth.entity.Menu;
import com.isheng.model.auth.enums.MenuType;
import com.isheng.service.auth.MenuService;
import com.isheng.web.admin.common.SessionHandler;

@SuppressWarnings("unchecked")
@Controller
public abstract class AbstractBaseController {
	
	@Reference
	private MenuService menuService;
	
	@Async
	public void initMenu() {
		HttpSession session = WebUtil.getSession();
		if (null == session) {
			return;
		}
		
		List<Menu> roots = null;//一级菜单
		List<Menu> menus = null;//二级菜单
		List<Menu> buttons = null;//三级按钮
		
		roots = (List<Menu>) SessionHandler.getSessionAttr(SysConfig.MENU_ROOT_KEY);
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
			
			SessionHandler.setSessionAttr(SysConfig.MENU_ROOT_KEY, roots);
			SessionHandler.setSessionAttr(SysConfig.MENU_MENU_KEY, roots);
			SessionHandler.setSessionAttr(SysConfig.MENU_BUTTON_KEY, roots);
		}
		
	}

}
