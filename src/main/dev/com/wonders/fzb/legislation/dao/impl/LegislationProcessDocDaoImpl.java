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
 * LegislationProcessDoc dao实现
 * 
 * @author scalffold created by lj
 */
@SuppressWarnings("unchecked")
@Repository("legislationProcessDocDao")
public class LegislationProcessDocDaoImpl extends BaseSupportDao implements LegislationProcessDocDao {

	@Override
	public void save(Object object) {
		LegislationProcessDoc info = (LegislationProcessDoc) object;
		info.setStDocId(super.getId("SEQ_LEGISLATION_PROCESS_DOC", "DFT_", 16));
		super.save(info);
	}
	
	@Override
	public String saveObj(Object object) {
		LegislationProcessDoc info = (LegislationProcessDoc) object;
		String newId=super.getId("SEQ_LEGISLATION_PROCESS_DOC", "DFT_", 16);
		info.setStDocId(newId);
		super.save(info);
		return newId;
	}
	

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("configName", CommonConst.ORDER_ASC);
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"LegislationProcessDoc");
	}

	@Override
	public List<LegislationProcessDoc> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("appId", CommonConst.ORDER_ASC);
		return super.findByList(condMap, sortMap, "LegislationProcessDoc");
	}

	@Override
	public void saveOrUpdate(LegislationProcessDoc info) {
		super.saveOrUpdate(info);
	}
	
	@Override
	public List<LegislationProcessDoc> findByHQL(String hql){
		Session session = getSession();
		Query query = session.createQuery(hql);
		List<LegislationProcessDoc> result = query.list();
		session.flush();
		return result;
	}

}
