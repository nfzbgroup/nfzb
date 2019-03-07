package com.wonders.fzb.legislate.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.OpinionItem;
import com.wonders.fzb.legislate.dao.OpinionItemDao;

@Repository("OpinionItemDao")
public class OpinionItemDaoImpl extends BaseSupportDao implements OpinionItemDao{
	@Override
	public void save(Object object) {
		OpinionItem info = (OpinionItem) object;
		super.save(info);
	}

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"OpinionItem");
	}

	@Override
	public List<OpinionItem> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByList(condMap, sortMap, "OpinionItem");
	}

	@Override
	public void saveOrUpdate(OpinionItem info) {
		// TODO Auto-generated method stub
		super.saveOrUpdate(info);
	}

}
