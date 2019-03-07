package com.wonders.fzb.legislate.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.AssessmentReport;
import com.wonders.fzb.legislate.dao.AssessmentReportDao;
import com.wonders.fzb.legislate.services.AssessmentReportService;
@Service("assessmentReportService")
@Transactional
public class AssessmentReportServiceImpl implements AssessmentReportService {

	@Autowired
	private AssessmentReportDao daoHelper;
	
	@Override
	public void saveOrUpdate(AssessmentReport info) {
		daoHelper.saveOrUpdate(info);
	}

	@Override
	public void add(AssessmentReport info) {
		daoHelper.add(info);
	}

	@Override
	public void delete(AssessmentReport info) {
		daoHelper.delete(info);
	}
	
	@Override
	public AssessmentReport findById(String assessmentId) {
		return daoHelper.findById(assessmentId);
	}

	@Override
	public List<AssessmentReport> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return daoHelper.findByList(condMap, sortMap);
	}

	@Override
	public Page<AssessmentReport> findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo,
			int pageSize) throws FzbDaoException {
		return daoHelper.findByPage(condMap, sortMap, pageNo, pageSize);
	}

}
