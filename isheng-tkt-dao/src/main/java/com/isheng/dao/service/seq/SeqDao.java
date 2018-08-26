package com.isheng.dao.service.seq;


import com.isheng.model.common.exception.BizException;
import com.isheng.model.seq.entity.Seq;

public interface SeqDao {
	
	/**
	 * 保存
	 * 
	 * @param entity
	 * @return
	 * @throws BizException
	 */
	public int save(Seq entity) throws BizException;
	
	/**
	 * 查询
	 * 
	 * @param id
	 * @return
	 * @throws BizException
	 */
	public Seq getById(String id) throws BizException;
	
	/**
	 * 获取下一ID
	 * 
	 * @param key
	 * @return
	 * @throws BizException
	 */
	public long nextVal(String key) throws BizException;
	
	/**
	 * 获取定制格式的ID
	 * 
	 * @param key
	 * @param noType
	 * @return
	 * @throws BizException
	 */
	public String getFormatNo(String key, String noType) throws BizException;
	
	/**
	 * 更新，返回更新成功的条数
	 * 
	 * @param entity
	 * @return
	 * @throws BizException
	 */
	public int update(Seq entity) throws BizException;

}
