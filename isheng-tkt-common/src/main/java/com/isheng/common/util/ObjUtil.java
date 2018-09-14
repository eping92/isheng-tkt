package com.isheng.common.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class ObjUtil {

	/** 可以用于判断Object,String,Map,Collection,String,Array是否为空 */
	@SuppressWarnings("rawtypes")
	public static boolean isNull(Object value) {
		if (value == null) {
			return true;
			
		} else if (value instanceof String) {
			if (((String) value).trim().replaceAll("\\s", "").equals("")) {
				return true;
			}
			
		} else if (value instanceof Collection) {
			if (((Collection) value).isEmpty()) {
				return true;
			}
			
		} else if (value instanceof Object[]) {
			Object[] object = (Object[]) value;
			if (object.length == 0) {
				return true;
			}
			boolean empty = true;
			for (int i = 0; i < object.length; i++) {
				if (!isNull(object[i])) {
					empty = false;
					break;
				}
			}
			return empty;
		} else if (value.getClass().isArray()) {
			if (Array.getLength(value) == 0) {
				return true;
			}
		} else if (value instanceof Map) {
			if (((Map) value).isEmpty()) {
				return true;
			}
		} else {
			return false;
		}
		return false;

	}

	public static boolean isNull(Object value, Object... items) {
		if (isNull(value) || isNull(items)) {
			return true;
		}
		for (Object item : items) {
			if (isNull(item)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isNotNull(Object value) {
		return !isNull(value);
	}

	public static boolean isNotNull(Object value, Object... items) {
		return !isNull(value, items);
	}


}
