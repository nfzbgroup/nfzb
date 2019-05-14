package com.wonders.fzb.legislation.services.impl;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.framework.services.UserInfoService;
import com.wonders.fzb.legislation.beans.LegislationProcessDeal;
import com.wonders.fzb.legislation.beans.LegislationProcessDoc;
import com.wonders.fzb.legislation.beans.LegislationProcessTask;
import com.wonders.fzb.legislation.beans.LegislationProcessTaskdetail;
import com.wonders.fzb.legislation.beans.LegislationSendNotice;
import com.wonders.fzb.legislation.dao.LegislationProcessDocDao;
import com.wonders.fzb.legislation.services.LegislationFilesService;
import com.wonders.fzb.legislation.services.LegislationProcessDealService;
import com.wonders.fzb.legislation.services.LegislationProcessDocService;
import com.wonders.fzb.legislation.services.LegislationProcessTaskService;
import com.wonders.fzb.legislation.services.LegislationProcessTaskdetailService;
import com.wonders.fzb.legislation.services.LegislationSendNoticeService;
import com.wonders.fzb.simpleflow.beans.WegovSimpleNode;
import com.wonders.fzb.simpleflow.services.WegovSimpleNodeService;
import dm.jdbc.util.StringUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LegislationProcessDoc service实现
 * 
 * @author scalffold created by lj
 */

@Service("legislationProcessDocService")
@Transactional
public class LegislationProcessDocServiceImpl implements LegislationProcessDocService {

	@Autowired
	private LegislationProcessDocDao legislationProcessDocDao;
	@Autowired
	private LegislationProcessTaskService legislationProcessTaskService;
	@Autowired
	private LegislationProcessDealService legislationProcessDealService;
	@Autowired
	private LegislationFilesService legislationFilesService;
	@Autowired
	private WegovSimpleNodeService wegovSimpleNodeService;
	@Autowired
	private LegislationProcessTaskdetailService legislationProcessTaskdetailService;
	@Autowired
	private LegislationSendNoticeService legislationSendNoticeService;
	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationProcessDoc info) {
		legislationProcessDocDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationProcessDoc info) {
		return legislationProcessDocDao.saveObj(info);
	}

	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationProcessDoc info) {
		legislationProcessDocDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationProcessDoc info) {
		legislationProcessDocDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationProcessDoc findById(String id) {
		return (LegislationProcessDoc) legislationProcessDocDao.load(id);
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
		return legislationProcessDocDao.findByPage(condMap, sortMap, pageNo, pageSize);
	}

	/**
	 * 根据Map中过滤条件、排序条件进行查询.
	 * 
	 * @param condMap 过滤条件<propertyName,properyValue>
	 * @param sortMap 排序条件<propertyName,properyValue>
	 * @return
	 */
	@Override
	public List<LegislationProcessDoc> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationProcessDocDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationProcessDoc info) {
		legislationProcessDocDao.saveOrUpdate(info);
	}

	@Override
	public List<LegislationProcessDoc> findByHQL(String hql) {
		List<LegislationProcessDoc> legislationProcessDocList = legislationProcessDocDao.findByHQL(hql);
		return legislationProcessDocList;
	}
	
	@Override
	public List<Map> findMapByHQL(String hql){
		List<Map> findMapByHQL = legislationProcessDocDao.findMapByHQL(hql);
		return findMapByHQL;
	}
	
	

	@Override
	public void executeSqlUpdate(String sql) {
		legislationProcessDocDao.executeSqlUpdate(sql);
	}

	/**
	 * 新增修改草案
	 *
	 * @param request
	 * @param currentPerson
	 */
	@Override
	public void editLegislationProcessDoc(HttpServletRequest request, UserInfo currentPerson, String userRoleId, String userRole) {
		String docId = request.getParameter("docId");
		String docName = request.getParameter("docName");
		String stComment = request.getParameter("stComent");

		String userId = currentPerson.getUserId();
		String userName = currentPerson.getName();
		String unitId = currentPerson.getTeamInfos().get(0).getId();
		String unitName = currentPerson.getTeamInfos().get(0).getUnitName();
		if (StringUtil.isEmpty(docId) || "null".equals(docId)) {
			LegislationProcessDoc legislationProcessDoc = new LegislationProcessDoc();
			legislationProcessDoc.setStDocName(docName);
			legislationProcessDoc.setStUnitId(unitId);
			legislationProcessDoc.setStUnitName(unitName);
			legislationProcessDoc.setStUserId(userId);
			legislationProcessDoc.setStUserName(userName);
			legislationProcessDoc.setStComent(stComment);
			legislationProcessDoc.setStNodeId("NOD_0000000101");
			legislationProcessDoc.setStNodeName("草案起草");
			legislationProcessDoc.setDtCreateDate(new Date());

			docId = addObj(legislationProcessDoc);
			legislationProcessDoc.setStDocNo(docId);
			update(legislationProcessDoc);

			LegislationProcessTask newTask = new LegislationProcessTask();
			newTask.setStDocId(docId);
			newTask.setStFlowId(docName);
			newTask.setStNodeId(legislationProcessDoc.getStNodeId());
			newTask.setStNodeName(legislationProcessDoc.getStNodeName());
			newTask.setStTaskStatus("TODO");
			newTask.setDtOpenDate(new Date());
			newTask.setStUserId(legislationProcessDoc.getStUserId());
			newTask.setStUserName(legislationProcessDoc.getStUserName());
			newTask.setStRoleId(userRoleId);
			newTask.setStRoleName(userRole);
			newTask.setStTeamId((currentPerson.getTeamInfos().get(0)).getId());
			newTask.setStTeamName((currentPerson.getTeamInfos().get(0)).getTeamName());
			legislationProcessTaskService.add(newTask);

			LegislationProcessDeal legislationProcessDeal = new LegislationProcessDeal();
			legislationProcessDeal.setStDocId(legislationProcessDoc.getStDocId());
			legislationProcessDeal.setStActionId(legislationProcessDoc.getStNodeId());
			legislationProcessDeal.setStActionName(legislationProcessDoc.getStNodeName());
			legislationProcessDeal.setStUserId(legislationProcessDoc.getStUserId());
			legislationProcessDeal.setStUserName(legislationProcessDoc.getStUserName());
			legislationProcessDeal.setStBakOne(legislationProcessDoc.getStDocName());
			legislationProcessDeal.setStBakTwo(legislationProcessDoc.getStComent());
			legislationProcessDeal.setDtDealDate(new Date());
			legislationProcessDealService.add(legislationProcessDeal);

		} else {
			LegislationProcessDoc legislationProcessDoc = findById(docId);
			legislationProcessDoc.setStDocName(docName);
			legislationProcessDoc.setStComent(stComment);
			update(legislationProcessDoc);

			LegislationProcessDeal legislationProcessDeal = legislationProcessDealService.findByHQL("from LegislationProcessDeal t where 1=1 and t.stDocId='" + docId + "' and t.stActionId='" + legislationProcessDoc.getStNodeId() + "'").get(0);
			legislationProcessDeal.setStBakOne(docName);
			legislationProcessDeal.setStBakTwo(stComment);
			legislationProcessDeal.setDtDealDate(new Date());
			legislationProcessDealService.update(legislationProcessDeal);

		}
		legislationFilesService.updateParentIdById(request, docId);
	}

	/**
	 * 提交分办
	 *
	 * @param request
	 */
	@Override
	public void draft_dist_info(HttpServletRequest request) throws ParseException {
		String action = request.getParameter("action");
		String stDocId = request.getParameter("stDocId");
		String stComment1 = request.getParameter("stComment1");// 分办意见
		String transactDate = request.getParameter("transactDate");// 办理时限
		String stDealId = request.getParameter("stDealId");
		String stDealName = request.getParameter("stDealName");

		LegislationProcessDoc legislationProcessDoc = findById(stDocId);

		// 修改当前task状态，并生成下一个节点的task
		List<LegislationProcessTask> list = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, "NOD_0000000102");
		for (LegislationProcessTask legislationProcessTask : list) {// 修改当前task状态
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
			legislationProcessTask.setStComment1(stComment1);
			if (StringUtils.hasText(transactDate) && transactDate.length() > 3) {
				legislationProcessTask.setDtDeadDate(formatter.parse(transactDate));
			}
			if (StringUtils.hasText(stDealId)) {// 有选中值的时候再赋值
				legislationProcessTask.setStDealId(stDealId);
				legislationProcessTask.setStDealName(stDealName);
			}

			if (action.equals("submit")) {// 党委提交的时候才改变状态并生成新的task
				legislationProcessTask.setDtCloseDate(new Date());
				legislationProcessTask.setStTaskStatus("DONE");
				legislationProcessTaskService.update(legislationProcessTask);

				// 新增一个Task
				LegislationProcessTask nextLegislationProcessTask = new LegislationProcessTask();
				nextLegislationProcessTask.setStDocId(legislationProcessTask.getStDocId());
				nextLegislationProcessTask.setStFlowId(legislationProcessTask.getStFlowId());
				List<WegovSimpleNode> nodeList = wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where 1=1 and t.stNodeId ='" + legislationProcessTask.getStNodeId() + "'");
				nextLegislationProcessTask.setStNodeId(nodeList.get(0).getStNextNode());
				nextLegislationProcessTask.setStNodeName(wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where 1=1 and t.stNodeId ='" + nodeList.get(0).getStNextNode() + "'").get(0).getStNodeName());
				nextLegislationProcessTask.setStTaskStatus("TODO");
				nextLegislationProcessTask.setStDealId(stDealId);
				nextLegislationProcessTask.setStDealName(stDealName);
				nextLegislationProcessTask.setDtOpenDate(new Date());

				legislationProcessTaskService.add(nextLegislationProcessTask);
				executeSqlUpdate("update LegislationProcessDoc s set s.stNodeName='" + nextLegislationProcessTask.getStNodeName() + "' where s.stDocId='" + nextLegislationProcessTask.getStDocId() + "'");

			} else {// 保存的话，就保存修改
				legislationProcessTaskService.update(legislationProcessTask);
			}

		}
	}

	// 保存草案信息
	@Override
	public String saveLegislation(HttpServletRequest request, UserInfo currentPerson, String userRoleId, String userRole, String stNodeId, String stNodeName) throws Exception {
		String stTaskId = request.getParameter("stTaskId");
		String stBakOne = request.getParameter("stBakOne");
		String stDocId = request.getParameter("stDocId");
		String stBakTwo = request.getParameter("stBakTwo");
		String dtBakDate = request.getParameter("dtBakDate");  
		String stComment1 = request.getParameter("stComment1");
		String stComment2 = request.getParameter("stComment2");
		String stNoticeId = request.getParameter("stNoticeId");
		String newDocName = request.getParameter("newDocName");
		String stPersonsId = request.getParameter("stPersonsId");
		String stPersons = request.getParameter("stPersons");
		String nodeStatus = request.getParameter("nodeStatus");// 可能有状态过来

		String userId = currentPerson.getUserId();
		String userName = currentPerson.getName();
		if(null!=stNoticeId&&!stNoticeId.isEmpty()) {
			//部门反馈
			LegislationSendNotice legislationSendNotice = legislationSendNoticeService.findById(stNoticeId);
			legislationSendNotice.setStFeedbackContent(stComment2);
			legislationSendNoticeService.update(legislationSendNotice);
		}else {
			if (stTaskId != null && !stTaskId.isEmpty()) {
				LegislationProcessTask legislationProcessTask = legislationProcessTaskService.findById(stTaskId);
				stDocId = legislationProcessTask.getStDocId();
			}
			LegislationProcessDoc legislationProcessDoc=null;
			if(null!=stDocId&&!stDocId.isEmpty()) {
				legislationProcessDoc = findById(stDocId);
			}
			// 一个任务中修改了草案的名称，就修改[主表中]的草案，同时记录在自己这条任务中。
			if (StringUtil.isNotEmpty(newDocName)) {
				legislationProcessDoc.setStDocName(newDocName);
				update(legislationProcessDoc);
				List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId='" + stDocId + "'");
				legislationProcessTaskList.forEach((LegislationProcessTask newLegislationProcessTask) -> {
					newLegislationProcessTask.setStFlowId(newDocName);
					legislationProcessTaskService.update(newLegislationProcessTask);
				});
			}
			if (StringUtil.isEmpty(stTaskId) || "null".equals(stTaskId)) {
				// 如果没有任务ID，就产生一个新的任务，任务节点就是传入的节点。
				LegislationProcessTask newTask = new LegislationProcessTask();
				newTask.setStDocId(stDocId);
				newTask.setStFlowId(legislationProcessDoc.getStDocName());
				newTask.setStBakOne(stBakOne);
				newTask.setStBakTwo(stBakTwo);
				if (StringUtil.isNotEmpty(dtBakDate)) {
					newTask.setDtBakDate(DateUtils.parseDate(dtBakDate, "yyyy-MM-dd"));
				} else {
					newTask.setDtBakDate(null);
				}
				newTask.setStComment1(stComment1);
				newTask.setStComment2(stComment2);
				newTask.setStNodeId(stNodeId);
				newTask.setStNodeName(stNodeName);
				newTask.setStTaskStatus("TODO");
				newTask.setDtOpenDate(new Date());
				newTask.setStUserId(userId);
				newTask.setStUserName(userName);
				newTask.setStRoleId(userRoleId);
				newTask.setStRoleName(userRole);
				newTask.setStTeamId((currentPerson.getTeamInfos().get(0)).getId());
				newTask.setStTeamName((currentPerson.getTeamInfos().get(0)).getTeamName());
				stTaskId = legislationProcessTaskService.addObj(newTask);

				// 产生一个操作记录
				LegislationProcessDeal legislationProcessDeal = new LegislationProcessDeal();
				legislationProcessDeal.setStDocId(stDocId);
				legislationProcessDeal.setStActionId(stNodeId);
				legislationProcessDeal.setStActionName(stNodeName);
				legislationProcessDeal.setStUserId(userId);
				legislationProcessDeal.setStUserName(userName);
				legislationProcessDeal.setStBakOne(legislationProcessDoc.getStDocName());
				legislationProcessDeal.setStBakTwo(legislationProcessDoc.getStComent());
				legislationProcessDeal.setDtDealDate(new Date());
				legislationProcessDealService.add(legislationProcessDeal);

			} else {
				// 如果有任务，就修改保存任务
				LegislationProcessTask legislationProcessTask = legislationProcessTaskService.findById(stTaskId);
				List<WegovSimpleNode> nodeList = wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where 1=1 and t.stNodeId ='" + stNodeId + "'");
				WegovSimpleNode curentNode = nodeList.get(0);

				// 如果有状态区分，统一保存的taskDetail中。2019年4月9日10:11:45 add 刘军
				if (curentNode.getStDoneName().split("#").length > 2) {

					List<LegislationProcessTaskdetail> detailList = legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where 1=1 and t.stTaskId ='" + stTaskId + "' and t.stTaskStatus='" + legislationProcessTask.getStTaskStatus() + "'");
					// 如果已经有detail，就修改，没有就新增加一个
					if (detailList.size() == 1) {
						LegislationProcessTaskdetail newDetail = detailList.get(0);
						newDetail.setStBak1(stComment2);
						newDetail.setDtBak1(new Date());
						newDetail.setStPersonId(stPersonsId);
						newDetail.setStPersonName(stPersons);
						legislationProcessTaskdetailService.update(newDetail);
					} else {
						LegislationProcessTaskdetail newDetail = new LegislationProcessTaskdetail();
						newDetail.setStNodeId(stNodeId);
						newDetail.setStTaskId(stTaskId);
						newDetail.setStTaskStatus(legislationProcessTask.getStTaskStatus());
						newDetail.setStBak1(stComment2);
						newDetail.setDtOpenDate(new Date());
						legislationProcessTaskdetailService.add(newDetail);
						// return stTaskId;
					}
				} else {
						legislationProcessTask.setStBakOne(stBakOne);
						legislationProcessTask.setStBakTwo(stBakTwo);
					if (StringUtil.isNotEmpty(dtBakDate)) {
						legislationProcessTask.setDtBakDate(DateUtils.parseDate(dtBakDate, "yyyy-MM-dd"));
					} else {
						legislationProcessTask.setDtBakDate(null);
					}
					if(("NOD_0000000121".equals(stNodeId)||"NOD_0000000161".equals(stNodeId))&&(legislationProcessTask.getStComment1()!=null||StringUtil.isNotEmpty(legislationProcessTask.getStComment1()))) {
						//部门意见征求-已发送OA 
					}else{
						legislationProcessTask.setStComment1(stComment1);
					}
					
					legislationProcessTask.setStComment2(stComment2);
					legislationProcessTaskService.update(legislationProcessTask);
				}
				// 产生处理记录
				List<LegislationProcessDeal> list = legislationProcessDealService.findByHQL("from LegislationProcessDeal t where 1=1 and t.stDocId='" + stDocId + "' and t.stActionId='" + stNodeId + "'");
				if (list != null && list.size() > 0) {
					LegislationProcessDeal legislationProcessDeal = list.get(0);
					legislationProcessDeal.setStBakOne(legislationProcessDoc.getStDocName());
					legislationProcessDeal.setStBakTwo(legislationProcessDoc.getStComent());
					legislationProcessDeal.setStUserId(userId);
					legislationProcessDeal.setStUserName(userName);
					legislationProcessDeal.setDtDealDate(new Date());
					legislationProcessDealService.update(legislationProcessDeal);
				} else {
					LegislationProcessDeal legislationProcessDeal = new LegislationProcessDeal();
					legislationProcessDeal.setStDocId(stDocId);
					legislationProcessDeal.setStActionId(stNodeId);
					if ("NOD_0000000151".equals(stNodeId)) {
						legislationProcessDeal.setStActionName("专家论证会会后");
					} else {
						legislationProcessDeal.setStActionName("立法听证会会后");
					}
					legislationProcessDeal.setStUserId(userId);
					legislationProcessDeal.setStUserName(userName);
					legislationProcessDeal.setStBakOne(legislationProcessDoc.getStDocName());
					legislationProcessDeal.setStBakTwo(legislationProcessDoc.getStComent());
					legislationProcessDeal.setDtDealDate(new Date());
					legislationProcessDealService.add(legislationProcessDeal);
				}

			}
		}
		
		// 处理附件内容
		//legislationFilesService.updateParentIdById(request, stDocId);
		return stTaskId;
	}

	@Override
	public List<UserInfo> findUserInfoListByString(String personIds) {
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		List<UserInfo> userInfoList=null;
		if(null!=personIds&&StringUtils.hasText(personIds)) {
			//获取人员信息集合
			List<String> idList=new ArrayList<String>();
			if(personIds.contains(",")) {
				String []personsIdArrray=personIds.split(",");
				for(String personId:personsIdArrray) {
					idList.add(personId);
				}
			}else {
				idList.add(personIds);
			}
			condMap.put("userIdList", idList);
			userInfoList = userInfoService.findUserInfoList(condMap, sortMap);
		}
		return userInfoList;
	}
}
