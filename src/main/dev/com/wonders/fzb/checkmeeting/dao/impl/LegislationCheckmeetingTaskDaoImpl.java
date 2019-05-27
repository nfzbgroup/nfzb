package com.wonders.fzb.checkmeeting.dao.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeeting;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeetingTask;
import com.wonders.fzb.checkmeeting.dao.LegislationCheckmeetingTaskDao;
import com.wonders.fzb.checkmeeting.services.LegislationCheckmeetingTaskService;
import com.wonders.fzb.legislation.beans.LegislationProcessDoc;
import com.wonders.fzb.legislation.beans.LegislationProcessTask;
import com.wonders.fzb.legislation.beans.LegislationSendNotice;
import com.wonders.fzb.legislation.dao.LegislationProcessDocDao;
import com.wonders.fzb.legislation.services.LegislationProcessDocService;
import com.wonders.fzb.legislation.services.LegislationProcessTaskService;
import com.wonders.fzb.legislation.services.LegislationSendNoticeService;
import com.wonders.fzb.legislation.services.impl.LegislationProcessDocServiceImpl;

/**
 * LegislationCheckmeetingTask dao实现
 * 
 * @author scalffold created by lj
 */
@SuppressWarnings("unchecked")
@Repository("legislationCheckmeetingTaskDao")
public class LegislationCheckmeetingTaskDaoImpl extends BaseSupportDao implements LegislationCheckmeetingTaskDao {

	@Autowired
	private LegislationProcessDocService legislationProcessDocService;
	
	@Autowired
	private LegislationSendNoticeService legislationSendNoticeService;
	
	@Autowired
	private LegislationProcessTaskService legislationProcessTaskService;

	@Override
	public void save(Object object) {
		LegislationCheckmeetingTask info = (LegislationCheckmeetingTask) object;
		info.setStTaskId(super.getId("SEQ_LEGISLATION_CHECKMEET_TASK", "KTK_", 16));
		super.save(info);
	}

	@Override
	public String saveObj(Object object) {
		LegislationCheckmeetingTask info = (LegislationCheckmeetingTask) object;
		String newId = super.getId("SEQ_LEGISLATION_CHECKMEET_TASK", "KTK_", 16);
		info.setStTaskId(newId);
		super.save(info);
		return newId;
	}

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		// sortMap.put("configName", CommonConst.ORDER_ASC);
		return super.findByPage(condMap, sortMap, pageNo, pageSize, "LegislationCheckmeetingTask");
	}

	@Override
	public List<LegislationCheckmeetingTask> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		// sortMap.put("appId", CommonConst.ORDER_ASC);
		return super.findByList(condMap, sortMap, "LegislationCheckmeetingTask");
	}

	@Override
	public void saveOrUpdate(LegislationCheckmeetingTask info) {
		super.saveOrUpdate(info);
	}

	@Override
	public List<LegislationCheckmeetingTask> findByHQL(String hql) {
		Session session = getSession();
		Query query = session.createQuery(hql);
		List<LegislationCheckmeetingTask> result = query.list();
		session.flush();
		return result;
	}

	// ----------以下为人工加入的方法----------------------------------
	@Override
	public List<Map> findTaskListByNodeId(String stNodeId, String stUserId, String UnitId, String roleId) {
		StringBuilder querySQL = new StringBuilder("select c.st_meeting_id,c.st_meeting_name,c.st_node_name,c.st_node_id" + " from LEGISLATION_CHECKMEETING c,LEGISLATION_CHECKMEETING_TASK t" + " where c.st_meeting_id = t.st_meeting_id");
		querySQL.append(" and t.st_node_id='" + stNodeId + "'");
		querySQL.append(" order by c.dt_create_date DESC");

		List<Map> results = executeSqlQuery(querySQL.toString());
		return results;
	}

	@Override
	public Page<LegislationCheckmeeting> findTaskDocListByNodeId(String wheresql, int pageNo, int pageSize) throws ParseException {
		String baseSql = " FROM LEGISLATION_CHECKMEETING c ";
		baseSql += " INNER JOIN LEGISLATION_CHECKMEETING_TASK t ";
		baseSql += " ON c.st_meeting_id = t.st_meeting_id ";
		baseSql += wheresql;
		String propView = "SELECT c.st_meeting_id,c.st_meeting_name,c.st_node_name,c.st_node_id,c.DT_CREATE_DATE,c.ST_USER_NAME,c.ST_TYPE";
		String totalView = "SELECT COUNT(1) ";

		List<LegislationCheckmeeting> users = packageDocInfoBean(executeSqlQuery(propView + baseSql, pageNo, pageSize));
		
		int totalSize = ((BigDecimal) (Object) executeSqlQueryWithoutPage(totalView + baseSql).get(0)).intValue();
		if (pageSize == 0)
			pageSize = totalSize;
		return new Page<LegislationCheckmeeting>(Page.getStartOfAnyPage(pageNo, pageSize), users.size(), totalSize, pageSize, users);
	}

	/**
	 * 执行SQL查询语句
	 * 
	 * @param sql      要执行的SQL语句
	 * @param pageSize 每页多少条
	 * @param pageNo   当前多少页
	 */
	private List<Object[]> executeSqlQuery(String sql, int pageNo, int pageSize) {
		Session session = getSession();
		Query query = session.createSQLQuery(sql);
		if (pageNo > 0) {
			if (pageSize == 0)
				pageSize = 10;
			query.setFirstResult(Page.getStartOfAnyPage(pageNo, pageSize) - 1);
			query.setMaxResults(pageSize);
		}
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.list();
		session.flush();

		return results;
	}

	private LinkedList<LegislationCheckmeeting> packageDocInfoBean(List<Object[]> results) throws ParseException {
		LinkedList<LegislationCheckmeeting> docInfos = new LinkedList<LegislationCheckmeeting>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for (Object[] array : results) {
			LegislationCheckmeeting docInfo = new LegislationCheckmeeting();
			// d.st_doc_id,d.st_doc_name,d.st_node_name,d.st_node_id
			docInfo.setStMeetingId(array[0].toString());
			docInfo.setStMeetingName(array[1] == null ? "" : array[1].toString());
			docInfo.setStNodeName(array[2] == null ? "" : array[2].toString());
			docInfo.setStNodeId(array[3] == null ? "" : array[3].toString());
			docInfo.setDtCreateDate(array[4] == null ? null : dateFormat.parse(array[4].toString()));

			docInfo.setStUserName(array[5] == null ? "" : array[5].toString());
			docInfo.setStType(array[6] == null ? "" : array[6].toString());

			docInfos.add(docInfo);
		}
		return docInfos;
	}

	@Override
	public Page<LegislationCheckmeetingTask> findTaskByNodeId(String wheresql, int pageNo, int pageSize) throws ParseException {
		String baseSql = " FROM LEGISLATION_CHECKMEETING_TASK t ";
		baseSql += wheresql;
		String propView = "SELECT t.st_task_id,t.st_meeting_id,t.st_flow_id,t.st_comment1,t.st_node_id," + "t.dt_open_date,t.st_team_name,t.dt_deal_date";
		String totalView = "SELECT COUNT(1) ";
		List<LegislationCheckmeetingTask> tasks = packageTaskInfoBean(executeSqlQuery(propView + baseSql, pageNo, pageSize));
		int totalSize = ((BigDecimal) (Object) executeSqlQueryWithoutPage(totalView + baseSql).get(0)).intValue();
		if (pageSize == 0)
			pageSize = totalSize;
		return new Page<LegislationCheckmeetingTask>(Page.getStartOfAnyPage(pageNo, pageSize), tasks.size(), totalSize, pageSize, tasks);
	}

	private LinkedList<LegislationCheckmeetingTask> packageTaskInfoBean(List<Object[]> results) throws ParseException {
		LinkedList<LegislationCheckmeetingTask> tasks = new LinkedList<LegislationCheckmeetingTask>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for (Object[] array : results) {
			LegislationCheckmeetingTask taskInfo = new LegislationCheckmeetingTask();
			// t.st_task_id,t.st_meeting_id,t.st_flow_id,t.st_comment1,t.st_node_id,t.dt_open_date,t.st_team_name
			taskInfo.setStTaskId(array[0].toString());
			taskInfo.setStMeetingId(array[1] == null ? "" : array[1].toString());
			taskInfo.setStFlowId(array[2] == null ? "" : array[2].toString());
			taskInfo.setStComment1(array[3] == null ? "" : array[3].toString());
			taskInfo.setStNodeId(array[4] == null ? "" : array[4].toString());
			taskInfo.setStTeamName(array[5] == null ? "" : array[5].toString());
			taskInfo.setDtOpenDate(array[6] == null ? null : dateFormat.parse(array[6].toString()));
			taskInfo.setDtDealDate(array[7] == null ? null : dateFormat.parse(array[7].toString()));

			tasks.add(taskInfo);
		}
		return tasks;
	}

	@Override
	public Page<LegislationCheckmeeting> findCheckMeetingByNodeId(String wheresql, int pageNo, int pageSize) throws ParseException {
		String baseSql = " FROM LEGISLATION_CHECKMEETING c ";
		baseSql += " INNER JOIN LEGISLATION_CHECKMEETING_TASK t ";
		baseSql += " ON c.st_meeting_id = t.st_meeting_id ";
		baseSql += wheresql;
		String propView = "SELECT c.st_meeting_id,c.st_meeting_name,c.st_node_name,c.st_node_id,c.DT_CREATE_DATE,c.ST_TOPIC,c.DT_BEGIN_DATE,c.DT_END_DATE,c.ST_ADDRESS,c.ST_PERSONS,c.ST_STATUS,c.ST_TYPE,c.st_doc_source";
		String totalView = "SELECT COUNT(1) ";

		List<LegislationCheckmeeting> users = packageCheckMeetingBean(executeSqlQuery(propView + baseSql, pageNo, pageSize));
		
		int totalSize = ((BigDecimal) (Object) executeSqlQueryWithoutPage(totalView + baseSql).get(0)).intValue();
		if (pageSize == 0)
			pageSize = totalSize;
		return new Page<LegislationCheckmeeting>(Page.getStartOfAnyPage(pageNo, pageSize), users.size(), totalSize, pageSize, users);
	}
	
	@Override
	public Page<LegislationCheckmeeting> findCheckMeetingByNodeId(String wheresql, int pageNo, int pageSize,String taskStatus) throws ParseException {
		String baseSql = " FROM LEGISLATION_CHECKMEETING c ";
		baseSql += " INNER JOIN LEGISLATION_CHECKMEETING_TASK t ";
		baseSql += " ON c.st_meeting_id = t.st_meeting_id ";
		baseSql += wheresql;
		String propView = "SELECT c.st_meeting_id,c.st_meeting_name,c.st_node_name,c.st_node_id,c.DT_CREATE_DATE,c.ST_TOPIC,c.DT_BEGIN_DATE,c.DT_END_DATE,c.ST_ADDRESS,c.ST_PERSONS,c.ST_STATUS,c.ST_TYPE,c.st_doc_source";
		String totalView = "SELECT COUNT(1) ";

		List<LegislationCheckmeeting> users = packageCheckMeetingBean(executeSqlQuery(propView + baseSql, pageNo, pageSize));
		
		if("FEEDBACK".equals(taskStatus)) {
			//查询审核会议通知反馈数并显示
			for(LegislationCheckmeeting legislationCheckmeeting:users) {
				String sql=totalView+"FROM LEGISLATION_SEND_NOTICE WHERE 1=1 and st_doc_id='"+legislationCheckmeeting.getStMeetingId()+"' and st_model_name='审核会议' and st_node_name='审核会议发送通知'";
				String allNotice = legislationProcessTaskService.queryTaskNum(sql)+"";
				sql=sql+"and st_notice_status='已反馈'";
				String feedbackNotice = legislationProcessTaskService.queryTaskNum(sql)+"";
				legislationCheckmeeting.setStMeetingName(legislationCheckmeeting.getStMeetingName()+"（反馈："+feedbackNotice+"/"+allNotice+"）");
			}
		}
		
		int totalSize = ((BigDecimal) (Object) executeSqlQueryWithoutPage(totalView + baseSql).get(0)).intValue();
		if (pageSize == 0)
			pageSize = totalSize;
		return new Page<LegislationCheckmeeting>(Page.getStartOfAnyPage(pageNo, pageSize), users.size(), totalSize, pageSize, users);
	}

	@Override
	public LegislationCheckmeetingTask findById(String id) {
		return (LegislationCheckmeetingTask) super.load(id);
	}

	private List<LegislationCheckmeeting> packageCheckMeetingBean(List<Object[]> results) throws ParseException {

		LinkedList<LegislationCheckmeeting> docInfos = new LinkedList<LegislationCheckmeeting>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for (Object[] array : results) {
			LegislationCheckmeeting docInfo = new LegislationCheckmeeting();
			docInfo.setStMeetingId(array[0].toString());
			docInfo.setStMeetingName(array[1] == null ? "" : array[1].toString());
			docInfo.setStNodeName(array[2] == null ? "" : array[2].toString());
			docInfo.setStNodeId(array[3] == null ? "" : array[3].toString());
			docInfo.setDtCreateDate(array[4] == null ? null : dateFormat.parse(array[4].toString()));
			docInfo.setStTopic(array[5] == null ? "" : array[5].toString());
			docInfo.setDtBeginDate(array[6] == null ? null : dateFormat.parse(array[6].toString()));
			docInfo.setDtEndDate(array[7] == null ? null : dateFormat.parse(array[7].toString()));
			docInfo.setStAddress(array[8] == null ? "" : array[8].toString());
			docInfo.setStPersons(array[9] == null ? "" : array[9].toString());
			docInfo.setStStatus(array[10] == null ? "" : array[10].toString());
			docInfo.setStType(array[11] == null ? "" : array[11].toString());
			docInfo.setStDocSource(array[12] == null ? "" : array[12].toString());
			
			//去草案表中查询信息。获得名称，返回在列表页面
			String strName = "";
			Object spstr = array[12];
			if(null!=spstr) {
				String[] strs = String.valueOf(spstr).split("#");
				for (String str : strs) {
					strName += legislationProcessDocService.findById(str).getStDocName()+"<br>";
				}
			}
			docInfo.setStDocSource(strName);
			docInfos.add(docInfo);
		}
		return docInfos;
	}


}
