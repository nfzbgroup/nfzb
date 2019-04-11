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
 * LegislationPlan dao实现
 * 
 * @author scalffold created by lj
 */
@SuppressWarnings("unchecked")
@Repository("legislationPlanDao")
public class LegislationPlanDaoImpl extends BaseSupportDao implements LegislationPlanDao {

	@Override
	public void save(Object object) {
		LegislationPlan info = (LegislationPlan) object;
		info.setStPlanId(super.getId("SEQ_LEGISLATION_PLAN", "PLA_", 16));
		super.save(info);
	}
	
	@Override
	public String saveObj(Object object) {
		LegislationPlan info = (LegislationPlan) object;
		String newId=super.getId("SEQ_LEGISLATION_PLAN", "PLA_", 16);
		info.setStPlanId(newId);
		super.save(info);
		return newId;
	}
	

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("configName", CommonConst.ORDER_ASC);
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"LegislationPlan");
	}

	@Override
	public List<LegislationPlan> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("appId", CommonConst.ORDER_ASC);
		return super.findByList(condMap, sortMap, "LegislationPlan");
	}

	@Override
	public void saveOrUpdate(LegislationPlan info) {
		super.saveOrUpdate(info);
	}
	
	@Override
	public List<LegislationPlan> findByHQL(String hql){
		Session session = getSession();
		Query query = session.createQuery(hql);
		List<LegislationPlan> result = query.list();
		session.flush();
		return result;
	}

}
