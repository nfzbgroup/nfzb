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
 * LegislationAssessItem service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationAssessItemService")
@Transactional
public class LegislationAssessItemServiceImpl implements LegislationAssessItemService {

	@Autowired
	private LegislationAssessItemDao legislationAssessItemDao;
	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationAssessItem info) {
		legislationAssessItemDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationAssessItem info) {
		return legislationAssessItemDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationAssessItem info) {
		legislationAssessItemDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationAssessItem info) {
		legislationAssessItemDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationAssessItem findById(String id) {
		return (LegislationAssessItem) legislationAssessItemDao.load(id);
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
		return legislationAssessItemDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<LegislationAssessItem> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationAssessItemDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationAssessItem info) {
		legislationAssessItemDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationAssessItem> findByHQL(String hql) {
		List<LegislationAssessItem> legislationAssessItemList = legislationAssessItemDao.findByHQL(hql);
		return legislationAssessItemList;
	}
}
