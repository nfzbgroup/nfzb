package com.wonders.fzb.legislation.dao.impl;


import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislation.beans.LegislationExample;
import com.wonders.fzb.legislation.dao.LegislationExampleDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * LegislationExample dao实现
 * 
 * @author scalffold created by lj
 */
@SuppressWarnings("unchecked")
@Repository("legislationExampleDao")
public class LegislationExampleDaoImpl extends BaseSupportDao implements LegislationExampleDao {

	@Override
	public void save(Object object) {
		LegislationExample info = (LegislationExample) object;
		info.setStExampleId(super.getId("SEQ_LEGISLATION_EXAMPLE", "EMP_", 16));
		super.save(info);
	}
	
	@Override
	public String saveObj(Object object) {
		LegislationExample info = (LegislationExample) object;
		String newId=super.getId("SEQ_LEGISLATION_EXAMPLE", "EMP_", 16);
		info.setStExampleId(newId);
		super.save(info);
		return newId;
	}
	

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("configName", CommonConst.ORDER_ASC);
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"LegislationExample");
	}

	@Override
	public List<LegislationExample> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("appId", CommonConst.ORDER_ASC);
		return super.findByList(condMap, sortMap, "LegislationExample");
	}

	@Override
	public void saveOrUpdate(LegislationExample info) {
		super.saveOrUpdate(info);
	}
	
	@Override
	public List<LegislationExample> findByHQL(String hql){
		Session session = getSession();
		Query query = session.createQuery(hql);
		List<LegislationExample> result = query.list();
		session.flush();
		return result;
	}

}
