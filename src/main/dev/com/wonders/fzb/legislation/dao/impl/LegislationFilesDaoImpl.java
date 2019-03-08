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
 * LegislationFiles dao实现
 * 
 * @author scalffold created by lj
 */
@SuppressWarnings("unchecked")
@Repository("legislationFilesDao")
public class LegislationFilesDaoImpl extends BaseSupportDao implements LegislationFilesDao {

	@Override
	public void save(Object object) {
		LegislationFiles info = (LegislationFiles) object;
		info.setStFileId(super.getId("SEQ_LEGISLATION_FILES", "FIL_", 16));
		super.save(info);
	}
	
	@Override
	public String saveObj(Object object) {
		LegislationFiles info = (LegislationFiles) object;
		String newId=super.getId("SEQ_LEGISLATION_FILES", "FIL_", 16);
		info.setStFileId(newId);
		super.save(info);
		return newId;
	}
	

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("configName", CommonConst.ORDER_ASC);
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"LegislationFiles");
	}

	@Override
	public List<LegislationFiles> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("appId", CommonConst.ORDER_ASC);
		return super.findByList(condMap, sortMap, "LegislationFiles");
	}

	@Override
	public void saveOrUpdate(LegislationFiles info) {
		super.saveOrUpdate(info);
	}
	
	@Override
	public List<LegislationFiles> findByHQL(String hql){
		Session session = getSession();
		Query query = session.createQuery(hql);
		List<LegislationFiles> result = query.list();
		session.flush();
		return result;
	}

}
