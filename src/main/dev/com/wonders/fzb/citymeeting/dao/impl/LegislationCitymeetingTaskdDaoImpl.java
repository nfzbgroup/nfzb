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
 * LegislationCitymeetingTaskd dao实现
 * 
 * @author scalffold created by lj
 */
@SuppressWarnings("unchecked")
@Repository("legislationCitymeetingTaskdDao")
public class LegislationCitymeetingTaskdDaoImpl extends BaseSupportDao implements LegislationCitymeetingTaskdDao {

	@Override
	public void save(Object object) {
		LegislationCitymeetingTaskd info = (LegislationCitymeetingTaskd) object;
		info.setStTaskdetailId(super.getId("SEQ_LEGISLATION_CITYMEET_TASKD", "TDE_", 16));
		super.save(info);
	}
	
	@Override
	public String saveObj(Object object) {
		LegislationCitymeetingTaskd info = (LegislationCitymeetingTaskd) object;
		String newId=super.getId("SEQ_LEGISLATION_CITYMEET_TASKD", "TDE_", 16);
		info.setStTaskdetailId(newId);
		super.save(info);
		return newId;
	}
	

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("configName", CommonConst.ORDER_ASC);
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"LegislationCitymeetingTaskd");
	}

	@Override
	public List<LegislationCitymeetingTaskd> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("appId", CommonConst.ORDER_ASC);
		return super.findByList(condMap, sortMap, "LegislationCitymeetingTaskd");
	}

	@Override
	public void saveOrUpdate(LegislationCitymeetingTaskd info) {
		super.saveOrUpdate(info);
	}
	
	@Override
	public List<LegislationCitymeetingTaskd> findByHQL(String hql){
		Session session = getSession();
		Query query = session.createQuery(hql);
		List<LegislationCitymeetingTaskd> result = query.list();
		session.flush();
		return result;
	}

}
