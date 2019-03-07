package com.wonders.fzb.legislate.dao;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.AssessmentReportAttach;

public abstract interface AssessmentReportAttachDao extends BaseDao {
	
	public void add(AssessmentReportAttach assessmentReportAttach);
	
	public void delete(AssessmentReportAttach assessmentReportAttach);
	
	public void saveOrUpdate(AssessmentReportAttach assessmentReportAttach);
	
	public AssessmentReportAttach findById(String attachId);
	
	public List<AssessmentReportAttach> findByList(Map<String,Object> condMap, Map<String,String> sortMap);
	
	public Page<AssessmentReportAttach> findByPage(Map<String,Object> condMap,Map<String,String> sortMap,int pageNo,int pageSize) throws FzbDaoException;
}
