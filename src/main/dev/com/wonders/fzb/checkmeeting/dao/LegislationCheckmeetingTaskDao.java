package com.wonders.fzb.checkmeeting.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeeting;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeetingTask;
import com.wonders.fzb.legislation.beans.LegislationProcessDoc;
import com.wonders.fzb.legislation.beans.LegislationProcessTask;


/**
 * LegislationCheckmeetingTask dao接口
 * @author scalffold created by lj
 */
public abstract interface LegislationCheckmeetingTaskDao extends BaseDao {
	
	public void save(Object object);
	
	public String saveObj(Object object);
	
	public void saveOrUpdate(LegislationCheckmeetingTask info);
	
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize)  throws FzbDaoException;
	
	public List<LegislationCheckmeetingTask> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

	public List<LegislationCheckmeetingTask> findByHQL(String hql);
	
	public List<Map> findTaskListByNodeId(String stNodeId,String stUserId,String UnitId,String roleId);

	Page<LegislationCheckmeeting> findTaskDocListByNodeId(String sql, int pageNo,
			int pageSize) throws ParseException;
	
	 Page<LegislationCheckmeetingTask> findTaskByNodeId(String sql, int pageNo, int pageSize) throws ParseException;
	
	Page<LegislationCheckmeeting> findCheckMeetingByNodeId(String sql, int pageNo, int pageSize) throws ParseException;
    
    public LegislationCheckmeetingTask findById(String id);
}
