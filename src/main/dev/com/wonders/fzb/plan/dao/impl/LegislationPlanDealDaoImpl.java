package com.wonders.fzb.plan.dao.impl;


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
import com.wonders.fzb.plan.beans.*;
import com.wonders.fzb.plan.dao.*;

/**
 * LegislationPlanDeal dao实现
 * 
 * @author scalffold created by lj
 */
@SuppressWarnings("unchecked")
@Repository("legislationPlanDealDao")
public class LegislationPlanDealDaoImpl extends BaseSupportDao implements LegislationPlanDealDao {

	@Override
	public void save(Object object) {
		LegislationPlanDeal info = (LegislationPlanDeal) object;
		info.setStDealId(super.getId("SEQ_LEGISLATION_PLAN_DEAL", "PDL_", 16));
		super.save(info);
	}
	
	@Override
	public String saveObj(Object object) {
		LegislationPlanDeal info = (LegislationPlanDeal) object;
		String newId=super.getId("SEQ_LEGISLATION_PLAN_DEAL", "PDL_", 16);
		info.setStDealId(newId);
		super.save(info);
		return newId;
	}
	

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("configName", CommonConst.ORDER_ASC);
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"LegislationPlanDeal");
	}

	@Override
	public List<LegislationPlanDeal> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("appId", CommonConst.ORDER_ASC);
		return super.findByList(condMap, sortMap, "LegislationPlanDeal");
	}

	@Override
	public void saveOrUpdate(LegislationPlanDeal info) {
		super.saveOrUpdate(info);
	}
	
	@Override
	public List<LegislationPlanDeal> findByHQL(String hql){
		Session session = getSession();
		Query query = session.createQuery(hql);
		List<LegislationPlanDeal> result = query.list();
		session.flush();
		return result;
	}

}
