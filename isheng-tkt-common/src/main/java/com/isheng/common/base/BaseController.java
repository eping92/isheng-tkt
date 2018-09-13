package com.isheng.common.base;

import com.isheng.common.model.ResultModel;

public class BaseController<T> {
	
	/**
	 * 返回对象
	 * @return
	 */
	protected ResultModel getModel() {
		return new ResultModel();
	}
	
	/**
	 * 数据验证
	 */
	protected ResultModel dataValid(T t) {
		return null;
	}

}
