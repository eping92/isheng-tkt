package com.isheng.dao.mapper.auth;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.isheng.common.base.BaseMapper;
import com.isheng.model.auth.entity.Menu;
import com.isheng.model.auth.request.MenuQuery;
/**
 * 权限
 *
 *
 * @author Administrator
 * @version $Id: MenuDao.java 2018年7月28日 上午11:51:50 $
 */
@Mapper
@Repository
public interface MenuMapper extends BaseMapper<Menu, MenuQuery> {

	
}
