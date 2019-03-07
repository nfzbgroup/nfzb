package com.wonders.fzb.legislate.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.AssessmentReportAttach;
import com.wonders.fzb.legislate.dao.AssessmentReportAttachDao;
@SuppressWarnings("unchecked")
@Repository("assessmentReportAttachDao")
public class AssessmentReportAttachDaoImpl extends BaseSupportDao implements AssessmentReportAttachDao  {

	@Override
	public void add(AssessmentReportAttach assessmentReportAttach) {
		assessmentReportAttach.setAssessmentAttachId(super.getId("assessment_report_attach_a", "ASSESSMENT_ATTACH_", 12));
		super.save(assessmentReportAttach);
	}

	@Override
	public void saveOrUpdate(AssessmentReportAttach assessmentReportAttach) {
		super.saveOrUpdate(assessmentReportAttach);
	}

	@Override
	public AssessmentReportAttach findById(String attachId) {
		return (AssessmentReportAttach)super.load(attachId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AssessmentReportAttach> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByList(condMap, sortMap, "AssessmentReportAttach");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<AssessmentReportAttach> findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo,
			int pageSize) throws FzbDaoException {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByPage(condMap, sortMap, pageNo, pageSize, "AssessmentReportAttach");
	}

	@Override
	public void delete(AssessmentReportAttach assessmentReportAttach) {
		super.delete(assessmentReportAttach);
	}

}
