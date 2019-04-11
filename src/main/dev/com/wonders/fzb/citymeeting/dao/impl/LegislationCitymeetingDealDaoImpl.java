package com.wonders.fzb.citymeeting.dao.impl;


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
import com.wonders.fzb.citymeeting.beans.*;
import com.wonders.fzb.citymeeting.dao.*;

/**
 * LegislationCitymeetingDeal dao实现
 * 
 * @author scalffold created by lj
 */
@SuppressWarnings("unchecked")
@Repository("legislationCitymeetingDealDao")
public class LegislationCitymeetingDealDaoImpl extends BaseSupportDao implements LegislationCitymeetingDealDao {

	@Override
	public void save(Object object) {
		LegislationCitymeetingDeal info = (LegislationCitymeetingDeal) object;
		info.setStDealId(super.getId("SEQ_LEGISLATION_CITYMEET_DEAL", "CDL_", 16));
		super.save(info);
	}
	
	@Override
	public String saveObj(Object object) {
		LegislationCitymeetingDeal info = (LegislationCitymeetingDeal) object;
		String newId=super.getId("SEQ_LEGISLATION_CITYMEET_DEAL", "CDL_", 16);
		info.setStDealId(newId);
		super.save(info);
		return newId;
	}
	

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("configName", CommonConst.ORDER_ASC);
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"LegislationCitymeetingDeal");
	}

	@Override
	public List<LegislationCitymeetingDeal> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("appId", CommonConst.ORDER_ASC);
		return super.findByList(condMap, sortMap, "LegislationCitymeetingDeal");
	}

	@Override
	public void saveOrUpdate(LegislationCitymeetingDeal info) {
		super.saveOrUpdate(info);
	}
	
	@Override
	public List<LegislationCitymeetingDeal> findByHQL(String hql){
		Session session = getSession();
		Query query = session.createQuery(hql);
		List<LegislationCitymeetingDeal> result = query.list();
		session.flush();
		return result;
	}

}
