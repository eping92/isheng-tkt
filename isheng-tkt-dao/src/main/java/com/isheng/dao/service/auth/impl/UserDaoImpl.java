package com.isheng.dao.service.auth.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import com.isheng.common.enums.ErrMsg;
import com.isheng.dao.core.auth.UserMapper;
import com.isheng.dao.service.auth.UserDao;
import com.isheng.dao.service.seq.SeqDao;
import com.isheng.model.auth.entity.User;
import com.isheng.model.auth.request.UserQuery;
import com.isheng.model.common.exception.BizException;

public class UserDaoImpl implements UserDao {

	private static final String SEQ_KEY = "SEQ_USER";

	@Resource
	private UserMapper userMapper;
	@Resource
	private SeqDao seqDao;

	@Override
	public long save(User entity) throws BizException {
		long result = 0;
		try {
			if (entity.getId() <= 0)
				entity.setId(seqDao.nextVal(SEQ_KEY));
			entity.setCreateTime(new Date());
			result = userMapper.insert(entity);
		} catch (Exception e) {
			throw new BizException(ErrMsg.EXP_ADD, e);
		}
		return result;
	}

	@Override
	public int delByIds(Long[] ids) throws BizException {
		int result = 0;
		if (ids != null && ids.length > 0) {
			try {
				result = userMapper.delByIds(ids);
			} catch (Exception e) {
				throw new BizException(ErrMsg.EXP_DEL, e);
			}
		}
		return result;
	}

	@Override
	public int update(User entity) throws BizException {
		int result = 0;
		try {
			result = userMapper.update(entity);
		} catch (Exception e) {
			throw new BizException(ErrMsg.EXP_UP, e);
		}
		return result;
	}

	@Override
	public User getById(Long id) throws BizException {
		User user = null;
		try {
			user = userMapper.selectById(id);
		} catch (Exception e) {
			throw new BizException(ErrMsg.EXP_QUERY, e);
		}
		return user;
	}

	@Override
	public long countByQuery(UserQuery query) throws BizException {
		long result = 0;
		try {
			result = userMapper.countByQuery(query);
		} catch (Exception e) {
			throw new BizException(ErrMsg.EXP_QUERY, e);
		}
		return result;
	}

	@Override
	public List<User> listByQuery(UserQuery query) throws BizException {
		List<User> list;
		try {
			list = userMapper.selectList(query);
		} catch (Exception e) {
			throw new BizException(ErrMsg.EXP_QUERY, e);
		}
		return list;
	}

	@Override
	public List<User> paging(UserQuery query, int pageNo, int pageSize) throws BizException {
		List<User> list = null;
		try {
			list = userMapper.selectLimit(query, pageNo, pageSize);
		} catch (Exception e) {
			throw new BizException(ErrMsg.EXP_QUERY, e);
		}
		return list;
	}

}
