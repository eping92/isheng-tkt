package com.isheng.dao.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.dubbo.config.annotation.Reference;
import com.isheng.dao.DaoApplication;
import com.isheng.dao.service.auth.MenuDao;
import com.isheng.model.auth.entity.Menu;
import com.isheng.model.auth.enums.MenuType;

import junit.framework.TestCase;

/**
 * Menu测试类
 *
 *
 * @author Administrator
 * @version $Id: MenuServiceTest.java 2018年7月28日 下午9:43:45 $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {DaoApplication.class})
public class MenuServiceTest extends TestCase {
	
	@Reference
	private MenuDao menuDao;
	
	@Test
	public void testAdd() {
		Menu entity = new Menu();
		entity.setCode("menu:add");
		entity.setCreateTime(new Date());
		entity.setCreateUser("何平波");
		entity.setName("增加权限");
		entity.setParentId(0);
		entity.setSort(2);
		entity.setType(MenuType.BUTTON);
		entity.setUpdateTime(new Date());
		entity.setUpdateUser("我自己");
		entity.setUrl("/menu/add");
		long result = menuDao.save(entity);
		System.out.println("result = " + result);
	}

}
