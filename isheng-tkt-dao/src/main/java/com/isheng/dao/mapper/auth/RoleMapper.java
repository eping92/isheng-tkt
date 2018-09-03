package com.isheng.dao.mapper.auth;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.isheng.common.base.BaseMapper;
import com.isheng.model.auth.entity.Role;
import com.isheng.model.auth.request.RoleQuery;

@Mapper
@Repository
public interface RoleMapper extends BaseMapper<Role, RoleQuery>{

}
