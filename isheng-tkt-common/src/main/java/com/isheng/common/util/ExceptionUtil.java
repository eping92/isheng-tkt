package com.isheng.common.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import com.isheng.common.constant.SysConfig;
import com.isheng.common.enums.ErrMsg;
import com.isheng.common.exception.BizException;

/**
 * 页面异常处理工具类
 *
 *
 * @author Administrator
 * @version $Id: ExceptionUtil.java 2018年9月10日 下午10:32:06 $
 */
public class ExceptionUtil {
	
	/**
	 * 出现异常跳转到指定错误页
	 * 
	 * @param e 异常
	 * @param errView 错误页面视图
	 * @return ModelAndView
	 */
	public static ModelAndView exceptionHandler(Exception e, String errView) {
		ModelAndView mv = new ModelAndView();
		mv.addObject(SysConfig.EXCEPTION_CODE_KEY, getErrCode(e));
		mv.addObject(SysConfig.EXCEPTION_MSG_KEY, getErrMsg(e));
		return mv;
	}
	
	/**
	 * 异常信息的json格式
	 * 
	 * @param errCode
	 * @param errMsg
	 * @return
	 */
	public static String excpetionJson(Exception e) {
		int errCode = getErrCode(e);
		String errMsg = getErrMsg(e);
		return "{\"code\": \"" + errCode + "\", \"message\":\"" + errMsg + "\"}";
	}
	
	/**
	 * 获取异常码，默认为系统异常
	 * 
	 * @param e
	 * @return
	 */
	public static final int getErrCode(Exception e) {
		int errCode = ErrMsg.EXP_SYS.getCode();
		if (e instanceof BizException) {
			BizException be = (BizException) e;
			errCode = be.getCode();
		}
		return errCode;
	}
	
	/**
	 * 获取异常错误提示信息，默认为系统异常
	 * 
	 * @param e
	 * @return
	 */
	public static final String getErrMsg(Exception e) {
		String errMsg = ErrMsg.EXP_SYS.getText();
		if (e instanceof BizException) {
			BizException be = (BizException) e;
			if (StringUtils.isNotEmpty(be.getMessage())) {
				errMsg = be.getMessage();
			}
		}
		return errMsg;
	}
}
