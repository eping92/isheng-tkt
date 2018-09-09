package com.isheng.dao.service;

import java.util.Date;

import org.junit.Test;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.isheng.common.base.BaseTest;
import com.isheng.dao.service.auth.MenuDao;
import com.isheng.model.auth.entity.Menu;
import com.isheng.model.auth.enums.MenuType;

/**
 * Menu测试类
 *
 *
 * @author Administrator
 * @version $Id: MenuServiceTest.java 2018年7月28日 下午9:43:45 $
 */
public class MenuServiceTest extends BaseTest {
	
	@Reference
	private MenuDao menuDao;
	
	@Test
	public void testAdd() {
		Menu entity = new Menu();
		entity.setCode("menu:add");
		entity.setCreateTime(new Date());
		entity.setCreateUser("何平波");
		entity.setName("增加权限");
		entity.setParentId("");
		entity.setSort(2);
		entity.setMenuType(MenuType.BUTTON);
		entity.setUpdateTime(new Date());
		entity.setUpdateUser("我自己");
		entity.setUrl("/menu/add");
		int result = menuDao.save(entity);
		System.out.println("保存成功条数 = " + result);
	}
	
	@Test
	public void testDetail() {
		String id = "11";
		Menu menu = menuDao.getById(id);
		System.out.println("查询结果---------------------------------------------" + JSON.toJSONString(menu));
	}

}
