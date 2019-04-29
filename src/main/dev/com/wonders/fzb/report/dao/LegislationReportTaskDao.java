package com.wonders.fzb.report.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.report.beans.LegislationReport;
import com.wonders.fzb.report.beans.LegislationReportTask;


/**
 * LegislationReportTask dao接口
 * @author scalffold created by lj
 */
public abstract interface LegislationReportTaskDao extends BaseDao {
	
	public void save(Object object);
	
	public String saveObj(Object object);
	
	public void saveOrUpdate(LegislationReportTask info);
	
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize)  throws FzbDaoException;
	
	public List<LegislationReportTask> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

	public List<LegislationReportTask> findByHQL(String hql);

	Page<LegislationReport> findReportTasksByNodeId(String wheresql,
			int pageNo, int pageSize) throws ParseException;
}
