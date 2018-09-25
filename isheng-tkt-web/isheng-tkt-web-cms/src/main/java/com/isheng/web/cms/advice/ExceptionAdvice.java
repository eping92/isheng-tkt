package com.isheng.web.cms.advice;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import com.isheng.common.constant.SysConfig;
import com.isheng.common.util.ExceptionUtil;
import com.isheng.common.util.WebUtil;

/**
 * 统一异常处理类
 *
 *
 * @author Administrator
 * @version $Id: ExceptionHandler.java 2018年9月10日 下午10:12:23 $
 */
@ControllerAdvice
public class ExceptionAdvice {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

	@ExceptionHandler(value = Exception.class)
	public ModelAndView exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
		logger.error("请求处理异常", e);
		ModelAndView mv = new ModelAndView();
		//json请求则像客户端写入异常的json数据，否则跳转错误页面
		if (WebUtil.isJsonRequest(request)) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setCharacterEncoding(SysConfig.CHARSET);
			response.setHeader("Cache-Control", "no-cache, must-revalidate");
			try {
				response.getWriter().write(ExceptionUtil.excpetionJson(e));
			} catch (IOException ex) {
				logger.error("抛出异常", ex);
			}
		} else {
			mv = ExceptionUtil.exceptionHandler(e, SysConfig.ERR_VIEW);
		}
		return mv;
	}
}
