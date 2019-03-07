package com.wonders.fzb.legislate.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.OpinionDetail;
import com.wonders.fzb.legislate.dao.OpinionDetailDao;

@Repository("opinionDetailDao")
public class OpinionDetailDaoImpl extends BaseSupportDao implements OpinionDetailDao{
	@Override
	public void save(Object object) {
		OpinionDetail info = (OpinionDetail) object;
		super.save(info);
	}

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"OpinionDetail");
	}

	@Override
	public List<OpinionDetail> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByList(condMap, sortMap, "OpinionDetail");
	}

	@Override
	public void saveOrUpdate(OpinionDetail info) {
		// TODO Auto-generated method stub
		super.saveOrUpdate(info);
	}

}
