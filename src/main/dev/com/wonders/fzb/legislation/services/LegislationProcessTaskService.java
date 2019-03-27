package com.wonders.fzb.legislation.services;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.legislation.beans.LegislationProcessDoc;
import com.wonders.fzb.legislation.beans.LegislationProcessTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;
import java.util.Map;


/**
 * LegislationProcessTask service接口
 * @author scalffold created by lj
 */
public interface LegislationProcessTaskService{
	/**
	 * 添加或修改实体对象
	 */
	public void saveOrUpdate(LegislationProcessTask info);
	
	/**
	 * 添加实体对象
	 */
	public void add(LegislationProcessTask info);
	
	/**
	 * 添加实体对象并返回主键ID
	 */
	public String addObj(LegislationProcessTask info);
	
	/**
	 * 更新实体对象
	 */
	public void update(LegislationProcessTask info);
	
	/**
	 * 删除实体对象
	 */
	public void delete(LegislationProcessTask info);
	
	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	public LegislationProcessTask findById(String id);
	
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
	public List<LegislationProcessTask> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

	/**
	 * 根据HQL进行查询.
	 */
	public List<LegislationProcessTask> findByHQL(String hql);


	/**
	 * 根据stNodeID找到任务列表.
	 */
	public List<Map> findTaskListByNodeId(String stNodeId,String stUserId,String UnitId,String roleId);
	
	Page<LegislationProcessDoc> findTaskDocListByNodeId(String sql, int pageNo,
			int pageSize) throws ParseException;

	/****************************************************************************************************/

	/**
	 * 大节点扭转
	 * @param stDocId
	 * @param stNodeId
	 */
	public void nextProcess(String stDocId, String stNodeId, HttpSession session);

	/**
	 * 退回（公共）
	 * @param stDocId
	 * @param stNodeId
	 * @param userRoleId
	 * @param userRole
	 * @param currentPerson
	 */
	void returnProcess(String stDocId, String stNodeId, String userRoleId, String userRole, UserInfo currentPerson);

	/**
	 * 次节点流转（公共）
	 * @param stDocId
	 * @param stNodeId
	 * @param userRoleId
	 * @param userRole
	 * @param currentPerson
	 */
	void nextChildProcess(String stDocId, String stNodeId, String userRoleId, String userRole, UserInfo currentPerson);

	/**
	 * TASK表分页
	 * @param sql
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Page<LegislationProcessTask> findTaskByNodeId(String sql, int pageNo,
												  int pageSize) throws ParseException;

	/**
	 * 查询审核会议
	 * @param sql
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Page<LegislationProcessDoc> findCheckMeetingByNodeId(String sql, int pageNo,
														 int pageSize) throws ParseException;

	/**
	 *
	 * @param request
	 * @param currentPerson
	 * @param userRoleId
	 * @param userRole
	 */
    void sendUnit(HttpServletRequest request, UserInfo currentPerson,String userRoleId,String userRole);

	void addOpinion(HttpServletRequest request, UserInfo currentPerson);

	void saveTaskCheck(HttpServletRequest request,UserInfo currentPerson,String userRoleId,String userRole) throws ParseException;

	void dealFinish(HttpServletRequest request, HttpSession session);

	void saveOnlineSummary(HttpServletRequest request);
}
