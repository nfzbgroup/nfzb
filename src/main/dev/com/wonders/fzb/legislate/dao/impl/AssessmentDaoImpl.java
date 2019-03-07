package com.wonders.fzb.legislate.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.Assessment;
import com.wonders.fzb.legislate.dao.AssessmentDao;
@Repository("assessmentDao")
public class AssessmentDaoImpl extends BaseSupportDao implements AssessmentDao{

	@Override
	public void add(Assessment assessment) {
		assessment.setAssessmentId(super.getId("assessment_", "ASSESSMENT_", 12));
		super.save(assessment);
	}

	@Override
	public void saveOrUpdate(Assessment assessment) {
		super.saveOrUpdate(assessment);
	}

	@Override
	public Assessment findById(String assessmentId) {
		return (Assessment)super.load(assessmentId);
	}

	@Override
	public List<Assessment> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByList(condMap, sortMap, "Assessment");
	}

	@Override
	public Page<Assessment> findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo,
			int pageSize) throws FzbDaoException {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByPage(condMap, sortMap, pageNo, pageSize, "Assessment");
	}

	@Override
	public void delete(Assessment assessment) {
		super.delete(assessment);
	}


}
