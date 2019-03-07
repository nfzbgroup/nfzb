package com.wonders.fzb.legislation.dao;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislation.beans.LegislationProcessDoc;

import java.util.List;
import java.util.Map;


/**
 * LegislationProcessDoc dao接口
 * @author scalffold created by lj
 */
public abstract interface LegislationProcessDocDao extends BaseDao {
	
	public void save(Object object);
	
	public String saveObj(Object object);
	
	public void saveOrUpdate(LegislationProcessDoc info);
	
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize)  throws FzbDaoException;
	
	public List<LegislationProcessDoc> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

	public List<LegislationProcessDoc> findByHQL(String hql);

	void executeSqlUpdate(String sql);
}
