package com.wonders.fzb.legislate.dao;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.AssessmentAttach;

public abstract interface AssessmentAttachDao extends BaseDao {
	
	public void add(AssessmentAttach assessmentAttach);
	
	public void delete(AssessmentAttach assessmentAttach);
	
	public void saveOrUpdate(AssessmentAttach assessmentAttach);
	
	public AssessmentAttach findById(String attachId);
	
	public List<AssessmentAttach> findByList(Map<String,Object> condMap, Map<String,String> sortMap);
	
	public Page<AssessmentAttach> findByPage(Map<String,Object> condMap,Map<String,String> sortMap,int pageNo,int pageSize) throws FzbDaoException;
}

