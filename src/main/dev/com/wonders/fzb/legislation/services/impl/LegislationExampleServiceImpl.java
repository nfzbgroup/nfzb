package com.wonders.fzb.legislation.services.impl;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislation.beans.LegislationExample;
import com.wonders.fzb.legislation.dao.LegislationExampleDao;
import com.wonders.fzb.legislation.services.LegislationExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * LegislationExample service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationExampleService")
@Transactional
public class LegislationExampleServiceImpl implements LegislationExampleService {

	@Autowired
	private LegislationExampleDao legislationExampleDao;
	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationExample info) {
		legislationExampleDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationExample info) {
		return legislationExampleDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationExample info) {
		legislationExampleDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationExample info) {
		legislationExampleDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationExample findById(String id) {
		return (LegislationExample) legislationExampleDao.load(id);
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
		return legislationExampleDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<LegislationExample> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationExampleDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationExample info) {
		legislationExampleDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationExample> findByHQL(String hql) {
		List<LegislationExample> legislationExampleList = legislationExampleDao.findByHQL(hql);
		return legislationExampleList;
	}

	/**
	 * 查询范本信息
	 *
	 * @param condMap
	 * @param sortMap
	 * @return
	 */
	@Override
	public List<Map> queryLegislationExampleFiles(Map<String, Object> condMap, Map<String, String> sortMap) {
		List<LegislationExample> legislationExampleList = findByList(condMap, sortMap);
		List<Map> legislationExampleFilesList=new ArrayList<>();
		legislationExampleList.forEach((LegislationExample legislationExample)->{
			Map map=new HashMap();
			map.put("stExampleId",legislationExample.getStExampleId());
			map.put("stExampleName",legislationExample.getStExampleName());
			map.put("stNeed",legislationExample.getStNeed());
			map.put("fileId",null);
			map.put("fileName",null);
			map.put("fileUrl",null);
			legislationExampleFilesList.add(map);
		});
		return legislationExampleFilesList;
	}
}
