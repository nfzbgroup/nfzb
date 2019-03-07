package com.wonders.fzb.legislate.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.AssessmentReport;
import com.wonders.fzb.legislate.dao.AssessmentReportDao;
@SuppressWarnings("unchecked")
@Repository("assessmentReportDao")
public class AssessmentReportDaoImpl extends BaseSupportDao implements AssessmentReportDao {

	@Override
	public void add(AssessmentReport assessmentReport) {
		assessmentReport.setAssessmentId(super.getId("assessment_report_a", "ASSESSMENT_", 12));
		super.save(assessmentReport);
	}

	@Override
	public void saveOrUpdate(AssessmentReport assessmentReport) {
		super.saveOrUpdate(assessmentReport);
	}

	@Override
	public AssessmentReport findById(String assessmentId) {
		return (AssessmentReport)super.load(assessmentId);
	}

	@Override
	public List<AssessmentReport> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByList(condMap, sortMap, "AssessmentReport");
	}

	@Override
	public Page<AssessmentReport> findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo,
			int pageSize) throws FzbDaoException {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByPage(condMap, sortMap, pageNo, pageSize, "AssessmentReport");
	}

	@Override
	public void delete(AssessmentReport assessmentReport) {
		super.delete(assessmentReport);
	}

}
