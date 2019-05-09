package com.wonders.fzb.assess.services.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.assess.beans.*;
import com.wonders.fzb.assess.dao.*;
import com.wonders.fzb.assess.services.*;


/**
 * LegislationAssessDeal service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationAssessDealService")
@Transactional
public class LegislationAssessDealServiceImpl implements LegislationAssessDealService {

	@Autowired
	private LegislationAssessDealDao legislationAssessDealDao;
	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationAssessDeal info) {
		legislationAssessDealDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationAssessDeal info) {
		return legislationAssessDealDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationAssessDeal info) {
		legislationAssessDealDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationAssessDeal info) {
		legislationAssessDealDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationAssessDeal findById(String id) {
		return (LegislationAssessDeal) legislationAssessDealDao.load(id);
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
		return legislationAssessDealDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<LegislationAssessDeal> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationAssessDealDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationAssessDeal info) {
		legislationAssessDealDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationAssessDeal> findByHQL(String hql) {
		List<LegislationAssessDeal> legislationAssessDealList = legislationAssessDealDao.findByHQL(hql);
		return legislationAssessDealList;
	}
}
