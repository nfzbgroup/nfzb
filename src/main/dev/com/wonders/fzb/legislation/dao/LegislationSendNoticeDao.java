package com.wonders.fzb.legislation.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislation.beans.LegislationSendNotice;
import com.wonders.fzb.legislation.beans.SendNoticeVO;


/**
 * LegislationSendNotice dao接口
 * @author scalffold created by lj
 */
public abstract interface LegislationSendNoticeDao extends BaseDao {
	
	public void save(Object object);
	
	public String saveObj(Object object);
	
	public void saveOrUpdate(LegislationSendNotice info);
	
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize)  throws FzbDaoException;
	
	public List<LegislationSendNotice> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

	public List<LegislationSendNotice> findByHQL(String hql);

	Page<SendNoticeVO> findSendNoticeList(String wheresql,
			String mainTableName, String columnNames, int pageNo, int pageSize)
			throws ParseException;

	Page<SendNoticeVO> findSendNoticeCitymeetingList(String wheresql, String mainTableName, String columnNames, int pageNo, int pageSize) throws ParseException;
}
