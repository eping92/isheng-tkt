package com.isheng.dao.service.auth.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Service;
import com.isheng.common.base.AbstractBaseDao;
import com.isheng.common.base.BaseMapper;
import com.isheng.common.exception.BizException;
import com.isheng.common.idgen.IdGenerate;
import com.isheng.dao.mapper.auth.MenuMapper;
import com.isheng.dao.service.auth.MenuDao;
import com.isheng.model.auth.entity.Menu;
import com.isheng.model.auth.request.MenuQuery;

@Component("menuDao")
@Service(interfaceClass = MenuDao.class)
public class MenuDaoImpl extends AbstractBaseDao<Menu, MenuQuery> implements MenuDao {

	@Resource
	private MenuMapper menuMapper;

	@Override
	protected BaseMapper<Menu, MenuQuery> getMapper() {
		return menuMapper;
	}

	@Override
	public String save(Menu entity) throws BizException {
		entity.setId(IdGenerate.nextId());
		return menuMapper.insert(entity);
	}
}
