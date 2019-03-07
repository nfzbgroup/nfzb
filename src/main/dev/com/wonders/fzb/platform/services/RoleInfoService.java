package com.wonders.fzb.platform.services;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.platform.beans.RoleInfo;

/**
 * WFC_F_ROLE_INFO业务接口
 * 
 * @author scalffold
 * 
 */
public interface RoleInfoService {
	/**
	 * 添加实体对象
	 * 
	 * @param roleInfo
	 */
	public void add(RoleInfo roleInfo);

	/**
	 * 更新实体对象
	 * 
	 * @param roleInfo
	 */
	public void update(RoleInfo roleInfo);
	
	/**
	 * 删除实体对象
	 * 
	 * @param roleInfo
	 */
	public void delete(String id);

	/**
	 * 
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 * 
	 * @param id 主键
	 * @return
	 */
	public RoleInfo findById(String id);

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
	public List<RoleInfo> findByList(Map<String, Object> condMap,
			Map<String, String> sortMap);

	/**
	 * 根据操作进行查询.
	 * 
	 * @param opId 操作标识
	 * @return
	 */
	public List<RoleInfo> findByOp(String opId);

	/**
	 * 根据用户标识进行查询.
	 * 
	 * @param userId 用户标识
	 * @return
	 */
	public List<RoleInfo> findByUserId(String userId);
	
	/**
	 * 根据组织标识进行查询.
	 * 
	 * @param teamId 组织标识
	 * @return
	 */
	public List<RoleInfo> findByTeamId(String teamId);
	
	
	/**
	 * 根据名称进行查询.
	 * 
	 * @param roleName 角色名称
	 * @return
	 */
	public List<RoleInfo> findByRoleName(String roleName);
}
