package com.isheng.dao;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.TestCase;

/**
 * dao层基础测试类，其他测试类继承该类
 *
 *
 * @author Administrator
 * @version $Id: BaseTest.java 2018年9月1日 下午7:20:12 $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {DaoApplication.class})
public class BaseTest extends TestCase{

}
