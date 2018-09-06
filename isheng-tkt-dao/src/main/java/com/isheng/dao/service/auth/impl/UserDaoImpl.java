package com.isheng.dao.service.auth.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Service;
import com.isheng.common.base.AbstractBaseDao;
import com.isheng.common.base.BaseMapper;
import com.isheng.common.exception.BizException;
import com.isheng.common.idgen.IdGenerate;
import com.isheng.dao.mapper.auth.UserMapper;
import com.isheng.dao.service.auth.UserDao;
import com.isheng.model.auth.entity.User;
import com.isheng.model.auth.request.UserQuery;

@Component("userDao")
@Service(interfaceClass = UserDao.class)
public class UserDaoImpl extends AbstractBaseDao<User, UserQuery> implements UserDao {

	@Resource
	private UserMapper userMapper;

	@Override
	protected BaseMapper<User, UserQuery> getMapper() {
		return userMapper;
	}

	@Override
	public String save(User entity) throws BizException {
		entity.setId(IdGenerate.nextId());
		return userMapper.insert(entity);
	}

}
