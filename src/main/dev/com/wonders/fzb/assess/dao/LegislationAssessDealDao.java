package com.wonders.fzb.assess.dao;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.assess.beans.LegislationAssessDeal;


/**
 * LegislationAssessDeal dao接口
 * @author scalffold created by lj
 */
public abstract interface LegislationAssessDealDao extends BaseDao {
	
	public void save(Object object);
	
	public String saveObj(Object object);
	
	public void saveOrUpdate(LegislationAssessDeal info);
	
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize)  throws FzbDaoException;
	
	public List<LegislationAssessDeal> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

	public List<LegislationAssessDeal> findByHQL(String hql);
}
