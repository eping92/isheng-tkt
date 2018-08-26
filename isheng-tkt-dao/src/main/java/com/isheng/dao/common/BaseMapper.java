package com.isheng.dao.common;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.isheng.model.common.exception.BizException;
import com.isheng.model.common.request.BaseQuery;


/**
 * 基础dao
 * @author Administrator
 *
 */
@Mapper
@Repository
public interface BaseMapper <T, K extends Serializable, Q extends BaseQuery>{
	
	/**
	 * 新增插入
	 * @param entity
	 * @return
	 */
	long insert(T entity);
	
	/**
	 * 按主键删除
	 * @param id
	 * @return
	 */
	int delByIds(@Param("ids")final K[] ids) throws BizException;
	
	/**
	 * 更新
	 * @param entity
	 * @return
	 */
	int update(T entity);
	
	/**
	 * 根据主键获取
	 * @param id
	 * @return
	 */
	T selectById(@Param("id")final K id);
	
	/**
	 * 获取满足条件的记录数
	 * @param query
	 * @return
	 */
	long countByQuery(Q query);
	
	/**
	 * 获取满足条件所有记录
	 * @param query
	 * @return
	 */
	List<T> selectList(Q query);
	
	/**
	 * 根据条件分页查询
	 * @param query
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<T> selectLimit(Q query, int pageNo, int pageSize);
	
	/**
	 * 查询指定的值是否有相同
	 * @param value
	 * @param id
	 * @return
	 */
	boolean isExist(@Param("id")final K id, String column, Object value);

}
