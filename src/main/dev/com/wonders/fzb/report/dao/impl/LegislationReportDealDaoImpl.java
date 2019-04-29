package com.wonders.fzb.report.dao.impl;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.report.beans.*;
import com.wonders.fzb.report.dao.*;

/**
 * LegislationReportDeal dao实现
 * 
 * @author scalffold created by lj
 */
@SuppressWarnings("unchecked")
@Repository("legislationReportDealDao")
public class LegislationReportDealDaoImpl extends BaseSupportDao implements LegislationReportDealDao {

	@Override
	public void save(Object object) {
		LegislationReportDeal info = (LegislationReportDeal) object;
		info.setStDealId(super.getId("SEQ_LEGISLATION_REPORT_DEAL", "RDL_", 16));
		super.save(info);
	}
	
	@Override
	public String saveObj(Object object) {
		LegislationReportDeal info = (LegislationReportDeal) object;
		String newId=super.getId("SEQ_LEGISLATION_REPORT_DEAL", "RDL_", 16);
		info.setStDealId(newId);
		super.save(info);
		return newId;
	}
	

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("configName", CommonConst.ORDER_ASC);
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"LegislationReportDeal");
	}

	@Override
	public List<LegislationReportDeal> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("appId", CommonConst.ORDER_ASC);
		return super.findByList(condMap, sortMap, "LegislationReportDeal");
	}

	@Override
	public void saveOrUpdate(LegislationReportDeal info) {
		super.saveOrUpdate(info);
	}
	
	@Override
	public List<LegislationReportDeal> findByHQL(String hql){
		Session session = getSession();
		Query query = session.createQuery(hql);
		List<LegislationReportDeal> result = query.list();
		session.flush();
		return result;
	}

}
