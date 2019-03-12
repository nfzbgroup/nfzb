package com.wonders.fzb.legislation.services.impl;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.legislation.beans.LegislationProcessDoc;
import com.wonders.fzb.legislation.beans.LegislationProcessTask;
import com.wonders.fzb.legislation.dao.LegislationProcessTaskDao;
import com.wonders.fzb.legislation.services.LegislationProcessDocService;
import com.wonders.fzb.legislation.services.LegislationProcessTaskService;
import com.wonders.fzb.simpleflow.beans.WegovSimpleNode;
import com.wonders.fzb.simpleflow.services.WegovSimpleNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * LegislationProcessTask service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationProcessTaskService")
@Transactional
public class LegislationProcessTaskServiceImpl implements LegislationProcessTaskService {

	@Autowired
	private LegislationProcessTaskDao legislationProcessTaskDao;
	@Autowired
	private WegovSimpleNodeService wegovSimpleNodeService;
	@Autowired
	private LegislationProcessDocService legislationProcessDocService;
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
	public void nextProcess(String stDocId, String stNodeId) {
		List<LegislationProcessTask> list = legislationProcessTaskDao.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId ='" + stDocId + "' and t.stNodeId='" + stNodeId + "' and t.stEnable is null");
		for (LegislationProcessTask legislationProcessTask : list) {
			legislationProcessTask.setStTaskStatus("DONE");
			legislationProcessTaskDao.update(legislationProcessTask);

			LegislationProcessTask nextLegislationProcessTask = new LegislationProcessTask();
			nextLegislationProcessTask.setStDocId(legislationProcessTask.getStDocId());
			nextLegislationProcessTask.setStFlowId(legislationProcessTask.getStFlowId());
			List<WegovSimpleNode> nodeList = wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where 1=1 and t.stNodeId ='" + stNodeId + "'");
			nextLegislationProcessTask.setStNodeId(nodeList.get(0).getStNextNode());
			nextLegislationProcessTask.setStNodeName(wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where 1=1 and t.stNodeId ='" + nodeList.get(0).getStNextNode() + "'").get(0).getStNodeName());
			nextLegislationProcessTask.setStTaskStatus("TODO");
			nextLegislationProcessTask.setStTeamId("U_3_1");
			nextLegislationProcessTask.setStBakOne(legislationProcessTask.getStBakOne());
			nextLegislationProcessTask.setStBakTwo(legislationProcessTask.getStBakTwo());
			nextLegislationProcessTask.setDtBakDate(legislationProcessTask.getDtBakDate());
			nextLegislationProcessTask.setStComment2(legislationProcessTask.getStComment2());

			legislationProcessTaskDao.save(nextLegislationProcessTask);
			legislationProcessDocService.executeSqlUpdate("update LegislationProcessDoc s set s.stNodeName='" + nextLegislationProcessTask.getStNodeName() + "' where s.stDocId='" + nextLegislationProcessTask.getStDocId() + "'");
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
			legislationProcessTask.setStTeamId(unitId);
			legislationProcessTask.setStTeamName(unitName);
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


}
