package com.wonders.fzb.legislate.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.Draft;
import com.wonders.fzb.legislate.dao.DraftDao;

@Repository("draftDao")
public class DraftDaoImpl extends BaseSupportDao implements DraftDao{
	@Override
	public void save(Object object) {
		Draft info = (Draft) object;
		super.save(info);
	}

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"Draft");
	}

	@Override
	public List<Draft> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		sortMap.put("createTime", CommonConst.ORDER_DESC);
		return super.findByList(condMap, sortMap, "Draft");
	}

	@Override
	public void saveOrUpdate(Draft info) {
		// TODO Auto-generated method stub
		super.saveOrUpdate(info);
	}
}
