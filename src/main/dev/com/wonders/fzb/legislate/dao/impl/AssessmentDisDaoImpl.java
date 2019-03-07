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
import com.wonders.fzb.legislate.beans.AssessmentDis;
import com.wonders.fzb.legislate.beans.AssessmentReportDis;
import com.wonders.fzb.legislate.dao.AssessmentDisDao;
@Repository("assessmentDisDao")
public class AssessmentDisDaoImpl extends BaseSupportDao implements AssessmentDisDao{
	@Override
	public void add(AssessmentDis assessmentReportDis) {
		assessmentReportDis.setAssessmentDisId(super.getId("assessment_dis_", "ASSESSMENT_DIS_", 12));
		super.save(assessmentReportDis);
	}

	@Override
	public void saveOrUpdate(AssessmentDis assessmentDis) {
		super.saveOrUpdate(assessmentDis);
	}

	@Override
	public AssessmentDis findById(String distributeId) {
		return (AssessmentDis)super.load(distributeId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AssessmentDis> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByList(condMap, sortMap, "AssessmentDis");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<AssessmentDis> findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo,
			int pageSize) throws FzbDaoException {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		return super.findByPage(condMap, sortMap, pageNo, pageSize, "AssessmentDis");
	}

	@Override
	public void delete(AssessmentDis assessmentDis) {
		super.delete(assessmentDis);
	}

	@Override
	public List<AssessmentDis> findByAssessmentId(String assessmentId, String unitId) {
		String sql = "";
		if(unitId==null) {
			sql = "select t.* from assessment_dis t where t.assessment_id = '" + assessmentId + "'";
		}else {
			sql = "select t.* from assessment_dis t where t.assessment_id = '" + assessmentId + "' and t.assessment_receive_unit = '" + unitId + "'";
		}
		
		Session session = getSession();
		Query query = session.createSQLQuery(sql).addEntity(AssessmentDis.class);
		List<AssessmentDis> results = query.list();
		return results;
	}

}
