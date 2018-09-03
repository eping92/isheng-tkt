package com.isheng.service.auth.impl;


import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.isheng.common.base.AbstractBaseService;
import com.isheng.common.base.BaseDao;
import com.isheng.dao.service.auth.UserDao;
import com.isheng.model.auth.entity.User;
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


}
