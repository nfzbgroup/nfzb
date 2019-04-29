package com.wonders.fzb.report.dao;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.report.beans.LegislationReportDeal;


/**
 * LegislationReportDeal dao接口
 * @author scalffold created by lj
 */
public abstract interface LegislationReportDealDao extends BaseDao {
	
	public void save(Object object);
	
	public String saveObj(Object object);
	
	public void saveOrUpdate(LegislationReportDeal info);
	
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize)  throws FzbDaoException;
	
	public List<LegislationReportDeal> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

	public List<LegislationReportDeal> findByHQL(String hql);
}
