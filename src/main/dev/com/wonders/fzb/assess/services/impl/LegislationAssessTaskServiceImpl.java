package com.wonders.fzb.assess.services.impl;

import com.wonders.fzb.assess.beans.LegislationAssess;
import com.wonders.fzb.assess.beans.LegislationAssessDeal;
import com.wonders.fzb.assess.beans.LegislationAssessItem;
import com.wonders.fzb.assess.beans.LegislationAssessTask;
import com.wonders.fzb.assess.dao.LegislationAssessTaskDao;
import com.wonders.fzb.assess.services.LegislationAssessDealService;
import com.wonders.fzb.assess.services.LegislationAssessItemService;
import com.wonders.fzb.assess.services.LegislationAssessService;
import com.wonders.fzb.assess.services.LegislationAssessTaskService;
import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.simpleflow.beans.WegovSimpleNode;
import com.wonders.fzb.simpleflow.services.WegovSimpleNodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * LegislationAssessTask service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationAssessTaskService")
@Transactional
public class LegislationAssessTaskServiceImpl implements LegislationAssessTaskService {

	@Autowired
	private LegislationAssessTaskDao legislationAssessTaskDao;

	@Autowired
	private WegovSimpleNodeService wegovSimpleNodeService;

	@Autowired
	private LegislationAssessItemService legislationAssessItemService;

	@Autowired
	private LegislationAssessService legislationAssessService;

	@Autowired
	private LegislationAssessDealService legislationAssessDealService;
	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationAssessTask info) {
		legislationAssessTaskDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationAssessTask info) {
		return legislationAssessTaskDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationAssessTask info) {
		legislationAssessTaskDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationAssessTask info) {
		legislationAssessTaskDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationAssessTask findById(String id) {
		return (LegislationAssessTask) legislationAssessTaskDao.load(id);
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
		return legislationAssessTaskDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<LegislationAssessTask> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationAssessTaskDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationAssessTask info) {
		legislationAssessTaskDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationAssessTask> findByHQL(String hql) {
		List<LegislationAssessTask> legislationAssessTaskList = legislationAssessTaskDao.findByHQL(hql);
		return legislationAssessTaskList;
	}

	@Override
	public void nextAssessProcess(HttpServletRequest request, HttpSession session) {
		String stTaskId = request.getParameter("stTaskId");
		String stNodeId = request.getParameter("stNodeId");
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String teamId=currentPerson.getTeamInfos().get(0).getId();
		String teamName=currentPerson.getTeamInfos().get(0).getTeamName();
		String userId=currentPerson.getUserId();
		String userName=currentPerson.getName();
		String userRoleId = session.getAttribute("userRoleId").toString();
		String userRole = session.getAttribute("userRole").toString();
		LegislationAssessTask legislationAssessTask=findById(stTaskId);
		legislationAssessTask.setStTaskStatus("DONE");
		legislationAssessTask.setDtDealDate(new Date());
		legislationAssessTask.setStDealId(userId);
		legislationAssessTask.setStDealName(userName);
		legislationAssessTask.setStRoleId(userRoleId);
		legislationAssessTask.setStRoleName(userRole);
		legislationAssessTask.setStTeamId(teamId);
		legislationAssessTask.setStTeamName(teamName);
		if("NOD_0000000254".equals(stNodeId)){
			//审核意见
			String stActive=request.getParameter("stActive");
			String stComment1=request.getParameter("stComment1");
			legislationAssessTask.setStActive(stActive);
			legislationAssessTask.setStComment1(stComment1);
		}
		update(legislationAssessTask);//修改任务状态

		WegovSimpleNode node = wegovSimpleNodeService.findById(stNodeId);
		WegovSimpleNode nextNode=wegovSimpleNodeService.findById(node.getStNextNode());
		if(!"END".equals(node.getStNextNode())) {//增加一条新任务
			LegislationAssessTask newLegislationAssessTask=new LegislationAssessTask();
			newLegislationAssessTask.setStFlowId(legislationAssessTask.getStFlowId());
			newLegislationAssessTask.setStTaskStatus("TODO");
			newLegislationAssessTask.setDtOpenDate(legislationAssessTask.getDtOpenDate());
			newLegislationAssessTask.setStUserId(legislationAssessTask.getStUserId());
			newLegislationAssessTask.setStUserName(legislationAssessTask.getStUserName());
			newLegislationAssessTask.setStParentId(legislationAssessTask.getStParentId());
			newLegislationAssessTask.setStNodeId(nextNode.getStNodeId());
			newLegislationAssessTask.setStNodeName(nextNode.getStNodeName());
			if("NOD_0000000256".equals(stNodeId)){
				//评估方案建议的处理单位是立法处初审时的处室
				LegislationAssessTask legislationAssessTaskExamine=findByHQL("from LegislationAssessTask t where 1=1 and t.stParentId='"+legislationAssessTask.getStParentId()+"' and t.stNodeId='NOD_0000000254' and t.stEnable is null").get(0);
				newLegislationAssessTask.setStTeamId(legislationAssessTaskExamine.getStTeamId());
			}
			//生成第一季度反馈评估进度任务,处理单位是发起单位
			if("NOD_0000000257".equals(stNodeId)){
				newLegislationAssessTask.setStActive("1");
				LegislationAssessItem legislationAssessItem=legislationAssessItemService.findById(legislationAssessTask.getStParentId());
				newLegislationAssessTask.setStTeamId(legislationAssessItem.getStUnitId());
			}
			//处理单位是发起单位
			if("NOD_0000000258".equals(stNodeId)){
				//生成第2,3,4季度反馈评估进度任务
				if(StringUtils.isNotEmpty(legislationAssessTask.getStActive())
						&&Integer.parseInt(legislationAssessTask.getStActive())<4){
					newLegislationAssessTask.setStActive(Integer.parseInt(legislationAssessTask.getStActive())+1+"");
					newLegislationAssessTask.setStNodeId(node.getStNodeId());
					newLegislationAssessTask.setStNodeName(node.getStNodeName());
				}
				LegislationAssessItem legislationAssessItem=legislationAssessItemService.findById(legislationAssessTask.getStParentId());
				newLegislationAssessTask.setStTeamId(legislationAssessItem.getStUnitId());
			}
			addObj(newLegislationAssessTask);
		}

		//添加一条操作记录
		LegislationAssessDeal legislationAssessDeal=new LegislationAssessDeal();
		legislationAssessDeal.setStActionId(stNodeId);
		legislationAssessDeal.setStActionName(node.getStNodeName());
		legislationAssessDeal.setStUserId(userId);
		legislationAssessDeal.setStUserName(userName);
		legislationAssessDeal.setDtDealDate(new Date());
		if("NOD_0000000251".equals(stNodeId)||"NOD_0000000253".equals(stNodeId)||"NOD_0000000255".equals(stNodeId)){
			LegislationAssess legislationAssess=legislationAssessService.findById(legislationAssessTask.getStParentId());
			legislationAssessDeal.setStAssessId(legislationAssess.getStAssessId());
			legislationAssessDeal.setStBakOne(legislationAssess.getStAssessName());
			legislationAssessDeal.setStBakTwo(legislationAssess.getStRemark());
			if (!"END".equals(node.getStNextNode())) {
				legislationAssess.setStNodeId(nextNode.getStNodeId());
				legislationAssess.setStNodeName(nextNode.getStNodeName());
				legislationAssessService.update(legislationAssess);
			}
			//评估汇总分送
			if("NOD_0000000253".equals(stNodeId)){
				String stTeamId=request.getParameter("stTeamId");
				Map<String, Object> condMap = new HashMap<>();
				Map<String, String> sortMap = new HashMap<>();
				condMap.put("stAssessId", legislationAssessTask.getStParentId());
				condMap.put("stIsDeleteIsNull", "null");
				List<LegislationAssessItem> legislationAssessItemList=legislationAssessItemService.findByList(condMap,sortMap);
				legislationAssessItemList.forEach((LegislationAssessItem legislationAssessItem)->{
					LegislationAssessTask legislationAssessTaskDistribute=new LegislationAssessTask();
					legislationAssessTaskDistribute.setStFlowId(legislationAssessItem.getStItemName());
					legislationAssessTaskDistribute.setStTaskStatus("TODO");
					legislationAssessTaskDistribute.setDtOpenDate(legislationAssessItem.getDtCreateDate());
					legislationAssessTaskDistribute.setStNodeId("NOD_0000000254");
					legislationAssessTaskDistribute.setStNodeName("立法处初审");
					legislationAssessTaskDistribute.setStUserId(legislationAssessItem.getStUserId());
					legislationAssessTaskDistribute.setStUserName(legislationAssessItem.getStUserName());
					legislationAssessTaskDistribute.setStParentId(legislationAssessItem.getStItemId());
					legislationAssessTaskDistribute.setStTeamId(stTeamId);
					addObj(legislationAssessTaskDistribute);
					legislationAssessItem.setStNodeId("NOD_0000000254");
					legislationAssessItem.setStNodeName("立法处初审");
					legislationAssessItemService.update(legislationAssessItem);
				});
			}
			//纳入后评估计划
			if("NOD_0000000255".equals(stNodeId)){
				Map<String, Object> condMap = new HashMap<>();
				Map<String, String> sortMap = new HashMap<>();
				condMap.put("stAssessId", legislationAssessTask.getStParentId());
				condMap.put("stIsDeleteIsNull", "null");
				List<LegislationAssessItem> legislationAssessItemList=legislationAssessItemService.findByList(condMap,sortMap);
				legislationAssessItemList.forEach((LegislationAssessItem legislationAssessItem)->{
					LegislationAssessTask legislationAssessTaskPlan=new LegislationAssessTask();
					legislationAssessTaskPlan.setStFlowId(legislationAssessItem.getStItemName());
					legislationAssessTaskPlan.setStTaskStatus("TODO");
					legislationAssessTaskPlan.setDtOpenDate(legislationAssessItem.getDtCreateDate());
					legislationAssessTaskPlan.setStNodeId("NOD_0000000256");
					legislationAssessTaskPlan.setStNodeName("制定评估方案");
					legislationAssessTaskPlan.setStUserId(legislationAssessItem.getStUserId());
					legislationAssessTaskPlan.setStUserName(legislationAssessItem.getStUserName());
					legislationAssessTaskPlan.setStParentId(legislationAssessItem.getStItemId());
					legislationAssessTaskPlan.setStTeamId(legislationAssessItem.getStUnitId());
					addObj(legislationAssessTaskPlan);
					legislationAssessItem.setStNodeId("NOD_0000000256");
					legislationAssessItem.setStNodeName("制定评估方案");
					legislationAssessItemService.update(legislationAssessItem);
				});
			}
		}else{
			LegislationAssessItem legislationAssessItem=legislationAssessItemService.findById(legislationAssessTask.getStParentId());
			legislationAssessDeal.setStAssessId(legislationAssessItem.getStItemId());
			legislationAssessDeal.setStBakOne(legislationAssessItem.getStItemName());
			if("NOD_0000000258".equals(stNodeId)){
				legislationAssessDeal.setStBakTwo("第"+legislationAssessTask.getStActive()+"季度反馈评估进度");
			}else{
				legislationAssessDeal.setStBakTwo(legislationAssessItem.getStBak());
			}
			if (!"END".equals(node.getStNextNode())&&!("NOD_0000000258".equals(stNodeId)&&StringUtils.isNotEmpty(legislationAssessTask.getStActive())
					&&Integer.parseInt(legislationAssessTask.getStActive())<4)) {
				legislationAssessItem.setStNodeId(nextNode.getStNodeId());
				legislationAssessItem.setStNodeName(nextNode.getStNodeName());
				legislationAssessItemService.update(legislationAssessItem);
			}
		}
		legislationAssessDealService.add(legislationAssessDeal);
	}
}
