package com.wonders.fzb.legislation.services.impl;

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
 * LegislationProcessDeal service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationProcessDealService")
@Transactional
public class LegislationProcessDealServiceImpl implements LegislationProcessDealService {

	@Autowired
	private LegislationProcessDealDao legislationProcessDealDao;
	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationProcessDeal info) {
		legislationProcessDealDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationProcessDeal info) {
		return legislationProcessDealDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationProcessDeal info) {
		legislationProcessDealDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationProcessDeal info) {
		legislationProcessDealDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationProcessDeal findById(String id) {
		return (LegislationProcessDeal) legislationProcessDealDao.load(id);
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
		return legislationProcessDealDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<LegislationProcessDeal> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationProcessDealDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationProcessDeal info) {
		legislationProcessDealDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationProcessDeal> findByHQL(String hql) {
		List<LegislationProcessDeal> legislationProcessDealList = legislationProcessDealDao.findByHQL(hql);
		return legislationProcessDealList;
	}
}
