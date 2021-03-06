package com.wonders.fzb.legislate.services;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.CompanySolicit;

/**
 * CompanySolicit service接口
 * @author scalffold created by lj
 */
public interface CompanySolicitService {
	/**
	 * 添加或修改实体对象
	 */
	public void saveOrUpdate(CompanySolicit info);
	
	/**
	 * 添加实体对象
	 */
	public void add(CompanySolicit info);
	
	/**
	 * 更新实体对象
	 */
	public void update(CompanySolicit info);
	
	/**
	 * 删除实体对象
	 */
	public void delete(CompanySolicit info);
	
	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	public CompanySolicit findById(String id);
	
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

	public List<CompanySolicit> findByList(Map<String, Object> condMap, Map<String, String> sortMap);
}
