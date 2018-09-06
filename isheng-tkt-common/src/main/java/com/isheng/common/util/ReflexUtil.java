package com.isheng.common.util;

import java.lang.reflect.Field;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 反射工具类
 * @author Administrator
 *
 */
public class ReflexUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ReflexUtil.class);
	
	/**
	 * 设置属性值
	 * @param obj
	 * @param key
	 * @param value
	 * @throws Exception
	 */
	public static void setFieldValue(Object obj, String key, Object value) throws Exception {
		if (null == obj || StringUtils.isEmpty(key)) {
			return;
		}
		synchronized(obj.getClass()) {
			try {
				Class<?> clazz = obj.getClass();
				Field field = clazz.getDeclaredField(key);
				field.setAccessible(true);
				field.set(obj, value);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("反射设置属性值异常：key={}, value={}", key, value);
			}
		}
	}

}
