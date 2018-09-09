package com.isheng.web.admin.auth;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isheng.common.constant.SysConfig;

/**
 * 首页
 *
 *
 * @author Administrator
 * @version $Id: IndexController.java 2018年9月8日 下午8:16:32 $
 */
@Controller
@RequestMapping("")
public class IndexController {
	
	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping("")
	public String index(HttpServletRequest req) {
		String gto = req.getParameter(SysConfig.GOTO_KEY);
		if (StringUtils.isEmpty(gto)) {
			gto = "index";
		}
		return gto;
	}
	
	/**
	 * 登录页
	 * 
	 * @return
	 */
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	/**
	 * 登出
	 * 
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout() {
		//处理逻辑
		return "login";
	}

}
