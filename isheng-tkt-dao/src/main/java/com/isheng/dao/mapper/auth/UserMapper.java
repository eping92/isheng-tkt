package com.isheng.dao.mapper.auth;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.isheng.common.base.BaseMapper;
import com.isheng.model.auth.entity.User;
import com.isheng.model.auth.request.UserQuery;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User, UserQuery> {

}
