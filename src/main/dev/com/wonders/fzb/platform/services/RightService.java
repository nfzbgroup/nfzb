package com.wonders.fzb.platform.services;

import java.util.ArrayList;
import java.util.List;

import com.wonders.fzb.platform.beans.OpRightInfo;
import com.wonders.fzb.platform.beans.RightOpRangeInfo;
import com.wonders.fzb.platform.beans.RoleInfo;
import com.wonders.fzb.platform.beans.RoleOpRangeInfo;


/**
 * 权限服务业务接口
 * 
 */
public interface RightService {
	/**
	 * 按用户查询其拥有的角色<br>
	 * 
	 * @return RoleInfo 列表<br>
	 */
	public List<RoleInfo> findRoleByUser(String userId);

	/**
	 * 判断用户是否有某一角色的权限<br>
	 * return: true:有权限 false:无权限<br>
	 */
	public boolean havaRightUserToRole(String user_id, String role_id);
	
	/**
	  * 根据用户操作获得操作范围标识列表
	  * @param opId
	  * @param userId
	  * @param rangeType
	  * @return ArrayList of
	  */
	public ArrayList getOpRangeIdsByUserId(String opId, String userId,String rangeType);
	
	public List<OpRightInfo> getOpRightByUserIdOpId(String userId,String opId);
	
	public List<OpRightInfo> getRoleOpRightByUserIdOpId(String userId,String opId);
	
	/**
	  * 删除用户操作范围
	  * @param info
	  */
	public boolean removeRightOpRanges(RightOpRangeInfo info);
	
	/**
	  * 删除角色操作范围
	  * @param info
	  */
	public boolean removeRoleOpRanges(RoleOpRangeInfo info);
	
	/**
	  * 根据角色标识列表获得角色列表
	  * @param roleIds
	  * @return ArrayList of RoleInfo
	  */
	public List getRoleListByRoleIds(ArrayList roleIds);
	 
	 /**
	  * 查找所有角色<br>
	  * return: RoleInfo 列表<br>
	  */
	public List findAllRole();
	
	/**
	 * 按角色标识查询角色<br>
	 * return: RoleInfo<br>
	 */
	public RoleInfo findRoleByRoleID(String roleId);
	
	/**
	 * 删除某一用户的某一角色<br>
	 * return: true:成功 false:失败<br>
	 */
	public boolean removeOpRightByUserAndRole(String user_id, String role_id);
	
	/**
	  * 按角色标识、用户标识查询权限<br>
	  * @param roleId
	  * @param userId
	  * return: OpRightId<br>
	  */
	public String findOpRightIdByRoleIDUserID(String roleId,String userId);
	
	/**
	  * 判断操作是否属于某角色
	  * @param opId
	  * @param roleId
	  * @return
	  */
	public boolean isOpInRole(String opId, String roleId);
	
	/**
	 * 添加操作权限信息<br>
	 * parameter ty_type="T",teamId有效；ty_type=P",userId有效；ro_type="R",roleId有效；ro_type="O",opId有效；<br>
	 * return: true:成功 false:失败<br>
	 */
	public boolean addOpRight(String userId, String teamId, String tp_type, String roleId, String opId, String ro_type);
	
	/**
	 * 按角色查找操作<br>
	 * return: OpInfo 列表<br>
	 */
	public ArrayList findOperationByRoleID(String roleId);
	
	/**
	 * 删除角色<br>
	 * return: true:成功 false:失败<br>
	 */
	public boolean removeRole(String role_id);
	
	/**
	 * 为角色删除一种操作<br>
	 * return: true:成功 false:失败<br>
	 */
	public boolean removeOperationFromRole(String role_id, String op_id);
	
	/**
	  *扩大用户已有操作的操作范围
	  * @param info
	  */
	public boolean addRightOpRange(String userId, String opId, String rangeType, String rangeId);
	
	/**
	  * 根据名字查找角色列表
	  * @param name
	  * @return
	  */
	public List findRoleByRoleName(String name);
	
	/**
	 * 添加角色<br>
	 * return: roleId<br>
	 */
	public String addRole(String role_name, String desc);
	 
	 /**
	  * 修改角色信息<br>
	  * return: true:成功 false:失败<br>
	  */
	public boolean modifyRole(String role_id,String role_name, String desc);
	
	/**
	 * 为角色添加操作<br>
	 * parameter rel_id = "01"
	 * return: true:成功 false:失败<br>
	 */
	public boolean addOperationtToRole(String role_id, String op_id);
	
	/**
	  * 新增用户操作范围
	  * @param info
	  */
	public boolean addRightOpRanges(RightOpRangeInfo info);
	
	/**
	  * 根据用户权限获得用户可操作的操作列表
	  * @param opId
	  * @param userId
	  * @return ArrayList of OpInfo
	  */
	public ArrayList getOpListByUserRight(String opId, String userId);
	
	/**
	  * 根据操作标识列表获得操作列表
	  * @param opIds
	  * @return ArrayList of OpInfo
	  */
	public ArrayList getOpListByOpIds(ArrayList opIds);
	
	/**
	  * 查找所有操作<br>
	  * return: OpInfo 列表<br>
	  */
	public ArrayList findAllOp();
	
	/**
	  * 根据用户权限获得用户可操作的角色列表
	  * @param opId
	  * @param userId
	  * @return ArrayList of RoleInfo
	  */
	public List getRoleListByUserRight(String opId, String userId);
	
	/**
	  * 根据权限标识获得单位范围列表
	  * @param opRightId
	  * @return ArrayList of String unitId
	  */
	public List getUnitIdListByOpRightId(String opRightId);
	
	/**
	  * 根据权限操作标识获得操作范围标识列表
	  * @param opRightId
	  * @param rangeType
	  * @return ArrayList of range ids
	  */
	public ArrayList getOpRangeIdsByOpRightId(String opRightId,String rangeType);
	
	/**
	  * 根据权限标识获得角色范围列表
	  * @param opRightId
	  * @return ArrayList of RoleInfo
	  */
	public List getRoleListByOpRightId(String opRightId);
	
	/**
	  * 根据权限标识获得操作范围列表
	  * @param opRightId
	  * @return ArrayList of OpInfo
	  */
	public ArrayList getOpListByOpRightId(String opRightId);
	
	/**
	  * 按操作查询角色<br>
	  * return: RoleInfo 列表<br>
	  */
	public ArrayList findRoleByOp(String opId);
}
