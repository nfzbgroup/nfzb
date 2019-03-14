package com.wonders.fzb.legislation.dao;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislation.beans.LegislationProcessDoc;
import com.wonders.fzb.legislation.beans.LegislationProcessTask;

import java.util.List;
import java.util.Map;


/**
 * LegislationProcessTask dao接口
 * @author scalffold created by lj
 */
public abstract interface LegislationProcessTaskDao extends BaseDao {
	
	public void save(Object object);
	
	public String saveObj(Object object);
	
	public void saveOrUpdate(LegislationProcessTask info);
	
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize)  throws FzbDaoException;
	
	public List<LegislationProcessTask> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

	public List<LegislationProcessTask> findByHQL(String hql);
	
	public List<Map> findTaskListByNodeId(String stNodeId,String stUserId,String UnitId,String roleId);

	Page<LegislationProcessDoc> findTaskDocListByNodeId(String sql, int pageNo,
			int pageSize);

    Page<LegislationProcessTask> findTaskByNodeId(String sql, int pageNo, int pageSize);

    Page<LegislationProcessDoc> findCheckMeetingByNodeId(String sql, int pageNo, int pageSize);
}
