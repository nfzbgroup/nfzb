package com.wonders.fzb.legislate.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.ExchangePivot;
import com.wonders.fzb.legislate.dao.ExchangePivotDao;

@Repository("ExchangePivotDao")
public class ExchangePivotDaoImpl extends BaseSupportDao implements ExchangePivotDao{
	@Override
	public void save(Object object) {
		ExchangePivot info = (ExchangePivot) object;
		super.save(info);
	}

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"ExchangePivot");
	}

	@Override
	public List<ExchangePivot> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByList(condMap, sortMap, "ExchangePivot");
	}

	@Override
	public void saveOrUpdate(ExchangePivot info) {
		// TODO Auto-generated method stub
		super.saveOrUpdate(info);
	}
}
