package com.wonders.fzb.legislation.services.impl;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.TeamInfo;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.framework.services.TeamInfoService;
import com.wonders.fzb.legislation.beans.LegislationProcessDeal;
import com.wonders.fzb.legislation.beans.LegislationProcessDoc;
import com.wonders.fzb.legislation.beans.LegislationProcessTask;
import com.wonders.fzb.legislation.beans.LegislationProcessTaskdetail;
import com.wonders.fzb.legislation.dao.LegislationProcessTaskDao;
import com.wonders.fzb.legislation.services.*;
import com.wonders.fzb.simpleflow.beans.WegovSimpleNode;
import com.wonders.fzb.simpleflow.services.WegovSimpleNodeService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;


/**
 * LegislationProcessTask service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationProcessTaskService")
@Transactional
public class LegislationProcessTaskServiceImpl implements LegislationProcessTaskService{

	@Autowired
	private LegislationProcessTaskDao legislationProcessTaskDao;
	@Autowired
	private WegovSimpleNodeService wegovSimpleNodeService;
	@Autowired
	private LegislationProcessDocService legislationProcessDocService;
	@Autowired
	private TeamInfoService teamInfoService;
	@Autowired
	private LegislationProcessDealService legislationProcessDealService;
	@Autowired
	private LegislationProcessTaskdetailService legislationProcessTaskdetailService;
	@Autowired
	private LegislationFilesService legislationFilesService;
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationProcessTask info) {
		legislationProcessTaskDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationProcessTask info) {
		return legislationProcessTaskDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationProcessTask info) {
		legislationProcessTaskDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationProcessTask info) {
		legislationProcessTaskDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationProcessTask findById(String id) {
		return (LegislationProcessTask) legislationProcessTaskDao.load(id);
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
		return legislationProcessTaskDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<LegislationProcessTask> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationProcessTaskDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationProcessTask info) {
		legislationProcessTaskDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationProcessTask> findByHQL(String hql) {
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskDao.findByHQL(hql);
		return legislationProcessTaskList;
	}

	@Override
	public List<Map> findTaskListByNodeId(String stNodeId, String stUserId,
			String UnitId, String roleId) {
		return legislationProcessTaskDao.findTaskListByNodeId(stNodeId, stUserId, UnitId, roleId);
	}

	@Override
	public Page<LegislationProcessDoc> findTaskDocListByNodeId(String sql,
			int pageNo, int pageSize) {
		return legislationProcessTaskDao.findTaskDocListByNodeId(sql, pageNo, pageSize);
	}

	/**
	 * 大节点扭转
	 *
	 * @param stDocId
	 * @param stNodeId
	 */
	@Override
	public void nextProcess(String stDocId, String stNodeId,HttpSession session) {
		List<LegislationProcessTask> list = legislationProcessTaskDao.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId ='" + stDocId + "' and t.stNodeId='" + stNodeId + "' and t.stEnable is null");
		for (LegislationProcessTask legislationProcessTask : list) {
			if("NOD_0000000105".equals(stNodeId)){
				if(legislationProcessTask.getStActive().equals("false")){
					legislationProcessTask.setStActive(null);
					legislationProcessTask.setStTaskStatus("TODO");
					legislationProcessTask.setDtOpenDate(new Date());
					legislationProcessTaskDao.saveOrUpdate(legislationProcessTask);
					return;
				}
			}

			legislationProcessTask.setStTaskStatus("DONE");
			if(stNodeId.equals("NOD_0000000120")){
				legislationProcessTask.setDtDealDate(new Date());
			}
			legislationProcessTaskDao.update(legislationProcessTask);

			LegislationProcessTask nextLegislationProcessTask = new LegislationProcessTask();
			nextLegislationProcessTask.setStDocId(legislationProcessTask.getStDocId());
			nextLegislationProcessTask.setStFlowId(legislationProcessTask.getStFlowId());
			List<WegovSimpleNode> nodeList = wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where 1=1 and t.stNodeId ='" + stNodeId + "'");

			if(!"END".equals(nodeList.get(0).getStNextNode())) {

				nextLegislationProcessTask.setStNodeId(nodeList.get(0).getStNextNode());
				nextLegislationProcessTask.setStNodeName(wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where 1=1 and t.stNodeId ='" + nodeList.get(0).getStNextNode() + "'").get(0).getStNodeName());
				nextLegislationProcessTask.setStTaskStatus("TODO");
				if (stNodeId.equals("NOD_0000000150")||stNodeId.equals("NOD_0000000104")||stNodeId.equals("NOD_0000000105")||stNodeId.equals("NOD_0000000106")) {
					nextLegislationProcessTask.setStTeamId(legislationProcessTask.getStTeamId());
					nextLegislationProcessTask.setStTeamName(legislationProcessTask.getStTeamName());

				}
				else {
					nextLegislationProcessTask.setStTeamId("U_3_1");
				}
				if (stNodeId.equals("NOD_0000000120")) {
					nextLegislationProcessTask.setDtOpenDate(legislationProcessTask.getDtOpenDate());
					nextLegislationProcessTask.setDtDealDate(legislationProcessTask.getDtDealDate());
					UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
					nextLegislationProcessTask.setStTeamId((currentPerson.getTeamInfos().get(0)).getId());
					nextLegislationProcessTask.setStTeamName((currentPerson.getTeamInfos().get(0)).getTeamName());
				} else {
					nextLegislationProcessTask.setDtOpenDate(new Date());
				}
				nextLegislationProcessTask.setStBakOne(legislationProcessTask.getStBakOne());
				nextLegislationProcessTask.setStBakTwo(legislationProcessTask.getStBakTwo());
				nextLegislationProcessTask.setDtBakDate(legislationProcessTask.getDtBakDate());
				nextLegislationProcessTask.setStComment2(legislationProcessTask.getStComment2());

				legislationProcessTaskDao.save(nextLegislationProcessTask);
				legislationProcessDocService.executeSqlUpdate("update LegislationProcessDoc s set s.stNodeName='" + nextLegislationProcessTask.getStNodeName() + "' where s.stDocId='" + nextLegislationProcessTask.getStDocId() + "'");
			}

			if(stNodeId.equals("NOD_0000000170")){
				String[] legDocIdArray=legislationProcessDocService.findById(stDocId).getStDocSource().split("#");
				for(String legDocId:legDocIdArray){
					nextProcess(legDocId, "NOD_0000000105",session);
				}
			}
		}
	}

	/**
	 * 退回（公共）
	 *
	 * @param stDocId
	 * @param stNodeId
	 * @param userRoleId
	 * @param userRole
	 * @param currentPerson
	 */
	@Override
	public void returnProcess(String stDocId, String stNodeId, String userRoleId, String userRole, UserInfo currentPerson) {
		String userId = currentPerson.getUserId();
		String userName = currentPerson.getName();
		String unitId = currentPerson.getTeamInfos().get(0).getId();
		String unitName = currentPerson.getTeamInfos().get(0).getUnitName();

		List<LegislationProcessTask> list = findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId ='" + stDocId + "' and t.stNodeId='" + stNodeId + "' and t.stEnable is null");
		for (LegislationProcessTask legislationProcessTask : list) {
			legislationProcessTask.setStUserId(userId);
			legislationProcessTask.setStUserName(userName);
			legislationProcessTask.setStRoleId(userRoleId);
			legislationProcessTask.setStRoleName(userRole);
			legislationProcessTask.setStTeamId(unitId);
			legislationProcessTask.setStTeamName(unitName);
			legislationProcessTask.setStEnable("UNABLE");
			update(legislationProcessTask);

			List<WegovSimpleNode> nodeList = wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where 1=1 and t.stNextNode ='" + stNodeId + "'");
			String nodeId = nodeList.get(0).getStNodeId();

			LegislationProcessTask lastLegislationProcessTask = findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId ='" + stDocId + "' and t.stNodeId='" + nodeId + "' and t.stEnable is null").get(0);
			lastLegislationProcessTask.setStEnable("UNABLE");
			update(lastLegislationProcessTask);

			LegislationProcessTask newLegislationProcessTask = new LegislationProcessTask();
			newLegislationProcessTask.setStDocId(lastLegislationProcessTask.getStDocId());
			newLegislationProcessTask.setStFlowId(lastLegislationProcessTask.getStFlowId());
			newLegislationProcessTask.setStNodeId(lastLegislationProcessTask.getStNodeId());
			newLegislationProcessTask.setStNodeName(lastLegislationProcessTask.getStNodeName());
			newLegislationProcessTask.setStTaskStatus("DOING");
			newLegislationProcessTask.setDtOpenDate(lastLegislationProcessTask.getDtOpenDate());
			newLegislationProcessTask.setStUserId(lastLegislationProcessTask.getStUserId());
			newLegislationProcessTask.setStUserName(lastLegislationProcessTask.getStUserName());
			newLegislationProcessTask.setStRoleId(lastLegislationProcessTask.getStRoleId());
			newLegislationProcessTask.setStRoleName(lastLegislationProcessTask.getStRoleName());
			newLegislationProcessTask.setStTeamId(lastLegislationProcessTask.getStTeamId());
			newLegislationProcessTask.setStTeamName(lastLegislationProcessTask.getStTeamName());

			add(newLegislationProcessTask);
			legislationProcessDocService.executeSqlUpdate("update LegislationProcessDoc s set s.stNodeName='" + lastLegislationProcessTask.getStNodeName() + "' where s.stDocId='" + lastLegislationProcessTask.getStDocId() + "'");
		}
	}

	/**
	 * 次节点流转（公共）
	 *
	 * @param stDocId
	 * @param stNodeId
	 * @param userRoleId
	 * @param userRole
	 * @param currentPerson
	 */
	@Override
	public void nextChildProcess(String stDocId, String stNodeId, String userRoleId, String userRole, UserInfo currentPerson) {
		String userId = currentPerson.getUserId();
		String userName = currentPerson.getName();
		String unitId = currentPerson.getTeamInfos().get(0).getId();
		String unitName = currentPerson.getTeamInfos().get(0).getUnitName();
		List<LegislationProcessTask> list = findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId ='" + stDocId + "' and t.stNodeId='" + stNodeId + "' and t.stEnable is null");
		for (LegislationProcessTask legislationProcessTask : list) {
			String curStTaskStatus = legislationProcessTask.getStTaskStatus();

			String[] stTaskStatusArray = wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where 1=1 and t.stNodeId ='" + stNodeId + "'").get(0).getStDoneName().split("#");
			for (int i = 0; i < stTaskStatusArray.length; i++) {
				if (curStTaskStatus.equals(stTaskStatusArray[i])) {
					legislationProcessTask.setStTaskStatus(stTaskStatusArray[i + 1]);
					break;
				}
			}
			legislationProcessTask.setStUserId(userId);
			legislationProcessTask.setStUserName(userName);
			legislationProcessTask.setStRoleId(userRoleId);
			legislationProcessTask.setStRoleName(userRole);
			if("NOD_0000000105".equals(stNodeId)){
				LegislationProcessTask lastTask = findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId ='" + stDocId + "' and t.stNodeId='NOD_0000000104' and t.stEnable is null").get(0);
				legislationProcessTask.setStTeamId(lastTask.getStTeamId());
				legislationProcessTask.setStTeamName(lastTask.getStTeamName());
			}else{
				legislationProcessTask.setStTeamId(unitId);
				legislationProcessTask.setStTeamName(unitName);
			}
			update(legislationProcessTask);
		}

	}

	/**
	 * task列表分页
	 * @param sql
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Override
	public Page<LegislationProcessTask> findTaskByNodeId(String sql, int pageNo,
														 int pageSize) {
		return legislationProcessTaskDao.findTaskByNodeId(sql, pageNo, pageSize);
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
	public Page<LegislationProcessDoc> findCheckMeetingByNodeId(String sql, int pageNo, int pageSize) {
		return legislationProcessTaskDao.findCheckMeetingByNodeId(sql, pageNo, pageSize);
	}

	@Override
	public void sendUnit(HttpServletRequest request, UserInfo currentPerson,String userRoleId,String userRole) {
		String teamId = request.getParameter("teamId");
		String stNodeId = request.getParameter("stNodeId");
		String stTaskId=request.getParameter("stTaskId");
		LegislationProcessTask legislationProcessTask=findById(stTaskId);
		legislationProcessTask.setStTaskStatus("DONE");
		update(legislationProcessTask);
		String[] teamIdArray=teamId.split(",");
		WegovSimpleNode node=wegovSimpleNodeService.findById(stNodeId);
		WegovSimpleNode nextNode=wegovSimpleNodeService.findById(node.getStNextNode());

		String userId = currentPerson.getUserId();
		String userName = currentPerson.getName();
		for (int i = 0; i < teamIdArray.length; i++) {
			TeamInfo teamInfo=teamInfoService.findTeamInfoByTeamId("MODULE_LEGISLATE",teamIdArray[i]);
			LegislationProcessTask newTask= new LegislationProcessTask();
			newTask.setStDocId(legislationProcessTask.getStDocId());
			newTask.setStFlowId(legislationProcessTask.getStFlowId());
			newTask.setStBakOne(legislationProcessTask.getStBakOne());
			newTask.setStBakTwo(legislationProcessTask.getStBakTwo());
			newTask.setStNodeId(nextNode.getStNodeId());
			newTask.setStNodeName(nextNode.getStNodeName());
			newTask.setStTaskStatus("TODO");
			newTask.setDtOpenDate(legislationProcessTask.getDtOpenDate());
			newTask.setDtDealDate(legislationProcessTask.getDtDealDate());
			newTask.setStUserId(userId);
			newTask.setStUserName(userName);
			newTask.setStRoleId(userRoleId);
			newTask.setStRoleName(userRole);
//			newTask.setStTeamId((currentPerson.getTeamInfos().get(0)).getId());
//			newTask.setStTeamName((currentPerson.getTeamInfos().get(0)).getTeamName());
			newTask.setStParentId(teamInfo.getId());
			newTask.setStComment1(teamInfo.getTeamName());
			add(newTask);
		}
	}

	@Override
	public void addOpinion(HttpServletRequest request, UserInfo currentPerson) {
		String stTaskId=request.getParameter("stTaskId");
		String opinion=request.getParameter("opinion");
		String stNodeId=request.getParameter("stNodeId");
		WegovSimpleNode node=wegovSimpleNodeService.findById(stNodeId);
		String userId = currentPerson.getUserId();
		String userName = currentPerson.getName();
		LegislationProcessTask legislationProcessTask=findById(stTaskId);
		legislationProcessTask.setStComment2(opinion);
		legislationProcessTask.setDtBakDate(new Date());
		legislationProcessTask.setStTaskStatus("DONE");
		update(legislationProcessTask);
		LegislationProcessDoc legislationProcessDoc=legislationProcessDocService.findById(legislationProcessTask.getStDocId());
		LegislationProcessDeal legislationProcessDeal = new LegislationProcessDeal();
		legislationProcessDeal.setStDocId(legislationProcessDoc.getStDocId());
		legislationProcessDeal.setStActionId(stNodeId);
		legislationProcessDeal.setStActionName(node.getStNodeName());
		legislationProcessDeal.setStUserId(userId);
		legislationProcessDeal.setStUserName(userName);
		legislationProcessDeal.setStBakOne(legislationProcessDoc.getStDocName());
		legislationProcessDeal.setStBakTwo(legislationProcessDoc.getStComent());
		legislationProcessDeal.setDtDealDate(new Date());
		legislationProcessDealService.add(legislationProcessDeal);
	}

	@Override
	public void saveTaskCheck(HttpServletRequest request,UserInfo currentPerson,String userRoleId,String userRole) throws ParseException {
		String stTaskId = request.getParameter("stTaskId");
		String stComent= request.getParameter("stComent");
		String stTaskStatus =request.getParameter("stTaskStatus");
		String nodeId = request.getParameter("stNodeId");

		LegislationProcessTaskdetail legislationProcessTaskdetail = new LegislationProcessTaskdetail();
		legislationProcessTaskdetail.setStTaskId(stTaskId);
		legislationProcessTaskdetail.setStTaskStatus(stTaskStatus);
		legislationProcessTaskdetail.setStContent(stComent);

		if(nodeId.equals("NOD_0000000104")){
			if("TODO".equals(stTaskStatus)){
				legislationProcessTaskdetail.setStTitle("OA审核");
			}else if("TODO-RETURN".equals(stTaskStatus)){
				legislationProcessTaskdetail.setStTitle("OA审核结果");
			}
		}else if(nodeId.equals("NOD_0000000170")){
			if("TODO".equals(stTaskStatus)){
				legislationProcessTaskdetail.setStTitle("会议发布");
			}else if("TODO-RETURN".equals(stTaskStatus)){
				legislationProcessTaskdetail.setStTitle("会议发布结果");
			}
		}else{
			if("SEND".equals(stTaskStatus)){
				legislationProcessTaskdetail.setStTitle("审核");
			}else if("PUBLISH".equals(stTaskStatus)){
				legislationProcessTaskdetail.setStTitle("发布");
			}else if("SEND-RETURN".equals(stTaskStatus)){
				legislationProcessTaskdetail.setStTitle("审核结果");
			}else if("GATHER-RETURN".equals(stTaskStatus)){
				legislationProcessTaskdetail.setStTitle("网上报名结果");
			}
		}

		if(nodeId.equals("NOD_0000000131")){
			if("SEND".equals(stTaskStatus)){
				String stComment1=request.getParameter("stComment1");
				LegislationProcessTask legislationProcessTask=findById(stTaskId);
				legislationProcessTask.setStComment1(stComment1);
				update(legislationProcessTask);
			}
			if("PUBLISH".equals(stTaskStatus)){
				String stBakOne=request.getParameter("stBakOne");
				String dtBakDate=request.getParameter("dtBakDate");
				String dtDeadDate=request.getParameter("dtDeadDate");
				LegislationProcessTask legislationProcessTask=findById(stTaskId);
				legislationProcessTask.setStBakOne(stBakOne);
				legislationProcessTask.setDtBakDate(DateUtils.parseDate(dtBakDate,"yyyy-MM-dd"));
				legislationProcessTask.setDtDeadDate(DateUtils.parseDate(dtDeadDate,"yyyy-MM-dd"));
				update(legislationProcessTask);
			}
		}
		String stTaskDetailId = legislationProcessTaskdetailService.addObj(legislationProcessTaskdetail);

		Enumeration keys=request.getParameterNames();
		while(keys.hasMoreElements()){
			String key=(String)keys.nextElement();
			String value=request.getParameter(key);
			if(value.startsWith("FIL_")){
				legislationFilesService.executeSqlUpdate("update LegislationFiles s set s.stParentId='"+stTaskDetailId+"' where s.stFileId='"+value+"'");
			}
		}
		if(!"PUBLISH".equals(stTaskStatus)&&!"SEND".equals(stTaskStatus)&&!"SEND-RETURN".equals(stTaskStatus) &&! "GATHER-RETURN".equals(stTaskStatus)&&! "TODO-RETURN".equals(stTaskStatus)){
			String stDocId = request.getParameter("stDocId");
			String stNodeId = request.getParameter("stNodeId");
			nextChildProcess(stDocId,stNodeId,userRoleId,userRole,currentPerson);
		}
		if(nodeId.equals("NOD_0000000170")&& "TODO".equals(stTaskStatus)){
			String stDocId = request.getParameter("stDocId");
			String[] legDocIdArray = legislationProcessDocService.findById(stDocId).getStDocSource().split("#");
			for(String legDocId:legDocIdArray){
				nextChildProcess(legDocId,"NOD_0000000105",userRoleId,userRole,currentPerson);
			}
		}
	}

	@Override
	public void dealFinish(HttpServletRequest request, HttpSession session) {
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");

		String stComent= request.getParameter("stComent");

		List<LegislationProcessTask> list = findByHQL("from LegislationProcessTask t where t.stDocId='"+stDocId+"' and t.stNodeId='NOD_0000000103' and t.stEnable is null");
		LegislationProcessTask legislationProcessTask = list.get(0);

		legislationProcessTask.setStComment2(stComent);
		update(legislationProcessTask);


		String stTaskId = legislationProcessTask.getStTaskId();

		Enumeration keys=request.getParameterNames();
		while(keys.hasMoreElements()){
			String key=(String)keys.nextElement();
			String value=request.getParameter(key);
			if(value.startsWith("FIL_")){
				legislationFilesService.executeSqlUpdate("update LegislationFiles s set s.stParentId='"+stTaskId+"' where s.stFileId='"+value+"'");
			}
		}
		nextProcess(stDocId,"NOD_0000000103",session);

	}

	@Override
	public void saveOnlineSummary(HttpServletRequest request) {
		String stTaskId = request.getParameter("stTaskId");
		String stBakTwo=request.getParameter("stBakTwo");
		LegislationProcessTask legislationProcessTask=findById(stTaskId);
		legislationProcessTask.setStBakTwo(stBakTwo);
		update(legislationProcessTask);
		Enumeration keys=request.getParameterNames();
		while(keys.hasMoreElements()){
			String key=(String)keys.nextElement();
			String value=request.getParameter(key);
			if(value.startsWith("FIL_")){
				legislationFilesService.executeSqlUpdate("update LegislationFiles s set s.stParentId='"+legislationProcessTask.getStDocId()+"' where s.stFileId='"+value+"'");
			}
		}
	}
}
