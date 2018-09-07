package com.isheng.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isheng.common.exception.BizException;

/**
 * 反射工具类
 * @author Administrator
 *
 */
public class ReflexUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ReflexUtil.class);
	
	private static final ReadWriteLock lock = new ReentrantReadWriteLock();
	
	/**
	 * 通过反射给指定的字段设置指定的值
	 * 循环向上获取父类所有属性
	 * @param obj 泛型对象
	 * @param fieldName 字段名
	 * @param value 要设置的字段值
	 */
	public static void setFieldValue(Object obj, String fieldName, Object value) throws BizException {
		if (null == obj || StringUtils.isEmpty(fieldName)) {
			return;
		}
		
		lock.readLock().lock();
		try {
			boolean lookup = true;
			String setMethod = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
				if (!lookup) {
					break;
				}
				Field[] fs = clazz.getDeclaredFields();
				for (Field f : fs) {
					if (fieldName.equals(f.getName())) {
						Method m = clazz.getDeclaredMethod(setMethod, value.getClass());
						m.invoke(obj, value);
						lookup = false;
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("反射设置属性值异常：Object={}, fieldName={}, value={}", obj, fieldName, value);
			throw new BizException("反射设置属性值异常");
			
		} finally {
			lock.readLock().unlock();
		}
	}
}
