package com.wonders.fzb.legislation.services.impl;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.legislation.beans.LegislationProcessDeal;
import com.wonders.fzb.legislation.beans.LegislationProcessDoc;
import com.wonders.fzb.legislation.beans.LegislationProcessTask;
import com.wonders.fzb.legislation.dao.LegislationProcessDocDao;
import com.wonders.fzb.legislation.services.LegislationFilesService;
import com.wonders.fzb.legislation.services.LegislationProcessDealService;
import com.wonders.fzb.legislation.services.LegislationProcessDocService;
import com.wonders.fzb.legislation.services.LegislationProcessTaskService;
import com.wonders.fzb.simpleflow.beans.WegovSimpleNode;
import com.wonders.fzb.simpleflow.services.WegovSimpleNodeService;
import dm.jdbc.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
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
		return legislationProcessDocDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public void editLegislationProcessDoc(HttpServletRequest request, UserInfo currentPerson,String userRoleId,String userRole) {
		String docId = request.getParameter("docId");
		String docName = request.getParameter("docName");
		String stComment = request.getParameter("stComent");

		String userId = currentPerson.getUserId();
		String userName = currentPerson.getName();
		String unitId = currentPerson.getTeamInfos().get(0).getId();
		String unitName = currentPerson.getTeamInfos().get(0).getUnitName();
		if(StringUtil.isEmpty(docId) || "null".equals(docId)){
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

			docId=addObj(legislationProcessDoc);
			legislationProcessDoc.setStDocNo(docId);
			update(legislationProcessDoc);

			LegislationProcessTask newTask= new LegislationProcessTask();
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

		}else {
			LegislationProcessDoc legislationProcessDoc = findById(docId);
			legislationProcessDoc.setStDocName(docName);
			legislationProcessDoc.setStComent(stComment);
			update(legislationProcessDoc);

			LegislationProcessDeal legislationProcessDeal = legislationProcessDealService.findByHQL("from LegislationProcessDeal t where 1=1 and t.stDocId='"+docId+"' and t.stActionId='"+legislationProcessDoc.getStNodeId()+"'").get(0);
			legislationProcessDeal.setStBakOne(docName);
			legislationProcessDeal.setStBakTwo(stComment);
			legislationProcessDeal.setDtDealDate(new Date());
			legislationProcessDealService.update(legislationProcessDeal);

		}
		Enumeration keys=request.getParameterNames();
		while(keys.hasMoreElements()){
			String key=(String)keys.nextElement();
			String value=request.getParameter(key);
			if(value.startsWith("FIL_")){
				legislationFilesService.executeSqlUpdate("update LegislationFiles s set s.stParentId='"+docId+"' where s.stFileId='"+value+"'");
			}
		}
	}

	/**
	 * 提交分办
	 *
	 * @param request
	 */
	@Override
	public void draft_dist_info(HttpServletRequest request) {
		String action=request.getParameter("action");
		String stDocId=request.getParameter("stDocId");
		String stComment1=request.getParameter("stComment1");//分办意见
		String transactDate=request.getParameter("transactDate");//办理时限
		String stDealId=request.getParameter("stDealId");
		String stDealName=request.getParameter("stDealName");

		LegislationProcessDoc legislationProcessDoc=findById(stDocId);

		//修改当前task状态，并生成下一个节点的task
		List<LegislationProcessTask> list = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId ='" + legislationProcessDoc.getStDocId() + "' and t.stNodeId='NOD_0000000102' and t.stEnable is null ");
		for (LegislationProcessTask legislationProcessTask : list) {//修改当前task状态
			SimpleDateFormat formatter = new SimpleDateFormat( "yyyy年MM月dd日");
			legislationProcessTask.setStComment1(stComment1);
			if(StringUtils.hasText(transactDate)&&transactDate.length()>3) {
				try {
					legislationProcessTask.setDtDeadDate(formatter.parse(transactDate));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(StringUtils.hasText(stDealId)) {//有选中值的时候再赋值
				legislationProcessTask.setStDealId(stDealId);
				legislationProcessTask.setStDealName(stDealName);
			}

			if(action.equals("submit")) {//党委提交的时候才改变状态并生成新的task
				legislationProcessTask.setDtCloseDate(new Date());
				legislationProcessTask.setStTaskStatus("DONE");
				legislationProcessTaskService.update(legislationProcessTask);

				//新增一个Task
				LegislationProcessTask nextLegislationProcessTask = new LegislationProcessTask();
				nextLegislationProcessTask.setStDocId(legislationProcessTask.getStDocId());
				nextLegislationProcessTask.setStFlowId(legislationProcessTask.getStFlowId());
				List<WegovSimpleNode> nodeList = wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where 1=1 and t.stNodeId ='" + legislationProcessTask.getStNodeId()+ "'");
				nextLegislationProcessTask.setStNodeId(nodeList.get(0).getStNextNode());
				nextLegislationProcessTask.setStNodeName(wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where 1=1 and t.stNodeId ='" + nodeList.get(0).getStNextNode() + "'").get(0).getStNodeName());
				nextLegislationProcessTask.setStTaskStatus("TODO");
				nextLegislationProcessTask.setStDealId(stDealId);
				nextLegislationProcessTask.setStDealName(stDealName);
				nextLegislationProcessTask.setDtOpenDate(new Date());

				legislationProcessTaskService.add(nextLegislationProcessTask);
				executeSqlUpdate("update LegislationProcessDoc s set s.stNodeName='" + nextLegislationProcessTask.getStNodeName() + "' where s.stDocId='" + nextLegislationProcessTask.getStDocId() + "'");

			}else {//保存的话，就保存修改
				legislationProcessTaskService.update(legislationProcessTask);
			}

		}
	}
}
