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
 * LegislationClean service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationCleanService")
@Transactional
public class LegislationCleanServiceImpl implements LegislationCleanService {

	@Autowired
	private LegislationCleanDao legislationCleanDao;
	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationClean info) {
		legislationCleanDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationClean info) {
		return legislationCleanDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationClean info) {
		legislationCleanDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationClean info) {
		legislationCleanDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationClean findById(String id) {
		return (LegislationClean) legislationCleanDao.load(id);
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
		return legislationCleanDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<LegislationClean> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationCleanDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationClean info) {
		legislationCleanDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationClean> findByHQL(String hql) {
		List<LegislationClean> legislationCleanList = legislationCleanDao.findByHQL(hql);
		return legislationCleanList;
	}
}
