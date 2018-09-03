package com.isheng.dao.service.seq.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.isheng.common.enums.ErrMsg;
import com.isheng.common.exception.BizException;
import com.isheng.dao.mapper.seq.SeqMapper;
import com.isheng.dao.service.seq.SeqDao;
import com.isheng.model.seq.constant.SeqConstant;
import com.isheng.model.seq.entity.Seq;

@Service("seqDao")
public class SeqDaoImpl implements SeqDao {

	@Resource
	private SeqMapper seqMapper;

	@Override
	public int save(Seq entity) throws BizException {
		try {
			entity.setUpdateTime(new Date());
			return seqMapper.insert(entity);
		} catch (Exception e) {
			throw new BizException(ErrMsg.EXP_ADD.getCode(), ErrMsg.EXP_ADD.getText());
		}
	}

	@Override
	public Seq getById(String id) throws BizException {
		try {
			return seqMapper.getById(id);
		} catch (Exception e) {
			throw new BizException(ErrMsg.EXP_QUERY.getCode(), ErrMsg.EXP_QUERY.getText());
		}
	}

	@Override
	public long nextVal(String key) throws BizException {
		Seq entity = this.getById(key);
		try {
			if (null != entity) {
				entity.setSeq(entity.getSeq() + 1);
				this.update(entity);
			} else {
				entity = new Seq(key, SeqConstant.MIN_ID);
				this.save(entity);
			}
			return entity.getSeq();
		} catch (Exception e) {
			throw new BizException(ErrMsg.EXP_SYS.getCode(), ErrMsg.EXP_SYS.getText());
		}
	}

	@Override
	public String getFormatNo(String key, String noType) throws BizException {
		String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
		long seq = this.nextVal(key);
		if (SeqConstant.MAX_ID <= seq) {
			Seq entity = this.getById(key);
			try {
				entity.setSeq(entity.getSeq() + 1);
				this.update(entity);
			} catch (Exception e) {
				throw new BizException(ErrMsg.EXP_UP.getCode(), ErrMsg.EXP_UP.getText());
			}
		}
		String seqNo = String.valueOf(seq);
		String value = "0000000".substring(seqNo.length(),  7) + seqNo;
		String no = String.format("%s%s%s%s", today, value.subSequence(0, 2), noType, value.subSequence(2, 7));
		return no;
	}

	@Override
	public int update(Seq entity) throws BizException {
		try {
			entity.setUpdateTime(new Date());
			return seqMapper.update(entity);
		} catch (Exception e) {
			throw new BizException(ErrMsg.EXP_UP.getCode(), ErrMsg.EXP_UP.getText());
		}
	}

}
