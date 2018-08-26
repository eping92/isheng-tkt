package com.isheng.dao.core.auth;

import com.isheng.dao.common.BaseMapper;
import com.isheng.model.auth.entity.User;
import com.isheng.model.auth.request.UserQuery;

public interface UserMapper extends BaseMapper<User, Long, UserQuery> {

}
