package com.wonders.fzb.legislate.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.PlanSummary;
import com.wonders.fzb.legislate.dao.PlanSummaryDao;

@Repository("planSummaryDao")
public class PlanSummaryDaoImpl extends BaseSupportDao implements PlanSummaryDao{
	@Override
	public void save(Object object) {
		PlanSummary info = (PlanSummary) object;
		super.save(info);
	}

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"PlanSummary");
	}

	@Override
	public List<PlanSummary> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByList(condMap, sortMap, "PlanSummary");
	}

	@Override
	public void saveOrUpdate(PlanSummary info) {
		super.saveOrUpdate(info);
	}
}
