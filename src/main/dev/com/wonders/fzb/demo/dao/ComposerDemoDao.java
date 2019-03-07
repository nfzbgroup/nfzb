package com.wonders.fzb.demo.dao;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.demo.beans.ComposerDemo;


public interface ComposerDemoDao extends BaseDao{
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize)  throws FzbDaoException;
	
	public List<ComposerDemo> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

	public void saveOrUpdate(ComposerDemo info);
}
