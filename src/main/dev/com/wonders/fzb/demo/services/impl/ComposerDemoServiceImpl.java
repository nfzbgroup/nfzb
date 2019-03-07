package com.wonders.fzb.demo.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.demo.beans.ComposerDemo;
import com.wonders.fzb.demo.dao.ComposerDemoDao;
import com.wonders.fzb.demo.services.ComposerDemoService;


/**
 * AuditMeeting service接口
 * @author scalffold created by lj
 */
@Service("composerDemoService")
@Transactional
public class ComposerDemoServiceImpl implements ComposerDemoService{
	@Autowired
	ComposerDemoDao daohelper;

	/**
	 * 添加实体对象
	 */
	@Override
	public void add(ComposerDemo info) {
		daohelper.save(info);
	}
	

	/**
	 * 更新实体对象
	 */
	@Override
	public void update(ComposerDemo info) {
		daohelper.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(ComposerDemo info) {
		daohelper.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public ComposerDemo findById(String id) {
		return (ComposerDemo) daohelper.load(id);
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
		return daohelper.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<ComposerDemo> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return daohelper.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(ComposerDemo info) {
		daohelper.saveOrUpdate(info);
	}
}
