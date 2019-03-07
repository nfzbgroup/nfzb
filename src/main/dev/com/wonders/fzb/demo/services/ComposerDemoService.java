package com.wonders.fzb.demo.services;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.demo.beans.ComposerDemo;

/**
 * AuditMeeting service接口
 * @author scalffold created by lj
 */
public interface ComposerDemoService {
	/**
	 * 添加或修改实体对象
	 */
	public void saveOrUpdate(ComposerDemo info);
	
	/**
	 * 添加实体对象
	 */
	public void add(ComposerDemo info);
	
	/**
	 * 更新实体对象
	 */
	public void update(ComposerDemo info);
	
	/**
	 * 删除实体对象
	 */
	public void delete(ComposerDemo info);
	
	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	public ComposerDemo findById(String id);
	
	/**
	 * 根据Map中过滤条件、排序条件和分页参数进行分页查询.
	 * 
	 * @param condMap
	 *            过滤条件<propertyName,properyValue>
	 * @param sortMap
	 *            排序条件<propertyName,properyValue>
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示记录数.
	 * @return
	 */
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException;

	public List<ComposerDemo> findByList(Map<String, Object> condMap, Map<String, String> sortMap);
}
