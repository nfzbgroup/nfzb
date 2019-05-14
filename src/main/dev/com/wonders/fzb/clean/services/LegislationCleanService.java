package com.wonders.fzb.clean.services;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.clean.beans.LegislationClean;


/**
 * LegislationClean service接口
 * @author scalffold created by lj
 */
public interface LegislationCleanService{
	/**
	 * 添加或修改实体对象
	 */
	public void saveOrUpdate(LegislationClean info);
	
	/**
	 * 添加实体对象
	 */
	public void add(LegislationClean info);
	
	/**
	 * 添加实体对象并返回主键ID
	 */
	public String addObj(LegislationClean info);
	
	/**
	 * 更新实体对象
	 */
	public void update(LegislationClean info);
	
	/**
	 * 删除实体对象
	 */
	public void delete(LegislationClean info);
	
	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	public LegislationClean findById(String id);
	
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

	/**
	 * 根据Map中过滤条件、排序条件进行查询.
	 * 
	 * @param condMap
	 *            过滤条件<propertyName,properyValue>
	 * @param sortMap
	 *            排序条件<propertyName,properyValue>
	 * @return
	 */
	public List<LegislationClean> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

	/**
	 * 根据HQL进行查询.
	 */
	public List<LegislationClean> findByHQL(String hql);


}
