package com.wonders.fzb.base.dao;

import java.io.Serializable;

/**
 * 持久化层基层接口
 * 
 * @author zsw
 */
public interface BaseDao {
	
	public void save(Object paramObject);

	public void update(Object paramObject);

	public void delete(Object paramObject);
	
	public void deleteById(Serializable paramSerializable);
	
	public Object load(Serializable paramSerializable);
}
