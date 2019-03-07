package com.wonders.fzb.platform.services;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.platform.beans.OpInfo;

/**
 * WFC_F_OP_INFO业务接口
 * 
 * @author scalffold
 * 
 */
public interface OpInfoService {
	/**
	 * 添加实体对象
	 * 
	 * @param opInfo
	 */
	public void add(OpInfo opInfo);

	/**
	 * 更新实体对象
	 * 
	 * @param opInfo
	 */
	public void update(OpInfo opInfo);
	
	/**
	 * 删除实体对象
	 * 
	 * @param opInfo
	 */
	public void delete(String id);

	/**
	 * 
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 * 
	 * @param id 主键
	 * @return
	 */
	public OpInfo findById(String id);

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
	public List<OpInfo> findByList(Map<String, Object> condMap,
			Map<String, String> sortMap);

	/**
	 * 根据组进行查询.
	 * 
	 * @param opId 操作标识
	 * @return
	 */
	public List<OpInfo> findByGroup(String groupId);
	
	/**
	 * 根据用户标识查询操作列表
	 * 
	 * @param userId 用户标识
	 * @return
	 */
	public List<OpInfo> findByUserId(String userId);
	
	/**
	 * 根据组织标识查询操作列表
	 * 
	 * @param teamId 组织标识
	 * @return
	 */
	public List<OpInfo> findByTeamId(String teamId);
	
	/**
	 * 根据角色标识查询操作列表
	 * 
	 * @param roleId 角色标识
	 * @return
	 */
	public List<OpInfo> findByRoleId(String roleId);
}
