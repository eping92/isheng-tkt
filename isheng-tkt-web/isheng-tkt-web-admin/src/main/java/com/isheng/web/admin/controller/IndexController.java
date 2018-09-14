package com.isheng.web.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.isheng.common.base.BaseController;
import com.isheng.common.constant.SysConfig;
import com.isheng.common.enums.ErrMsg;
import com.isheng.common.model.ResultModel;
import com.isheng.common.model.ResultResp;
import com.isheng.model.auth.domain.UserLogin;
import com.isheng.model.auth.entity.User;
import com.isheng.service.auth.UserService;
import com.isheng.web.admin.common.SessionHandler;
import com.isheng.web.admin.common.UserToken;

/**
 * 首页
 *
 *
 * @author Administrator
 * @version $Id: IndexController.java 2018年9月8日 下午8:16:32 $
 */
@Controller
@RequestMapping("")
public class IndexController extends BaseController<UserLogin> {
	
	@Reference
	private UserService userService;
	
	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping("")
	public String index(HttpServletRequest req) {
		String gto = req.getParameter(SysConfig.GOTO_KEY);
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
	@RequestMapping("/login")
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
		
		SecurityUtils.getSubject().logout();
		SecurityUtils.getSubject().login(new UserToken(user, user.getPwd()));
		String gto = (String) SessionHandler.getSessionAttr(SysConfig.GOTO_KEY);
		if (StringUtils.isEmpty(gto) || "null".equalsIgnoreCase(gto)) {
			gto = "index";
		}
		
		return result.setResult(ErrMsg.SUCCESS).addData(SysConfig.GOTO_KEY, gto);
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
		SecurityUtils.getSubject().logout();
		return result.setCode(ErrMsg.SUCCESS.getCode()).setMsg("退出成功").addData(SysConfig.GOTO_KEY, "index");
	}
}
