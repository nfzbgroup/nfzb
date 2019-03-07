package com.wonders.fzb.platform.services;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.platform.beans.OpRightInfo;

/**
 * WFC_F_OP_RIGHT业务接口
 * 
 * @author scalffold
 * 
 */
public interface OpRightInfoService {
	
	/**
	 * 添加实体对象
	 * 
	 * @param OpRightInfo
	 */
	public void add(OpRightInfo OpRightInfo);

	/**
	 * 更新实体对象
	 * 
	 * @param OpRightInfo
	 */
	public void update(OpRightInfo OpRightInfo);
	
	/**
	 * 删除实体对象
	 * 
	 * @param OpRightInfo
	 */
	public void delete(String id);

	/**
	 * 
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 * 
	 * @param id 主键
	 * @return
	 */
	public OpRightInfo findById(String id);

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
	public List<OpRightInfo> findByList(Map<String, Object> condMap,
			Map<String, String> sortMap);

	/**
	 * 根据用户标识进行查询.
	 * 
	 * @param userId 用户标识
	 * @return
	 */
	public List<OpRightInfo> findByUserID(String userId);

	/**
	 * 根据组织标识进行查询.
	 * 
	 * @param teamId 组织标识
	 * @return
	 */
	public List<OpRightInfo> findByTeamID(String teamId);

	/**
	 * 根据操作标识进行查询.
	 * 
	 * @param opId 操作标识
	 * @return
	 */
	public List<OpRightInfo> findByOpID(String opId);

	/**
	 * 根据角色标识进行查询.
	 * 
	 * @param roleId 角色标识
	 * @return
	 */
	public List<OpRightInfo> findByRoleID(String roleId);

	/**
	 * 根据用户标识和操作标识进行查询.
	 * 
	 * @param userId 用户标识
	 * @param opId 操作标识
	 * @return
	 */
	public List<OpRightInfo> findByUserAndOp(String userId, String opId);

	/**
	 * 根据用户标识和角色标识进行查询.
	 * 
	 * @param userId 用户标识
	 * @param roleId 角色标识
	 * @return
	 */
	public List<OpRightInfo> findByUserAndRole(String userId, String roleId);

	/**
	 * 根据组织标识和操作标识进行查询.
	 * 
	 * @param teamId 组织标识
	 * @param opId 操作标识
	 * @return
	 */
	public List<OpRightInfo> findByTeamAndOp(String teamId, String opId);

	/**
	 * 根据组织标识和角色标识进行查询.
	 * 
	 * @param teamId 组织标识
	 * @param roleId 角色标识
	 * @return
	 */
	public List<OpRightInfo> findByTeamAndRole(String teamId, String roleId);

	/**
	 * 根据用户标识和客体类别进行查询.
	 * 
	 * @param userId 用户标识
	 * @param roType 客体类别 默认都是“R”
	 * @return
	 */
	public List<OpRightInfo> findROByUser(String userId, String roType);

	/**
	 * 根据组织标识和客体类别进行查询.
	 * 
	 * @param teamId 组织标识
	 * @param roType 客体类别
	 * @return
	 */
	public List<OpRightInfo> findROByTeam(String teamId, String roType);	
	
	
	 
	
	
	
	
	
}
