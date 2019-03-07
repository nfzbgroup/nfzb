package com.wonders.fzb.platform.services;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.platform.beans.BulletinInfo;

/**
 * WEGOV_P_APP_CONFIG业务接口
 * 
 * @author scalffold
 * 
 */
public interface BulletinInfoService {
	
	/**
	 * 添加或修改实体对象

	 * 
	 * @param appConfig
	 */
	public void saveOrUpdate(BulletinInfo bulletinInfo);
	
	/**
	 * 添加实体对象
	 * 
	 * @param appConfig
	 */
	public void add(BulletinInfo bulletinInfo);

	/**
	 * 更新实体对象
	 * 
	 * @param appConfig
	 */
	public void update(BulletinInfo bulletinInfo);
	
	/**
	 * 删除实体对象
	 * 
	 * @param appConfig
	 */
	public void delete(BulletinInfo bulletinInfo);
	
	/**
	 * 根据分页参数进行分页查询.
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示记录数.
	 * @return
	 */
	public Page findByPage(int pageNo, int pageSize);

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
	 */
	public Page findByPage(Map<String, Object> condMap,
			Map<String, String> sortMap, int pageNo, int pageSize);

	public BulletinInfo findById(String id);

	/**
	 * 根据Map中过滤条件、排序条件进行查询.
	 * 
	 * @param condMap
	 *            过滤条件<propertyName,properyValue>
	 * @param sortMap
	 *            排序条件<propertyName,properyValue>
	 * @return
	 */
	public List<BulletinInfo> findByList(Map<String, Object> condMap,
			Map<String, String> sortMap);

	/**
	   * 得到公告列表
	   * @return 公告信息的列表
	   * @throws ServiceException
	   */
	public List<BulletinInfo> getBulletinList();
	
}
