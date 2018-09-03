package com.isheng.common.base;

import java.util.List;

import com.isheng.common.exception.BizException;

public abstract class AbstractBaseDao<T, Q extends BaseQuery> implements BaseDao<T, Q> {
	
	/**
	 * 不同的dao中传递不同的mapper
	 * 
	 * @return
	 */
	protected abstract BaseMapper<T, Q> getMapper();

	@Override
	public String save(T entity) throws BizException {
		return this.getMapper().insert(entity);
	}

	@Override
	public int deleteById(String id) throws BizException {
		return this.getMapper().deleteById(id);
	}

	@Override
	public int update(T entity) throws BizException {
		return this.getMapper().update(entity);
	}

	@Override
	public T getById(String id) throws BizException {
		return this.getMapper().selectById(id);
	}

	@Override
	public long countByParam(Q query) throws BizException {
		return this.getMapper().selectCount(query);
	}

	@Override
	public List<T> listByParam(Q query) throws BizException {
		return this.getMapper().selectList(query);
	}

	@Override
	public List<T> limitByParam(Q query, int pageNo, int pageSize) throws BizException {
		return this.getMapper().selectLimit(query, pageNo, pageSize);
	}

	@Override
	public boolean isExist(String id, String column, Object value) throws BizException {
		return this.getMapper().isExist(id, column, value);
	}

}
