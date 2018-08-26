package com.isheng.service.auth.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.isheng.model.auth.entity.Menu;
import com.isheng.model.auth.request.MenuQuery;
import com.isheng.service.auth.MenuService;
import com.isheng.service.common.AbstractBaseService;

@Component
@Service(interfaceClass = MenuService.class)
public class MenuServiceImpl extends AbstractBaseService<Menu, Long, MenuQuery> implements MenuService {

}
