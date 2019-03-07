package com.wonders.fzb.legislate.services.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.FileRecord;
import com.wonders.fzb.legislate.beans.ModelFileRecord;
import com.wonders.fzb.legislate.dao.FileRecordDao;
import com.wonders.fzb.legislate.services.FileRecordService;

/**
 * FileRecord service实现
 * 
 * @author scalffold created by lj
 */
@Service("fileRecordService")
@Transactional
public class FileRecordServiceImpl implements FileRecordService{
	@Autowired
	FileRecordDao fileRecordDao;
	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(FileRecord info) {
		fileRecordDao.save(info);
	}
	

	/**
	 * 更新实体对象
	 */
	@Override
	public void update(FileRecord info) {
		fileRecordDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(FileRecord info) {
		fileRecordDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public FileRecord findById(String id) {
		return (FileRecord) fileRecordDao.load(id);
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
		return fileRecordDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<FileRecord> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return fileRecordDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(FileRecord info) {
		fileRecordDao.saveOrUpdate(info);
	}
	
	/**
	 *@see com.wonders.fzb.legislate.services.FileRecordService#findByList(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<ModelFileRecord> findByList(String outId, String activityType,
			String bizType) {
		Map<String, Object> condMap = new LinkedHashMap<String, Object>();
		if (StringUtils.hasText(outId)) {
			condMap.put("out_id", outId);
		}
		if (StringUtils.hasText(activityType)) {
			condMap.put("ACTIVITY_TYPE", activityType);
		}
		condMap.put("MODEL_TYPE", bizType);
		return this.fileRecordDao.findModelFileRecordByList(condMap );
	}
}
