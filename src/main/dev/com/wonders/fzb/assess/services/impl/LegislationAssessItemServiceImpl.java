package com.wonders.fzb.assess.services.impl;

import com.wonders.fzb.assess.beans.LegislationAssessItem;
import com.wonders.fzb.assess.beans.LegislationAssessTask;
import com.wonders.fzb.assess.dao.LegislationAssessItemDao;
import com.wonders.fzb.assess.services.LegislationAssessItemService;
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
import java.util.*;


/**
 * LegislationAssessItem service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationAssessItemService")
@Transactional
public class LegislationAssessItemServiceImpl implements LegislationAssessItemService {

	@Autowired
	private LegislationAssessItemDao legislationAssessItemDao;

	@Autowired
	private WegovSimpleNodeService wegovSimpleNodeService;

	@Autowired
	private LegislationFilesService legislationFilesService;

	@Autowired
	private LegislationAssessTaskService legislationAssessTaskService;

	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationAssessItem info) {
		legislationAssessItemDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationAssessItem info) {
		return legislationAssessItemDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationAssessItem info) {
		legislationAssessItemDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationAssessItem info) {
		legislationAssessItemDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationAssessItem findById(String id) {
		return (LegislationAssessItem) legislationAssessItemDao.load(id);
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
		return legislationAssessItemDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<LegislationAssessItem> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationAssessItemDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationAssessItem info) {
		legislationAssessItemDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationAssessItem> findByHQL(String hql) {
		List<LegislationAssessItem> legislationAssessItemList = legislationAssessItemDao.findByHQL(hql);
		return legislationAssessItemList;
	}

	@Override
	public void saveLegislationAssessItem(HttpServletRequest request, HttpSession session) {
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
		String stAssessId=request.getParameter("stAssessId");
		String stItemName=request.getParameter("stItemName");
		String stBak=request.getParameter("stBak");
		String stNodeId=request.getParameter("stNodeId");
		String stItemId;
		if(StringUtils.isEmpty(stTaskId)){
			WegovSimpleNode node = wegovSimpleNodeService.findById(stNodeId);
			//添加评估项目
			LegislationAssessItem legislationAssessItem=new LegislationAssessItem();
			legislationAssessItem.setStAssessId(stAssessId);
			legislationAssessItem.setDtCreateDate(new Date());
			legislationAssessItem.setStBak(stBak);
			legislationAssessItem.setStItemName(stItemName);
			legislationAssessItem.setStUnitId(unitId);
			legislationAssessItem.setStUnitName(unitName);
			legislationAssessItem.setStUserId(userId);
			legislationAssessItem.setStUserName(userName);
			legislationAssessItem.setStNodeId(stNodeId);
			legislationAssessItem.setStNodeName(node.getStNodeName());
			stItemId=addObj(legislationAssessItem);
			//添加评估项目任务
			LegislationAssessTask legislationAssessTask=new LegislationAssessTask();
			legislationAssessTask.setStFlowId(stItemName);
			legislationAssessTask.setStParentId(stItemId);
			legislationAssessTask.setStRoleId(userRoleId);
			legislationAssessTask.setStRoleName(userRole);
			legislationAssessTask.setStNodeId(stNodeId);
			legislationAssessTask.setStNodeName(node.getStNodeName());
			legislationAssessTask.setStTaskStatus("TODO");
			legislationAssessTask.setDtOpenDate(new Date());
			legislationAssessTask.setStUserId(userId);
			legislationAssessTask.setStUserName(userName);
			legislationAssessTask.setStTeamId(teamId);
			legislationAssessTask.setStTeamName(teamName);
			legislationAssessTaskService.add(legislationAssessTask);
		}else{
			//修改评估项目任务
			LegislationAssessTask legislationAssessTask=legislationAssessTaskService.findById(stTaskId);
			legislationAssessTask.setStFlowId(stItemName);
			legislationAssessTaskService.update(legislationAssessTask);
			//评估项目
			stItemId=legislationAssessTask.getStParentId();
			LegislationAssessItem legislationAssessItem=findById(stItemId);
			legislationAssessItem.setStBak(stBak);
			legislationAssessItem.setStItemName(stItemName);
			update(legislationAssessItem);
		}
		// 处理附件内容
		legislationFilesService.updateParentIdById(request, stItemId);
	}

	@Override
	public List<Map<String, Object>> queryProjectByAssessId(String stAssessId) {
		List<Map<String, Object>> result=new ArrayList<>();
		List<LegislationAssessItem> legislationAssessItemList=findByHQL("from LegislationAssessItem t where 1=1 and t.stAssessId='"+stAssessId+"' and t.stIsDelete is null");
		legislationAssessItemList.forEach((LegislationAssessItem legislationAssessItem)->{
			Map<String, Object> map=new HashMap<>();
			map.put("stItemId",legislationAssessItem.getStItemId());
			map.put("stItemName",legislationAssessItem.getStItemName());
			LegislationAssessTask legislationAssessTask=legislationAssessTaskService.findByHQL("from LegislationAssessTask t where 1=1 and t.stParentId='"+legislationAssessItem.getStItemId()+"' and t.stNodeId='"+legislationAssessItem.getStNodeId()+"' and t.stEnable is null").get(0);
			String stStatus=legislationAssessTask.getStNodeName();
			if("TODO".equals(legislationAssessTask.getStTaskStatus())){
				stStatus=stStatus+"(待处理)";
			}else{
				stStatus=stStatus+"(已处理)";
			}
			map.put("stStatus",stStatus);
			map.put("stUserName",legislationAssessItem.getStUserName());
			map.put("dtCreateDate",legislationAssessItem.getDtCreateDate());
			result.add(map);
		});
		return result;
	}
}
