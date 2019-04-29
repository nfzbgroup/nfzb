package com.wonders.fzb.report.services.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.report.beans.*;
import com.wonders.fzb.report.dao.*;
import com.wonders.fzb.report.services.*;


/**
 * LegislationReportTaskdetail service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationReportTaskdetailService")
@Transactional
public class LegislationReportTaskdetailServiceImpl implements LegislationReportTaskdetailService {

	@Autowired
	private LegislationReportTaskdetailDao legislationReportTaskdetailDao;
	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationReportTaskdetail info) {
		legislationReportTaskdetailDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationReportTaskdetail info) {
		return legislationReportTaskdetailDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationReportTaskdetail info) {
		legislationReportTaskdetailDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationReportTaskdetail info) {
		legislationReportTaskdetailDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationReportTaskdetail findById(String id) {
		return (LegislationReportTaskdetail) legislationReportTaskdetailDao.load(id);
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
		return legislationReportTaskdetailDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<LegislationReportTaskdetail> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationReportTaskdetailDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationReportTaskdetail info) {
		legislationReportTaskdetailDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationReportTaskdetail> findByHQL(String hql) {
		List<LegislationReportTaskdetail> legislationReportTaskdetailList = legislationReportTaskdetailDao.findByHQL(hql);
		return legislationReportTaskdetailList;
	}
}
