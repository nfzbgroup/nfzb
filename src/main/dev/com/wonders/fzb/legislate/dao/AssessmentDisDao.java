package com.wonders.fzb.legislate.dao;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.AssessmentDis;

public abstract interface AssessmentDisDao extends BaseDao {
	
	public void delete(AssessmentDis assessmentDis);
	
	public void add(AssessmentDis assessmentDis);
	
	public void saveOrUpdate(AssessmentDis assessmentDis);
	
	public AssessmentDis findById(String assessmentDisId);
	
	public List<AssessmentDis> findByList(Map<String,Object> condMap, Map<String,String> sortMap);
	
	public List<AssessmentDis> findByAssessmentId(String assessmentId, String unitId);
	
	public Page<AssessmentDis> findByPage(Map<String,Object> condMap,Map<String,String> sortMap,int pageNo,int pageSize) throws FzbDaoException;
}

