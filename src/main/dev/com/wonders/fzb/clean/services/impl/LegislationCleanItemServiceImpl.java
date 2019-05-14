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
 * LegislationCleanItem service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationCleanItemService")
@Transactional
public class LegislationCleanItemServiceImpl implements LegislationCleanItemService {

	@Autowired
	private LegislationCleanItemDao legislationCleanItemDao;
	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationCleanItem info) {
		legislationCleanItemDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationCleanItem info) {
		return legislationCleanItemDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationCleanItem info) {
		legislationCleanItemDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationCleanItem info) {
		legislationCleanItemDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationCleanItem findById(String id) {
		return (LegislationCleanItem) legislationCleanItemDao.load(id);
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
		return legislationCleanItemDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<LegislationCleanItem> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationCleanItemDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationCleanItem info) {
		legislationCleanItemDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationCleanItem> findByHQL(String hql) {
		List<LegislationCleanItem> legislationCleanItemList = legislationCleanItemDao.findByHQL(hql);
		return legislationCleanItemList;
	}
}
