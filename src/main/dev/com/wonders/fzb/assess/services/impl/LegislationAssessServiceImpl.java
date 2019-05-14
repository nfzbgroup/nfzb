package com.wonders.fzb.assess.services.impl;

import com.wonders.fzb.assess.beans.LegislationAssess;
import com.wonders.fzb.assess.beans.LegislationAssessTask;
import com.wonders.fzb.assess.dao.LegislationAssessDao;
import com.wonders.fzb.assess.services.LegislationAssessItemService;
import com.wonders.fzb.assess.services.LegislationAssessService;
import com.wonders.fzb.assess.services.LegislationAssessTaskService;
import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.legislation.services.LegislationFilesService;
import com.wonders.fzb.simpleflow.beans.WegovSimpleNode;
import com.wonders.fzb.simpleflow.services.WegovSimpleNodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * LegislationAssess service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationAssessService")
@Transactional
public class LegislationAssessServiceImpl implements LegislationAssessService {

	@Autowired
	private LegislationAssessDao legislationAssessDao;

	@Autowired
	private WegovSimpleNodeService wegovSimpleNodeService;

	@Autowired
	private LegislationFilesService legislationFilesService;

	@Autowired
	private LegislationAssessTaskService legislationAssessTaskService;

	@Autowired
	private LegislationAssessItemService legislationAssessItemService;

	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationAssess info) {
		legislationAssessDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationAssess info) {
		return legislationAssessDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationAssess info) {
		legislationAssessDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationAssess info) {
		legislationAssessDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationAssess findById(String id) {
		return (LegislationAssess) legislationAssessDao.load(id);
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
		return legislationAssessDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<LegislationAssess> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationAssessDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationAssess info) {
		legislationAssessDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationAssess> findByHQL(String hql) {
		List<LegislationAssess> legislationAssessList = legislationAssessDao.findByHQL(hql);
		return legislationAssessList;
	}

	@Override
	public void saveLegislationAssess(HttpServletRequest request, HttpSession session) {
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
		String stAssessName=request.getParameter("stAssessName");
		String stRemark=request.getParameter("stRemark");
		String stNodeId=request.getParameter("stNodeId");
		String stAssessId;
		if(StringUtils.isEmpty(stTaskId)){
			WegovSimpleNode node = wegovSimpleNodeService.findById(stNodeId);
			//添加评估规划
			LegislationAssess legislationAssess=new LegislationAssess();
			legislationAssess.setStAssessName(stAssessName);
			legislationAssess.setStRemark(stRemark);
			legislationAssess.setDtCreateDate(new Date());
			legislationAssess.setStCreatorId(userId);
			legislationAssess.setStCreatorName(userName);
			legislationAssess.setStUnitId(unitId);
			legislationAssess.setStUnitName(unitName);
			legislationAssess.setStNodeId(stNodeId);
			legislationAssess.setStNodeName(node.getStNodeName());
			stAssessId=addObj(legislationAssess);

			//添加评估规划任务
			LegislationAssessTask legislationAssessTask=new LegislationAssessTask();
			legislationAssessTask.setStFlowId(stAssessName);
			legislationAssessTask.setStParentId(stAssessId);
			legislationAssessTask.setStNodeId(stNodeId);
			legislationAssessTask.setStNodeName(node.getStNodeName());
			legislationAssessTask.setStTaskStatus("TODO");
			legislationAssessTask.setDtOpenDate(new Date());
			legislationAssessTask.setStRoleId(userRoleId);
			legislationAssessTask.setStRoleName(userRole);
			legislationAssessTask.setStUserId(userId);
			legislationAssessTask.setStUserName(userName);
			legislationAssessTask.setStTeamId(teamId);
			legislationAssessTask.setStTeamName(teamName);
			legislationAssessTaskService.add(legislationAssessTask);
		}else{
			LegislationAssessTask legislationAssessTask=legislationAssessTaskService.findById(stTaskId);
			stAssessId=legislationAssessTask.getStParentId();
			LegislationAssess legislationAssess=findById(stAssessId);
			if("NOD_0000000263".equals(stNodeId)){
				//市政府反馈
				String stComment1=request.getParameter("stComment1");
				legislationAssessTask.setStComment1(stComment1);
			}else{
				//修改评估规划
				legislationAssess.setStAssessName(stAssessName);
				legislationAssess.setStRemark(stRemark);
				update(legislationAssess);
				//修改评估规划任务
				legislationAssessTask.setStFlowId(stAssessName);
			}
			legislationAssessTaskService.update(legislationAssessTask);
		}
		//处理附件内容
		legislationFilesService.updateParentIdById(request,stAssessId);
	}
}
