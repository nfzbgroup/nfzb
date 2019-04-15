package com.wonders.fzb.plan.services.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.legislation.services.LegislationFilesService;
import com.wonders.fzb.simpleflow.beans.WegovSimpleNode;
import com.wonders.fzb.simpleflow.services.WegovSimpleNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.plan.beans.*;
import com.wonders.fzb.plan.dao.*;
import com.wonders.fzb.plan.services.*;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * LegislationPlan service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationPlanService")
@Transactional
public class LegislationPlanServiceImpl implements LegislationPlanService {

	@Autowired
	private LegislationPlanDao legislationPlanDao;

	@Autowired
	private WegovSimpleNodeService wegovSimpleNodeService;

	@Autowired
	private LegislationPlanTaskService legislationPlanTaskService;

	@Autowired
	private LegislationPlanDealService legislationPlanDealService;

	@Autowired
	private LegislationFilesService legislationFilesService;

	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationPlan info) {
		legislationPlanDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationPlan info) {
		return legislationPlanDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationPlan info) {
		legislationPlanDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationPlan info) {
		legislationPlanDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationPlan findById(String id) {
		return (LegislationPlan) legislationPlanDao.load(id);
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
		return legislationPlanDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<LegislationPlan> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationPlanDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationPlan info) {
		legislationPlanDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationPlan> findByHQL(String hql) {
		List<LegislationPlan> legislationPlanList = legislationPlanDao.findByHQL(hql);
		return legislationPlanList;
	}

	@Override
	public void saveLegislationNotice(HttpServletRequest request, HttpSession session) {
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String unitId = currentPerson.getTeamInfos().get(0).getId();
		String unitName = currentPerson.getTeamInfos().get(0).getUnitName();
		String teamId=currentPerson.getTeamInfos().get(0).getId();
		String teamName=currentPerson.getTeamInfos().get(0).getTeamName();
		String userId=currentPerson.getUserId();
		String userName=currentPerson.getName();
		String userRoleId = session.getAttribute("userRoleId").toString();
		String userRole = session.getAttribute("userRole").toString();
		String stTaskId=request.getParameter("stTaskId");
		String stPlanName=request.getParameter("stPlanName");
		String stPlanType=request.getParameter("stPlanType");
		String stReason=request.getParameter("stReason");
		String stProgress=request.getParameter("stProgress");
		String stRemark=request.getParameter("stRemark");
		String stNodeId=request.getParameter("stNodeId");
		String stPlanId;
		if(StringUtils.isEmpty(stTaskId)){
			//添加征集通知
			LegislationPlan legislationPlan=new LegislationPlan();
			legislationPlan.setStPlanName(stPlanName);
			legislationPlan.setStRemark(stRemark);
			legislationPlan.setDtCreateDate(new Date());
			legislationPlan.setStCreatorId(userId);
			legislationPlan.setStCreatorName(userName);
			legislationPlan.setStUnitId(unitId);
			legislationPlan.setStUnitName(unitName);
			stPlanId=addObj(legislationPlan);

			WegovSimpleNode node = wegovSimpleNodeService.findById(stNodeId);
			//添加征集通知任务
			LegislationPlanTask legislationPlanTask=new LegislationPlanTask();
			legislationPlanTask.setStFlowId(stPlanName);
			legislationPlanTask.setStPlanId(stPlanId);
			legislationPlanTask.setStNodeId(stNodeId);
			legislationPlanTask.setStNodeName(node.getStNodeName());
			legislationPlanTask.setStTaskStatus("TODO");
			legislationPlanTask.setDtOpenDate(new Date());
			legislationPlanTask.setStRoleId(userRoleId);
			legislationPlanTask.setStRoleName(userRole);
			legislationPlanTask.setStUserId(userId);
			legislationPlanTask.setStUserName(userName);
			legislationPlanTask.setStTeamId(teamId);
			legislationPlanTask.setStTeamName(teamName);
			legislationPlanTaskService.add(legislationPlanTask);

			//添加一条操作记录
			LegislationPlanDeal legislationPlanDeal=new LegislationPlanDeal();
			legislationPlanDeal.setStActionId(stNodeId);
			legislationPlanDeal.setStActionName(node.getStNodeName());
			legislationPlanDeal.setStPlanId(stPlanId);
			legislationPlanDeal.setStUserId(userId);
			legislationPlanDeal.setStUserName(userName);
			legislationPlanDeal.setDtDealDate(new Date());
			legislationPlanDeal.setStBakOne(stPlanName);
			legislationPlanDeal.setStBakTwo(stRemark);
			legislationPlanDealService.add(legislationPlanDeal);
		}else{
			//修改征集通知任务
			LegislationPlanTask legislationPlanTask=legislationPlanTaskService.findById(stTaskId);
			legislationPlanTask.setStFlowId(stPlanName);
			legislationPlanTaskService.update(legislationPlanTask);

			//修改征集通知
			LegislationPlan legislationPlan=findById(legislationPlanTask.getStPlanId());
			legislationPlan.setStPlanName(stPlanName);
			legislationPlan.setStRemark(stRemark);
			update(legislationPlan);
			stPlanId=legislationPlan.getStPlanId();

			//修改操作记录
			LegislationPlanDeal legislationPlanDeal=legislationPlanDealService.findByHQL("from LegislationPlanDeal t where 1=1 and t.stPlanId='"+stPlanId+"' and t.stActionId='"+stNodeId+"'").get(0);
			legislationPlanDeal.setStUserId(userId);
			legislationPlanDeal.setStUserName(userName);
			legislationPlanDeal.setDtDealDate(new Date());
			legislationPlanDeal.setStBakOne(stPlanName);
			legislationPlanDeal.setStBakTwo(stRemark);
			legislationPlanDealService.update(legislationPlanDeal);
		}
		// 处理附件内容
		legislationFilesService.updateParentIdById(request, stPlanId);
	}
}
