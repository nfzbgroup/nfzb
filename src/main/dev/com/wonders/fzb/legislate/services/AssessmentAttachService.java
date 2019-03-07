package com.wonders.fzb.legislate.services;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.AssessmentAttach;

public interface AssessmentAttachService {
	/**
	 * 添加或修改实体对象
	 */
	public void saveOrUpdate(AssessmentAttach info);
	
	/**
	 * 添加实体对象
	 */
	public void add(AssessmentAttach info);
	
	/**
	 * 删除实体对象
	 */
	public void delete(AssessmentAttach info);
	
	/**
	 * 添加或修改实体对象
	 */
	public List<AssessmentAttach> findByList(Map<String,Object> condMap,Map<String,String> sortMap);
	
	/**
	 * 添加实体对象
	 * @throws FzbDaoException 
	 */
	public Page<AssessmentAttach> findByPage(Map<String,Object> condMap,Map<String,String> sortMap,int pageNo,int pageSize) throws FzbDaoException;
}
