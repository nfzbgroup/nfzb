package com.wonders.fzb.legislate.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.PublicOpinion;
import com.wonders.fzb.legislate.dao.PublicOpinionDao;

@Repository("publicOpinionDao")
public class PublicOpinionDaoImpl extends BaseSupportDao implements PublicOpinionDao {

	@Override
	public void save(Object object) {
		PublicOpinion info = (PublicOpinion) object;
		super.save(info);
	}

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"PublicOpinion");
	}

	@Override
	public List<PublicOpinion> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByList(condMap, sortMap, "PublicOpinion");
	}

	@Override
	public void saveOrUpdate(PublicOpinion info) {
		// TODO Auto-generated method stub
		super.saveOrUpdate(info);
	}


}
