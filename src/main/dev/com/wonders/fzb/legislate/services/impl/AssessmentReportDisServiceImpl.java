package com.wonders.fzb.legislate.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.AssessmentReportDis;
import com.wonders.fzb.legislate.dao.AssessmentReportDisDao;
import com.wonders.fzb.legislate.services.AssessmentReportDisService;
@Service("assessmentReportDisService")
@Transactional
public class AssessmentReportDisServiceImpl implements AssessmentReportDisService{

	@Autowired
	private AssessmentReportDisDao daoHelper;
	
	@Override
	public void saveOrUpdate(AssessmentReportDis info) {
		daoHelper.saveOrUpdate(info);
	}

	@Override
	public void add(AssessmentReportDis info) {
		daoHelper.add(info);
	}

	@Override
	public void delete(AssessmentReportDis info) {
		daoHelper.delete(info);
	}

	@Override
	public List<AssessmentReportDis> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return daoHelper.findByList(condMap, sortMap);
	}

	@Override
	public Page<AssessmentReportDis> findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo,
			int pageSize) throws FzbDaoException {
		return daoHelper.findByPage(condMap, sortMap, pageNo, pageSize);
	}

	@Override
	public List<AssessmentReportDis> findByAssessmentId(String assessmentId, String unitId) {
		return daoHelper.findByAssessmentId(assessmentId,unitId);
	}

}
