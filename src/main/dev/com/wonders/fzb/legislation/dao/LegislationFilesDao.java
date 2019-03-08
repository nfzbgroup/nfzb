package com.wonders.fzb.legislation.dao;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislation.beans.LegislationFiles;

import java.util.List;
import java.util.Map;


/**
 * LegislationFiles dao接口
 * @author scalffold created by lj
 */
public abstract interface LegislationFilesDao extends BaseDao {
	
	public void save(Object object);
	
	public String saveObj(Object object);
	
	public void saveOrUpdate(LegislationFiles info);
	
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize)  throws FzbDaoException;
	
	public List<LegislationFiles> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

	public List<LegislationFiles> findByHQL(String hql);

	public void executeSqlUpdate(String sql);

}
