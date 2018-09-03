package com.isheng.common.base;

import java.util.List;
import com.isheng.common.exception.BizException;
import com.isheng.common.model.Page;

/**
 * 基础抽象服务类
 *
 *
 * @author Administrator
 * @version $Id: BaseAbstractService.java 2018年9月1日 下午6:38:28 $
 */
public abstract class AbstractBaseService<T, Q extends BaseQuery> implements BaseService<T, Q> {
	
	/**
	 * 获取dao
	 * 
	 * @return
	 */
	protected abstract BaseDao<T, Q> getDao();

	@Override
	public String add(T entity) throws BizException {
		return this.getDao().save(entity);
	}

	@Override
	public int deleteById(String id) throws BizException {
		return this.getDao().deleteById(id);
	}

	@Override
	public int update(T entity) throws BizException {
		return this.getDao().update(entity);
	}

	@Override
	public T getById(String id) throws BizException {
		return this.getDao().getById(id);
	}

	@Override
	public long getCount(Q query) throws BizException {
		return this.getDao().countByParam(query);
	}

	@Override
	public List<T> getList(Q query) throws BizException {
		return this.getDao().listByParam(query);
	}

	@Override
	public Page<T> getPaging(Q query, int pageNo, int pageSize) throws BizException {
		if (pageNo <= 0) {
			pageNo = 1;
		}
		long count = 0;
		List<T> list = null;
		try {
			count = this.getDao().countByParam(query);
			if (count >= 1) {
				list = this.getDao().limitByParam(query, pageNo, pageSize);
			}
			return new Page<T>(list, count, pageNo, pageSize);
		} catch (Exception e) {
			throw new BizException(e);
		}
		
	}

}
