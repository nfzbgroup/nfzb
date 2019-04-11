package com.wonders.fzb.checkmeeting.services.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.checkmeeting.beans.*;
import com.wonders.fzb.checkmeeting.dao.*;
import com.wonders.fzb.checkmeeting.services.*;


/**
 * LegislationCheckmeetingItem service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationCheckmeetingItemService")
@Transactional
public class LegislationCheckmeetingItemServiceImpl implements LegislationCheckmeetingItemService {

	@Autowired
	private LegislationCheckmeetingItemDao legislationCheckmeetingItemDao;
	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationCheckmeetingItem info) {
		legislationCheckmeetingItemDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationCheckmeetingItem info) {
		return legislationCheckmeetingItemDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationCheckmeetingItem info) {
		legislationCheckmeetingItemDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationCheckmeetingItem info) {
		legislationCheckmeetingItemDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationCheckmeetingItem findById(String id) {
		return (LegislationCheckmeetingItem) legislationCheckmeetingItemDao.load(id);
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
		return legislationCheckmeetingItemDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<LegislationCheckmeetingItem> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationCheckmeetingItemDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationCheckmeetingItem info) {
		legislationCheckmeetingItemDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationCheckmeetingItem> findByHQL(String hql) {
		List<LegislationCheckmeetingItem> legislationCheckmeetingItemList = legislationCheckmeetingItemDao.findByHQL(hql);
		return legislationCheckmeetingItemList;
	}
}
