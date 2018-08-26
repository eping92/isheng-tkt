package com.isheng.dao.service.auth.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.isheng.common.enums.ErrMsg;
import com.isheng.dao.core.auth.MenuMapper;
import com.isheng.dao.service.auth.MenuDao;
import com.isheng.dao.service.seq.SeqDao;
import com.isheng.model.auth.entity.Menu;
import com.isheng.model.auth.request.MenuQuery;
import com.isheng.model.common.exception.BizException;

@Component
@Service(interfaceClass = MenuDao.class)
public class MenuDaoImpl implements MenuDao {

	private Logger log = LoggerFactory.getLogger(getClass());

	private static final String SEQ_KEY = "SEQ_MENU";

	@Resource
	private MenuMapper menuMapper;
	@Resource
	private SeqDao seqDao;

	@Override
	@Transactional
	public long save(Menu entity) throws BizException {
		long result;
		try {
			entity.setId(seqDao.nextVal(SEQ_KEY));
			entity.setCreateTime(new Date());
			result = menuMapper.insert(entity);
		} catch (Exception e) {
			log.error(ErrMsg.EXP_ADD.getText());
			throw new BizException(ErrMsg.EXP_ADD, e);
		}
		return result > 0 ? entity.getId() : 0;
	}

	@Override
	public int delByIds(Long[] ids) throws BizException {
		int result = 0;
		if (null != ids && ids.length >= 1) {
			try {
				result = menuMapper.delByIds(ids);
			} catch (Exception e) {
				throw new BizException(ErrMsg.EXP_DEL, e);
			}
		}
		return result;
	}
	
	@Override
	@Transactional
	public int update(Menu entity) throws BizException {
		int result = 0;
		try {
			entity.setUpdateTime(new Date());
			result = menuMapper.update(entity);
		} catch (Exception e) {
			log.error(ErrMsg.EXP_UP.getText() + ",Menu={}", entity);
			throw new BizException(ErrMsg.EXP_UP, e);
		}
		return result;
	}

	@Override
	public Menu getById(Long id) throws BizException {
		Menu menu = null;
		try {
			menu = menuMapper.selectById(id);
		} catch (Exception e) {
			log.error(ErrMsg.EXP_QUERY.getText() + ",MenuId={}", id);
			throw new BizException(ErrMsg.EXP_QUERY, e);
		}
		return menu;
	}

	@Override
	public long countByQuery(MenuQuery query) throws BizException {
		long count = 0;
		try {
			count = menuMapper.countByQuery(query);
		} catch (Exception e) {
			log.error(ErrMsg.EXP_QUERY.getText() + ",MenuQuery={}", query);
			throw new BizException(ErrMsg.EXP_QUERY, e);
		}
		return count;
	}

	@Override
	public List<Menu> listByQuery(MenuQuery query) throws BizException {
		List<Menu> list = null;
		try {
			list = menuMapper.selectList(query);
		} catch (Exception e) {
			log.error(ErrMsg.EXP_QUERY.getText() + ",MenuQuery={}", query);
			throw new BizException(ErrMsg.EXP_QUERY, e);
		}
		return list;
	}

	@Override
	public List<Menu> paging(MenuQuery query, int pageNo, int pageSize) throws BizException {
		List<Menu> list = null;
		if (pageNo <= 0)
			pageNo = 1;
		try {
			list = menuMapper.selectLimit(query, pageNo, pageSize);
		} catch (Exception e) {
			throw new BizException(ErrMsg.EXP_QUERY, e);
		}
		return list;
	}



	

}
