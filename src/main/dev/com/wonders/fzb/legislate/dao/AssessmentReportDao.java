package com.wonders.fzb.legislate.dao;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.AssessmentReport;

public abstract interface AssessmentReportDao extends BaseDao {
	
	public void delete(AssessmentReport assessmentReport);
	
	public void add(AssessmentReport assessmentReport);
	
	public void saveOrUpdate(AssessmentReport assessmentReport);
	
	public AssessmentReport findById(String assessmentId);
	
	public List<AssessmentReport> findByList(Map<String,Object> condMap, Map<String,String> sortMap);
	
	public Page<AssessmentReport> findByPage(Map<String,Object> condMap,Map<String,String> sortMap,int pageNo,int pageSize) throws FzbDaoException;
}
