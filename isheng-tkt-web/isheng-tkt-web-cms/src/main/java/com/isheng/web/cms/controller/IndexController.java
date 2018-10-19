package com.isheng.web.cms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.config.annotation.Reference;
import com.isheng.common.constant.SysConfig;
import com.isheng.common.enums.ErrMsg;
import com.isheng.common.model.ResultModel;
import com.isheng.common.model.ResultResp;
import com.isheng.model.auth.domain.SessionUser;
import com.isheng.model.auth.domain.UserLogin;
import com.isheng.model.auth.entity.Menu;
import com.isheng.model.auth.entity.User;
import com.isheng.model.auth.enums.MenuType;
import com.isheng.service.auth.MenuService;
import com.isheng.service.auth.UserService;
import com.isheng.web.cms.common.AbstractBaseController;
import com.isheng.web.cms.common.ContextUtil;

/**
 * 首页
 *
 *
 * @author Administrator
 * @version $Id: IndexController.java 2018年9月8日 下午8:16:32 $
 */
@Controller
public class IndexController extends AbstractBaseController {
	
	@Reference
	private UserService userService;
	@Reference
	private MenuService menuService;
	
	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public String index(HttpServletRequest request) {
		String gto = request.getParameter(SysConfig.GOTO_KEY);
		if (StringUtils.isEmpty(gto) || "null".equalsIgnoreCase(gto)) {
			gto = "index";
		}
		return gto;
	}
	
	/**
	 * 登录页
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResultModel login(UserLogin userLogin) {
		ResultModel result = new ResultModel();
		if (null == userLogin) {
			return result.setResult(ErrMsg.LOGIN_NULL);
		}
		if ( (StringUtils.isEmpty(userLogin.getLoginName()) && StringUtils.isEmpty(userLogin.getMobile())) || StringUtils.isEmpty(userLogin.getPwd())) {
			return result.setResult(ErrMsg.LOGIN_NULL);
		}
		
		ResultResp<User> resp = userService.login(userLogin);
		if (!resp.isSuccess()) {
			return result.setResult(resp);
		}
		User user = resp.getData();
		if (null == user) {
			return result.setResult(ErrMsg.EXP_SYS);
		}
		
		ContextUtil.logout();
		ContextUtil.login(user);
		String gto = ContextUtil.getSessionAttr(SysConfig.GOTO_KEY, String.class);
		if (StringUtils.isEmpty(gto) || "null".equalsIgnoreCase(gto)) {
			gto = "index";
		}
		
		//初始化菜单
		initMenu();
		
		return result.setCode(ErrMsg.SUCCESS.getCode()).setMsg("登陆成功").addData(SysConfig.GOTO_KEY, gto);
	}
	
	/**
	 * 登出
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/logout")
	public Object logout() {
		ResultModel result = new ResultModel();
		ContextUtil.logout();
		return result.setCode(ErrMsg.SUCCESS.getCode()).setMsg("退出成功").addData(SysConfig.GOTO_KEY, "index");
	}
	
	/**
	 * 加载导航菜单
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/loadMenu")
	public ModelAndView loadMenu() {
		ModelAndView mv = new ModelAndView();
		List<Menu> list = ContextUtil.getSessionAttr(SysConfig.MENU_USER_KEY, List.class);
		if (null == list || list.isEmpty()) {
			list = menuService.getMenuTree(null);//this.initMenu();
		}
		mv.setViewName("/layout/left");
		mv.addObject("menuList", list);
		return mv;
	}
	
	
	@SuppressWarnings("unchecked")
	@Async
	private List<Menu> initMenu() {
		HttpSession session = ContextUtil.getHttpSession();
		if (null == session) {
			return new ArrayList<>();
		}
		
		List<Menu> roots = null;//一级菜单
		List<Menu> menus = null;//二级菜单
		List<Menu> buttons = null;//三级按钮
		List<Menu> userMenus = null;//用户的所有权限
		
		roots = (List<Menu>) ContextUtil.getSessionAttr(SysConfig.MENU_ROOT_KEY);
		if (null != roots && !roots.isEmpty()) {
			return new ArrayList<>();
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
			
			ContextUtil.setSessionAttr(SysConfig.MENU_ROOT_KEY, roots);
			ContextUtil.setSessionAttr(SysConfig.MENU_MENU_KEY, roots);
			ContextUtil.setSessionAttr(SysConfig.MENU_BUTTON_KEY, roots);
		}
		
		userMenus = (List<Menu>) ContextUtil.getSessionAttr(SysConfig.MENU_USER_KEY);
		if (null == userMenus || userMenus.isEmpty()) {
			String userId = "";
			SessionUser user = getCurrentUser();
			if (null != user) {
				userId = user.getUserId();
			}
			userMenus = menuService.getListByUserId(userId);
			ContextUtil.setSessionAttr(SysConfig.MENU_USER_KEY, userMenus);
		}
		return userMenus;
	}
	
}
