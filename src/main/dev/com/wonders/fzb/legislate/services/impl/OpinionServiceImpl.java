package com.wonders.fzb.legislate.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.FileRecord;
import com.wonders.fzb.legislate.beans.Opinion;
import com.wonders.fzb.legislate.dao.FileRecordDao;
import com.wonders.fzb.legislate.dao.OpinionDao;
import com.wonders.fzb.legislate.services.OpinionService;

/**
 * Opinion service实现
 * 
 * @author scalffold created by lj
 */
@Service(value="opinionService")
@Transactional
public class OpinionServiceImpl implements OpinionService{
	@Autowired
	private  OpinionDao opinionDao;
	@Autowired
	private FileRecordDao fileRecordDao; 
	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(Opinion info,FileRecord filerecord) {
		opinionDao.save(info);
		fileRecordDao.save(filerecord);
	}
	

	/**
	 * 更新实体对象
	 */
	@Override
	public void update(Opinion info) {
		opinionDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(Opinion info) {
		opinionDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public Opinion findById(String id) {
		return (Opinion) opinionDao.load(id);
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
		return opinionDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<Opinion> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return opinionDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(Opinion info) {
		opinionDao.saveOrUpdate(info);
	}
}
