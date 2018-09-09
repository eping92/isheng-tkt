package com.isheng.common.base;

import java.util.Date;
import java.util.List;
import com.isheng.common.exception.BizException;
import com.isheng.common.idgen.IdGenerate;
import com.isheng.common.model.Page;
import com.isheng.common.util.ReflexUtil;

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
		String id = IdGenerate.nextId();
		ReflexUtil.setFieldValue(entity, "id", id);//通过反射设置id
		ReflexUtil.setFieldValue(entity, "createTime", new Date());//通过反射设置createTime
		int result = this.getDao().save(entity);
		return result > 0 ? id : "";
	}

	@Override
	public int deleteById(String id) throws BizException {
		return this.getDao().deleteById(id);
	}

	@Override
	public int update(T entity) throws BizException {
		ReflexUtil.setFieldValue(entity, "updateTime", new Date());
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

	@Override
	public boolean isExist(String id, String column, Object value) throws BizException {
		return this.getDao().isExist(id, column, value);
	}
	
	

}
