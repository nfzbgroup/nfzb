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
 * LegislationCheckmeeting service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationCheckmeetingService")
@Transactional
public class LegislationCheckmeetingServiceImpl implements LegislationCheckmeetingService {

	@Autowired
	private LegislationCheckmeetingDao legislationCheckmeetingDao;
	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationCheckmeeting info) {
		legislationCheckmeetingDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationCheckmeeting info) {
		return legislationCheckmeetingDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationCheckmeeting info) {
		legislationCheckmeetingDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationCheckmeeting info) {
		legislationCheckmeetingDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationCheckmeeting findById(String id) {
		return (LegislationCheckmeeting) legislationCheckmeetingDao.load(id);
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
		return legislationCheckmeetingDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<LegislationCheckmeeting> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationCheckmeetingDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationCheckmeeting info) {
		legislationCheckmeetingDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationCheckmeeting> findByHQL(String hql) {
		List<LegislationCheckmeeting> legislationCheckmeetingList = legislationCheckmeetingDao.findByHQL(hql);
		return legislationCheckmeetingList;
	}

	@Override
	public void executeSqlUpdate(String sql) {
		legislationCheckmeetingDao.executeSqlUpdate(sql);
	}
}
