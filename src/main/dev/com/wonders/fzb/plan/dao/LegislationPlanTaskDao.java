package com.wonders.fzb.plan.dao;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.plan.beans.LegislationPlanTask;

import java.util.List;
import java.util.Map;


/**
 * LegislationPlanTask dao接口
 * @author scalffold created by lj
 */
public abstract interface LegislationPlanTaskDao extends BaseDao {
	
	public void save(Object object);
	
	public String saveObj(Object object);
	
	public void saveOrUpdate(LegislationPlanTask info);
	
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize)  throws FzbDaoException;
	
	public List<LegislationPlanTask> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

	public List<LegislationPlanTask> findByHQL(String hql);

	public Page findWithEnableByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize)  throws FzbDaoException;
}
