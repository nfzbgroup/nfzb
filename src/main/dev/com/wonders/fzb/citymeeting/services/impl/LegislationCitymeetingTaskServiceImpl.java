package com.wonders.fzb.citymeeting.services.impl;

import java.text.ParseException;
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
import com.wonders.fzb.legislation.beans.LegislationProcessTask;


/**
 * LegislationCitymeetingTask service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationCitymeetingTaskService")
@Transactional
public class LegislationCitymeetingTaskServiceImpl implements LegislationCitymeetingTaskService {

	@Autowired
	private LegislationCitymeetingTaskDao legislationCitymeetingTaskDao;
	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationCitymeetingTask info) {
		legislationCitymeetingTaskDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationCitymeetingTask info) {
		return legislationCitymeetingTaskDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationCitymeetingTask info) {
		legislationCitymeetingTaskDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationCitymeetingTask info) {
		legislationCitymeetingTaskDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationCitymeetingTask findById(String id) {
		return (LegislationCitymeetingTask) legislationCitymeetingTaskDao.load(id);
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
		return legislationCitymeetingTaskDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<LegislationCitymeetingTask> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationCitymeetingTaskDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationCitymeetingTask info) {
		legislationCitymeetingTaskDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationCitymeetingTask> findByHQL(String hql) {
		List<LegislationCitymeetingTask> legislationCitymeetingTaskList = legislationCitymeetingTaskDao.findByHQL(hql);
		return legislationCitymeetingTaskList;
	}
	
	/**
	 * task列表分页
	 * @param sql
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Override
	public Page<LegislationCitymeeting> findTaskByNodeId(String sql, int pageNo,
														 int pageSize) throws ParseException {
		return legislationCitymeetingTaskDao.findTaskDocListByNodeId(sql, pageNo, pageSize);
	}
}
