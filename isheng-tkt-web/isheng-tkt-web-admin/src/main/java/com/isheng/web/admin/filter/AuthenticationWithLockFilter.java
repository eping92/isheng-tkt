package com.isheng.web.admin.filter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isheng.common.constant.SysConfig;
import com.isheng.common.util.WebUtil;
import com.isheng.model.auth.domain.SessionUser;
import com.isheng.model.auth.enums.SessionStatus;
import com.isheng.web.admin.common.SessionHandler;

/**
 * 表单请求权限验证拦截器
 *
 *
 * @author Administrator
 * @version $Id: AuthenticationWithLocakFilter.java 2018年9月9日 下午10:09:37 $
 */
public class AuthenticationWithLockFilter extends FormAuthenticationFilter {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationWithLockFilter.class);
	
	private long maxLoginAttempts = 10;
	
	public static ConcurrentHashMap<String, AtomicLong> accountLockMap = new ConcurrentHashMap<>();

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		Subject subject = getSubject(request, response);
		boolean isAuthenticated = subject.isAuthenticated();
		if (!isAuthenticated) {
			return false;
		}
		
//		UserSession user = SessionHandler.currentUser();
		//redis缓存中获取
//		String loginSession = valueoptService.get(user.getLoginId() + Constants.PRINCIPAL_NAME_ATTRIBUTE_OMSNAME);
//		if (StringUtils.isEmpty(loginSession)) {
//			SecurityUtils.getSubject().logout();
//			return false;
//		}
		
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		String code = WebUtil.parentUri(uri);
		try {
			if (StringUtils.isNotEmpty(code) && !":".equals(code) && subject.isPermitted(code)) {
				logger.debug("-----------当前请求【{}】,没有权限访问----------", code);
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		String redirect = (String) request.getAttribute(SysConfig.GOTO_KEY);
		HttpServletResponse resp = (HttpServletResponse) response;
		if (null != redirect) {
			resp.sendRedirect(redirect);
			return false;
		}
		if (isLoginRequest(request, response)) {
			return true;
		}
		//用户已经登录了,没有权限访问
		SessionUser user = SessionHandler.currentUser();
		if (null != user) {
			resp.setHeader(SysConfig.SESSION_STATUS_KEY, SessionStatus.noPermis.name());
			WebUtils.issueRedirect(request, response, getSuccessUrl());
			return false;
		}
		resp.setHeader(SysConfig.SESSION_STATUS_KEY, SessionStatus.timeOut.name());
		return false;
	}
	
	@Override
	protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
		Map<?, ?> params = request.getParameterMap();
		String[] keys = params.keySet().toArray(new String[0]);
		StringBuilder sb = new StringBuilder();
		for (String key : keys) {
			Object value = request.getParameter(key);
			if (null == value) {
				value = "";
			}
			sb.append(key).append("=").append(value).append("&");
		}
		if (sb.length() >= 1) {
			sb = sb.deleteCharAt(sb.lastIndexOf("&"));
			sb.insert(0, "?");
		}
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getServletPath() + sb.toString();
		String loginUrl = getLoginUrl() + "?" + SysConfig.GOTO_KEY + "=" + uri;
		SessionHandler.setSessionAttr(SysConfig.GOTO_KEY, uri);
		WebUtils.issueRedirect(request, response, loginUrl);
	}
	
	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		AuthenticationToken token = createToken(request, response);
		if (token == null) {
			String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken "
					+ "must be created in order to execute a login attempt.";
			throw new IllegalStateException(msg);
		}

		if (checkIfAccountLocked(request)) {
			return onLoginFailure(token, new ExcessiveAttemptsException(), request, response);
		}
		if (!doLogin(request, response, token)) {
			resetAccountLock(getUsername(request));
			return false;
		}
		return true;
	}

	private boolean checkIfAccountLocked(ServletRequest request) {
		String username = getUsername(request);
		if (accountLockMap.get(username) != null) {
			long remainLoginAttempts = accountLockMap.get(username).get();
			if (remainLoginAttempts <= 0) {
				return true;
			}
		}
		return false;
	}

	private boolean doLogin(ServletRequest request, ServletResponse response, AuthenticationToken token)
			throws Exception {
		try {
			Subject subject = getSubject(request, response);
			subject.login(token);
			return onLoginSuccess(token, subject, request, response);
		} catch (IncorrectCredentialsException e) {
			decreaseAccountLoginAttempts(request);
			checkIfAccountLocked(request);
			return onLoginFailure(token, e, request, response);
		} catch (AuthenticationException e) {
			return onLoginFailure(token, e, request, response);
		}
	}

	private void decreaseAccountLoginAttempts(ServletRequest request) {
		AtomicLong initValue = new AtomicLong(maxLoginAttempts);
		AtomicLong remainLoginAttempts = accountLockMap.putIfAbsent(getUsername(request),
				new AtomicLong(maxLoginAttempts));
		if (remainLoginAttempts == null) {
			remainLoginAttempts = initValue;
		}
		remainLoginAttempts.getAndDecrement();
		accountLockMap.put(getUsername(request), remainLoginAttempts);
	}

	private void resetAccountLock(String username) {
		accountLockMap.put(username, new AtomicLong(maxLoginAttempts));
	}

	public void setMaxLoginAttempts(long maxLoginAttempts) {
		this.maxLoginAttempts = maxLoginAttempts;
	}

	/**
	 * 是否是Json请求
	 * 
	 * @param request
	 *            Http请求
	 * @return 是则返回<code>TRUE</code>
	 */
	public final boolean isJsonRequest(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest) request;
		String requestedWith = req.getHeader("X-Requested-With");
		if (StringUtils.equalsIgnoreCase(requestedWith, "XMLHttpRequest")) {
			return true;
		}

		String requestUri = req.getRequestURI();
		if (StringUtils.endsWithIgnoreCase(requestUri, ".json")
				|| StringUtils.endsWithIgnoreCase(requestUri, ".jsonp")) {
			return true;
		}

		return false;
	}
}
