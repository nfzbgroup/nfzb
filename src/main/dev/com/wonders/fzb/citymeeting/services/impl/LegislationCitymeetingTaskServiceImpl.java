package com.wonders.fzb.citymeeting.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.citymeeting.beans.LegislationCitymeeting;
import com.wonders.fzb.citymeeting.beans.LegislationCitymeetingTask;
import com.wonders.fzb.citymeeting.beans.LegislationCitymeetingTaskd;
import com.wonders.fzb.citymeeting.dao.LegislationCitymeetingTaskDao;
import com.wonders.fzb.citymeeting.services.LegislationCitymeetingService;
import com.wonders.fzb.citymeeting.services.LegislationCitymeetingTaskService;
import com.wonders.fzb.citymeeting.services.LegislationCitymeetingTaskdService;
import com.wonders.fzb.framework.beans.TeamInfo;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.framework.services.TeamInfoService;
import com.wonders.fzb.legislation.beans.LegislationSendNotice;
import com.wonders.fzb.legislation.services.LegislationFilesService;
import com.wonders.fzb.legislation.services.LegislationProcessDocService;
import com.wonders.fzb.legislation.services.LegislationProcessTaskService;
import com.wonders.fzb.legislation.services.LegislationSendNoticeService;
import com.wonders.fzb.plan.beans.LegislationPlanTask;
import com.wonders.fzb.plan.services.LegislationPlanTaskService;
import com.wonders.fzb.simpleflow.beans.WegovSimpleNode;
import com.wonders.fzb.simpleflow.services.WegovSimpleNodeService;

import dm.jdbc.util.StringUtil;


/**
 * LegislationCitymeetingTask service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationCitymeetingTaskService")
@Transactional
public class LegislationCitymeetingTaskServiceImpl implements LegislationCitymeetingTaskService {

	@Autowired
	private LegislationCitymeetingService legislationCitymeetingService;
	
	@Autowired
	private LegislationCitymeetingTaskDao legislationCitymeetingTaskDao;
	
	@Autowired
	private WegovSimpleNodeService wegovSimpleNodeService;

	@Autowired
	@Qualifier("legislationProcessDocService")
	private LegislationProcessDocService legislationProcessDocService;

	@Autowired
	@Qualifier("legislationProcessTaskService")
	private LegislationProcessTaskService legislationProcessTaskService;

	@Autowired
	private LegislationPlanTaskService legislationPlanTaskService;
	
	@Autowired
	@Qualifier("legislationSendNoticeService")
	private LegislationSendNoticeService legislationSendNoticeService;
	
	@Autowired
	@Qualifier("teamInfoService")
	private TeamInfoService teamInfoService;
	

    @Autowired
    @Qualifier("legislationFilesService")
    private LegislationFilesService legislationFilesService;
    
    @Autowired
    @Qualifier("legislationCitymeetingTaskdService")
    private LegislationCitymeetingTaskdService legislationCitymeetingTaskdService;

	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationCitymeetingTask info) {
		legislationCitymeetingTaskDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationCitymeetingTask info) {
		return legislationCitymeetingTaskDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationCitymeetingTask info) {
		legislationCitymeetingTaskDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationCitymeetingTask info) {
		legislationCitymeetingTaskDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationCitymeetingTask findById(String id) {
		return (LegislationCitymeetingTask) legislationCitymeetingTaskDao.load(id);
	}

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
	 * @throws FzbDaoException
	 */
	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException {
		return legislationCitymeetingTaskDao.findByPage(condMap, sortMap, pageNo, pageSize);
	}

	/**
	 * 根据Map中过滤条件、排序条件进行查询.
	 * 
	 * @param condMap
	 *            过滤条件<propertyName,properyValue>
	 * @param sortMap
	 *            排序条件<propertyName,properyValue>
	 * @return
	 */
	@Override
	public List<LegislationCitymeetingTask> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationCitymeetingTaskDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationCitymeetingTask info) {
		legislationCitymeetingTaskDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationCitymeetingTask> findByHQL(String hql) {
		List<LegislationCitymeetingTask> legislationCitymeetingTaskList = legislationCitymeetingTaskDao.findByHQL(hql);
		return legislationCitymeetingTaskList;
	}
	
	/**
	 * task列表分页
	 * @param sql
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Override
	public Page<LegislationCitymeeting> findTaskByNodeId(String sql, int pageNo,
														 int pageSize) throws ParseException {
		return legislationCitymeetingTaskDao.findTaskDocListByNodeId(sql, pageNo, pageSize);
	}

	@Override
	public String saveCitymeeting(HttpServletRequest request, HttpSession session) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String op = request.getParameter("op");
		//System.out.println("保存还是提交？op=" + op);
		String stTaskStatus = request.getParameter("stTaskStatus");
		//System.out.println("任务状态？stTaskStatus=" + stTaskStatus);
		String[] personFeedback = request.getParameterValues("ddd");
		String allPersonFeedBack = request.getParameter("allPersonFeedBack");
		String allPersonFeedBackTime = request.getParameter("allPersonFeedBackTime");
		//System.out.println("allPersonFeedBack------------" + allPersonFeedBack);
		//System.out.println("allPersonFeedBackTime------------" + allPersonFeedBackTime);
//		String stType = request.getParameter("stType");
		String stType = request.getParameter("stType");
		String stAddress = request.getParameter("stAddress");
		String stPersons = request.getParameter("stPersons");
		String stTopicId = request.getParameter("stTopicId");
		String stTopicName = request.getParameter("stTopicName");
		String stDocNo = request.getParameter("stDocNo");
		String stDocSource = request.getParameter("stDocSource");// 包含的草案ID，#
		String stNodeName = request.getParameter("stNodeName");
		String dtBeginDate = request.getParameter("dtBeginDate");
		String stComent = request.getParameter("stComent");
		String stBak = request.getParameter("stBak");
		String stBak1 = request.getParameter("stBak1");
		String stBak2 = request.getParameter("stBak2");
		String stBak3 = request.getParameter("stBak3");
		String stBak4 = request.getParameter("stBak4");
		String stBak5 = request.getParameter("stBak5");
		String stBak6 = request.getParameter("stBak6");
		String stContent = request.getParameter("stContent");
		String dtBak1 = request.getParameter("dtBak1");
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String userId = currentPerson.getUserId();
		String userName = currentPerson.getName();
		String unitId = currentPerson.getTeamInfos().get(0).getId();
		String unitName = currentPerson.getTeamInfos().get(0).getUnitName();
		LegislationCitymeeting auditMeeting = new LegislationCitymeeting();

		
		if ("TODO".equals(stTaskStatus)) {
			// 如果是修改签报
			if (StringUtil.isEmpty(stTopicId)) {

				auditMeeting.setStTopicName(stTopicName);
				auditMeeting.setStAddress(stAddress);
				auditMeeting.setStPersons(stPersons);
				auditMeeting.setStType(stType);
				auditMeeting.setDtCreateDate(new Date());
				//auditMeeting.setDtBeginDate(formatter.parse(dtBeginDate));//
				auditMeeting.setStTopic(stDocNo);
				auditMeeting.setStNodeId("NOD_0000000180");
				auditMeeting.setStTopic(stComent);
				auditMeeting.setStNodeName("常务会议");
				// auditMeeting.setDtCreateDate(DateUtils.parseDate(dtCreateDate,"yyyy-MM-dd"));
				//auditMeeting.setStDocSource(stDocSource);// 包含的草案
				auditMeeting.setStUserId(userId);
				auditMeeting.setStUserName(userName);
				auditMeeting.setStUnitId(unitId);
				auditMeeting.setStUnitName(unitName);
				stTopicId = legislationCitymeetingService.addObj(auditMeeting);

				LegislationCitymeetingTask legislationCitymeetingTask = new LegislationCitymeetingTask();
				legislationCitymeetingTask.setStTopicId(stTopicId);
				legislationCitymeetingTask.setStNodeId("NOD_0000000180");
				legislationCitymeetingTask.setStNodeName("常务会议");
				legislationCitymeetingTask.setStTaskStatus("TODO");
				legislationCitymeetingTask.setStBak(stBak);
				if ("submit".equals(op)) {
					legislationCitymeetingTask.setStTaskStatus("RESULT");
				}
				legislationCitymeetingTask.setStFlowId("");
				legislationCitymeetingTask.setDtOpenDate(new Date());
				legislationCitymeetingTask.setStUserId(userId);
				legislationCitymeetingTask.setStUserName(userName);
				// 一个任务的角色由节点配置定，而不是当前人，万一当前人多个角色呢？LJ
				legislationCitymeetingTask.setStRoleId(session.getAttribute("userRoleId").toString());
				legislationCitymeetingTask.setStRoleName(session.getAttribute("userRole").toString());
				legislationCitymeetingTask.setStTeamId((currentPerson.getTeamInfos().get(0)).getId());
				legislationCitymeetingTask.setStTeamName((currentPerson.getTeamInfos().get(0)).getTeamName());
				String citymeetingTaskId = addObj(legislationCitymeetingTask);
				//保存相关信息到detail表中
				LegislationCitymeetingTaskd legislationCitymeetingTaskd=new LegislationCitymeetingTaskd();
				legislationCitymeetingTaskd.setStTaskId(citymeetingTaskId);
				legislationCitymeetingTaskd.setStNodeId(auditMeeting.getStNodeId());
				legislationCitymeetingTaskd.setDtOpenDate(new Date());
				legislationCitymeetingTaskd.setStTaskStatus("TODO");
				legislationCitymeetingTaskd.setStBak1(stBak1);
				legislationCitymeetingTaskd.setStBak2(stBak2);
				legislationCitymeetingTaskd.setStBak3(stBak3);
				legislationCitymeetingTaskd.setStBak4(stBak4);
				legislationCitymeetingTaskd.setStBak5(stBak5);
				legislationCitymeetingTaskd.setStBak6(stBak6);
				legislationCitymeetingTaskd.setStContent(stContent);
				legislationCitymeetingTaskd.setDtBak1(DateUtils.parseDate(dtBak1, "yyyy-MM-dd"));
				legislationCitymeetingTaskdService.add(legislationCitymeetingTaskd);
			} else {
				// 如果是TODO的修改
				auditMeeting.setStNodeName("常务会议");
				auditMeeting.setStTopicId(stTopicId);
				auditMeeting.setStTopicName(stTopicName);
				auditMeeting.setStType(stType);
				auditMeeting.setStTopic(stDocNo);
				auditMeeting.setStTopic(stComent);
				auditMeeting.setStAddress(stAddress);
				auditMeeting.setStPersons(stPersons);
				auditMeeting.setDtCreateDate(new Date());
				//auditMeeting.setDtBeginDate(formatter.parse(dtBeginDate));// 会议时间
				legislationCitymeetingService.update(auditMeeting);
				LegislationCitymeetingTask legislationCitymeetingTask = findByHQL("from LegislationCitymeetingTask t where t.stTopicId='" + stTopicId + "' and t.stNodeId='NOD_0000000180'").get(0);
				legislationCitymeetingTask.setStBak(stBak);
				if ("submit".equals(op)) {
					legislationCitymeetingTask.setStTaskStatus("RESULT");
					legislationCitymeetingTask.setStBak(stBak);
					
				}
				this.update(legislationCitymeetingTask);
				LegislationCitymeetingTaskd legislationCitymeetingTaskd = legislationCitymeetingTaskdService.findByHQL("from LegislationCitymeetingTaskd t where t.stTaskId='" + legislationCitymeetingTask.getStTaskId() + "' and t.stTaskStatus='TODO'").get(0);
				legislationCitymeetingTaskd.setStBak1(stBak1);
				legislationCitymeetingTaskd.setStBak2(stBak2);
				legislationCitymeetingTaskd.setStBak3(stBak3);
				legislationCitymeetingTaskd.setStBak4(stBak4);
				legislationCitymeetingTaskd.setStBak5(stBak5);
				legislationCitymeetingTaskd.setStBak6(stBak6);
				legislationCitymeetingTaskd.setStContent(stContent);
				legislationCitymeetingTaskd.setDtBak1(DateUtils.parseDate(dtBak1, "yyyy-MM-dd"));
				legislationCitymeetingTaskdService.update(legislationCitymeetingTaskd);
			}

		} else if ("RESULT".equals(stTaskStatus)) {
			// 如果是RESULT的修改
			auditMeeting.setStTopicId(stTopicId);
			auditMeeting.setStTopicName(stTopicName);
			auditMeeting.setStType(stType);
			auditMeeting.setStTopic(stDocNo);
			auditMeeting.setStTopic(stComent);
			auditMeeting.setStAddress(stAddress);
			auditMeeting.setStPersons(stPersons);
			auditMeeting.setStNodeName("常务会议");
			auditMeeting.setDtCreateDate(new Date());
			//auditMeeting.setDtBeginDate(formatter.parse(dtBeginDate));// 会议时间
			legislationCitymeetingService.update(auditMeeting);
			LegislationCitymeetingTask legislationCitymeetingTask = findByHQL("from LegislationCitymeetingTask t where t.stTopicId='" + stTopicId + "' and t.stNodeId='NOD_0000000180'").get(0);
			legislationCitymeetingTask.setStTaskStatus("RESULT");
			legislationCitymeetingTask.setStBak(stBak);
			legislationCitymeetingTask.setStBak1(stBak1);
			legislationCitymeetingTask.setStBak3(stBak3);
		
			this.update(legislationCitymeetingTask);
			
			if ("submit".equals(op)) {
				LegislationCitymeetingTask legislationCitymeetingTask2 = findByHQL("from LegislationCitymeetingTask t where t.stTopicId='" + stTopicId + "' and t.stNodeId='NOD_0000000180'").get(0);
				legislationCitymeetingTask2.setStTaskStatus("AFFIRM");
				legislationCitymeetingTask2.setStBak(stBak);
				legislationCitymeetingTask2.setStBak1(stBak1);
				legislationCitymeetingTask2.setStBak3(stBak3);
				this.update(legislationCitymeetingTask2);
				//议题发送处室
				WegovSimpleNode nodeInfo = wegovSimpleNodeService.findById("NOD_0000000110");
				TeamInfo teamInfo = teamInfoService.findTeamInfoByTeamId("MODULE_LEGISLATE", stBak3);
				LegislationSendNotice legislationSendNotice=new LegislationSendNotice();
				legislationSendNotice.setStTeamId(stBak3);
				legislationSendNotice.setStTeamName(teamInfo.getTeamName());
				legislationSendNotice.setStNoticeStatus("已发送");
				legislationSendNotice.setStDocId(stTopicId);
				legislationSendNotice.setDtOpenDate(new Date());
				legislationSendNotice.setStModelName("常务会议");
				legislationSendNotice.setStNodeName(nodeInfo.getStNodeName());
				legislationSendNoticeService.add(legislationSendNotice);
			}
			
			
		} else if ("AFFIRM".equals(stTaskStatus)) {

			auditMeeting.setStTopicId(stTopicId);
			auditMeeting.setStTopicName(stTopicName);
			auditMeeting.setStType(stType);
			auditMeeting.setStTopic(stDocNo);
			auditMeeting.setStTopic(stComent);
			auditMeeting.setStAddress(stAddress);
			auditMeeting.setStPersons(stPersons);
			auditMeeting.setStNodeName("常务会议");
			auditMeeting.setDtCreateDate(new Date());// 会议时间
			//auditMeeting.setDtBeginDate(formatter.parse(dtBeginDate));// 会议时间
			legislationCitymeetingService.update(auditMeeting);
			LegislationCitymeetingTask legislationCitymeetingTask = findByHQL("from LegislationCitymeetingTask t where t.stTopicId='" + stTopicId + "' and t.stNodeId='NOD_0000000180'").get(0);
			legislationCitymeetingTask.setStTaskStatus("AFFIRM");
			legislationCitymeetingTask.setStBak(stBak);
			legislationCitymeetingTask.setStBak1(stBak1);
			this.update(legislationCitymeetingTask);
			if ("submit".equals(op)) {
				LegislationCitymeetingTask legislationCitymeetingTask2 = findByHQL("from LegislationCitymeetingTask t where t.stTopicId='" + stTopicId + "' and t.stNodeId='NOD_0000000180'").get(0);
				legislationCitymeetingTask2.setStTaskStatus("INPUT");
				legislationCitymeetingTask2.setStBak(stBak);
				legislationCitymeetingTask2.setStBak1(stBak1);
				this.update(legislationCitymeetingTask2);
				
				//常务会议议题对应的立法计划处理
				List<LegislationPlanTask> legislationPlanTaskList = legislationPlanTaskService.findByHQL("from LegislationPlanTask t where t.stNodeId='NOD_0000000214' and t.stTaskStatus='TODO' and t.stTopicId='"+stTopicId+"' order by t.dtOpenDate desc");
				if(legislationPlanTaskList.size()>0){
					for(LegislationPlanTask legislationPlanTask : legislationPlanTaskList) {
						legislationPlanTask.setStTaskStatus("DOING");
						legislationPlanTaskService.update(legislationPlanTask);
				}}
				
			}
		
			
			
		}else if ("INPUT".equals(stTaskStatus)) {
			auditMeeting.setDtCreateDate(new Date());// 会议时间
			auditMeeting.setStTopicId(stTopicId);
			auditMeeting.setStTopicName(stTopicName);
			auditMeeting.setStType(stType);
			auditMeeting.setStTopic(stDocNo);
			auditMeeting.setStNodeName("常务会议");
			auditMeeting.setStTopic(stComent);
			auditMeeting.setStAddress(stAddress);
			auditMeeting.setStPersons(stPersons);
			auditMeeting.setDtBeginDate(formatter.parse(dtBeginDate));// 会议时间
			legislationCitymeetingService.update(auditMeeting);
			LegislationCitymeetingTask legislationCitymeetingTask = findByHQL("from LegislationCitymeetingTask t where t.stTopicId='" + stTopicId + "' and t.stNodeId='NOD_0000000180'").get(0);
			legislationCitymeetingTask.setStTaskStatus("INPUT");
			legislationCitymeetingTask.setStBak(stBak);
			legislationCitymeetingTask.setStBak1(stBak1);
			legislationCitymeetingTask.setStBak2(stBak2);
			this.update(legislationCitymeetingTask);
			if ("submit".equals(op)) {
				LegislationCitymeetingTask legislationCitymeetingTaskTwo = findByHQL("from LegislationCitymeetingTask t where t.stTopicId='" + stTopicId + "' and t.stNodeId='NOD_0000000180'").get(0);
				legislationCitymeetingTaskTwo.setStTaskStatus("DONE");
				legislationCitymeetingTaskTwo.setStBak(stBak);
				legislationCitymeetingTaskTwo.setStBak1(stBak1);
				legislationCitymeetingTask.setStBak2(stBak2);
				this.update(legislationCitymeetingTaskTwo);
				//常务会议议题对应的立法计划节点传递
				List<LegislationPlanTask> legislationPlanTaskList = legislationPlanTaskService.findByHQL("from LegislationPlanTask t where t.stNodeId='NOD_0000000214' and t.stTaskStatus='DOING' and t.stTopicId='"+stTopicId+"' order by t.dtOpenDate desc");
				if(legislationPlanTaskList.size()>0){
					for(LegislationPlanTask legislationPlanTask : legislationPlanTaskList) {
						
						String stNodeId = legislationPlanTask.getStNodeId();
						WegovSimpleNode node = wegovSimpleNodeService.findById(stNodeId);
						WegovSimpleNode nextNode=wegovSimpleNodeService.findById(node.getStNextNode());
						if(!"DOING".equals(node.getStNextNode())){
							//增加一条新任务
							LegislationPlanTask newLegislationPlanTask=new LegislationPlanTask();
							newLegislationPlanTask.setStPlanId(legislationPlanTask.getStPlanId());
							newLegislationPlanTask.setStFlowId(legislationPlanTask.getStFlowId());
							newLegislationPlanTask.setStNodeId(nextNode.getStNodeId());
							newLegislationPlanTask.setStNodeName(nextNode.getStNodeName());
							newLegislationPlanTask.setStTaskStatus("TODO");
							newLegislationPlanTask.setDtOpenDate(legislationPlanTask.getDtOpenDate());
							newLegislationPlanTask.setDtCloseDate(legislationPlanTask.getDtCloseDate());
							newLegislationPlanTask.setDtDeadDate(legislationPlanTask.getDtDeadDate());
							newLegislationPlanTask.setStUserId(legislationPlanTask.getStUserId());
							newLegislationPlanTask.setStUserName(legislationPlanTask.getStUserName());
							newLegislationPlanTask.setStRoleId(legislationPlanTask.getStRoleId());
							newLegislationPlanTask.setStRoleName(legislationPlanTask.getStRoleName());
							newLegislationPlanTask.setStTeamId(legislationPlanTask.getStTeamId());
							newLegislationPlanTask.setStTeamName(legislationPlanTask.getStTeamName());
							newLegislationPlanTask.setStActive(legislationPlanTask.getStActive());
							newLegislationPlanTask.setStEnable(legislationPlanTask.getStEnable());
							newLegislationPlanTask.setStParentId(legislationPlanTask.getStParentId());
							newLegislationPlanTask.setStDealId(legislationPlanTask.getStDealId());
							newLegislationPlanTask.setStDealName(legislationPlanTask.getStDealName());
							newLegislationPlanTask.setDtDeadDate(legislationPlanTask.getDtDeadDate());
							newLegislationPlanTask.setStTopicId(stTopicId);
							legislationPlanTaskService.addObj(newLegislationPlanTask);
							
							/*//legislationPlanItem立法计划信息记录  同步
							LegislationPlanItem legislationPlanItem = new LegislationPlanItem();
							legislationPlanItem.setStPlanId(legislationPlanTask.getStPlanId());
							legislationPlanItem.setStItemName(legislationPlanTask.getStFlowId());
							legislationPlanItem.setStNodeId(nextNode.getStNodeId());
							legislationPlanItem.setStNodeName(nextNode.getStNodeName());
							legislationPlanItem.setStStatus("TODO");
							legislationPlanItem.setDtCreateDate(legislationPlanTask.getDtOpenDate());
							legislationPlanItem.setStUserId(legislationPlanTask.getStUserId());
							legislationPlanItem.setStUserName(legislationPlanTask.getStUserName());
							legislationPlanItem.setStUnitId(legislationPlanTask.getStRoleId());
							legislationPlanItem.setStUnitName(legislationPlanTask.getStRoleName());
							legislationPlanItem.setStTypeId(legislationPlanTask.getStTeamId());
							legislationPlanItem.setStTypeName(legislationPlanTask.getStTeamName());
							legislationPlanItem.setStSourceId(stTopicId);
							legislationPlanItemService.addObj(legislationPlanItem);*/
							}
						}
			}}
			
		}else if ("DONE".equals(stTaskStatus)) {
			auditMeeting.setStNodeName("常务会议");
			auditMeeting.setDtCreateDate(new Date());
			auditMeeting.setStTopicId(stTopicId);
			auditMeeting.setStTopicName(stTopicName);
			auditMeeting.setStType(stType);
			auditMeeting.setStTopic(stDocNo);
			auditMeeting.setStTopic(stComent);
			auditMeeting.setStAddress(stAddress);
			auditMeeting.setStPersons(stPersons);
			auditMeeting.setDtBeginDate(formatter.parse(dtBeginDate));// 会议时间
			legislationCitymeetingService.update(auditMeeting);
			LegislationCitymeetingTask legislationCitymeetingTask = findByHQL("from LegislationCitymeetingTask t where t.stTopicId='" + stTopicId + "' and t.stNodeId='NOD_0000000180'").get(0);
			legislationCitymeetingTask.setStTaskStatus("DONE");
			legislationCitymeetingTask.setStBak(stBak);
			legislationCitymeetingTask.setStBak1(stBak1);
			legislationCitymeetingTask.setStBak2(stBak2);
			this.update(legislationCitymeetingTask);
			if ("submit".equals(op)) {
				LegislationCitymeetingTask legislationCitymeetingTask2 = findByHQL("from LegislationCitymeetingTask t where t.stTopicId='" + stTopicId + "' and t.stNodeId='NOD_0000000180'").get(0);
				legislationCitymeetingTask2.setStTaskStatus("DONE");
				legislationCitymeetingTask2.setStBak(stBak);
				legislationCitymeetingTask2.setStBak1(stBak1);
				legislationCitymeetingTask.setStBak2(stBak2);
				this.update(legislationCitymeetingTask2);
			}
		
			
		
		}
		// 处理附件内容
		legislationFilesService.updateParentIdById(request, stTopicId);
		return stTopicId;
	}
}
