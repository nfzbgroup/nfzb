package com.wonders.fzb.legislation.dao.impl;


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
import com.wonders.fzb.legislation.beans.*;
import com.wonders.fzb.legislation.dao.*;

/**
 * LegislationProcessDeal dao实现
 * 
 * @author scalffold created by lj
 */
@SuppressWarnings("unchecked")
@Repository("legislationProcessDealDao")
public class LegislationProcessDealDaoImpl extends BaseSupportDao implements LegislationProcessDealDao {

	@Override
	public void save(Object object) {
		LegislationProcessDeal info = (LegislationProcessDeal) object;
		info.setStDealId(super.getId("SEQ_LEGISLATION_PROCESS_DEAL", "DEL_", 16));
		super.save(info);
	}
	
	@Override
	public String saveObj(Object object) {
		LegislationProcessDeal info = (LegislationProcessDeal) object;
		String newId=super.getId("SEQ_LEGISLATION_PROCESS_DEAL", "DEL_", 16);
		info.setStDealId(newId);
		super.save(info);
		return newId;
	}
	

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("configName", CommonConst.ORDER_ASC);
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"LegislationProcessDeal");
	}

	@Override
	public List<LegislationProcessDeal> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("appId", CommonConst.ORDER_ASC);
		return super.findByList(condMap, sortMap, "LegislationProcessDeal");
	}

	@Override
	public void saveOrUpdate(LegislationProcessDeal info) {
		super.saveOrUpdate(info);
	}
	
	@Override
	public List<LegislationProcessDeal> findByHQL(String hql){
		Session session = getSession();
		Query query = session.createQuery(hql);
		List<LegislationProcessDeal> result = query.list();
		session.flush();
		return result;
	}

}
