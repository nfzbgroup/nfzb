package com.wonders.fzb.legislate.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.AssessmentAttach;
import com.wonders.fzb.legislate.dao.AssessmentAttachDao;
import com.wonders.fzb.legislate.services.AssessmentAttachService;
@Service("assessmentAttachService")
@Transactional
public class AssessmentAttachServiceImpl implements AssessmentAttachService{

	@Autowired
	private AssessmentAttachDao daoHelper;
	
	@Override
	public void saveOrUpdate(AssessmentAttach info) {
		daoHelper.saveOrUpdate(info);
	}

	@Override
	public void add(AssessmentAttach info) {
		daoHelper.add(info);
	}

	@Override
	public void delete(AssessmentAttach info) {
		daoHelper.delete(info);
	}

	@Override
	public List<AssessmentAttach> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return daoHelper.findByList(condMap, sortMap);
	}

	@Override
	public Page<AssessmentAttach> findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo,
			int pageSize) throws FzbDaoException {
		return daoHelper.findByPage(condMap, sortMap, pageNo, pageSize);
	}

}
