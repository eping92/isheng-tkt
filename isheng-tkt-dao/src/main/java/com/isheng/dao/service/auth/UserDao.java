package com.isheng.dao.service.auth;

import com.isheng.dao.common.BaseDao;
import com.isheng.model.auth.entity.User;
import com.isheng.model.auth.request.UserQuery;

public interface UserDao extends BaseDao<User, Long, UserQuery>{

}
