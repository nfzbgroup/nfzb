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
 * LegislationAssessItem dao实现
 * 
 * @author scalffold created by lj
 */
@SuppressWarnings("unchecked")
@Repository("legislationAssessItemDao")
public class LegislationAssessItemDaoImpl extends BaseSupportDao implements LegislationAssessItemDao {

	@Override
	public void save(Object object) {
		LegislationAssessItem info = (LegislationAssessItem) object;
		info.setStItemId(super.getId("SEQ_LEGISLATION_ASSESS_ITEM", "AIT_", 16));
		super.save(info);
	}
	
	@Override
	public String saveObj(Object object) {
		LegislationAssessItem info = (LegislationAssessItem) object;
		String newId=super.getId("SEQ_LEGISLATION_ASSESS_ITEM", "AIT_", 16);
		info.setStItemId(newId);
		super.save(info);
		return newId;
	}
	

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("configName", CommonConst.ORDER_ASC);
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"LegislationAssessItem");
	}

	@Override
	public List<LegislationAssessItem> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("appId", CommonConst.ORDER_ASC);
		return super.findByList(condMap, sortMap, "LegislationAssessItem");
	}

	@Override
	public void saveOrUpdate(LegislationAssessItem info) {
		super.saveOrUpdate(info);
	}
	
	@Override
	public List<LegislationAssessItem> findByHQL(String hql){
		Session session = getSession();
		Query query = session.createQuery(hql);
		List<LegislationAssessItem> result = query.list();
		session.flush();
		return result;
	}

}
