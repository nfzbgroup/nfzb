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
 * LegislationCheckmeetingTaskd service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationCheckmeetingTaskdService")
@Transactional
public class LegislationCheckmeetingTaskdServiceImpl implements LegislationCheckmeetingTaskdService {

	@Autowired
	private LegislationCheckmeetingTaskdDao legislationCheckmeetingTaskdDao;
	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationCheckmeetingTaskd info) {
		legislationCheckmeetingTaskdDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationCheckmeetingTaskd info) {
		return legislationCheckmeetingTaskdDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationCheckmeetingTaskd info) {
		legislationCheckmeetingTaskdDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationCheckmeetingTaskd info) {
		legislationCheckmeetingTaskdDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationCheckmeetingTaskd findById(String id) {
		return (LegislationCheckmeetingTaskd) legislationCheckmeetingTaskdDao.load(id);
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
		return legislationCheckmeetingTaskdDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<LegislationCheckmeetingTaskd> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationCheckmeetingTaskdDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationCheckmeetingTaskd info) {
		legislationCheckmeetingTaskdDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationCheckmeetingTaskd> findByHQL(String hql) {
		List<LegislationCheckmeetingTaskd> legislationCheckmeetingTaskdList = legislationCheckmeetingTaskdDao.findByHQL(hql);
		return legislationCheckmeetingTaskdList;
	}
}
