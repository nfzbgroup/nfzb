package com.wonders.fzb.report.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.legislation.beans.LegislationProcessDoc;
import com.wonders.fzb.legislation.services.LegislationFilesService;
import com.wonders.fzb.legislation.services.LegislationProcessDocService;
import com.wonders.fzb.legislation.services.LegislationSendNoticeService;
import com.wonders.fzb.plan.beans.LegislationPlan;
import com.wonders.fzb.plan.beans.LegislationPlanDeal;
import com.wonders.fzb.plan.beans.LegislationPlanTask;
import com.wonders.fzb.plan.services.LegislationPlanDealService;
import com.wonders.fzb.plan.services.LegislationPlanService;
import com.wonders.fzb.plan.services.LegislationPlanTaskService;
import com.wonders.fzb.report.beans.LegislationReport;
import com.wonders.fzb.report.beans.LegislationReportTask;
import com.wonders.fzb.report.dao.LegislationReportTaskDao;
import com.wonders.fzb.report.services.LegislationReportService;
import com.wonders.fzb.report.services.LegislationReportTaskService;
import com.wonders.fzb.simpleflow.beans.WegovSimpleNode;
import com.wonders.fzb.simpleflow.services.WegovSimpleNodeService;

import dm.jdbc.util.StringUtil;


/**
 * LegislationReportTask service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationReportTaskService")
@Transactional
public class LegislationReportTaskServiceImpl implements LegislationReportTaskService {

	@Autowired
	private LegislationReportTaskDao legislationReportTaskDao;
	
	@Autowired
	@Qualifier("legislationReportService")
	private LegislationReportService legislationReportService;
	
	@Autowired
	@Qualifier("legislationReportTaskService")
	private LegislationReportTaskService legislationReportTaskService;
	
	@Autowired
	@Qualifier("legislationProcessDocService")
	private LegislationProcessDocService legislationProcessDocService;
	
	@Autowired
	@Qualifier("legislationPlanTaskService")
	private LegislationPlanTaskService legislationPlanTaskService;
	
	@Autowired
	@Qualifier("legislationPlanDealService")
	private LegislationPlanDealService legislationPlanDealService;
	
	@Autowired
	@Qualifier("wegovSimpleNodeService")
	private WegovSimpleNodeService wegovSimpleNodeService;
	
	@Autowired
	@Qualifier("legislationPlanService")
	private LegislationPlanService legislationPlanService;
	
    @Autowired
    @Qualifier("legislationFilesService")
    private LegislationFilesService legislationFilesService;
    
    @Autowired
    @Qualifier("legislationSendNoticeService")
    private LegislationSendNoticeService legislationSendNoticeService;
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationReportTask info) {
		legislationReportTaskDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationReportTask info) {
		return legislationReportTaskDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationReportTask info) {
		legislationReportTaskDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationReportTask info) {
		legislationReportTaskDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationReportTask findById(String id) {
		return (LegislationReportTask) legislationReportTaskDao.load(id);
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
		return legislationReportTaskDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<LegislationReportTask> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationReportTaskDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationReportTask info) {
		legislationReportTaskDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationReportTask> findByHQL(String hql) {
		List<LegislationReportTask> legislationReportTaskList = legislationReportTaskDao.findByHQL(hql);
		return legislationReportTaskList;
	}
	
	@Override
	public Page<LegislationReport> findReportTasksByNodeId(String sql, int pageNo, int pageSize) throws ParseException {
		return legislationReportTaskDao.findReportTasksByNodeId(sql, pageNo, pageSize);
	}
	
	
	@Override
	public void saveReport(HttpServletRequest request, HttpSession session) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String op = request.getParameter("op");
		System.out.println("保存还是提交？op=" + op);
		String stTaskStatus = request.getParameter("stTaskStatus");
		System.out.println("任务状态？stTaskStatus=" + stTaskStatus);
		String[] personFeedback = request.getParameterValues("ddd");
		String allPersonFeedBack = request.getParameter("allPersonFeedBack");
		String allPersonFeedBackTime = request.getParameter("allPersonFeedBackTime");
		System.out.println("allPersonFeedBack------------" + allPersonFeedBack);
		System.out.println("allPersonFeedBackTime------------" + allPersonFeedBackTime);
//		String stType = request.getParameter("stType");
		String stAddress = request.getParameter("stAddress");
//		String stPersons = request.getParameter("stPersons");
		String stPersonsId = request.getParameter("stPersonsId");
		String stReportId = request.getParameter("stReportId");
		String stReportName = request.getParameter("stReportName");
		String stDocNo = request.getParameter("stDocNo");
		String stDocSource = request.getParameter("stDocSource");// 包含的草案ID，#
		String stNodeName = request.getParameter("stNodeName");
		String dtBeginDate = request.getParameter("dtBeginDate");
		String stComent = request.getParameter("stComent");
		String stComment1 = request.getParameter("stComment1");//领导意见
		String stComment2 = request.getParameter("stComment2");//报审说明
		String stBakOne = request.getParameter("stBakOne");//结果说明
		String stBakTwo = request.getParameter("stBakTwo");//报送市领导
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String stUserId = currentPerson.getUserId();
		String stUserName = currentPerson.getName();
		String unitId = currentPerson.getTeamInfos().get(0).getId();
		String stTeamName = currentPerson.getTeamInfos().get(0).getTeamName();
		String unitName = currentPerson.getTeamInfos().get(0).getUnitName();
		String userRoleId = session.getAttribute("userRoleId").toString();
		String userRole = session.getAttribute("userRole").toString();
		LegislationReport legislationReport = new LegislationReport();
		
		// 当页面没有传递议题编号 并且有会议id时候
		if (StringUtil.isNotEmpty(stReportId)) {
			legislationReport = legislationReportService.findById(stReportId);
			if (StringUtil.isEmpty(stDocSource)) {
				stDocSource = legislationReport.getStSourceDoc();
				System.out.println("stDocSource----" + stDocSource);
			}
		}
		//保存
		LegislationReportTask legislationReportTask = findByHQL("from LegislationReportTask t where t.stReportId='" + stReportId + "' and t.stNodeId='NOD_0000000190'").get(0);
		legislationReportTask.setStTeamId(unitId);
		legislationReportTask.setStTeamName(stTeamName);
		legislationReportTask.setStUserId(stUserId);
		legislationReportTask.setStUserName(stUserName);
		//立法草案类型
		String stSourceDoc = legislationReport.getStSourceDoc();
		String[] doc = stSourceDoc.split("_");
		if ("TODO".equals(stTaskStatus)) {
			// 如果是修改签报
			if (StringUtil.isEmpty(stReportId)) {} else {
				// 如果是TODO的修改
				legislationReport.setStReportName(stReportName);
//				legislationReport.setStType(stType);
				legislationReport.setStTopic(stDocNo);
//				legislationReport.setStNodeName(stNodeName);
				legislationReport.setStTopic(stComent);
				legislationReport.setStSourceDoc(stDocSource);// 包含的草案
				legislationReport.setStAddress(stAddress);
				legislationReport.setStPersons(stPersonsId);
//				legislationReport.setDtBeginDate(formatter.parse(dtBeginDate));// 时间
				legislationReportService.update(legislationReport);
				if ("submit".equals(op)) {
					//送审领导
					legislationSendNoticeService.sendNotice(stPersonsId, "签报", stReportId, "签报件报OA审核");
					legislationReportTask.setStTaskStatus("INPUT");
					this.update(legislationReportTask);
				}
				if("PLA".equals(doc[0])) {
					//修改计划名称
					LegislationPlan legislationPlan = legislationPlanService.findById(stSourceDoc);
					legislationPlan.setStPlanName(stReportName);
					legislationPlanService.update(legislationPlan);
				}else if("DFT".equals(doc[0])) {
					//修改草案名称
					LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocSource);
					legislationProcessDoc.setStDocName(stReportName);
					legislationProcessDocService.update(legislationProcessDoc);
				}

			}

		} else if ("INPUT".equals(stTaskStatus)) {// 如果不是TODO，是另一个环节
			String stFeedback = request.getParameter("stFeedback");// 反馈信息
			legislationReportTask.setStParentId(stFeedback);
			legislationReportTask.setStComment1(stComment1);
			if ("submit".equals(op) && "INPUT".equals(legislationReportTask.getStTaskStatus())) {
				legislationReportTask.setStTaskStatus("DOING");
			}
			this.update(legislationReportTask);
		}

		// 如果不是TODO，是另一个环节，准备办公厅签报
		else if ("DOING".equals(stTaskStatus)) {
			String stSummary = request.getParameter("stSummary");// 纪要信息
			legislationReportTask.setStDealName(stSummary);
			legislationReportTask.setStComment2(stComment2);
			legislationReportTask.setStBakTwo(stBakTwo);
			if ("submit".equals(op) && "DOING".equals(legislationReportTask.getStTaskStatus())) {
				legislationReportTask.setStTaskStatus("RESULT");
			}
			this.update(legislationReportTask);
		}
		// 如果不是TODO，是另一个环节
		else if ("RESULT".equals(stTaskStatus)) {
			legislationReportTask.setStBakOne(stBakOne);
			if ("submit".equals(op) && "RESULT".equals(legislationReportTask.getStTaskStatus())) {
				if("PLA".equals(doc[0])) {
					//为计划草案，生成送审稿
					LegislationPlan legislationPlan = legislationPlanService.findById(stSourceDoc);
					List<WegovSimpleNode> nodeList = wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where 1=1 and t.stNodeId ='NOD_0000000212'" );
					WegovSimpleNode curentNode = nodeList.get(0);
					String nextNodeId=curentNode.getStNextNode();
					WegovSimpleNode nextNode = wegovSimpleNodeService.findById(nextNodeId);
					LegislationPlanTask legislationPlanTaskNew=new LegislationPlanTask();
					legislationPlanTaskNew.setStPlanId(stSourceDoc);
					legislationPlanTaskNew.setStRoleId(userRoleId);
					legislationPlanTaskNew.setStRoleName(userRole);
					legislationPlanTaskNew.setStNodeId(curentNode.getStNextNode());
					legislationPlanTaskNew.setStNodeName(nextNode.getStNodeName());
					legislationPlanTaskNew.setStTaskStatus("TODO");
					legislationPlanTaskNew.setStFlowId(legislationPlan.getStPlanName());
					legislationPlanTaskNew.setDtOpenDate(new Date());
					legislationPlanTaskService.add(legislationPlanTaskNew);
					//修改立法计划-审签task的状态
					LegislationPlanTask legislationPlanTask = legislationPlanTaskService.findByHQL("from LegislationPlanTask t where t.stPlanId='" + legislationPlan.getStPlanId() + "' and t.stNodeId='NOD_0000000212'").get(0);
					legislationPlanTask.setStTaskStatus("DONE");
					legislationPlanTaskService.update(legislationPlanTask);
					//添加deal记录
					LegislationPlanDeal legislationPlanDeal=new LegislationPlanDeal();
					legislationPlanDeal.setStPlanId(legislationPlanTask.getStPlanId());
					legislationPlanDeal.setStUserId(stUserId);
					legislationPlanDeal.setStUserName(stUserName);
					legislationPlanDeal.setDtDealDate(new Date());
					legislationPlanDeal.setStActionName(legislationPlanTask.getStNodeName());
					legislationPlanDeal.setStActionId(legislationPlanTask.getStNodeId());
					legislationPlanDealService.add(legislationPlanDeal);
					//修改plan主表node
					legislationPlan.setStNodeId(nextNodeId);
					legislationPlan.setStNodeName(nextNode.getStNodeName());
					legislationPlanService.update(legislationPlan);
				}else {
					// 同时把所有的草案变成109的DONE，反馈也完成了
					String stDocId = legislationReport.getStSourceDoc();
						// 如果已经开始了审核会议 那主表legislation_process_doc 的这条数据 st_node_id 和st_node_name 都需要修改 成
						// 2019年4月12日11:22:49 sy
						// NOD_0000000190 审核会议
						LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
						String stNodeId = "NOD_0000000103";	
						stNodeName = "立法办理";
						legislationProcessDoc.setStNodeId(stNodeId);
						legislationProcessDoc.setStNodeName(stNodeName);
						legislationProcessDocService.update(legislationProcessDoc);
				}
				legislationReportTask.setStTaskStatus("DONE");
				
			}
			this.update(legislationReportTask);
		}

		legislationFilesService.updateParentIdById(request,stReportId);
	}
}
