package com.wonders.fzb.legislate.services;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.AssessmentReportAttach;

public interface AssessmentReportAttachService {
	/**
	 * 添加或修改实体对象
	 */
	public void saveOrUpdate(AssessmentReportAttach info);
	
	/**
	 * 添加实体对象
	 */
	public void add(AssessmentReportAttach info);
	
	/**
	 * 删除实体对象
	 */
	public void delete(AssessmentReportAttach info);
	
	/**
	 * 添加或修改实体对象
	 */
	public List<AssessmentReportAttach> findByList(Map<String,Object> condMap,Map<String,String> sortMap);
	
	/**
	 * 添加实体对象
	 * @throws FzbDaoException 
	 */
	public Page<AssessmentReportAttach> findByPage(Map<String,Object> condMap,Map<String,String> sortMap,int pageNo,int pageSize) throws FzbDaoException;
}
