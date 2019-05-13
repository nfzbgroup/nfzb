package com.wonders.fzb.checkmeeting.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeeting;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeetingDeal;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeetingItem;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeetingTask;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeetingTaskd;
import com.wonders.fzb.checkmeeting.dao.LegislationCheckmeetingTaskDao;
import com.wonders.fzb.checkmeeting.services.LegislationCheckmeetingDealService;
import com.wonders.fzb.checkmeeting.services.LegislationCheckmeetingItemService;
import com.wonders.fzb.checkmeeting.services.LegislationCheckmeetingService;
import com.wonders.fzb.checkmeeting.services.LegislationCheckmeetingTaskService;
import com.wonders.fzb.checkmeeting.services.LegislationCheckmeetingTaskdService;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.framework.services.UserInfoService;
import com.wonders.fzb.legislation.beans.LegislationProcessDoc;
import com.wonders.fzb.legislation.beans.LegislationProcessTask;
import com.wonders.fzb.legislation.beans.LegislationSendNotice;
import com.wonders.fzb.legislation.services.LegislationExampleService;
import com.wonders.fzb.legislation.services.LegislationFilesService;
import com.wonders.fzb.legislation.services.LegislationProcessDocService;
import com.wonders.fzb.legislation.services.LegislationProcessTaskService;
import com.wonders.fzb.legislation.services.LegislationSendNoticeService;
import com.wonders.fzb.plan.beans.LegislationPlan;
import com.wonders.fzb.plan.beans.LegislationPlanTask;
import com.wonders.fzb.plan.services.LegislationPlanService;
import com.wonders.fzb.plan.services.LegislationPlanTaskService;
import com.wonders.fzb.simpleflow.beans.WegovSimpleNode;
import com.wonders.fzb.simpleflow.services.WegovSimpleNodeService;

import dm.jdbc.util.StringUtil;

/**
 * LegislationCheckmeetingTask service实现
 * 
 * @author scalffold created by lj
 */

@Service("legislationCheckmeetingTaskService")
@Transactional
public class LegislationCheckmeetingTaskServiceImpl implements LegislationCheckmeetingTaskService {

	@Autowired
	private LegislationCheckmeetingTaskDao legislationCheckmeetingTaskDao;

	@Autowired
	private WegovSimpleNodeService wegovSimpleNodeService;

	@Autowired
	private LegislationCheckmeetingService legislationCheckmeetingService;

	@Autowired
	@Qualifier("legislationProcessDocService")
	private LegislationProcessDocService legislationProcessDocService;

	@Autowired
	@Qualifier("legislationProcessTaskService")
	private LegislationProcessTaskService legislationProcessTaskService;

	@Autowired
	@Qualifier("legislationFilesService")
	private LegislationFilesService legislationFilesService;

	@Autowired
	@Qualifier("legislationExampleService")
	private LegislationExampleService legislationExampleService;
	@Autowired
	@Qualifier("legislationCheckmeetingDealService")
	private LegislationCheckmeetingDealService legislationCheckmeetingDealService;
	
	@Autowired
	@Qualifier("legislationPlanTaskService")
	private LegislationPlanTaskService legislationPlanTaskService;
	
	@Autowired
	@Qualifier("legislationPlanService")
	private LegislationPlanService legislationPlanService;
	
	@Autowired
	@Qualifier("legislationCheckmeetingItemService")
	private LegislationCheckmeetingItemService legislationCheckmeetingItemService;

	@Autowired
	@Qualifier("legislationCheckmeetingTaskdService")
	private LegislationCheckmeetingTaskdService legislationCheckmeetingTaskdService;

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private LegislationSendNoticeService legislationSendNoticeService;
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationCheckmeetingTask info) {
		legislationCheckmeetingTaskDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationCheckmeetingTask info) {
		return legislationCheckmeetingTaskDao.saveObj(info);
	}

	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationCheckmeetingTask info) {
		legislationCheckmeetingTaskDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationCheckmeetingTask info) {
		legislationCheckmeetingTaskDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationCheckmeetingTask findById(String id) {
		return (LegislationCheckmeetingTask) legislationCheckmeetingTaskDao.load(id);
	}

	/**
	 * 根据Map中过滤条件、排序条件和分页参数进行分页查询.
	 * 
	 * @param condMap  过滤条件<propertyName,properyValue>
	 * @param sortMap  排序条件<propertyName,properyValue>
	 * @param pageNo   当前页码
	 * @param pageSize 每页显示记录数.
	 * @return
	 * @throws FzbDaoException
	 */
	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException {
		return legislationCheckmeetingTaskDao.findByPage(condMap, sortMap, pageNo, pageSize);
	}

	/**
	 * 根据Map中过滤条件、排序条件进行查询.
	 * 
	 * @param condMap 过滤条件<propertyName,properyValue>
	 * @param sortMap 排序条件<propertyName,properyValue>
	 * @return
	 */
	@Override
	public List<LegislationCheckmeetingTask> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationCheckmeetingTaskDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationCheckmeetingTask info) {
		legislationCheckmeetingTaskDao.saveOrUpdate(info);
	}

	@Override
	public List<LegislationCheckmeetingTask> findByHQL(String hql) {
		List<LegislationCheckmeetingTask> legislationCheckmeetingTaskList = legislationCheckmeetingTaskDao.findByHQL(hql);
		return legislationCheckmeetingTaskList;
	}

	@Override
	public List<Map> findTaskListByNodeId(String stNodeId, String stUserId, String unitId, String roleId) {
		return legislationCheckmeetingTaskDao.findTaskListByNodeId(stNodeId, stUserId, unitId, roleId);
	}

	@Override
	public Page<LegislationCheckmeeting> findTaskDocListByNodeId(String sql, int pageNo, int pageSize) throws ParseException {
		return legislationCheckmeetingTaskDao.findTaskDocListByNodeId(sql, pageNo, pageSize);
	}

	/**
	 * 查询审核会议
	 *
	 * @param sql
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Override
	public Page<LegislationCheckmeeting> findCheckMeetingByNodeId(String sql, int pageNo, int pageSize) throws ParseException {
		return legislationCheckmeetingTaskDao.findCheckMeetingByNodeId(sql, pageNo, pageSize);
	}

	@Override
	public Page<LegislationCheckmeetingTask> findTaskByNodeId(String sql, int pageNo, int pageSize) throws ParseException {
		return legislationCheckmeetingTaskDao.findTaskByNodeId(sql, pageNo, pageSize);
	}

	@Override
	public void nextCheckmeeting(String stDocId, String stNodeId, HttpSession session) {
		List<LegislationCheckmeetingTask> list = findTaskByDocIdAndNodeId(stDocId, stNodeId);
		for (LegislationCheckmeetingTask legislationCheckmeetingTask : list) {
			if ("NOD_0000000105".equals(stNodeId)) {
				if ("false".equals(legislationCheckmeetingTask.getStActive())) {
					legislationCheckmeetingTask.setStActive(null);
					legislationCheckmeetingTask.setStTaskStatus("TODO");
					legislationCheckmeetingTask.setDtOpenDate(new Date());
					legislationCheckmeetingTaskDao.saveOrUpdate(legislationCheckmeetingTask);
					return;
				}
			}

			legislationCheckmeetingTask.setStTaskStatus("DONE");
			if ("NOD_0000000120".equals(stNodeId) || "NOD_0000000160".equals(stNodeId)) {
				legislationCheckmeetingTask.setDtDealDate(new Date());
			}
			legislationCheckmeetingTaskDao.update(legislationCheckmeetingTask);

			LegislationCheckmeetingTask nextLegislationCheckmeetingTask = new LegislationCheckmeetingTask();
			nextLegislationCheckmeetingTask.setStMeetingId(legislationCheckmeetingTask.getStMeetingId());
			nextLegislationCheckmeetingTask.setStFlowId(legislationCheckmeetingTask.getStFlowId());
			List<WegovSimpleNode> nodeList = wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where 1=1 and t.stNodeId ='" + stNodeId + "'");

			if (!"END".equals(nodeList.get(0).getStNextNode())) {

				nextLegislationCheckmeetingTask.setStNodeId(nodeList.get(0).getStNextNode());
				nextLegislationCheckmeetingTask.setStNodeName(wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where 1=1 and t.stNodeId ='" + nodeList.get(0).getStNextNode() + "'").get(0).getStNodeName());
				nextLegislationCheckmeetingTask.setStTaskStatus("TODO");
				if ("NOD_0000000150".equals(stNodeId) || "NOD_0000000104".equals(stNodeId) || "NOD_0000000105".equals(stNodeId) || "NOD_0000000106".equals(stNodeId)) {
					nextLegislationCheckmeetingTask.setStTeamId(legislationCheckmeetingTask.getStTeamId());
					nextLegislationCheckmeetingTask.setStTeamName(legislationCheckmeetingTask.getStTeamName());

				} else {
					nextLegislationCheckmeetingTask.setStTeamId("U_3_1");
				}
				if (stNodeId.equals("NOD_0000000120") || stNodeId.equals("NOD_0000000160")) {
					nextLegislationCheckmeetingTask.setDtOpenDate(legislationCheckmeetingTask.getDtOpenDate());
					nextLegislationCheckmeetingTask.setDtDealDate(legislationCheckmeetingTask.getDtDealDate());
					UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
					nextLegislationCheckmeetingTask.setStTeamId((currentPerson.getTeamInfos().get(0)).getId());
					nextLegislationCheckmeetingTask.setStTeamName((currentPerson.getTeamInfos().get(0)).getTeamName());
				} else {
					nextLegislationCheckmeetingTask.setDtOpenDate(new Date());
				}
				nextLegislationCheckmeetingTask.setStComment1(legislationCheckmeetingTask.getStComment1());

				legislationCheckmeetingTaskDao.save(nextLegislationCheckmeetingTask);
				legislationCheckmeetingService.executeSqlUpdate("update LegislationCheckmeeting s set s.stNodeName='" + nextLegislationCheckmeetingTask.getStNodeName() + "' where s.stMeetingId='" + nextLegislationCheckmeetingTask.getStMeetingId() + "'");
			}

			/*
			 * if("NOD_0000000170".equals(stNodeId)){ String[]
			 * legDocIdArray=legislationCheckmeetingService.findById(stDocId).getStDocSource
			 * ().split("#"); for(String legDocId:legDocIdArray){ nextProcess(legDocId,
			 * "NOD_0000000105",session); } }
			 */
		}

	}

	@Override
	public List<LegislationCheckmeetingTask> findTaskByDocIdAndNodeId(String stMeetingId, String stNodeId) {
		return findByHQL("from LegislationCheckmeetingTask t where 1=1 and t.stMeetingId='" + stMeetingId + "' and t.stNodeId='" + stNodeId + "' and t.stEnable is null");
	}

	/**
	 * 退回（公共）
	 *
	 * @param stMeetingId
	 * @param stNodeId
	 * @param userRoleId
	 * @param userRole
	 * @param currentPerson
	 */
	@Override
	public void returnCheckmeeting(String stMeetingId, String stNodeId, String userRoleId, String userRole, UserInfo currentPerson) {
		String userId = currentPerson.getUserId();
		String userName = currentPerson.getName();
		String unitId = currentPerson.getTeamInfos().get(0).getId();
		String unitName = currentPerson.getTeamInfos().get(0).getUnitName();

		List<LegislationCheckmeetingTask> list = findTaskByDocIdAndNodeId(stMeetingId, stNodeId);
		for (LegislationCheckmeetingTask legislationCheckmeetingTask : list) {
			legislationCheckmeetingTask.setStUserId(userId);
			legislationCheckmeetingTask.setStUserName(userName);
			legislationCheckmeetingTask.setStRoleId(userRoleId);
			legislationCheckmeetingTask.setStRoleName(userRole);
			legislationCheckmeetingTask.setStTeamId(unitId);
			legislationCheckmeetingTask.setStTeamName(unitName);
			legislationCheckmeetingTask.setStEnable("UNABLE");
			update(legislationCheckmeetingTask);

			List<WegovSimpleNode> nodeList = wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where 1=1 and t.stNextNode ='" + stNodeId + "'");
			String nodeId = nodeList.get(0).getStNodeId();

			LegislationCheckmeetingTask lastLegislationCheckmeetingTask = findTaskByDocIdAndNodeId(stMeetingId, nodeId).get(0);
			lastLegislationCheckmeetingTask.setStEnable("UNABLE");
			update(lastLegislationCheckmeetingTask);

			LegislationCheckmeetingTask newLegislationCheckmeetingTask = new LegislationCheckmeetingTask();
			newLegislationCheckmeetingTask.setStMeetingId(lastLegislationCheckmeetingTask.getStMeetingId());
			newLegislationCheckmeetingTask.setStFlowId(lastLegislationCheckmeetingTask.getStFlowId());
			newLegislationCheckmeetingTask.setStNodeId(lastLegislationCheckmeetingTask.getStNodeId());
			newLegislationCheckmeetingTask.setStNodeName(lastLegislationCheckmeetingTask.getStNodeName());
			newLegislationCheckmeetingTask.setStTaskStatus("DOING");
			newLegislationCheckmeetingTask.setDtOpenDate(lastLegislationCheckmeetingTask.getDtOpenDate());
			newLegislationCheckmeetingTask.setStUserId(lastLegislationCheckmeetingTask.getStUserId());
			newLegislationCheckmeetingTask.setStUserName(lastLegislationCheckmeetingTask.getStUserName());
			newLegislationCheckmeetingTask.setStRoleId(lastLegislationCheckmeetingTask.getStRoleId());
			newLegislationCheckmeetingTask.setStRoleName(lastLegislationCheckmeetingTask.getStRoleName());
			newLegislationCheckmeetingTask.setStTeamId(lastLegislationCheckmeetingTask.getStTeamId());
			newLegislationCheckmeetingTask.setStTeamName(lastLegislationCheckmeetingTask.getStTeamName());

			add(newLegislationCheckmeetingTask);
			legislationCheckmeetingService.executeSqlUpdate("update LegislationCheckmeeting s set s.stNodeName='" + lastLegislationCheckmeetingTask.getStNodeName() + "' where s.stMeetingId='" + lastLegislationCheckmeetingTask.getStMeetingId() + "'");
		}
	}

	/**
	 * 次节点流转（公共）
	 *
	 * @param stMeetingId
	 * @param stNodeId
	 * @param userRoleId
	 * @param userRole
	 * @param currentPerson
	 */
	@Override
	public void nextChildCheckmeeting(String stMeetingId, String stNodeId, String userRoleId, String userRole, UserInfo currentPerson) {
		String userId = currentPerson.getUserId();
		String userName = currentPerson.getName();
		String unitId = currentPerson.getTeamInfos().get(0).getId();
		String unitName = currentPerson.getTeamInfos().get(0).getUnitName();
		List<LegislationCheckmeetingTask> list = findTaskByDocIdAndNodeId(stMeetingId, stNodeId);
		for (LegislationCheckmeetingTask legislationCheckmeetingTask : list) {
			String curStTaskStatus = legislationCheckmeetingTask.getStTaskStatus();

			String[] stTaskStatusArray = wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where 1=1 and t.stNodeId ='" + stNodeId + "'").get(0).getStDoneName().split("#");
			for (int i = 0; i < stTaskStatusArray.length; i++) {
				if (curStTaskStatus.equals(stTaskStatusArray[i])) {
					legislationCheckmeetingTask.setStTaskStatus(stTaskStatusArray[i + 1]);
					break;
				}
			}
			legislationCheckmeetingTask.setStUserId(userId);
			legislationCheckmeetingTask.setStUserName(userName);
			legislationCheckmeetingTask.setStRoleId(userRoleId);
			legislationCheckmeetingTask.setStRoleName(userRole);
			if ("NOD_0000000105".equals(stNodeId)) {
				LegislationCheckmeetingTask lastTask = findTaskByDocIdAndNodeId(stMeetingId, "NOD_0000000104").get(0);
				legislationCheckmeetingTask.setStTeamId(lastTask.getStTeamId());
				legislationCheckmeetingTask.setStTeamName(lastTask.getStTeamName());
			} else {
				legislationCheckmeetingTask.setStTeamId(unitId);
				legislationCheckmeetingTask.setStTeamName(unitName);
			}
			update(legislationCheckmeetingTask);
		}

	}

	@Override
	public String saveAuditMeeting(HttpServletRequest request, HttpSession session) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String op = request.getParameter("op");
		// System.out.println("保存还是提交？op=" + op);
		String stTaskStatus = request.getParameter("stTaskStatus");
		// System.out.println("会议任务状态？stTaskStatus=" + stTaskStatus);
		String[] personFeedback = request.getParameterValues("ddd");
		String allPersonFeedBack = request.getParameter("allPersonFeedBack");
		String allPersonFeedBackTime = request.getParameter("allPersonFeedBackTime");
		// System.out.println("allPersonFeedBack------------" + allPersonFeedBack);
		// System.out.println("allPersonFeedBackTime------------" +
		// allPersonFeedBackTime);
		String stType = request.getParameter("stType");
		String stAddress = request.getParameter("stAddress");
		String stPersons = request.getParameter("stPersons");
		String stPersonsId = request.getParameter("stPersonsId");
		String otherPersonsName = request.getParameter("otherPersonsName");
		String stMeetingId = request.getParameter("stMeetingId");
		String stItemId = request.getParameter("stItemId")==null?"":request.getParameter("stItemId");
		String stMeetingName = request.getParameter("stMeetingName");
		String stDocNo = request.getParameter("stDocNo");
		String stDocSource = request.getParameter("stDocSource");// 包含的草案ID，#
		String stPlanSource = request.getParameter("stPlanSource");// 包含的计划ID
		String stNodeName = request.getParameter("stNodeName");
		String dtBeginDate = request.getParameter("dtBeginDate");
		String stComent = request.getParameter("stComent");
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String userId = currentPerson.getUserId();
		String userName = currentPerson.getName();
		String unitId = currentPerson.getTeamInfos().get(0).getId();
		String unitName = currentPerson.getTeamInfos().get(0).getUnitName();
		LegislationCheckmeeting auditMeeting = new LegislationCheckmeeting();
		legislationFilesService.updateParentIdById(request,stMeetingId);//更新上传的材料pid
		// 当页面没有传递议题编号 并且有会议id时候
		if (StringUtil.isNotEmpty(stMeetingId)) {
			auditMeeting = legislationCheckmeetingService.findById(stMeetingId);
			if (StringUtil.isEmpty(stDocSource)) {
				stDocSource = auditMeeting.getStDocSource();
				// System.out.println("stDocSource----" + stDocSource);
			}
			if (StringUtil.isEmpty(stPlanSource)) {
				stPlanSource = auditMeeting.getStPlanSource();
			}
		}
		if ("TODO".equals(stTaskStatus)) {
			String meetingTaskId;
			// 如果是新增加会议
			if (StringUtil.isEmpty(stMeetingId)) {
				auditMeeting.setStMeetingName(stMeetingName);
				auditMeeting.setStAddress(stAddress);
				auditMeeting.setStPersons(stPersons);
				auditMeeting.setStType(stType);
				auditMeeting.setDtCreateDate(new Date());
				auditMeeting.setDtBeginDate(formatter.parse(dtBeginDate));// 会议时间
				auditMeeting.setStTopic(stDocNo);
				auditMeeting.setStNodeId("NOD_0000000170");
				auditMeeting.setStNodeName(stNodeName);
				auditMeeting.setStTopic(stComent);
				auditMeeting.setStDocSource(stDocSource);// 包含的草案
				auditMeeting.setStPlanSource(stPlanSource);// 包含的计划
				auditMeeting.setStUserId(userId);
				auditMeeting.setStUserName(userName);
				auditMeeting.setStUnitId(unitId);
				auditMeeting.setStUnitName(unitName);
				stMeetingId = legislationCheckmeetingService.addObj(auditMeeting);
				
				LegislationCheckmeetingTask legislationProcessTask = new LegislationCheckmeetingTask();
				legislationProcessTask.setStMeetingId(stMeetingId);
				legislationProcessTask.setStNodeId("NOD_0000000170");
				legislationProcessTask.setStNodeName("审核会议处理(单独)");
				if ("submit".equals(op)) { //提交时状态为FEEDBACK
					legislationProcessTask.setStTaskStatus("FEEDBACK");
				}else {
					legislationProcessTask.setStTaskStatus("TODO");
				}
				legislationProcessTask.setStFlowId("");
				legislationProcessTask.setDtOpenDate(new Date());
				legislationProcessTask.setStUserId(userId);
				legislationProcessTask.setStUserName(userName);
				// 一个任务的角色由节点配置定，而不是当前人，万一当前人多个角色呢？LJ
				legislationProcessTask.setStRoleId(session.getAttribute("userRoleId").toString());
				legislationProcessTask.setStRoleName(session.getAttribute("userRole").toString());
				legislationProcessTask.setStTeamId((currentPerson.getTeamInfos().get(0)).getId());
				legislationProcessTask.setStTeamName((currentPerson.getTeamInfos().get(0)).getTeamName());
				meetingTaskId=addObj(legislationProcessTask);

				// 用户一个操作，只用增加一条经办记录就可以了，草案可以作为记录的备注，不用象原来多个记录。
				LegislationCheckmeetingDeal legislationProcessDeal = new LegislationCheckmeetingDeal();
				legislationProcessDeal.setStMeetingId(stMeetingId);
				legislationProcessDeal.setStActionId("NOD_0000000170");
				legislationProcessDeal.setStActionName("新增审核会议");
				legislationProcessDeal.setStUserId(userId);
				legislationProcessDeal.setStUserName(userName);
				legislationProcessDeal.setStBakOne(stDocSource);
				legislationProcessDeal.setStBakTwo(stPlanSource);
				legislationProcessDeal.setDtDealDate(new Date());
				legislationCheckmeetingDealService.add(legislationProcessDeal);
				
				
				String stItemIds = request.getParameter("stItemIds");//itemid组
				//更新Item表
				String[] itemIds = null; 
				if(!StringUtils.isEmpty(stItemIds)){
					itemIds = stItemIds.split(",");
				}
				if(null!=itemIds&&itemIds.length>0) {
					for (String string : itemIds) {
						try {
							LegislationCheckmeetingItem checkmeetingItem = legislationCheckmeetingItemService.findById(string);
							checkmeetingItem.setStMeetingId(stMeetingId);
							checkmeetingItem.setStStatus("DOING");
							if(null!=checkmeetingItem)
								legislationCheckmeetingItemService.update(checkmeetingItem);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			} else {
				// 如果是TODO的修改
				auditMeeting.setStMeetingName(stMeetingName);
				auditMeeting.setStType(stType);
				auditMeeting.setStTopic(stDocNo);
				auditMeeting.setStNodeName(stNodeName);
				auditMeeting.setStTopic(stComent);
				auditMeeting.setStDocSource(stDocSource);// 包含的草案
				auditMeeting.setStPlanSource(stPlanSource);
				auditMeeting.setStAddress(stAddress);
				auditMeeting.setStPersons(stPersons);
				auditMeeting.setDtBeginDate(formatter.parse(dtBeginDate));// 会议时间
				legislationCheckmeetingService.update(auditMeeting);
				
				LegislationCheckmeetingTask legislationCheckmeetingTask = findByHQL("from LegislationCheckmeetingTask t where t.stMeetingId='" + stMeetingId + "' and t.stNodeId='NOD_0000000170'").get(0);
				meetingTaskId=legislationCheckmeetingTask.getStTaskId();
				if ("submit".equals(op)) {
					legislationCheckmeetingTask.setStTaskStatus("FEEDBACK");
					this.update(legislationCheckmeetingTask);
				}
			}
			saveJoinMeetingPerson(stPersons, stPersonsId, otherPersonsName, stMeetingId, stMeetingName, meetingTaskId);
			
			// 如果TODO提交了
			if ("submit".equals(op)) {
				if(!StringUtils.isEmpty(stDocSource)) {
					String[] stDocIdArray = stDocSource.split("#");
					for (int i = 0; i < stDocIdArray.length; i++) {
						String newDocId = stDocIdArray[i];
						if (StringUtil.isNotEmpty(newDocId)) {
							// 下面为什么要设置？LJ
							// LegislationProcessDoc
							// legislationProcessDoc=legislationProcessDocService.findById(stDocIdArray[i]);
							// legislationProcessDoc.setStDocName(newDocName);
							// legislationProcessDocService.update(legislationProcessDoc);
							// 修改每一个草案的105的TODO到DOING，表示已经上会了,不可以被其它新审核会议选择到。
							List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stNodeId='NOD_0000000105' and t.stDocId='" + stDocIdArray[i] + "'");
							legislationProcessTaskList.forEach((LegislationProcessTask newLegislationProcessTask) -> {
								newLegislationProcessTask.setStTaskStatus("DOING");
								legislationProcessTaskService.update(newLegislationProcessTask);
							});
							// 如果已经开始了审核会议 那主表legislation_process_doc 的这条数据 st_node_id 和st_node_name 都需要修改 成
							// 2019年4月12日11:22:49 sy
							// NOD_0000000170 审核会议
							LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocIdArray[i]);
							String stNodeId = "NOD_0000000170";
							stNodeName = "审核会议";
							legislationProcessDoc.setStNodeId(stNodeId);
							legislationProcessDoc.setStNodeName(stNodeName);
							legislationProcessDocService.update(legislationProcessDoc);
							// ed
						}
					}
				}
				// ？？？？？
				if(!StringUtils.isEmpty(stItemId)) {
					LegislationCheckmeetingItem item = legislationCheckmeetingItemService.findById(stItemId);
					item.setStMeetingId(stMeetingId);
					item.setStStatus("DOING");
					legislationCheckmeetingItemService.update(item);
				}
				
				if (StringUtil.isNotEmpty(stPlanSource)) {
					String[] stPlanIdArray = stPlanSource.split("#");
					for (int i = 0; i < stPlanIdArray.length; i++) {
						String newPlanId = stPlanIdArray[i];
						if (StringUtil.isNotEmpty(newPlanId)) {
							List<LegislationPlanTask> legislationPlanTaskList = legislationPlanTaskService.findByHQL(
									"from LegislationPlanTask t where 1=1 and t.stNodeId='NOD_0000000210' and t.stPlanId='"
											+ stPlanIdArray[i] + "'");

							legislationPlanTaskList.forEach((LegislationPlanTask newLegislationPlanTask) -> {
								newLegislationPlanTask.setStTaskStatus("DOING");
								legislationPlanTaskService.update(newLegislationPlanTask);
							});
							// NOD_0000000170 审核会议
							LegislationPlan legislationPlan = legislationPlanService.findById(stPlanIdArray[i]);
							String stNodeId = "NOD_0000000170";
							stNodeName = "审核会议";
							legislationPlan.setStNodeId(stNodeId);
							legislationPlan.setStNodeName(stNodeName);
							legislationPlanService.update(legislationPlan);
							// ed
						}
					}
				}
				sendNoticeToJoinMeetingPerson(stPersonsId, otherPersonsName, stMeetingId, stMeetingName);
			}
		} else if ("FEEDBACK".equals(stTaskStatus)) {// 如果不是TODO，是另一个环节
			String stFeedback = request.getParameter("stFeedback");// 反馈信息
			LegislationCheckmeetingTask legislationCheckmeetingTask = findByHQL("from LegislationCheckmeetingTask t where t.stMeetingId='" + stMeetingId + "' and t.stNodeId='NOD_0000000170'").get(0);
			// allPersonFeedBack
			String[] stPersonNames = auditMeeting.getStPersons().split("、");
			String[] allPersonFeedBacks = allPersonFeedBack.split("#");
			String[] allPersonFeedBackTimes = allPersonFeedBackTime.split("#");
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < stPersonNames.length; i++) {
				Map map = new HashMap();
				map.put("name", stPersonNames[i]);
				map.put("feedBack", allPersonFeedBacks[i]);
				map.put("feedBackT", allPersonFeedBackTimes[i]);
				JSONObject json = new JSONObject(map);
				jsonArray.add(json);
			}
			legislationCheckmeetingTask.setStFeedback(jsonArray.toString());
			if ("submit".equals(op) && "FEEDBACK".equals(legislationCheckmeetingTask.getStTaskStatus())) {
				legislationCheckmeetingTask.setStTaskStatus("INPUT");
			}
			this.update(legislationCheckmeetingTask);
		}

		// 如果不是TODO，是另一个环节
		else if ("INPUT".equals(stTaskStatus)) {
			String stSummary = request.getParameter("stSummary");// 纪要信息
			LegislationCheckmeetingTask legislationCheckmeetingTask = findByHQL("from LegislationCheckmeetingTask t where t.stMeetingId='" + stMeetingId + "' and t.stNodeId='NOD_0000000170'").get(0);
			legislationCheckmeetingTask.setStSummary(stSummary);
			if ("submit".equals(op) && "INPUT".equals(legislationCheckmeetingTask.getStTaskStatus())) {
				legislationCheckmeetingTask.setStTaskStatus("AFFIRM");
				List<LegislationCheckmeetingItem> legislationCheckmeetingItemList = legislationCheckmeetingItemService.findByHQL("from LegislationCheckmeetingItem t where t.stStatus='DOING' and t.stMeetingId='" + stMeetingId + "' order by t.dtCreateDate desc");
				for(LegislationCheckmeetingItem legislationCheckmeetingItem:legislationCheckmeetingItemList){
					String stTypeName = legislationCheckmeetingItem.getStTypeName();
					WegovSimpleNode nextNodeInfo = wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where t.stNodeId='NOD_0000000109'").get(0);

					if ("草案".equals(stTypeName)) {
						String legislationProcessTaskId = legislationCheckmeetingItem.getStSourceId();
						LegislationProcessTask newLegislationProcessTask = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stNodeId='NOD_0000000105' and t.stTaskStatus='TODO' and t.stDocId='" + legislationProcessTaskId + "'").get(0);
						
						newLegislationProcessTask.setStTaskStatus("DONE");
						legislationProcessTaskService.update(newLegislationProcessTask);// 105结束
						// 产生一个个109的任务。
						LegislationProcessTask legislationProcessTask = new LegislationProcessTask();
						legislationProcessTask.setStDocId(newLegislationProcessTask.getStDocId());
						legislationProcessTask.setStNodeId("NOD_0000000109");
						legislationProcessTask.setStNodeName(nextNodeInfo.getStNodeName());
						legislationProcessTask.setStTaskStatus("TODO");
						legislationProcessTask.setStFlowId(newLegislationProcessTask.getStFlowId());
						legislationProcessTask.setDtOpenDate(new Date());
						legislationProcessTask.setStUserId(userId);
						legislationProcessTask.setStUserName(userName);
						legislationProcessTask.setStRoleId(nextNodeInfo.getStSubmitRole());
						legislationProcessTask.setStRoleName(nextNodeInfo.getStSubmitRole());
						legislationProcessTaskService.add(legislationProcessTask);
					}
					if ("立法计划".equals(stTypeName)) {
						String legislationPlanTaskId = legislationCheckmeetingItem.getStSourceId();
						LegislationPlanTask newLegislationPlanTask = legislationPlanTaskService.findByHQL("from LegislationPlanTask t where 1=1 and t.stNodeId='NOD_0000000210' and t.stTaskStatus='TODO' and t.stPlanId='" + legislationPlanTaskId + "'").get(0);

						newLegislationPlanTask.setStTaskStatus("DONE");
						legislationPlanTaskService.update(newLegislationPlanTask);// 210结束
						// 产生一个个211的任务。
						LegislationPlanTask legislationPlanTask = new LegislationPlanTask();
						legislationPlanTask.setStPlanId(newLegislationPlanTask.getStPlanId());
						legislationPlanTask.setStNodeId("NOD_0000000211");
						legislationPlanTask.setStNodeName(nextNodeInfo.getStNodeName());
						legislationPlanTask.setStTaskStatus("TODO");
						legislationPlanTask.setStFlowId(newLegislationPlanTask.getStFlowId());
						legislationPlanTask.setDtOpenDate(new Date());
						legislationPlanTask.setStUserId(userId);
						legislationPlanTask.setStUserName(userName);
						legislationPlanTask.setStRoleId(nextNodeInfo.getStSubmitRole());
						legislationPlanTask.setStRoleName(nextNodeInfo.getStSubmitRole());
						legislationPlanTaskService.add(legislationPlanTask);
						
						
					}
					legislationCheckmeetingItem.setStStatus("DONE");
					legislationCheckmeetingItemService.update(legislationCheckmeetingItem);
				
			}
		}
			this.update(legislationCheckmeetingTask);

		}
		// 如果不是TODO，是另一个环节
		else if ("AFFIRM".equals(stTaskStatus)) {
			LegislationCheckmeetingTask legislationCheckmeetingTask = findByHQL("from LegislationCheckmeetingTask t where t.stMeetingId='" + stMeetingId + "' and t.stNodeId='NOD_0000000170'").get(0);
			if ("submit".equals(op) && "AFFIRM".equals(legislationCheckmeetingTask.getStTaskStatus())) {
				legislationCheckmeetingTask.setStTaskStatus("DONE");
				
				
				Map<String, Object> condMap = new HashMap<>();
				condMap.put("stMeetingId", stMeetingId);
				Map<String, String> sortMap = new HashMap<>();
				List<LegislationCheckmeetingItem> checkmeetingItem = legislationCheckmeetingItemService.findByList(condMap, sortMap);
				for (LegislationCheckmeetingItem lci : checkmeetingItem) {
					String stTypeName = lci.getStTypeName();
					String stSourceId = lci.getStSourceId();
					if(null==stSourceId) {
						continue;
					}
					if("草案".endsWith(stTypeName)) {
						List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stNodeId='NOD_0000000109' and t.stDocId='" + stSourceId + "'");
						legislationProcessTaskList.forEach((LegislationProcessTask newLegislationProcessTask) -> {
							newLegislationProcessTask.setStTaskStatus("DONE");
							legislationProcessTaskService.update(newLegislationProcessTask);// 109结束
						});
						
						LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stSourceId);
						String stNodeId = "NOD_0000000103";
						stNodeName = "立法办理";
						legislationProcessDoc.setStNodeId(stNodeId);
						legislationProcessDoc.setStNodeName(stNodeName);
						legislationProcessDocService.update(legislationProcessDoc);
					}
				}
//				// 同时把所有的草案变成109的DONE，反馈也完成了
//				String[] stDocIdArray = auditMeeting.getStDocSource().split("#");
//				for (int i = 0; i < stDocIdArray.length; i++) {
//					String newDocId = request.getParameter(stDocIdArray[i]);
//					if (StringUtil.isNotEmpty(newDocId)) {
//						// 草案的109的TODO都进行确认，结束了DONE
//						List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stNodeId='NOD_0000000109' and t.stDocId='" + stDocIdArray[i] + "'");
//						legislationProcessTaskList.forEach((LegislationProcessTask newLegislationProcessTask) -> {
//							newLegislationProcessTask.setStTaskStatus("DONE");
//							legislationProcessTaskService.update(newLegislationProcessTask);// 109结束
//						});
//					}
//
//					// 如果已经开始了审核会议 那主表legislation_process_doc 的这条数据 st_node_id 和st_node_name 都需要修改 成
//					// 2019年4月12日11:22:49 sy
//					// NOD_0000000170 审核会议
//					LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocIdArray[i]);
//					String stNodeId = "NOD_0000000103";
//					stNodeName = "立法办理";
//					legislationProcessDoc.setStNodeId(stNodeId);
//					legislationProcessDoc.setStNodeName(stNodeName);
//					legislationProcessDocService.update(legislationProcessDoc);
//					// ed
//
//				}
				
			}
			this.update(legislationCheckmeetingTask);
		}

		legislationFilesService.updateParentIdById(request,stMeetingId);
		return stMeetingId;
	}
	/**
	 * 保存参会人员<保存或修改会议信息时调，注意提交时不触发!>
	 * @param stPersons
	 * @param stPersonsId
	 * @param otherPersonsName
	 * @param stMeetingId
	 * @param stMeetingName
	 * @param meetingTaskId
	 */
	private void saveJoinMeetingPerson(String stPersons, String stPersonsId, String otherPersonsName, String stMeetingId,
			String stMeetingName, String meetingTaskId) {
		List<LegislationCheckmeetingTaskd> legislationCheckmeetingTaskdList=legislationCheckmeetingTaskdService.findByHQL("from LegislationCheckmeetingTaskd t where 1=1 and t.stTaskId='"+meetingTaskId+"' and t.stTaskStatus='TODO'");
		LegislationCheckmeetingTaskd legislationCheckmeetingTaskd=null;
		if(legislationCheckmeetingTaskdList.size()>0){ //更新已存在临时保存数据
			legislationCheckmeetingTaskd=legislationCheckmeetingTaskdList.get(0);
			legislationCheckmeetingTaskd.setStPersonId(stPersonsId);
			legislationCheckmeetingTaskd.setStPersonName(stPersons);
			legislationCheckmeetingTaskd.setStBak1(otherPersonsName);
			legislationCheckmeetingTaskdService.update(legislationCheckmeetingTaskd);
		}else{ //新增一条任务，并临时缓存参会人员数据
			legislationCheckmeetingTaskd = new LegislationCheckmeetingTaskd();
			legislationCheckmeetingTaskd.setStTaskId(meetingTaskId);
			legislationCheckmeetingTaskd.setStTaskStatus("TODO");
			legislationCheckmeetingTaskd.setStPersonId(stPersonsId);
			legislationCheckmeetingTaskd.setStPersonName(stPersons);
			legislationCheckmeetingTaskd.setStBak1(otherPersonsName);
			legislationCheckmeetingTaskd.setDtOpenDate(new Date());
			legislationCheckmeetingTaskd.setStNodeId("NOD_0000000170");
			legislationCheckmeetingTaskdService.add(legislationCheckmeetingTaskd);
		}
	}
	/**
	 * 发送消息给参会人员<提交操作时触发发送消息给参会人员>
	 * @param stPersonsId
	 * @param otherPersonsName
	 * @param stMeetingId
	 * @param stMeetingName
	 */
	private void sendNoticeToJoinMeetingPerson(String stPersonsId, String otherPersonsName, String stMeetingId, String stMeetingName) {
		if(StringUtils.isEmpty(stPersonsId)||StringUtils.isEmpty(stMeetingId)) {
			return ; 
		}
		String[] userIdArray=stPersonsId.split(",");
		for (String s:userIdArray) {
			UserInfo userInfo=userInfoService.findByUserId(s);
			LegislationSendNotice legislationSendNotice=new LegislationSendNotice();
			legislationSendNotice.setStDocId(stMeetingId);
			legislationSendNotice.setDtOpenDate(new Date());
			legislationSendNotice.setStUserId(userInfo.getUserId());
			legislationSendNotice.setStUserName(userInfo.getName());
			legislationSendNotice.setStModelName("审核会议");
			legislationSendNotice.setStNodeName("审核会议发送通知");
			legislationSendNotice.setStNoticeStatus("已发送");
			legislationSendNotice.setStNoticeContent("参加审核会议: "+stMeetingName+"的通知");
			legislationSendNoticeService.add(legislationSendNotice);
		}
		if(StringUtils.isNotEmpty(otherPersonsName)){
			LegislationSendNotice legislationSendNotice=new LegislationSendNotice();
			legislationSendNotice.setStDocId(stMeetingId);
			legislationSendNotice.setDtOpenDate(new Date());
			legislationSendNotice.setStUserName(otherPersonsName);
			legislationSendNotice.setStModelName("审核会议");
			legislationSendNotice.setStNodeName("审核会议发送通知");
			legislationSendNotice.setStNoticeStatus("已发送");
			legislationSendNotice.setStNoticeContent("参加审核会议: "+stMeetingName+"的通知");
			legislationSendNoticeService.add(legislationSendNotice);
		}
	}
}
