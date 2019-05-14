package com.wonders.fzb.legislation.dao.impl;


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
import com.wonders.fzb.citymeeting.beans.LegislationCitymeeting;
import com.wonders.fzb.legislation.beans.*;
import com.wonders.fzb.legislation.dao.*;

/**
 * LegislationSendNotice dao实现
 * 
 * @author scalffold created by lj
 */
@SuppressWarnings("unchecked")
@Repository("legislationSendNoticeDao")
public class LegislationSendNoticeDaoImpl extends BaseSupportDao implements LegislationSendNoticeDao {

	@Override
	public void save(Object object) {
		LegislationSendNotice info = (LegislationSendNotice) object;
		info.setStNoticeId(super.getId("SEQ_LEGISLATION_SEND_NOTICE", "NTC_", 16));
		super.save(info);
	}
	
	@Override
	public String saveObj(Object object) {
		LegislationSendNotice info = (LegislationSendNotice) object;
		String newId=super.getId("SEQ_LEGISLATION_SEND_NOTICE", "NTC_", 16);
		info.setStNoticeId(newId);
		super.save(info);
		return newId;
	}
	

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("configName", CommonConst.ORDER_ASC);
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"LegislationSendNotice");
	}

	@Override
	public List<LegislationSendNotice> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("appId", CommonConst.ORDER_ASC);
		return super.findByList(condMap, sortMap, "LegislationSendNotice");
	}

	@Override
	public void saveOrUpdate(LegislationSendNotice info) {
		super.saveOrUpdate(info);
	}
	
	@Override
	public List<LegislationSendNotice> findByHQL(String hql){
		Session session = getSession();
		Query query = session.createQuery(hql);
		List<LegislationSendNotice> result = query.list();
		session.flush();
		return result;
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
	
	private LinkedList<SendNoticeVO> packageVOBean(List<Object[]> results ,int length) throws ParseException {
		LinkedList<SendNoticeVO> docInfos = new LinkedList<SendNoticeVO>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for (Object[] array : results) {
			SendNoticeVO docInfo = new SendNoticeVO();
			//d.st_doc_id,d.st_doc_name,d.st_node_name,d.st_node_id
			docInfo.setStNoticeId(array[0].toString());
			docInfo.setStDocId(array[1] == null ? "" : array[1].toString());
			docInfo.setStTile(array[2] == null ? "" : array[2].toString());
			docInfo.setStUser(array[3] == null ? "" : array[3].toString());
			docInfo.setStUnitName(array[4] == null ? "" : array[4].toString());
			docInfo.setStBak(array[5] == null ? "" : array[5].toString());
			docInfo.setStBak1(array[6] == null ? "" : array[6].toString());
			docInfo.setStBak2(array[7] == null ? "" : array[7].toString());
			
			docInfo.setDtSendDate(array[8] == null ? null : dateFormat.parse(array[8].toString()));
			docInfo.setDtBakDate(array[9] == null ? null : dateFormat.parse(array[9].toString()));


			docInfos.add(docInfo);
		}
		return docInfos;
	}
	
	
	/*
	 * columnNames:d.st_topic_id,d.st_topic_name,d.st_node_name,t.st_node_id,d.DT_CREATE_DATE,d.ST_USER_NAME,d.ST_TOPIC
	 * 一定要8位，后两位日期，与VO对应。不足的字段前6个补 ''，后两个日期 null，或者与之前的重复。这样统一处理。
	 */
	@Override
	public Page<SendNoticeVO> findSendNoticeList(String wheresql,String mainTableName,String columnNames, int pageNo, int pageSize) throws ParseException {
		String baseSql = " FROM "+mainTableName+ " d ";
		String mainId="d.st_doc_id";
		if(mainTableName.equals("LEGISLATION_CHECKMEETING")) {
			mainId="d.st_meeting_id";
		}if(mainTableName.equals("LEGISLATION_REPORT")) {
			mainId="d.st_report_id";
		}if(mainTableName.equals("LEGISLATION_PLAN")) {
			mainId="d.st_plan_id";
		}
		baseSql += " INNER JOIN LEGISLATION_SEND_NOTICE t ";
		baseSql += " ON "+mainId+ "= t.ST_DOC_ID ";
		baseSql += wheresql;
//		String propView = "SELECT d.st_topic_id,d.st_topic_name,d.st_node_name,t.st_node_id,d.DT_CREATE_DATE,d.ST_USER_NAME,d.ST_TOPIC";
		String propView = "SELECT "+columnNames;
		String totalView = "SELECT COUNT(1) ";

		List<SendNoticeVO> users = packageVOBean(executeSqlQuery(propView + baseSql, pageNo, pageSize),columnNames.split(",").length);
		int totalSize = ((BigDecimal) (Object) executeSqlQueryWithoutPage(totalView + baseSql).get(0)).intValue();
		if (pageSize == 0)
			pageSize = totalSize;
		return new Page<SendNoticeVO>(Page.getStartOfAnyPage(pageNo, pageSize), users.size(), totalSize, pageSize, users);
	}
	
	/*
	 * columnNames:d.st_topic_id,d.st_topic_name,d.st_node_name,t.st_node_id,d.DT_CREATE_DATE,d.ST_USER_NAME,d.ST_TOPIC
	 * 一定要8位，后两位日期，与VO对应。不足的字段前6个补 ''，后两个日期 null，或者与之前的重复。这样统一处理。
	 */
	@Override
	public Page<SendNoticeVO> findSendNoticeCitymeetingList(String wheresql,String mainTableName,String columnNames, int pageNo, int pageSize) throws ParseException {
		String baseSql = " FROM "+mainTableName+ " d ";
		baseSql += " INNER JOIN LEGISLATION_SEND_NOTICE t ";
		baseSql += " ON d.ST_TOPIC_ID = t.ST_DOC_ID ";
		baseSql += wheresql;
//		String propView = "SELECT d.st_topic_id,d.st_topic_name,d.st_node_name,t.st_node_id,d.DT_CREATE_DATE,d.ST_USER_NAME,d.ST_TOPIC";
		String propView = "SELECT "+columnNames;
		String totalView = "SELECT COUNT(1) ";

		List<SendNoticeVO> users = packageVOBean(executeSqlQuery(propView + baseSql, pageNo, pageSize),columnNames.split(",").length);
		int totalSize = ((BigDecimal) (Object) executeSqlQueryWithoutPage(totalView + baseSql).get(0)).intValue();
		if (pageSize == 0)
			pageSize = totalSize;
		return new Page<SendNoticeVO>(Page.getStartOfAnyPage(pageNo, pageSize), users.size(), totalSize, pageSize, users);
	}

}
