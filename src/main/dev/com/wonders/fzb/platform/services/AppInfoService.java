package com.wonders.fzb.platform.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.platform.beans.AppInfo;

/**
 * WEGOV_P_APP_CONFIG业务接口
 * 
 * @author scalffold
 * 
 */
public interface AppInfoService {
	
	/**
	 * 添加或修改实体对象

	 * 
	 * @param appConfig
	 */
	public void saveOrUpdate(AppInfo appInfo);
	
	/**
	 * 添加实体对象
	 * 
	 * @param appConfig
	 */
	public void add(AppInfo appInfo);

	/**
	 * 更新实体对象
	 * 
	 * @param appConfig
	 */
	public void update(AppInfo appInfo);
	
	/**
	 * 删除实体对象
	 * 
	 * @param appConfig
	 */
	public void delete(AppInfo appInfo);
	
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
	public List<AppInfo> findByList(Map<String, Object> condMap,
			Map<String, String> sortMap);

	/**
	 *查询所有应用列表
	 *@return ArrayList of StrucApp
	 *@exception SystemException,QueryException
	 */
	public List<AppInfo> getAllApp();
	
	/**
	 *查询所有应用功能
	 *@param String app_id--应用标识<br>char type--类型，'f'流程功能，'p'权限功能
	 *@return ArrayList of StrucFunc,QueryException
	 *@exception SystemException
	 */
	public ArrayList getAllAppFunc(String app_id,char type);
}
