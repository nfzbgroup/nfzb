package com.wonders.fzb.legislate.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.AssessmentReportAttach;
import com.wonders.fzb.legislate.dao.AssessmentReportAttachDao;
import com.wonders.fzb.legislate.services.AssessmentReportAttachService;
@Service("assessmentReportAttachService")
@Transactional
public class AssessmentReportAttachServiceImpl implements AssessmentReportAttachService {

	@Autowired
	private AssessmentReportAttachDao daoHelper;
	
	@Override
	public void saveOrUpdate(AssessmentReportAttach info) {
		daoHelper.saveOrUpdate(info);
	}

	@Override
	public void add(AssessmentReportAttach info) {
		daoHelper.add(info);
	}

	@Override
	public void delete(AssessmentReportAttach info) {
		daoHelper.delete(info);
	}

	@Override
	public List<AssessmentReportAttach> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return daoHelper.findByList(condMap, sortMap);
	}

	@Override
	public Page<AssessmentReportAttach> findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo,
			int pageSize) throws FzbDaoException {
		return daoHelper.findByPage(condMap, sortMap, pageNo, pageSize);
	}

}
