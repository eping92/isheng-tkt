package com.isheng.common.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.isheng.common.constant.SysConfig;

/**
 * web工具类
 *
 *
 * @author Administrator
 * @version $Id: WebUtil.java 2018年9月9日 下午10:58:09 $
 */
public final class WebUtil {

	private static final Logger logger = LoggerFactory.getLogger(WebUtil.class);

	/**
	 * 得到访问者ip
	 * 
	 * @param req
	 * @return
	 */
	public static String getIp(HttpServletRequest req) {
		String ip = null;
		if (null != req) {
			ip = req.getHeader("X-Real-IP");
			if (ObjUtil.isNull(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = req.getHeader("X-Forwarded-For");
			}
			if (ObjUtil.isNull(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = req.getHeader("Proxy-Client-IP");
			}
			if (ObjUtil.isNull(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = req.getHeader("WL-Proxy-Client-IP");
			}
			if (ObjUtil.isNull(ip) || "unknown".equalsIgnoreCase(ip)) {
				return req.getRemoteAddr();
			}
		}
		return ip;
	}

	/**
	 * 得到客户端类型
	 * 
	 * @param req
	 * @return
	 */
	public static String getAgent(HttpServletRequest req) {
		String agent = SysConfig.CLIENT_ANDORID;
		String head = req.getHeader(SysConfig.AGENT_UA);
		if ("os".equalsIgnoreCase(head)) {
			agent = SysConfig.CLIENT_IOS;
		}
		return agent;
	}
	
	/**
	 * 判断是否为json请求
	 * 
	 * @return
	 */
	public static final boolean isJsonRequest(HttpServletRequest req) {
		String requestWith = req.getHeader("X-Requested-With");
		if (StringUtils.equalsIgnoreCase(requestWith, "XMLHttpRequest")) {
			return true;
		}
		
		String requestUri = req.getRequestURI();
		if (StringUtils.endsWithIgnoreCase(requestUri, ".json")
				|| StringUtils.endsWithIgnoreCase(requestUri, ".jsonp")) {
			return true;
		}
		return false;
	}

	public static Object toResponse(String content) {
		HttpServletResponse response = getResponse();
		response.setContentType("text/plain");
		response.setCharacterEncoding(SysConfig.CHARSET);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(content);
		} catch (IOException e1) {
			if (logger.isDebugEnabled()) {
				logger.debug("系统异常", e1);
			}
		} finally {
			if (null != writer) {
				writer.flush();
				writer.close();
			}
		}
		return null;
	}

	/**
	 * 获得当前请求 request
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * 获得当前请求 response
	 * 
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	}

	/**
	 * 获取当前 Session
	 * 
	 * @return
	 */
	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * 获取请求浏览器信息
	 * 
	 * @return
	 */
	public static String getUserAgent() {
		HttpServletRequest request = getRequest();
		return request.getHeader("user-agent");
	}

	/**
	 * 获取门户地址
	 * 
	 * @return
	 */
	public static String getWebpath() {
		HttpServletRequest request = getRequest();
		String path = request.getContextPath();
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	}

}
