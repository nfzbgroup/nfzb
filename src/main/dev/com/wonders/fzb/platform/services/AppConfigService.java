package com.wonders.fzb.platform.services;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.platform.beans.AppConfig;

/**
 * WEGOV_P_APP_CONFIG业务接口
 * 
 * @author scalffold
 * 
 */
public interface AppConfigService {
	
	/**
	 * 添加或修改实体对象

	 * 
	 * @param appConfig
	 */
	public void saveOrUpdate(AppConfig appConfig);
	
	/**
	 * 添加实体对象
	 * 
	 * @param appConfig
	 */
	public void add(AppConfig appConfig);

	/**
	 * 更新实体对象
	 * 
	 * @param appConfig
	 */
	public void update(AppConfig appConfig);
	
	/**
	 * 删除实体对象
	 * 
	 * @param appConfig
	 */
	public void delete(AppConfig appConfig);
	
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


	/**
	 * 根据Map中过滤条件、排序条件进行查询.
	 * 
	 * @param condMap
	 *            过滤条件<propertyName,properyValue>
	 * @param sortMap
	 *            排序条件<propertyName,properyValue>
	 * @return
	 */
	public List<AppConfig> findByList(Map<String, Object> condMap,
			Map<String, String> sortMap);


	/**
	 * 根据应用标识进行查询.
	 * 
	 * @param appId
	 *            应用标识
	 * @return
	 */
	public List<AppConfig> findByAppId(String appId);
	
	/**
	 * 根据配置项名称获得应用配置项值
	 * @param appId  应用标识
	 * @param name  配置项名称
	 * @return 配置项值
	 * @throws SystemException
	 */
	public String getAppConfigItem(String appId,String name);
	
}
