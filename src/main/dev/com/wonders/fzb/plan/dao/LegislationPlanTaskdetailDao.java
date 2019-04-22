package com.wonders.fzb.plan.dao;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.plan.beans.LegislationPlanTaskdetail;


/**
 * LegislationPlanTaskdetail dao接口
 * @author scalffold created by lj
 */
public abstract interface LegislationPlanTaskdetailDao extends BaseDao {
	
	public void save(Object object);
	
	public String saveObj(Object object);
	
	public void saveOrUpdate(LegislationPlanTaskdetail info);
	
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize)  throws FzbDaoException;
	
	public List<LegislationPlanTaskdetail> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

	public List<LegislationPlanTaskdetail> findByHQL(String hql);
}
