package com.wonders.fzb.legislation.services.impl;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislation.beans.LegislationFiles;
import com.wonders.fzb.legislation.dao.LegislationFilesDao;
import com.wonders.fzb.legislation.services.LegislationFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;


/**
 * LegislationFiles service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationFilesService")
@Transactional
public class LegislationFilesServiceImpl implements LegislationFilesService {

	@Autowired
	private LegislationFilesDao legislationFilesDao;
	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationFiles info) {
		legislationFilesDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationFiles info) {
		return legislationFilesDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationFiles info) {
		legislationFilesDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationFiles info) {
		legislationFilesDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationFiles findById(String id) {
		return (LegislationFiles) legislationFilesDao.load(id);
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
		return legislationFilesDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<LegislationFiles> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationFilesDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationFiles info) {
		legislationFilesDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationFiles> findByHQL(String hql) {
		List<LegislationFiles> legislationFilesList = legislationFilesDao.findByHQL(hql);
		return legislationFilesList;
	}
	@Override
	public void executeSqlUpdate(String sql){
		legislationFilesDao.executeSqlUpdate(sql);
	}

	@Override
	public void updateParentIdById(HttpServletRequest request,String stParentId) {
		Enumeration keys=request.getParameterNames();
		while(keys.hasMoreElements()){
			String key=(String)keys.nextElement();
			String value=request.getParameter(key);
			if(value.startsWith("FIL_")){
				executeSqlUpdate("update LegislationFiles s set s.stParentId='"+stParentId+"' where s.stFileId='"+value+"'");
			}
		}
	}

}
