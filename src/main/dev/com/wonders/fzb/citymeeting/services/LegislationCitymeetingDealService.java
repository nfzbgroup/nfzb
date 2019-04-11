package com.wonders.fzb.citymeeting.services;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.citymeeting.beans.LegislationCitymeetingDeal;


/**
 * LegislationCitymeetingDeal service接口
 * @author scalffold created by lj
 */
public interface LegislationCitymeetingDealService{
	/**
	 * 添加或修改实体对象
	 */
	public void saveOrUpdate(LegislationCitymeetingDeal info);
	
	/**
	 * 添加实体对象
	 */
	public void add(LegislationCitymeetingDeal info);
	
	/**
	 * 添加实体对象并返回主键ID
	 */
	public String addObj(LegislationCitymeetingDeal info);
	
	/**
	 * 更新实体对象
	 */
	public void update(LegislationCitymeetingDeal info);
	
	/**
	 * 删除实体对象
	 */
	public void delete(LegislationCitymeetingDeal info);
	
	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	public LegislationCitymeetingDeal findById(String id);
	
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
	public List<LegislationCitymeetingDeal> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

	/**
	 * 根据HQL进行查询.
	 */
	public List<LegislationCitymeetingDeal> findByHQL(String hql);


}
