package com.wonders.fzb.legislate.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.Fbd;
import com.wonders.fzb.legislate.dao.FbdDao;

@Repository("fbdDao")
public class FbdDaoImpl extends BaseSupportDao implements FbdDao{
	@Override
	public void save(Object object) {
		Fbd info = (Fbd) object;
		super.save(info);
	}

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"Fbd");
	}

	@Override
	public List<Fbd> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByList(condMap, sortMap, "Fbd");
	}

	@Override
	public void saveOrUpdate(Fbd info) {
		// TODO Auto-generated method stub
		super.saveOrUpdate(info);
	}
}
