package com.isheng.common.model;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
/**
 * 分页
 *
 *
 * @author Administrator
 * @version $Id: Page.java 2018年8月18日 下午11:06:11 $
 */
public class Page<T> implements Serializable{

	/**  */
	private static final long serialVersionUID = -3642028310384505741L;

	/** 当前第几页 */
	int pageNo;

	/** 每页记录数 */
	int pageSize;

	/** 总记录数 */
	long totalSize;

	/** 总页数 */
	long totalPage;

	/** 数据 */
	private List<T> data;

	public Page() {
	};

	public Page(List<T> data, long totalSize, int pageNo, int pageSize) {
		this.data = (null != data) ? data : new ArrayList<T>();
		this.totalSize = totalSize;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalPage = (totalSize - 1) / pageSize + 1;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		Method[] methods = this.getClass().getMethods();
		boolean isFirst = true;
		for (int i = 0, n = methods.length; i < n; i++) {
			try {
				Method method = methods[i];
				if ((method.getModifiers() & Modifier.PUBLIC) == 1 && method.getDeclaringClass() != Object.class
						&& (method.getParameterTypes() == null || method.getParameterTypes().length == 0)) {
					String methodName = method.getName();
					String property = null;
					if (methodName.startsWith("get")) {
						property = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
					} else if (methodName.startsWith("is")) {
						property = methodName.substring(2, 3).toLowerCase() + methodName.substring(3);
					}
					if (property != null) {
						Object value = method.invoke(this, new Object[0]);
						if (isFirst)
							isFirst = false;
						else
							buf.append(",");
						buf.append(property);
						buf.append(":");
						if (value instanceof String)
							buf.append("\"");
						buf.append(value);
						if (value instanceof String)
							buf.append("\"");
					}
				}
			} catch (Exception e) {
				// ignore
			}
		}
		return "{" + buf.toString() + "}";
	}


}
