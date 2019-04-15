package com.wonders.fzb.plan.services.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.wonders.fzb.framework.beans.UserInfo;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * LegislationPlanTask service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationPlanTaskService")
@Transactional
public class LegislationPlanTaskServiceImpl implements LegislationPlanTaskService {

	@Autowired
	private LegislationPlanTaskDao legislationPlanTaskDao;

	@Autowired
	private WegovSimpleNodeService wegovSimpleNodeService;
	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationPlanTask info) {
		legislationPlanTaskDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationPlanTask info) {
		return legislationPlanTaskDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationPlanTask info) {
		legislationPlanTaskDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationPlanTask info) {
		legislationPlanTaskDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationPlanTask findById(String id) {
		return (LegislationPlanTask) legislationPlanTaskDao.load(id);
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
		return legislationPlanTaskDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<LegislationPlanTask> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationPlanTaskDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationPlanTask info) {
		legislationPlanTaskDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationPlanTask> findByHQL(String hql) {
		List<LegislationPlanTask> legislationPlanTaskList = legislationPlanTaskDao.findByHQL(hql);
		return legislationPlanTaskList;
	}

	@Override
	public void nextPlanProcess(HttpServletRequest request, HttpSession session) {
		String stTaskId = request.getParameter("stTaskId");
		String stNodeId = request.getParameter("stNodeId");
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String unitId = currentPerson.getTeamInfos().get(0).getId();
		String unitName = currentPerson.getTeamInfos().get(0).getUnitName();
		String teamId=currentPerson.getTeamInfos().get(0).getId();
		String teamName=currentPerson.getTeamInfos().get(0).getTeamName();
		String userId=currentPerson.getUserId();
		String userName=currentPerson.getName();
		String userRoleId = session.getAttribute("userRoleId").toString();
		String userRole = session.getAttribute("userRole").toString();
		LegislationPlanTask legislationPlanTask=findById(stTaskId);
		legislationPlanTask.setStTaskStatus("DONE");
		legislationPlanTask.setDtDealDate(new Date());
		legislationPlanTask.setStDealId(userId);
		legislationPlanTask.setStDealName(userName);
		update(legislationPlanTask);

		WegovSimpleNode node = wegovSimpleNodeService.findById(stNodeId);
		if(!"END".equals(node.getStNextNode())){
			WegovSimpleNode nextNode=wegovSimpleNodeService.findById(node.getStNextNode());
			LegislationPlanTask newLegislationPlanTask=new LegislationPlanTask();
			newLegislationPlanTask.setStFlowId(legislationPlanTask.getStFlowId());
			newLegislationPlanTask.setStTaskStatus("TODO");
			newLegislationPlanTask.setDtOpenDate(legislationPlanTask.getDtOpenDate());
			newLegislationPlanTask.setStNodeId(nextNode.getStNodeId());
			newLegislationPlanTask.setStNodeName(nextNode.getStNodeName());
			newLegislationPlanTask.setStPlanId(legislationPlanTask.getStPlanId());
			newLegislationPlanTask.setStTeamId(teamId);
			newLegislationPlanTask.setStTeamName(teamName);
			newLegislationPlanTask.setStRoleId(userRoleId);
			newLegislationPlanTask.setStRoleName(userRole);
			newLegislationPlanTask.setStParentId(legislationPlanTask.getStParentId());
			addObj(newLegislationPlanTask);
		}
	}
}
