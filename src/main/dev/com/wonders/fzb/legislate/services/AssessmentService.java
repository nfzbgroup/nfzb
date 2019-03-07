package com.wonders.fzb.legislate.services;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.Assessment;

public interface AssessmentService {
	/**
	 * 添加或修改实体对象
	 */
	public void saveOrUpdate(Assessment info);
	
	/**
	 * 添加实体对象
	 */
	public void add(Assessment info);
	
	/**
	 * 删除实体对象
	 */
	public void delete(Assessment info);
	
	/**
	 * 添加或修改实体对象
	 */
	public List<Assessment> findByList(Map<String,Object> condMap,Map<String,String> sortMap);
	
	/**
	 * 添加实体对象
	 * @throws FzbDaoException 
	 */
	public Page<Assessment> findByPage(Map<String,Object> condMap,Map<String,String> sortMap,int pageNo,int pageSize) throws FzbDaoException;

	public Assessment findById(String assessmentId);
	
	/**
	 *  Word Export (poi-scratchpad-3.14.jar)
	 * 
	 * @param paramMap
	 *            所需替换模板的参数  <propertyName,properyValue>
	 * @param docName
	 *            所生成.doc文件Name (string)
	 * @param templateId
	 *            所需模板id (string)
	 * @return inputStream
	 */
	public InputStream exportWord(Map<String,String> paramMap,String docName,String templateId) throws Exception;
}
