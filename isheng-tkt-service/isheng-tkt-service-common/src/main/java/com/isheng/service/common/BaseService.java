package com.isheng.service.common;

import java.io.Serializable;
import com.isheng.model.common.exception.BizException;
import com.isheng.model.common.pojo.Response;
import com.isheng.model.common.request.BaseQuery;

public interface BaseService <T, K extends Serializable, Q extends BaseQuery>{
	
	/*
	 * 保存
	 */
	Response save(T entity) throws BizException;
	
	/**
	 * 删除及批量删除
	 * 
	 * @param ids
	 * @return
	 * @throws BizException
	 */
	Response batchDelByIds(K[] ids) throws BizException;
	
	/**
	 * 更新
	 * 
	 * @param entity
	 * @return
	 * @throws BizException
	 */
	Response update(T entity) throws BizException;
	
	/**
	 * 根据ID查询
	 * 
	 * @param id
	 * @return
	 * @throws BizException
	 */
	Response getById(K id) throws BizException;
	
	/**
	 * 根据条件查询
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	Response listByParam(Q query) throws BizException;
	
	/**
	 * 获取满足条件的记录数
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	Response countByParam(Q query) throws BizException;
	
	/**
	 * 分页查询
	 * 
	 * @param query
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws BizException
	 */
	Response paging(Q query, int pageNo, int pageSize) throws BizException;
}
