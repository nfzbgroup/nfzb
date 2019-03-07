package com.wonders.fzb.simpleflow.dao.impl;


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
import com.wonders.fzb.simpleflow.beans.*;
import com.wonders.fzb.simpleflow.dao.*;

/**
 * WegovSimpleNode dao实现
 * 
 * @author scalffold created by lj
 */
@SuppressWarnings("unchecked")
@Repository("wegovSimpleNodeDao")
public class WegovSimpleNodeDaoImpl extends BaseSupportDao implements WegovSimpleNodeDao {

	@Override
	public void save(Object object) {
		WegovSimpleNode info = (WegovSimpleNode) object;
		info.setStNodeId(super.getId("SEQ_FZB_SIMPLENODE", "NOD_", 16));
		super.save(info);
	}
	
	@Override
	public String saveObj(Object object) {
		WegovSimpleNode info = (WegovSimpleNode) object;
		String newId=super.getId("SEQ_FZB_SIMPLENODE", "NOD_", 16);
		info.setStNodeId(newId);
		super.save(info);
		return newId;
	}
	

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("configName", CommonConst.ORDER_ASC);
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"WegovSimpleNode");
	}

	@Override
	public List<WegovSimpleNode> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("appId", CommonConst.ORDER_ASC);
		return super.findByList(condMap, sortMap, "WegovSimpleNode");
	}

	@Override
	public void saveOrUpdate(WegovSimpleNode info) {
		super.saveOrUpdate(info);
	}
	
	@Override
	public List<WegovSimpleNode> findByHQL(String hql){
		Session session = getSession();
		Query query = session.createQuery(hql);
		List<WegovSimpleNode> result = query.list();
		session.flush();
		return result;
	}

}
