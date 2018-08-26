package com.isheng.service.auth.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.isheng.model.auth.entity.User;
import com.isheng.model.auth.request.UserQuery;
import com.isheng.service.auth.UserService;
import com.isheng.service.common.AbstractBaseService;

@Component
@Service(interfaceClass = UserService.class)
public class UserServiceImpl extends AbstractBaseService<User, Long, UserQuery> implements UserService {

	

}
