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
 * LegislationCheckmeeting dao实现
 * 
 * @author scalffold created by lj
 */
@SuppressWarnings("unchecked")
@Repository("legislationCheckmeetingDao")
public class LegislationCheckmeetingDaoImpl extends BaseSupportDao implements LegislationCheckmeetingDao {

	@Override
	public void save(Object object) {
		LegislationCheckmeeting info = (LegislationCheckmeeting) object;
		info.setStMeetingId(super.getId("SEQ_LEGISLATION_CHECKMEETING", "CKM_", 16));
		super.save(info);
	}
	
	@Override
	public String saveObj(Object object) {
		LegislationCheckmeeting info = (LegislationCheckmeeting) object;
		String newId=super.getId("SEQ_LEGISLATION_CHECKMEETING", "CKM_", 16);
		info.setStMeetingId(newId);
		super.save(info);
		return newId;
	}
	

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("configName", CommonConst.ORDER_ASC);
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"LegislationCheckmeeting");
	}

	@Override
	public List<LegislationCheckmeeting> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("appId", CommonConst.ORDER_ASC);
		return super.findByList(condMap, sortMap, "LegislationCheckmeeting");
	}

	@Override
	public void saveOrUpdate(LegislationCheckmeeting info) {
		super.saveOrUpdate(info);
	}
	
	@Override
	public List<LegislationCheckmeeting> findByHQL(String hql){
		Session session = getSession();
		Query query = session.createQuery(hql);
		List<LegislationCheckmeeting> result = query.list();
		session.flush();
		return result;
	}

}
