package com.wonders.fzb.legislation.dao.impl;


import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislation.beans.LegislationProcessDoc;
import com.wonders.fzb.legislation.beans.LegislationProcessTask;
import com.wonders.fzb.legislation.dao.LegislationProcessTaskDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * LegislationProcessTask dao实现
 * 
 * @author scalffold created by lj
 */
@SuppressWarnings("unchecked")
@Repository("legislationProcessTaskDao")
public class LegislationProcessTaskDaoImpl extends BaseSupportDao implements LegislationProcessTaskDao {

	@Override
	public void save(Object object) {
		LegislationProcessTask info = (LegislationProcessTask) object;
		info.setStTaskId(super.getId("SEQ_LEGISLATION_PROCESS_TASK", "TSK_", 16));
		super.save(info);
	}
	
	@Override
	public String saveObj(Object object) {
		LegislationProcessTask info = (LegislationProcessTask) object;
		String newId=super.getId("SEQ_LEGISLATION_PROCESS_TASK", "TSK_", 16);
		info.setStTaskId(newId);
		super.save(info);
		return newId;
	}
	

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("configName", CommonConst.ORDER_ASC);
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"LegislationProcessTask");
	}

	@Override
	public List<LegislationProcessTask> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("appId", CommonConst.ORDER_ASC);
		return super.findByList(condMap, sortMap, "LegislationProcessTask");
	}

	@Override
	public void saveOrUpdate(LegislationProcessTask info) {
		super.saveOrUpdate(info);
	}
	
	@Override
	public List<LegislationProcessTask> findByHQL(String hql){
		Session session = getSession();
		Query query = session.createQuery(hql);
		List<LegislationProcessTask> result = query.list();
		session.flush();
		return result;
	}
	
	//----------以下为人工加入的方法----------------------------------
	@Override
	public List<Map> findTaskListByNodeId(String stNodeId,String stUserId,String UnitId,String roleId){
		StringBuilder querySQL=new StringBuilder("select d.st_doc_id,d.st_doc_name,d.st_node_name,d.st_node_id"
				+" from LEGISLATION_PROCESS_DOC d,LEGISLATION_PROCESS_TASK t"
				+" where d.st_doc_id = t.st_doc_id");
		querySQL.append(" and t.st_node_id='"+ stNodeId +"'");
		querySQL.append(" order by d.dt_create_date DESC");
		
		List<Map> results = executeSqlQuery(querySQL.toString());
		return results;
	}
	
	/**
	 * 执行SQL查询语句
	 * @param sql 要执行的SQL语句
	 * @param pageSize 每页多少条
	 * @param pageNo 当前多少页
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
	
	private LinkedList<LegislationProcessDoc> packageDocInfoBean(List<Object[]> results) {
		LinkedList<LegislationProcessDoc> docInfos = new LinkedList<LegislationProcessDoc>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for (Object[] array : results) {
			LegislationProcessDoc docInfo = new LegislationProcessDoc();
			//d.st_doc_id,d.st_doc_name,d.st_node_name,d.st_node_id
			docInfo.setStDocId(array[0].toString());
			docInfo.setStDocName(array[1] == null ? "" : array[1].toString());
			docInfo.setStNodeName(array[2] == null ? "" : array[2].toString());
			docInfo.setStNodeId(array[3] == null ? "" : array[3].toString());
			try {
				docInfo.setDtCreateDate(array[4] == null ? null : dateFormat.parse(array[4].toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			docInfo.setStUserName(array[5] == null ? "" : array[5].toString());
			docInfo.setStDocNo(array[6] == null ? "" : array[6].toString());

			docInfos.add(docInfo);
		}
		return docInfos;
	}
	
	@Override
	public Page<LegislationProcessDoc> findTaskDocListByNodeId(String wheresql, int pageNo, int pageSize) {
		String baseSql = " FROM LEGISLATION_PROCESS_DOC d ";
		baseSql += " INNER JOIN LEGISLATION_PROCESS_TASK t ";
		baseSql += " ON d.st_doc_id = t.st_doc_id ";
		baseSql += wheresql;
		String propView = "SELECT d.st_doc_id,d.st_doc_name,d.st_node_name,t.st_node_id,d.DT_CREATE_DATE,d.ST_USER_NAME,d.ST_DOC_NO";
		String totalView = "SELECT COUNT(1) ";

		List<LegislationProcessDoc> users = packageDocInfoBean(executeSqlQuery(propView + baseSql, pageNo, pageSize));
		int totalSize = ((BigDecimal) (Object) executeSqlQueryWithoutPage(totalView + baseSql).get(0)).intValue();
		if (pageSize == 0)
			pageSize = totalSize;
		return new Page<LegislationProcessDoc>(Page.getStartOfAnyPage(pageNo, pageSize), users.size(), totalSize, pageSize, users);
	}

	@Override
	public Page<LegislationProcessTask> findTaskByNodeId(String wheresql, int pageNo, int pageSize) {
		String baseSql = " FROM LEGISLATION_PROCESS_TASK t ";
		baseSql += wheresql;
		String propView = "SELECT t.st_task_id,t.st_doc_id,t.st_flow_id,t.st_bak_one,t.dt_bak_date,t.st_bak_two,t.st_node_id," +
				"t.dt_open_date,t.st_team_name";
		String totalView = "SELECT COUNT(1) ";

		List<LegislationProcessTask> tasks = packageTaskInfoBean(executeSqlQuery(propView + baseSql, pageNo, pageSize));
		int totalSize = ((BigDecimal) (Object) executeSqlQueryWithoutPage(totalView + baseSql).get(0)).intValue();
		if (pageSize == 0)
			pageSize = totalSize;
		return new Page<LegislationProcessTask>(Page.getStartOfAnyPage(pageNo, pageSize), tasks.size(), totalSize, pageSize, tasks);
	}

	private LinkedList<LegislationProcessTask> packageTaskInfoBean(List<Object[]> results) {
		LinkedList<LegislationProcessTask> tasks = new LinkedList<LegislationProcessTask>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for (Object[] array : results) {
			LegislationProcessTask taskInfo = new LegislationProcessTask();
			//t.st_task_id,t.st_doc_id,t.st_flow_id,t.st_bak_one,t.dt_bak_date,t.st_bak_two,t.st_node_id,t.dt_open_date,t.st_team_name
			taskInfo.setStTaskId(array[0].toString());
			taskInfo.setStDocId(array[1]== null ? "" : array[1].toString());
			taskInfo.setStFlowId(array[2]== null ? "" : array[2].toString());
			taskInfo.setStBakOne(array[3]== null ? "" : array[3].toString());
			taskInfo.setStBakTwo(array[5]== null ? "" : array[5].toString());
			taskInfo.setStNodeId(array[6]== null ? "" : array[6].toString());
			taskInfo.setStTeamName(array[8]== null ? "" : array[8].toString());
			try {
				taskInfo.setDtBakDate(array[4] == null ? null : dateFormat.parse(array[4].toString()));
				taskInfo.setDtOpenDate(array[7] == null ? null : dateFormat.parse(array[7].toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			tasks.add(taskInfo);
		}
		return tasks;
	}


}
