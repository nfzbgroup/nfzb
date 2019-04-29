package com.wonders.fzb.report.dao.impl;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeeting;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeetingTask;
import com.wonders.fzb.report.beans.*;
import com.wonders.fzb.report.dao.*;

/**
 * LegislationReportTask dao实现
 * 
 * @author scalffold created by lj
 */
@SuppressWarnings("unchecked")
@Repository("legislationReportTaskDao")
public class LegislationReportTaskDaoImpl extends BaseSupportDao implements LegislationReportTaskDao {

	@Override
	public void save(Object object) {
		LegislationReportTask info = (LegislationReportTask) object;
		info.setStTaskId(super.getId("SEQ_LEGISLATION_REPORT_TASK", "RTK_", 16));
		super.save(info);
	}
	
	@Override
	public String saveObj(Object object) {
		LegislationReportTask info = (LegislationReportTask) object;
		String newId=super.getId("SEQ_LEGISLATION_REPORT_TASK", "RTK_", 16);
		info.setStTaskId(newId);
		super.save(info);
		return newId;
	}
	

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("configName", CommonConst.ORDER_ASC);
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"LegislationReportTask");
	}

	@Override
	public List<LegislationReportTask> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("appId", CommonConst.ORDER_ASC);
		return super.findByList(condMap, sortMap, "LegislationReportTask");
	}

	@Override
	public void saveOrUpdate(LegislationReportTask info) {
		super.saveOrUpdate(info);
	}
	
	@Override
	public List<LegislationReportTask> findByHQL(String hql){
		Session session = getSession();
		Query query = session.createQuery(hql);
		List<LegislationReportTask> result = query.list();
		session.flush();
		return result;
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
	public Page<LegislationReport> findReportTasksByNodeId(String wheresql, int pageNo, int pageSize) throws ParseException {
		String baseSql = " FROM LEGISLATION_REPORT c ";
		baseSql += " INNER JOIN LEGISLATION_REPORT_TASK t ";
		baseSql += " ON c.st_report_id = t.st_report_id ";
		baseSql += wheresql;
		String propView = "SELECT c.st_report_id,c.st_report_name,c.st_node_name,c.st_node_id,c.DT_CREATE_DATE,c.st_source_doc";
		String totalView = "SELECT COUNT(1) ";

		List<LegislationReport> users = packageReportBean(executeSqlQuery(propView + baseSql, pageNo, pageSize));
		int totalSize = ((BigDecimal) (Object) executeSqlQueryWithoutPage(totalView + baseSql).get(0)).intValue();
		if (pageSize == 0)
			pageSize = totalSize;
		return new Page<LegislationReport>(Page.getStartOfAnyPage(pageNo, pageSize), users.size(), totalSize, pageSize, users);
	}


	private List<LegislationReport> packageReportBean(List<Object[]> results) throws ParseException {

		LinkedList<LegislationReport> docInfos = new LinkedList<LegislationReport>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for (Object[] array : results) {
			LegislationReport docInfo = new LegislationReport();
			docInfo.setStReportId(array[0].toString());
			docInfo.setStReportName(array[1] == null ? "" : array[1].toString());
			docInfo.setStNodeName(array[2] == null ? "" : array[2].toString());
			docInfo.setStNodeId(array[3] == null ? "" : array[3].toString());
			docInfo.setDtCreateDate(array[4] == null ? null : dateFormat.parse(array[4].toString()));
			docInfo.setStSourceDoc(array[5] == null ? "" : array[5].toString());
			docInfos.add(docInfo);
		}
		return docInfos;
	}
	
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

}
