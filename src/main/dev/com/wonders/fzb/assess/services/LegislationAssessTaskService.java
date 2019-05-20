package com.wonders.fzb.assess.services;

import com.wonders.fzb.assess.beans.LegislationAssessTask;
import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


/**
 * LegislationAssessTask service接口
 * @author scalffold created by lj
 */
public interface LegislationAssessTaskService{
	/**
	 * 添加或修改实体对象
	 */
	public void saveOrUpdate(LegislationAssessTask info);
	
	/**
	 * 添加实体对象
	 */
	public void add(LegislationAssessTask info);
	
	/**
	 * 添加实体对象并返回主键ID
	 */
	public String addObj(LegislationAssessTask info);
	
	/**
	 * 更新实体对象
	 */
	public void update(LegislationAssessTask info);
	
	/**
	 * 删除实体对象
	 */
	public void delete(LegislationAssessTask info);
	
	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	public LegislationAssessTask findById(String id);
	
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
	public List<LegislationAssessTask> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

	/**
	 * 根据HQL进行查询.
	 */
	public List<LegislationAssessTask> findByHQL(String hql);

	/**
	 * 立法后评估大节点流转
	 * @param request
	 * @param session
	 */
	void nextAssessProcess(HttpServletRequest request, HttpSession session);

	List<LegislationAssessTask> findByParentIdAndNodeId(String stParentId,String stNodeId);


}
