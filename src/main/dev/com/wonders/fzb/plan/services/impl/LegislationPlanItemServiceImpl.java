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
 * LegislationPlanItem service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationPlanItemService")
@Transactional
public class LegislationPlanItemServiceImpl implements LegislationPlanItemService {

	@Autowired
	private LegislationPlanItemDao legislationPlanItemDao;

	@Autowired
	private LegislationPlanDealService legislationPlanDealService;

	@Autowired
	private LegislationFilesService legislationFilesService;

	@Autowired
	private WegovSimpleNodeService wegovSimpleNodeService;

	@Autowired
	private LegislationPlanTaskService legislationPlanTaskService;

	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationPlanItem info) {
		legislationPlanItemDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationPlanItem info) {
		return legislationPlanItemDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationPlanItem info) {
		legislationPlanItemDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationPlanItem info) {
		legislationPlanItemDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationPlanItem findById(String id) {
		return (LegislationPlanItem) legislationPlanItemDao.load(id);
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
		return legislationPlanItemDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<LegislationPlanItem> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationPlanItemDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationPlanItem info) {
		legislationPlanItemDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationPlanItem> findByHQL(String hql) {
		List<LegislationPlanItem> legislationPlanItemList = legislationPlanItemDao.findByHQL(hql);
		return legislationPlanItemList;
	}

	@Override
	public void saveLegislationPlan(HttpServletRequest request, HttpSession session) {
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
		String stPlanId=request.getParameter("stPlanId");
		String stItemName=request.getParameter("stItemName");
		String stTypeName=request.getParameter("stTypeName");
		String stContent=request.getParameter("stContent");
		String stStatus=request.getParameter("stStatus");
		String stBak=request.getParameter("stBak");
		String stNodeId=request.getParameter("stNodeId");
		String stItemId;
		if(StringUtils.isEmpty(stTaskId)){
			//添加立法计划
			LegislationPlanItem legislationPlanItem=new LegislationPlanItem();
			legislationPlanItem.setStPlanId(stPlanId);
			legislationPlanItem.setDtCreateDate(new Date());
			legislationPlanItem.setStBak(stBak);
			legislationPlanItem.setStContent(stContent);
			legislationPlanItem.setStItemName(stItemName);
			legislationPlanItem.setStStatus(stStatus);
			legislationPlanItem.setStTypeName(stTypeName);
			legislationPlanItem.setStUnitId(unitId);
			legislationPlanItem.setStUnitName(unitName);
			legislationPlanItem.setStUserId(userId);
			legislationPlanItem.setStUserName(userName);
			stItemId=addObj(legislationPlanItem);

			WegovSimpleNode node = wegovSimpleNodeService.findById(stNodeId);
			//添加立法计划任务
			LegislationPlanTask legislationPlanTask=new LegislationPlanTask();
			legislationPlanTask.setStFlowId(stItemName);
			legislationPlanTask.setStPlanId(stPlanId);
			legislationPlanTask.setStParentId(stItemId);
			legislationPlanTask.setStRoleId(userRoleId);
			legislationPlanTask.setStRoleName(userRole);
			legislationPlanTask.setStNodeId(stNodeId);
			legislationPlanTask.setStNodeName(node.getStNodeName());
			legislationPlanTask.setStTaskStatus("TODO");
			legislationPlanTask.setDtOpenDate(new Date());
			legislationPlanTask.setStUserId(userId);
			legislationPlanTask.setStUserName(userName);
			legislationPlanTask.setStTeamId(teamId);
			legislationPlanTask.setStTeamName(teamName);
			legislationPlanTaskService.add(legislationPlanTask);
		}else{
			//修改征集通知任务
			LegislationPlanTask legislationPlanTask=legislationPlanTaskService.findById(stTaskId);
			legislationPlanTask.setStFlowId(stItemName);
			legislationPlanTask.setStPlanId(stPlanId);
			legislationPlanTaskService.update(legislationPlanTask);

			LegislationPlanItem legislationPlanItem=findById(legislationPlanTask.getStParentId());
			legislationPlanItem.setStPlanId(stPlanId);
			legislationPlanItem.setStBak(stBak);
			legislationPlanItem.setStContent(stContent);
			legislationPlanItem.setStItemName(stItemName);
			legislationPlanItem.setStStatus(stStatus);
			legislationPlanItem.setStTypeName(stTypeName);
			update(legislationPlanItem);
			stItemId=legislationPlanItem.getStItemId();

		}

		// 处理附件内容
		legislationFilesService.updateParentIdById(request, stItemId);
	}
}
