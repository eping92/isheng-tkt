package com.isheng.security.auth;

import java.io.Serializable;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isheng.common.util.ObjUtil;
import com.isheng.model.auth.entity.Menu;
import com.isheng.model.auth.entity.User;
import com.isheng.security.common.AbstractUser;
import com.isheng.security.pojo.SessionUser;
import com.isheng.security.pojo.UserToken;

public class UserRealm extends AuthorizingRealm {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserRealm.class);
	
	private MenuService menuService;
	
	/**
	 * 认证回调函数，登陆时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UserToken userToken = (UserToken)token;
		User user = userToken.getUser();
		/** 登陆重复处理 */
		LOG.info("账户登陆，账户:{},被踢出", user.getLoginName());
		/** 缓存中删除用户 */
		
		
		SessionUser sessionUser = this.buildSessionUser(user);
		AbstractUser.setSessionAttribute("SESSION_USER", sessionUser);
		Serializable sessionId = SecurityUtils.getSubject().getSession().getId();
		/** 放入缓存中 */
		//TODO
		
		LOG.info("账户登陆,账户:{},sessionId={}", user.getLoginName(), sessionId);
		return new SimpleAuthenticationInfo(user.getLoginName(), userToken.getPwd(), getName());
	}
	
	private SessionUser buildSessionUser(User user) {
		SessionUser sessionUser = new SessionUser();
		sessionUser.setHeadPath(user.getHeadPath());
		sessionUser.setLoginName(user.getLoginName());
		sessionUser.setMobile(user.getMobile());
		sessionUser.setNick(user.getNick());
		sessionUser.setRealName(user.getRealName());
		sessionUser.setUserId(user.getId());
		return sessionUser;
	}

	/**
	 * 授权查询回调函数，进行鉴权但是缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String loginName = (String)principals.fromRealm(getName()).iterator().next();
		if (ObjUtil.isNull(loginName)) {
			return null;
		}
		
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo();
		List<Menu> menuList = 
		
		
		
		return null;
	}

	

}
