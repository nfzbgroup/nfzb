package com.wonders.fzb.checkmeeting.dao;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeeting;


/**
 * LegislationCheckmeeting dao接口
 * @author scalffold created by lj
 */
public abstract interface LegislationCheckmeetingDao extends BaseDao {
	
	public void save(Object object);
	
	public String saveObj(Object object);
	
	public void saveOrUpdate(LegislationCheckmeeting info);
	
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize)  throws FzbDaoException;
	
	public List<LegislationCheckmeeting> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

	public List<LegislationCheckmeeting> findByHQL(String hql);
	
	void executeSqlUpdate(String sql);
}
