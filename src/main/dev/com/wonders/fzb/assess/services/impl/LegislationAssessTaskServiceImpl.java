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
 * LegislationAssessTask service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationAssessTaskService")
@Transactional
public class LegislationAssessTaskServiceImpl implements LegislationAssessTaskService {

	@Autowired
	private LegislationAssessTaskDao legislationAssessTaskDao;
	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationAssessTask info) {
		legislationAssessTaskDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationAssessTask info) {
		return legislationAssessTaskDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationAssessTask info) {
		legislationAssessTaskDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationAssessTask info) {
		legislationAssessTaskDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationAssessTask findById(String id) {
		return (LegislationAssessTask) legislationAssessTaskDao.load(id);
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
		return legislationAssessTaskDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<LegislationAssessTask> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationAssessTaskDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationAssessTask info) {
		legislationAssessTaskDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationAssessTask> findByHQL(String hql) {
		List<LegislationAssessTask> legislationAssessTaskList = legislationAssessTaskDao.findByHQL(hql);
		return legislationAssessTaskList;
	}
}
