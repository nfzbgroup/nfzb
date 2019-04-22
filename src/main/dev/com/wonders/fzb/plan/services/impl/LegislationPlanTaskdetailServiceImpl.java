package com.wonders.fzb.plan.services.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.plan.beans.*;
import com.wonders.fzb.plan.dao.*;
import com.wonders.fzb.plan.services.*;


/**
 * LegislationPlanTaskdetail service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationPlanTaskdetailService")
@Transactional
public class LegislationPlanTaskdetailServiceImpl implements LegislationPlanTaskdetailService {

	@Autowired
	private LegislationPlanTaskdetailDao legislationPlanTaskdetailDao;
	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationPlanTaskdetail info) {
		legislationPlanTaskdetailDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationPlanTaskdetail info) {
		return legislationPlanTaskdetailDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationPlanTaskdetail info) {
		legislationPlanTaskdetailDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationPlanTaskdetail info) {
		legislationPlanTaskdetailDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationPlanTaskdetail findById(String id) {
		return (LegislationPlanTaskdetail) legislationPlanTaskdetailDao.load(id);
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
		return legislationPlanTaskdetailDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<LegislationPlanTaskdetail> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationPlanTaskdetailDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationPlanTaskdetail info) {
		legislationPlanTaskdetailDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationPlanTaskdetail> findByHQL(String hql) {
		List<LegislationPlanTaskdetail> legislationPlanTaskdetailList = legislationPlanTaskdetailDao.findByHQL(hql);
		return legislationPlanTaskdetailList;
	}
}
