package com.isheng.security.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

public abstract class AbstractUser implements BaseUser{

	private static final long serialVersionUID = 8004408212566020516L;
	
	/**
	 * 获取存放在会话中的对象
	 * @param key
	 * @return
	 */
	public static Object getSessionAttribute(String key) {
		Session session = SecurityUtils.getSubject().getSession(false);
		return null != session ? session.getAttribute(key) : null;
	}
	
	/**
	 * 获取存放在会话中的对象
	 * @param key
	 * @param requiredType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getSessionAttribute(String key, Class<T> requiredType) {
		return (T)getSessionAttribute(key);
	}
	
	/**
	 * 设置会话属性
	 * @param key
	 * @param value
	 */
	public static void setSessionAttribute(String key, Object value) {
		Session session = SecurityUtils.getSubject().getSession(true);
		session.setAttribute(key, value);
	}
	
	/**
	 * 删除会话中的对象
	 * @param key
	 */
	public static void removeSessionAttribute(String key) {
		SecurityUtils.getSubject().getSession(true).removeAttribute(key);
	}

}
