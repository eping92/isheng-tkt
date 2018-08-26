package com.isheng.dao.common;

import java.io.Serializable;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.isheng.model.common.exception.BizException;
import com.isheng.model.common.request.BaseQuery;

public interface BaseDao<T, K extends Serializable, Q extends BaseQuery> {
	
	/**
	 * 新增
	 * 
	 * @param entity
	 * @return
	 * @throws BizException
	 */
	long save(T entity) throws BizException;
	
	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 * @throws BizException
	 */
	int delByIds(@Param("ids")K[] ids) throws BizException;
	
	/**
	 * 更新
	 * 
	 * @param entity
	 * @return
	 * @throws BizException
	 */
	int update(T entity) throws BizException;
	
	/**
	 * 根据ID获取
	 * 
	 * @param id
	 * @return
	 * @throws BizException
	 */
	T getById(K id) throws BizException;
	
	/**
	 * 满足条件的记录数
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	long countByQuery(Q query) throws BizException;
	
	/**
	 * 满足条件的记录
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	List<T> listByQuery(Q query) throws BizException;
	
	/**
	 * 分页查询
	 * 
	 * @param query
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws BizException
	 */
	List<T> paging(Q query, int pageNo, int pageSize) throws BizException;

}
