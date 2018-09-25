package com.isheng.web.cms.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
//import org.apache.shiro.session.Session;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.isheng.common.constant.SysConfig;
import com.isheng.common.exception.BizException;
import com.isheng.model.auth.domain.SessionUser;
import com.isheng.model.auth.entity.User;

/**
 * request, session, response工具类
 *
 *
 * @author Administrator
 * @version $Id: ContextUtil.java 2018年9月19日 下午11:02:37 $
 */
public class ContextUtil {
	
	public static HttpServletRequest getRequest() throws BizException {
		try {
			return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		} catch (Exception e) {
			throw new BizException(e);
		}
	}
	
	public static HttpServletResponse getResponse() throws BizException {
		try {
			return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		} catch (Exception e) {
			throw new BizException(e);
		}
	}
	
	public static HttpSession getHttpSession() throws BizException {
		try {
			return getRequest().getSession();
		} catch (Exception e) {
			throw new BizException(e);
		}
	}
	
	public static Session getShiroSession() throws BizException {
		try {
			return SecurityUtils.getSubject().getSession();
		} catch (Exception e) {
			throw new BizException(e);
		}
	}

	/**
	 * 获取存放在会话中的对象
	 * 
	 * @param key
	 * @return
	 */
	public static Object getSessionAttr(String key) {
		Session session = getShiroSession();
		if (null != session) {
			return session.getAttribute(key);
		}
		return null;
	}
	
	/**
	 * 获取存放在会话中的已知类型的对象
	 * 
	 * @param key
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getSessionAttr(String key, Class<T> clazz) {
		return (T) getSessionAttr(key);
	}
	
	/**
	 * 设置会话属性
	 * 
	 * @param key
	 * @param value
	 */
	public static void setSessionAttr(String key, Object value) {
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute(key, value);
	}
	
	/**
	 * 移除会话属性
	 * 
	 * @param key
	 */
	public static void removeSessionAttr(String key) {
		Session session = SecurityUtils.getSubject().getSession(false);
		if (null != session) {
			session.removeAttribute(key);
		}
	}
	
	/**
	 * 获取当前登录的用户
	 * 
	 * @return
	 */
	public static SessionUser getCurrentUser() {
		return getSessionAttr(SysConfig.SESSION_USER_KEY, SessionUser.class);
	}
	
	/**
	 * 退出
	 */
	public static void logout() {
		SecurityUtils.getSubject().logout();
	}
	
	/**
	 * 登录
	 * 
	 * @param user
	 */
	public static void login(User user) {
		SecurityUtils.getSubject().login(new UserToken(user, user.getPwd()));
	}
}
