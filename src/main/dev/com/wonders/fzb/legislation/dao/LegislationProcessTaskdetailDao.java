package com.wonders.fzb.legislation.dao;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislation.beans.LegislationProcessTaskdetail;


/**
 * LegislationProcessTaskdetail dao接口
 * @author scalffold created by lj
 */
public abstract interface LegislationProcessTaskdetailDao extends BaseDao {
	
	public void save(Object object);
	
	public String saveObj(Object object);
	
	public void saveOrUpdate(LegislationProcessTaskdetail info);
	
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize)  throws FzbDaoException;
	
	public List<LegislationProcessTaskdetail> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

	public List<LegislationProcessTaskdetail> findByHQL(String hql);
}
