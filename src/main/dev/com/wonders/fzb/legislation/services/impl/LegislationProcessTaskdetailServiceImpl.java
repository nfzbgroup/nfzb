package com.wonders.fzb.legislation.services.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislation.beans.*;
import com.wonders.fzb.legislation.dao.*;
import com.wonders.fzb.legislation.services.*;


/**
 * LegislationProcessTaskdetail service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationProcessTaskdetailService")
@Transactional
public class LegislationProcessTaskdetailServiceImpl implements LegislationProcessTaskdetailService {

	@Autowired
	private LegislationProcessTaskdetailDao legislationProcessTaskdetailDao;
	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationProcessTaskdetail info) {
		legislationProcessTaskdetailDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationProcessTaskdetail info) {
		return legislationProcessTaskdetailDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationProcessTaskdetail info) {
		legislationProcessTaskdetailDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationProcessTaskdetail info) {
		legislationProcessTaskdetailDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationProcessTaskdetail findById(String id) {
		return (LegislationProcessTaskdetail) legislationProcessTaskdetailDao.load(id);
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
		return legislationProcessTaskdetailDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<LegislationProcessTaskdetail> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationProcessTaskdetailDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationProcessTaskdetail info) {
		legislationProcessTaskdetailDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationProcessTaskdetail> findByHQL(String hql) {
		List<LegislationProcessTaskdetail> legislationProcessTaskdetailList = legislationProcessTaskdetailDao.findByHQL(hql);
		return legislationProcessTaskdetailList;
	}
}
