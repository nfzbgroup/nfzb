package com.wonders.fzb.clean.dao.impl;


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
import com.wonders.fzb.clean.beans.*;
import com.wonders.fzb.clean.dao.*;

/**
 * LegislationCleanTask dao实现
 * 
 * @author scalffold created by lj
 */
@SuppressWarnings("unchecked")
@Repository("legislationCleanTaskDao")
public class LegislationCleanTaskDaoImpl extends BaseSupportDao implements LegislationCleanTaskDao {

	@Override
	public void save(Object object) {
		LegislationCleanTask info = (LegislationCleanTask) object;
		info.setStTaskId(super.getId("SEQ_LEGISLATION_CLEAN_TASK", "CTK_", 16));
		super.save(info);
	}
	
	@Override
	public String saveObj(Object object) {
		LegislationCleanTask info = (LegislationCleanTask) object;
		String newId=super.getId("SEQ_LEGISLATION_CLEAN_TASK", "CTK_", 16);
		info.setStTaskId(newId);
		super.save(info);
		return newId;
	}
	

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("configName", CommonConst.ORDER_ASC);
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"LegislationCleanTask");
	}

	@Override
	public List<LegislationCleanTask> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("appId", CommonConst.ORDER_ASC);
		return super.findByList(condMap, sortMap, "LegislationCleanTask");
	}

	@Override
	public void saveOrUpdate(LegislationCleanTask info) {
		super.saveOrUpdate(info);
	}
	
	@Override
	public List<LegislationCleanTask> findByHQL(String hql){
		Session session = getSession();
		Query query = session.createQuery(hql);
		List<LegislationCleanTask> result = query.list();
		session.flush();
		return result;
	}

}
