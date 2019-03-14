package com.wonders.fzb.legislation.services;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.legislation.beans.LegislationProcessDoc;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * LegislationProcessDoc service接口
 * @author scalffold created by lj
 */
public interface LegislationProcessDocService{
	/**
	 * 添加或修改实体对象
	 */
	public void saveOrUpdate(LegislationProcessDoc info);
	
	/**
	 * 添加实体对象
	 */
	public void add(LegislationProcessDoc info);
	
	/**
	 * 添加实体对象并返回主键ID
	 */
	public String addObj(LegislationProcessDoc info);
	
	/**
	 * 更新实体对象
	 */
	public void update(LegislationProcessDoc info);
	
	/**
	 * 删除实体对象
	 */
	public void delete(LegislationProcessDoc info);
	
	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	public LegislationProcessDoc findById(String id);
	
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
	public List<LegislationProcessDoc> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

	/**
	 * 根据HQL进行查询.
	 */
	public List<LegislationProcessDoc> findByHQL(String hql);


	public void executeSqlUpdate(String sql);

	/****************************************************************************************************/


	/**
	 * 新增修改草案
	 * @param request
	 * @param currentPerson
	 */
    void editLegislationProcessDoc(HttpServletRequest request, UserInfo currentPerson,String userRoleId,String userRole);

	/**
	 * 提交分办
	 * @param request
	 */
	void draft_dist_info(HttpServletRequest request);

    void saveLegislation(HttpServletRequest request, UserInfo currentPerson, String userRoleId, String userRole,String stNodeId,String stNodeName) throws Exception;
}
