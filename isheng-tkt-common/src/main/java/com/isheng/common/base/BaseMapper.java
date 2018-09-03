package com.isheng.common.base;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.isheng.common.base.BaseQuery;

/**
 * 基础映射
 * @author Administrator
 *
 */
@Mapper
@Repository
public interface BaseMapper <T, Q extends BaseQuery>{
	
	/**
	 * 新增插入
	 * @param entity
	 * @return
	 */
	String insert(T entity);
	
	/**
	 * 按主键删除
	 * @param id
	 * @return
	 */
	int deleteById(String id);
	
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
	T selectById(String id);
	
	/**
	 * 获取满足条件的记录数
	 * @param query
	 * @return
	 */
	long selectCount(Q query);
	
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
	boolean isExist(String id, String column, Object value);

}
