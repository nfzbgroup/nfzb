package com.wonders.fzb.legislate.dao.impl;

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
import com.wonders.fzb.legislate.beans.Plan;
import com.wonders.fzb.legislate.beans.ModelFile;
import com.wonders.fzb.legislate.dao.PlanDao;

@Repository("planDao")
public class PlanDaoImpl extends BaseSupportDao implements PlanDao{
	@Override
	public void save(Object object) {
		Plan info = (Plan) object;
		super.save(info);
	}

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"Plan");
	}

	@Override
	public List<Plan> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByList(condMap, sortMap, "Plan");
	}

	@Override
	public void saveOrUpdate(Plan info) {
		// TODO Auto-generated method stub
		super.saveOrUpdate(info);
	}

}
