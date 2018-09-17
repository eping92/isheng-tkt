package com.isheng.common.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.isheng.common.exception.BizException;

/**
 * 基础Controller
 *
 *
 * @author Administrator
 * @version $Id: BaseController.java 2018年9月16日 下午10:07:59 $
 */
public class BaseController {
	
	protected HttpServletRequest getRequest() throws BizException {
		try {
			return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		} catch (Exception e) {
			throw new BizException(e);
		}
	}
	
	protected HttpServletResponse getResponse() throws BizException {
		try {
			return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		} catch (Exception e) {
			throw new BizException(e);
		}
	}
	
	protected HttpSession getSession() throws BizException {
		try {
			return getRequest().getSession();
		} catch (Exception e) {
			throw new BizException(e);
		}
	}

	protected Object getSessionAttr(String key) throws BizException {
		try {
			return getSession().getAttribute(key);
		} catch (Exception e) {
			throw new BizException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T getSessionAttr(String key, Class<T> clazz) throws BizException {
		try {
			return (T) getSessionAttr(key);
		} catch (Exception e) {
			throw new BizException(e);
		}
	}
	
	protected void setSessionAttr(String key, Object value) throws BizException {
		try {
			getSession().setAttribute(key, value);
		} catch (Exception e) {
			throw new BizException(e);
		}
	}
	
	protected void removeSessionAttr(String key) throws BizException {
		try {
			getSession().removeAttribute(key);
		} catch (Exception e) {
			throw new BizException(e);
		}
	}
	
}
