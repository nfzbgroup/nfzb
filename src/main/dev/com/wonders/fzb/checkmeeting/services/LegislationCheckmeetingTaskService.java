package com.wonders.fzb.checkmeeting.services;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeeting;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeetingTask;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.legislation.beans.LegislationProcessDoc;
import com.wonders.fzb.legislation.beans.LegislationProcessTask;

/**
 * LegislationCheckmeetingTask service接口
 * 
 * @author scalffold created by lj
 */
public interface LegislationCheckmeetingTaskService {
	/**
	 * 添加或修改实体对象
	 */
	public void saveOrUpdate(LegislationCheckmeetingTask info);

	/**
	 * 添加实体对象
	 */
	public void add(LegislationCheckmeetingTask info);

	/**
	 * 添加实体对象并返回主键ID
	 */
	public String addObj(LegislationCheckmeetingTask info);

	/**
	 * 更新实体对象
	 */
	public void update(LegislationCheckmeetingTask info);

	/**
	 * 删除实体对象
	 */
	public void delete(LegislationCheckmeetingTask info);

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	public LegislationCheckmeetingTask findById(String id);

	/**
	 * 根据Map中过滤条件、排序条件和分页参数进行分页查询.
	 * 
	 * @param condMap  过滤条件<propertyName,properyValue>
	 * @param sortMap  排序条件<propertyName,properyValue>
	 * @param pageNo   当前页码
	 * @param pageSize 每页显示记录数.
	 * @return
	 */
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException;

	/**
	 * 根据Map中过滤条件、排序条件进行查询.
	 * 
	 * @param condMap 过滤条件<propertyName,properyValue>
	 * @param sortMap 排序条件<propertyName,properyValue>
	 * @return
	 */
	public List<LegislationCheckmeetingTask> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

	/**
	 * 根据HQL进行查询.
	 */
	public List<LegislationCheckmeetingTask> findByHQL(String hql);

	/**
	 * 根据stNodeID找到任务列表.
	 */
	public List<Map> findTaskListByNodeId(String stNodeId, String stUserId, String UnitId, String roleId);

	Page<LegislationCheckmeeting> findTaskDocListByNodeId(String sql, int pageNo, int pageSize) throws ParseException;

	/**
	 * 查询审核会议
	 * 
	 * @param sql
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Page<LegislationCheckmeeting> findCheckMeetingByNodeId(String sql, int pageNo, int pageSize) throws ParseException;

	/**
	 * TASK表分页
	 * 
	 * @param sql
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Page<LegislationCheckmeetingTask> findTaskByNodeId(String sql, int pageNo, int pageSize) throws ParseException;

	/**
	 * 大节点扭转
	 * 
	 * @param stDocId
	 * @param stNodeId
	 */
	public void nextCheckmeeting(String stDocId, String stNodeId, HttpSession session);

	public List<LegislationCheckmeetingTask> findTaskByDocIdAndNodeId(String stMeetingId, String stNodeId);

	/**
	 * 退回（公共）
	 * 
	 * @param stMeetingId
	 * @param stNodeId
	 * @param userRoleId
	 * @param userRole
	 * @param currentPerson
	 */
	void returnCheckmeeting(String stMeetingId, String stNodeId, String userRoleId, String userRole, UserInfo currentPerson);

	/**
	 * 次节点流转（公共）
	 * 
	 * @param stMeetingId
	 * @param stNodeId
	 * @param userRoleId
	 * @param userRole
	 * @param currentPerson
	 */
	void nextChildCheckmeeting(String stMeetingId, String stNodeId, String userRoleId, String userRole, UserInfo currentPerson);

	/**
	 * 保存
	 * 
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 * @author sy
	 */
	String saveAuditMeeting(HttpServletRequest request, HttpSession session) throws Exception;
}
