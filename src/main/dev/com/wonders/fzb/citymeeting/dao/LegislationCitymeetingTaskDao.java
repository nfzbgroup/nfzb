package com.wonders.fzb.citymeeting.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.citymeeting.beans.LegislationCitymeeting;
import com.wonders.fzb.citymeeting.beans.LegislationCitymeetingTask;
import com.wonders.fzb.legislation.beans.LegislationProcessDoc;


/**
 * LegislationCitymeetingTask dao接口
 * @author scalffold created by lj
 */
public abstract interface LegislationCitymeetingTaskDao extends BaseDao {
	
	public void save(Object object);
	
	public String saveObj(Object object);
	
	public void saveOrUpdate(LegislationCitymeetingTask info);
	
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize)  throws FzbDaoException;
	
	public List<LegislationCitymeetingTask> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

	public List<LegislationCitymeetingTask> findByHQL(String hql);
	
	Page<LegislationCitymeeting> findTaskDocListByNodeId(String wheresql,
			int pageNo, int pageSize) throws ParseException;
}
