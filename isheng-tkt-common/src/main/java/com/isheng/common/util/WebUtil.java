package com.isheng.common.util;

import javax.servlet.http.HttpServletRequest;

import com.isheng.common.constant.SysConstants;

public class WebUtil {
	
	public static String getIp (HttpServletRequest req) {
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
	
	public static String getAgent (HttpServletRequest req) {
		String agent = SysConstants.CLIENT_ANDORID;
		String head = req.getHeader(SysConstants.AGENT_UA);
		if ("os".equalsIgnoreCase(head)) {
			agent = SysConstants.CLIENT_IOS;
		}
		return agent;
	}

}
