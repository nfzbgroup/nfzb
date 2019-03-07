package com.wonders.fzb.legislate.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.AssessmentReportDis;
import com.wonders.fzb.legislate.dao.AssessmentReportDisDao;
@SuppressWarnings("unchecked")
@Repository("assessmentReportDisDao")
public class AssessmentReportDisDaoImpl extends BaseSupportDao implements AssessmentReportDisDao {

	@Override
	public void add(AssessmentReportDis assessmentReportDis) {
		assessmentReportDis.setDistributeId(super.getId("assessment_report_dis_a", "ASSESSMENT_DIS_", 12));
		super.save(assessmentReportDis);
	}

	@Override
	public void saveOrUpdate(AssessmentReportDis assessmentReportDis) {
		super.saveOrUpdate(assessmentReportDis);
	}

	@Override
	public AssessmentReportDis findById(String distributeId) {
		return (AssessmentReportDis)super.load(distributeId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AssessmentReportDis> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByList(condMap, sortMap, "AssessmentReportDis");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<AssessmentReportDis> findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo,
			int pageSize) throws FzbDaoException {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByPage(condMap, sortMap, pageNo, pageSize, "AssessmentReportDis");
	}

	@Override
	public void delete(AssessmentReportDis assessmentReportDis) {
		super.delete(assessmentReportDis);
	}

	@Override
	public List<AssessmentReportDis> findByAssessmentId(String assessmentId, String unitId) {
		String sql = "";
		if(unitId==null) {
			sql = "select t.* from assessment_report_dis t where t.assessment_id = '" + assessmentId + "'";
		}else {
			sql = "select t.* from assessment_report_dis t where t.assessment_id = '" + assessmentId + "' and t.receive_unit = '" + unitId + "'";
		}
		
		Session session = getSession();
		Query query = session.createSQLQuery(sql).addEntity(AssessmentReportDis.class);
		List<AssessmentReportDis> results = query.list();
		return results;
	}

}
