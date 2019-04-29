package com.wonders.fzb.citymeeting.services.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.citymeeting.beans.*;
import com.wonders.fzb.citymeeting.dao.*;
import com.wonders.fzb.citymeeting.services.*;


/**
 * LegislationCitymeeting service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationCitymeetingService")
@Transactional
public class LegislationCitymeetingServiceImpl implements LegislationCitymeetingService {

	@Autowired
	private LegislationCitymeetingDao legislationCitymeetingDao;
	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationCitymeeting info) {
		legislationCitymeetingDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationCitymeeting info) {
		return legislationCitymeetingDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationCitymeeting info) {
		legislationCitymeetingDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationCitymeeting info) {
		legislationCitymeetingDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationCitymeeting findById(String id) {
		return (LegislationCitymeeting) legislationCitymeetingDao.load(id);
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
		return legislationCitymeetingDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<LegislationCitymeeting> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationCitymeetingDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationCitymeeting info) {
		legislationCitymeetingDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationCitymeeting> findByHQL(String hql) {
		List<LegislationCitymeeting> legislationCitymeetingList = legislationCitymeetingDao.findByHQL(hql);
		return legislationCitymeetingList;
	}
}
