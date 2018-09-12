package com.isheng.common.base;

import java.util.List;
import com.isheng.common.exception.BizException;
import com.isheng.common.model.Page;

/**
 * 基础服务
 *
 *
 * @author Administrator
 * @version $Id: BaseService.java 2018年9月1日 下午6:32:43 $
 */
public interface BaseService <T, Q extends BaseQuery>{
	
	/**
	 * 保存
	 * @param entity
	 * @return
	 * @throws BizException
	 */
	String add(T entity) throws BizException;
	
	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 * @throws BizException
	 */
	int deleteById(String id) throws BizException;
	
	/**
	 * 更新
	 * 
	 * @param entity
	 * @return
	 * @throws BizException
	 */
	int update(T entity) throws BizException;
	
	/**
	 * 根据ID查询
	 * 
	 * @param id
	 * @return
	 * @throws BizException
	 */
	T getById(String id) throws BizException;
	
	/**
	 * 获取满足条件的记录数
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	long getCount(Q query) throws BizException;
	
	/**
	 * 查询所有
	 * @return
	 * @throws BizException
	 */
	List<T> getAll() throws BizException;
	
	/**
	 * 根据条件查询
	 * 
	 * @param query
	 * @return
	 * @throws BizException
	 */
	List<T> getList(Q query) throws BizException;
	
	/**
	 * 分页查询
	 * 
	 * @param query
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws BizException
	 */
	Page<T> getPaging(Q query, int pageNo, int pageSize) throws BizException;
	
	/**
	 * 查询指定的值是否有相同
	 * @param value
	 * @param id
	 * @return
	 */
	boolean isExist(String id, String column, Object value) throws BizException;
}
