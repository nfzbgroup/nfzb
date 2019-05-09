package com.wonders.fzb.assess.dao.impl;


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
import com.wonders.fzb.assess.beans.*;
import com.wonders.fzb.assess.dao.*;

/**
 * LegislationAssessDeal dao实现
 * 
 * @author scalffold created by lj
 */
@SuppressWarnings("unchecked")
@Repository("legislationAssessDealDao")
public class LegislationAssessDealDaoImpl extends BaseSupportDao implements LegislationAssessDealDao {

	@Override
	public void save(Object object) {
		LegislationAssessDeal info = (LegislationAssessDeal) object;
		info.setStDealId(super.getId("SEQ_LEGISLATION_ASSESS_DEAL", "ADL_", 16));
		super.save(info);
	}
	
	@Override
	public String saveObj(Object object) {
		LegislationAssessDeal info = (LegislationAssessDeal) object;
		String newId=super.getId("SEQ_LEGISLATION_ASSESS_DEAL", "ADL_", 16);
		info.setStDealId(newId);
		super.save(info);
		return newId;
	}
	

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("configName", CommonConst.ORDER_ASC);
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"LegislationAssessDeal");
	}

	@Override
	public List<LegislationAssessDeal> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("appId", CommonConst.ORDER_ASC);
		return super.findByList(condMap, sortMap, "LegislationAssessDeal");
	}

	@Override
	public void saveOrUpdate(LegislationAssessDeal info) {
		super.saveOrUpdate(info);
	}
	
	@Override
	public List<LegislationAssessDeal> findByHQL(String hql){
		Session session = getSession();
		Query query = session.createQuery(hql);
		List<LegislationAssessDeal> result = query.list();
		session.flush();
		return result;
	}

}
