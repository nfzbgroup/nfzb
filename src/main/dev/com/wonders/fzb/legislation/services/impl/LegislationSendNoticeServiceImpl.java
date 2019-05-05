package com.wonders.fzb.legislation.services.impl;

import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislation.beans.*;
import com.wonders.fzb.legislation.dao.*;
import com.wonders.fzb.legislation.services.*;


/**
 * LegislationSendNotice service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationSendNoticeService")
@Transactional
public class LegislationSendNoticeServiceImpl implements LegislationSendNoticeService {

	@Autowired
	private LegislationSendNoticeDao legislationSendNoticeDao;
	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationSendNotice info) {
		legislationSendNoticeDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationSendNotice info) {
		return legislationSendNoticeDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationSendNotice info) {
		legislationSendNoticeDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationSendNotice info) {
		legislationSendNoticeDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationSendNotice findById(String id) {
		return (LegislationSendNotice) legislationSendNoticeDao.load(id);
	}

	/**
	 * 根据Map中过滤条件、排序条件和分页参数进行分页查询.
	 * 
	 * @param condMap
	 *            过滤条件<propertyName,properyValue>
	 * @param sortMap
	 *            排序条件<propertyName,properyValue>
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示记录数.
	 * @return
	 * @throws FzbDaoException
	 */
	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException {
		return legislationSendNoticeDao.findByPage(condMap, sortMap, pageNo, pageSize);
	}

	/**
	 * 根据Map中过滤条件、排序条件进行查询.
	 * 
	 * @param condMap
	 *            过滤条件<propertyName,properyValue>
	 * @param sortMap
	 *            排序条件<propertyName,properyValue>
	 * @return
	 */
	@Override
	public List<LegislationSendNotice> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationSendNoticeDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationSendNotice info) {
		legislationSendNoticeDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationSendNotice> findByHQL(String hql) {
		List<LegislationSendNotice> legislationSendNoticeList = legislationSendNoticeDao.findByHQL(hql);
		return legislationSendNoticeList;
	}
	
	
	@Override
	public Page<SendNoticeVO> findSendNoticeList(String wheresql,
			String mainTableName, String columnNames, int pageNo,
														 int pageSize) throws ParseException {
		return legislationSendNoticeDao.findSendNoticeList(wheresql, mainTableName, columnNames, pageNo, pageSize);
	}
}
