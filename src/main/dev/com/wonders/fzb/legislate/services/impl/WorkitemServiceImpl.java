package com.wonders.fzb.legislate.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.Workitem;
import com.wonders.fzb.legislate.dao.WorkitemDao;
import com.wonders.fzb.legislate.services.WorkitemService;

/**
 * Workitem service实现
 * 
 * @author scalffold created by lj
 */
@Service(value="workitemService")
@Transactional
public class WorkitemServiceImpl implements WorkitemService{
	@Autowired
	WorkitemDao workitemDao;
	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(Workitem info) {
		workitemDao.save(info);
	}
	

	/**
	 * 更新实体对象
	 */
	@Override
	public void update(Workitem info) {
		workitemDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(Workitem info) {
		workitemDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public Workitem findById(String id) {
		return (Workitem) workitemDao.load(id);
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
		return workitemDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<Workitem> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return workitemDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(Workitem info) {
		workitemDao.saveOrUpdate(info);
	}
}


