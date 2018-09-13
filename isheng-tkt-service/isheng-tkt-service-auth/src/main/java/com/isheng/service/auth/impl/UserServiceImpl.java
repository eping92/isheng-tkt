package com.isheng.service.auth.impl;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.isheng.common.base.AbstractBaseService;
import com.isheng.common.base.BaseDao;
import com.isheng.common.codec.Md5Utils;
import com.isheng.common.enums.ErrMsg;
import com.isheng.common.exception.BizException;
import com.isheng.common.model.ResultResp;
import com.isheng.dao.service.auth.UserDao;
import com.isheng.model.auth.domain.UserLogin;
import com.isheng.model.auth.entity.User;
import com.isheng.model.auth.enums.UserStatus;
import com.isheng.model.auth.request.UserQuery;
import com.isheng.service.auth.UserService;

@Component("userService")
@Service(interfaceClass = UserService.class)
public class UserServiceImpl extends AbstractBaseService<User, UserQuery> implements UserService {
	
	@Reference
	private UserDao userDao;

	@Override
	protected BaseDao<User, UserQuery> getDao() {
		return userDao;
	}
	
	@Override
	public User getByLoginName(String loginName) throws BizException {
		return userDao.getByLoginName(loginName);
	}

	@Override
	public User getByMobile(String mobile) throws BizException {
		return userDao.getByMobile(mobile);
	}

	@Override
	public ResultResp<User> login(UserLogin userLogin) throws BizException {
		ResultResp<User> resp = new ResultResp<User>();
		if (null == userLogin) {
			return resp.setResponse(ErrMsg.LOGIN_NULL);
		}
		
		logger.info("用户登录，loginName={}, mobile={}", userLogin.getLoginName(), userLogin.getMobile());
		
		if ( (StringUtils.isEmpty(userLogin.getLoginName()) && StringUtils.isEmpty(userLogin.getMobile())) || StringUtils.isEmpty(userLogin.getPwd())) {
			return resp.setResponse(ErrMsg.LOGIN_NULL);
		}
		
		User user = getByLoginName(userLogin.getLoginName());//登录名登录
		if (null == user) {
			user = getByMobile(userLogin.getMobile());//手机号登录
		}
		
		if (null == user) {
			logger.info("用户登录，用户不存在或被删除");
			return resp.setResponse(ErrMsg.LOGIN_ERR);
		}
		
		if (UserStatus.ENABLE != user.getUserStatus()) {
			logger.info("用户登录，账户状态为:{}", user.getUserStatus());
			return resp.setResponse(ErrMsg.LOGIN_EXP);
		}
		
		String encryptPwd = Md5Utils.md5(userLogin.getPwd());
		if (StringUtils.isEmpty(user.getPwd()) || !user.getPwd().equals(encryptPwd)) {
			logger.info("用户登录，密码错误:loginName={}, mobile={}", userLogin.getLoginName(), userLogin.getMobile());
			return resp.setResponse(ErrMsg.LOGIN_ERR);
		}
		
		logger.info("用户登录，登录成功：loginName={}, mobile={}", userLogin.getLoginName(), userLogin.getMobile());
		return resp.setResponse(ErrMsg.SUCCESS, user);
	}

	


}
