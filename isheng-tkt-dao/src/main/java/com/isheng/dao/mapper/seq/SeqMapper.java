package com.isheng.dao.mapper.seq;

import org.apache.ibatis.annotations.Mapper;
import com.isheng.common.exception.BizException;
import com.isheng.model.seq.entity.Seq;

@Mapper
public interface SeqMapper {
	
	/**
	 * 存储序列
	 * 
	 * @param seq
	 * @return
	 * @throws BizException
	 */
	public int insert(Seq seq) throws BizException;
	
	/**
	 * 根据ID查询
	 * 
	 * @param id
	 * @return
	 * @throws BizException
	 */
	public Seq getById(String id) throws BizException;
	
	/**
	 * 更新
	 * 
	 * @param seq
	 * @return
	 * @throws BizException
	 */
	public int update(Seq seq) throws BizException; 
}
