package com.wonders.fzb.checkmeeting.dao.impl;


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
import com.wonders.fzb.checkmeeting.beans.*;
import com.wonders.fzb.checkmeeting.dao.*;

/**
 * LegislationCheckmeetingDeal dao实现
 * 
 * @author scalffold created by lj
 */
@SuppressWarnings("unchecked")
@Repository("legislationCheckmeetingDealDao")
public class LegislationCheckmeetingDealDaoImpl extends BaseSupportDao implements LegislationCheckmeetingDealDao {

	@Override
	public void save(Object object) {
		LegislationCheckmeetingDeal info = (LegislationCheckmeetingDeal) object;
		info.setStDealId(super.getId("SEQ_LEGISLATION_CHECKMEET_DEAL", "KDL_", 16));
		super.save(info);
	}
	
	@Override
	public String saveObj(Object object) {
		LegislationCheckmeetingDeal info = (LegislationCheckmeetingDeal) object;
		String newId=super.getId("SEQ_LEGISLATION_CHECKMEET_DEAL", "KDL_", 16);
		info.setStDealId(newId);
		super.save(info);
		return newId;
	}
	

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("configName", CommonConst.ORDER_ASC);
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"LegislationCheckmeetingDeal");
	}

	@Override
	public List<LegislationCheckmeetingDeal> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("appId", CommonConst.ORDER_ASC);
		return super.findByList(condMap, sortMap, "LegislationCheckmeetingDeal");
	}

	@Override
	public void saveOrUpdate(LegislationCheckmeetingDeal info) {
		super.saveOrUpdate(info);
	}
	
	@Override
	public List<LegislationCheckmeetingDeal> findByHQL(String hql){
		Session session = getSession();
		Query query = session.createQuery(hql);
		List<LegislationCheckmeetingDeal> result = query.list();
		session.flush();
		return result;
	}

}
