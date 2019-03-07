package com.wonders.fzb.demo.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.demo.beans.ComposerDemo;
import com.wonders.fzb.demo.dao.ComposerDemoDao;


@Repository("composerDemoDao")
public class ComposerDemoDaoImpl extends BaseSupportDao implements ComposerDemoDao{
	@Override
	public void save(Object object) {
		ComposerDemo info = (ComposerDemo) object;
		super.save(info);
	}

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"ComposerDemo");
	}

	@Override
	public List<ComposerDemo> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByList(condMap, sortMap, "ComposerDemo");
	}

	@Override
	public void saveOrUpdate(ComposerDemo info) {
		// TODO Auto-generated method stub
		super.saveOrUpdate(info);
	}
}
