package com.isheng.dao.mapper.auth;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.isheng.common.base.BaseMapper;
import com.isheng.common.exception.BizException;
import com.isheng.model.auth.entity.User;
import com.isheng.model.auth.request.UserQuery;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User, UserQuery> {
	
	/**
	 * 根据登录名查询
	 * @param loginName
	 * @return
	 * @throws BizException
	 */
	User selectByLoginName(@Param("loginName")String loginName) throws BizException;
	
	/**
	 * 根据手机号查询
	 * @param mobile
	 * @return
	 * @throws BizException
	 */
	User selectByMobile(@Param("mobile")String mobile) throws BizException;

}
