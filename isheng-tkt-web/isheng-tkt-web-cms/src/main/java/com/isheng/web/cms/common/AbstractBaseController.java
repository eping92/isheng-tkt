package com.isheng.web.cms.common;

import org.springframework.stereotype.Controller;

import com.isheng.common.constant.SysConfig;
import com.isheng.common.exception.BizException;
import com.isheng.common.model.ResultModel;
import com.isheng.model.auth.domain.SessionUser;

@Controller
public class AbstractBaseController {
	
	/**
	 * 返回对象
	 * @return
	 */
	protected ResultModel getModel() {
		return new ResultModel();
	}
	
	/**
	 * 数据验证
	 */
	protected ResultModel dataValid(Object object) {
		return null;
	}
	
	/**
	 * 获取当前登录的user
	 * 
	 * @return
	 */
	protected SessionUser getCurrentUser() {
		try {
			return ContextUtil.getSessionAttr(SysConfig.SESSION_USER_KEY, SessionUser.class);
		} catch (Exception e) {
			throw new BizException(e);
		}
	}

}
