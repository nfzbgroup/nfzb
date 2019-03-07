package com.wonders.fzb.legislate.services;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.AssessmentReportDis;

public interface AssessmentReportDisService {
	/**
	 * 添加或修改实体对象
	 */
	public void saveOrUpdate(AssessmentReportDis info);
	
	/**
	 * 添加实体对象
	 */
	public void add(AssessmentReportDis info);
	
	/**
	 * 删除实体对象
	 */
	public void delete(AssessmentReportDis info);
	
	/**
	 * 添加或修改实体对象
	 */
	public List<AssessmentReportDis> findByList(Map<String,Object> condMap,Map<String,String> sortMap);
	
	/**
	 * 添加或修改实体对象
	 */
	public List<AssessmentReportDis> findByAssessmentId(String assessmentId,String unitId);
	
	/**
	 * 添加实体对象
	 * @throws FzbDaoException 
	 */
	public Page<AssessmentReportDis> findByPage(Map<String,Object> condMap,Map<String,String> sortMap,int pageNo,int pageSize) throws FzbDaoException;
}
