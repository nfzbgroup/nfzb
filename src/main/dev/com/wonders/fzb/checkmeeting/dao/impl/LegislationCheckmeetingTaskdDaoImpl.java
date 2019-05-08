package com.wonders.fzb.checkmeeting.dao.impl;


import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeetingTaskd;
import com.wonders.fzb.checkmeeting.dao.LegislationCheckmeetingTaskdDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * LegislationCheckmeetingTaskd dao实现
 * 
 * @author scalffold created by lj
 */
@SuppressWarnings("unchecked")
@Repository("legislationCheckmeetingTaskdDao")
public class LegislationCheckmeetingTaskdDaoImpl extends BaseSupportDao implements LegislationCheckmeetingTaskdDao {

	@Override
	public void save(Object object) {
		LegislationCheckmeetingTaskd info = (LegislationCheckmeetingTaskd) object;
		info.setStTaskdetailId(super.getId("SEQ_LEGISLATION_CHECKMEET_TSKD", "TDE_", 16));
		super.save(info);
	}
	
	@Override
	public String saveObj(Object object) {
		LegislationCheckmeetingTaskd info = (LegislationCheckmeetingTaskd) object;
		String newId=super.getId("SEQ_LEGISLATION_CHECKMEET_TASKD", "TDE_", 16);
		info.setStTaskdetailId(newId);
		super.save(info);
		return newId;
	}
	

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("configName", CommonConst.ORDER_ASC);
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"LegislationCheckmeetingTaskd");
	}

	@Override
	public List<LegislationCheckmeetingTaskd> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("appId", CommonConst.ORDER_ASC);
		return super.findByList(condMap, sortMap, "LegislationCheckmeetingTaskd");
	}

	@Override
	public void saveOrUpdate(LegislationCheckmeetingTaskd info) {
		super.saveOrUpdate(info);
	}
	
	@Override
	public List<LegislationCheckmeetingTaskd> findByHQL(String hql){
		Session session = getSession();
		Query query = session.createQuery(hql);
		List<LegislationCheckmeetingTaskd> result = query.list();
		session.flush();
		return result;
	}

}
