package com.wonders.fzb.legislate.dao;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.Assessment;

public abstract interface AssessmentDao extends BaseDao {
	
	public void delete(Assessment assessment);
	
	public void add(Assessment assessment);
	
	public void saveOrUpdate(Assessment assessment);
	
	public Assessment findById(String assessmentId);
	
	public List<Assessment> findByList(Map<String,Object> condMap, Map<String,String> sortMap);
	
	public Page<Assessment> findByPage(Map<String,Object> condMap,Map<String,String> sortMap,int pageNo,int pageSize) throws FzbDaoException;
}

