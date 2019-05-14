package com.wonders.fzb.clean.services.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.clean.beans.*;
import com.wonders.fzb.clean.dao.*;
import com.wonders.fzb.clean.services.*;


/**
 * LegislationCleanDeal service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationCleanDealService")
@Transactional
public class LegislationCleanDealServiceImpl implements LegislationCleanDealService {

	@Autowired
	private LegislationCleanDealDao legislationCleanDealDao;
	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationCleanDeal info) {
		legislationCleanDealDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationCleanDeal info) {
		return legislationCleanDealDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationCleanDeal info) {
		legislationCleanDealDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationCleanDeal info) {
		legislationCleanDealDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationCleanDeal findById(String id) {
		return (LegislationCleanDeal) legislationCleanDealDao.load(id);
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
		return legislationCleanDealDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<LegislationCleanDeal> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationCleanDealDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationCleanDeal info) {
		legislationCleanDealDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationCleanDeal> findByHQL(String hql) {
		List<LegislationCleanDeal> legislationCleanDealList = legislationCleanDealDao.findByHQL(hql);
		return legislationCleanDealList;
	}
}
