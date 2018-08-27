package com.isheng.service.common;

import java.io.Serializable;
import java.util.List;

import com.isheng.common.enums.ErrMsg;
import com.isheng.dao.common.BaseDao;
import com.isheng.model.common.exception.BizException;
import com.isheng.model.common.pojo.Page;
import com.isheng.model.common.pojo.Response;
import com.isheng.model.common.request.BaseQuery;

/**
 * 基础抽象service 17362686962
 *
 *
 * @author Administrator
 * @version $Id: AbstractBaseService.java 2018年8月19日 上午9:30:51 $
 */
public class AbstractBaseService<T, K extends Serializable, Q extends BaseQuery> implements BaseService<T, K, Q>{
	
	protected BaseDao<T, K, Q> baseDao;

	@Override
	public Response save(T entity) throws BizException {
		Response resp = new Response();
		if (null == entity) {
			return resp.setResponse(ErrMsg.PARAM_NULL);
		}
		long result = 0;
		try {
			result = baseDao.save(entity);
			if (result <= 0) {
				return resp.setResponse(ErrMsg.FAILED);
			}
		} catch (Exception e) {
			return resp.setResponse(ErrMsg.EXP_ADD);
		}
		return resp.setResponse(ErrMsg.SUCCESS, result);
	}

	@Override
	public Response batchDelByIds(K[] ids) throws BizException {
		Response resp = new Response();
		if (null == ids || ids.length <= 0)
			return resp.setResponse(ErrMsg.PARAM_NULL);
		
		int result = 0;
		try {
			result = baseDao.delByIds(ids);
			if (result <= 0)
				return resp.setResponse(ErrMsg.FAILED);
		} catch (Exception e) {
			return resp.setResponse(ErrMsg.EXP_DEL);
		}
		return resp.setCode(ErrMsg.SUCCESS.getCode()).setMsg("删除成功：" + result + "条,失败：" + (ids.length - result) + "条");
	}

	@Override
	public Response update(T entity) throws BizException {
		Response resp = new Response();
		if (null == entity)
			return resp.setResponse(ErrMsg.PARAM_NULL);
		int result = 0;
		try {
			result = baseDao.update(entity);
			if (result <= 0)
				return resp.setResponse(ErrMsg.FAILED);
		} catch (Exception e) {
			return resp.setResponse(ErrMsg.EXP_UP);
		}
		return resp.setResponse(ErrMsg.SUCCESS);
	}

	@Override
	public Response getById(K id) throws BizException {
		Response resp = new Response();
		T result = null;
		try {
			result = baseDao.getById(id);
		} catch (Exception e) {
			return resp.setResponse(ErrMsg.EXP_QUERY);
		}
		return resp.setResponse(ErrMsg.SUCCESS).setData(result);
	}

	@Override
	public Response listByParam(Q query) throws BizException {
		Response resp = new Response();
		List<T> result = null;
		try {
			result = baseDao.listByQuery(query);
		} catch (Exception e) {
			return resp.setResponse(ErrMsg.EXP_QUERY);
		}
		return resp.setResponse(ErrMsg.SUCCESS, result);
	}

	@Override
	public Response countByParam(Q query) throws BizException {
		Response resp = new Response();
		long result = 0;
		try {
			result = baseDao.countByQuery(query);
		} catch (Exception e) {
			return resp.setResponse(ErrMsg.EXP_QUERY);
		}
		return resp.setResponse(ErrMsg.SUCCESS, result);
	}

	@Override
	public Response paging(Q query, int pageNo, int pageSize) throws BizException {
		Response resp = new Response();
		if (pageNo <= 0) 
			pageNo = 1;
		
		List<T> list = null;
		long count = 0;
		try {
			count = baseDao.countByQuery(query);
			if (count >= 1) {
				list = baseDao.paging(query, pageNo, pageSize);
			}
		} catch (Exception e) {
			return resp.setResponse(ErrMsg.EXP_QUERY);
		}
		
		Page<T> result = new Page<T>(list, count, pageNo, pageSize);
		
		return resp.setResponse(ErrMsg.SUCCESS, result);
	}

	

}
