package com.isheng.service.auth;

import com.isheng.model.auth.entity.User;
import com.isheng.model.auth.request.UserQuery;
import com.isheng.service.common.BaseService;

public interface UserService extends BaseService<User, Long, UserQuery> {

}
