package com.wonders.fzb.legislate.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.AssessmentDis;
import com.wonders.fzb.legislate.dao.AssessmentDisDao;
import com.wonders.fzb.legislate.services.AssessmentDisService;
@Service("assessmentDisService")
@Transactional
public class AssessmentDisServiceImpl implements AssessmentDisService{

	@Autowired
	private AssessmentDisDao daoHelper;
	
	@Override
	public void saveOrUpdate(AssessmentDis info) {
		daoHelper.saveOrUpdate(info);
	}

	@Override
	public void add(AssessmentDis info) {
		daoHelper.add(info);
	}

	@Override
	public void delete(AssessmentDis info) {
		daoHelper.delete(info);
	}

	@Override
	public List<AssessmentDis> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return daoHelper.findByList(condMap, sortMap);
	}

	@Override
	public List<AssessmentDis> findByAssessmentId(String assessmentId, String unitId) {
		return daoHelper.findByAssessmentId( assessmentId, unitId);
	}

	@Override
	public Page<AssessmentDis> findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo,
			int pageSize) throws FzbDaoException {
		return daoHelper.findByPage(condMap, sortMap, pageNo, pageSize);
	}

}
