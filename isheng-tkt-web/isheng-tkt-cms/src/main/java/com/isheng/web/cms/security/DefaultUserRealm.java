package com.isheng.web.cms.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.config.annotation.Reference;
import com.isheng.common.constant.SysConfig;
import com.isheng.model.auth.domain.SessionUser;
import com.isheng.model.auth.entity.Menu;
import com.isheng.model.auth.entity.User;
import com.isheng.service.auth.MenuService;
import com.isheng.web.cms.common.ContextUtil;
import com.isheng.web.cms.common.UserToken;

public class DefaultUserRealm extends AuthorizingRealm {
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultUserRealm.class);
	
	@Reference
	private MenuService menuService;
	
	/**
	 * 认证回调函数,登录时调用.
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UserToken userToken = (UserToken) token;
		User user = userToken.getUser();
		logger.info("账户登录，账户:{},被踢出", user.getLoginName());
		//////TODO 从缓存中删除user session
		SessionUser sessionUser = new SessionUser();
		sessionUser.setLoginName(user.getLoginName());
		sessionUser.setMobile(user.getMobile());
		sessionUser.setNick(user.getNick());
		sessionUser.setRealName(user.getRealName());
		sessionUser.setUserId(user.getId());
		
		//重新放入会话中
		ContextUtil.setSessionAttr(SysConfig.SESSION_USER_KEY, sessionUser);
		//放入缓存中
		logger.info("账户登录,账户:{}, sessionId:{}", user.getLoginName(), SecurityUtils.getSubject().getSession().getId());
		
		return new SimpleAuthenticationInfo(user.getId(), userToken.getPassword(), getName());
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userId = (String) principals.fromRealm(getName()).iterator().next();
		if (StringUtils.isEmpty(userId)) {
			return null;
		}
		
		SimpleAuthorizationInfo info = (SimpleAuthorizationInfo) ContextUtil.getSessionAttr(SysConfig.PERMISSIONS);
		if (null != info) {
			return info;
		}
		
		info = new SimpleAuthorizationInfo();
		List<Menu> menuList = new ArrayList<>();//TODO menuService.listByUserId(userId);
		if (null != menuList && !menuList.isEmpty()) {
			for (Menu m : menuList) {
				info.addStringPermission(m.getCode());
			}
			ContextUtil.setSessionAttr(SysConfig.SESSION_USER_KEY, info);
		}
		
		return info;
	}

	
	/**
	 * 更新用户授权信息缓存.
     */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    @Override
    public Class<?> getAuthenticationTokenClass() {
        return UserToken.class;
    }

}
