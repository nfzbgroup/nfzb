package com.wonders.fzb.legislate.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.AssessmentAttach;
import com.wonders.fzb.legislate.dao.AssessmentAttachDao;
@Repository("assessmentAttachDao")
public class AssessmentAttachDaoImpl extends BaseSupportDao implements AssessmentAttachDao{

	@Override
	public void add(AssessmentAttach assessmentAttach) {
		assessmentAttach.setAssessmentAttachId(super.getId("assessment_attach_", "ASSESSMENT_ATTACH_", 12));
		super.save(assessmentAttach);
	}

	@Override
	public void saveOrUpdate(AssessmentAttach assessmentAttach) {
		super.saveOrUpdate(assessmentAttach);
	}

	@Override
	public AssessmentAttach findById(String attachId) {
		return (AssessmentAttach)super.load(attachId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AssessmentAttach> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByList(condMap, sortMap, "AssessmentAttach");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<AssessmentAttach> findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo,
			int pageSize) throws FzbDaoException {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByPage(condMap, sortMap, pageNo, pageSize, "AssessmentAttach");
	}

	@Override
	public void delete(AssessmentAttach assessmentAttach) {
		super.delete(assessmentAttach);
	}

}
