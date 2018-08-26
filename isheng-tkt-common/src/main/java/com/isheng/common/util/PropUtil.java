package com.isheng.common.util;

import java.util.Properties;

/**
 * 通用属性操作类
 *
 *
 * @author Administrator
 * @version $Id: Property.java 2018年7月15日 上午11:48:47 $
 */
public class PropUtil {
	
	private static Properties property = null;
	
	private PropUtil() {};
	
	/**
	 * 初始化属性
	 * @param prop
	 */
	public static void init(Properties props) {
		property = props;
	}
	
	/**
	 * 设置属性
	 * @param key
	 * @param value
	 */
	public static void setProperty(String key, Object value) {
		property.put(key, value);
	}
	
	/**
	 * 获取指定key的属性value
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		return property.getProperty(key);
	}
	
	/**
	 * 获取指定key的属性value,为空则返回指定默认值
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getProperty(String key, String defaultValue) {
		return property.getProperty(key, defaultValue);
	}

}
