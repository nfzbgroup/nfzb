package com.wonders.fzb.legislation.services.impl;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislation.beans.LegislationProcessDoc;
import com.wonders.fzb.legislation.dao.LegislationProcessDocDao;
import com.wonders.fzb.legislation.services.LegislationProcessDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * LegislationProcessDoc service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationProcessDocService")
@Transactional
public class LegislationProcessDocServiceImpl implements LegislationProcessDocService {

	@Autowired
	private LegislationProcessDocDao legislationProcessDocDao;
	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationProcessDoc info) {
		legislationProcessDocDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationProcessDoc info) {
		return legislationProcessDocDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationProcessDoc info) {
		legislationProcessDocDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationProcessDoc info) {
		legislationProcessDocDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationProcessDoc findById(String id) {
		return (LegislationProcessDoc) legislationProcessDocDao.load(id);
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
		return legislationProcessDocDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<LegislationProcessDoc> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationProcessDocDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationProcessDoc info) {
		legislationProcessDocDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationProcessDoc> findByHQL(String hql) {
		List<LegislationProcessDoc> legislationProcessDocList = legislationProcessDocDao.findByHQL(hql);
		return legislationProcessDocList;
	}

	@Override
	public void executeSqlUpdate(String sql) {
		legislationProcessDocDao.executeSqlUpdate(sql);
	}
}
