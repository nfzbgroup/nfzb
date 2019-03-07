package com.wonders.fzb.platform.services;

import java.util.ArrayList;
import java.util.List;

import com.wonders.fzb.platform.beans.TeamInfoOld;
import com.wonders.fzb.platform.beans.UserInfoOld;

/**
 * 权限服务业务接口
 * 
 */
public interface OrgService {
	
	/**
	 * 获得用户所属单位列表
     * @param userId 用户标识
     * @return List of TeamInfo, 若用户不属于任何单位，则返回空列表
	 */
	public List<TeamInfoOld> getUserUnits(String userId);
	
	/**
	   * 获得用户在某个单位中的级别
	   * @param unit
	   * @param userId
	   * @return String
	   * @throws SystemException
	   */
	public String getUserLevel(String unitId, String userId);
	
	/**
	   * 根据用户级别返回大于或等于此用户级别的用户列表
	   * @param level 级别 PlatformConst.LEADER_LEVEL_XXX
	   * @return ArrayList of StrucUserInfo
	   * @throws SystemException
	   */
	public List<UserInfoOld> getUsersAboveLevel(String level);
	
	/**
	   * 查找所有单位
	   * @return ArrayList of StrucUnit
	   * @throws SystemException
	   */
	public ArrayList getAllUnits();
	
	/**
	   * 获取单位信息
	   * @param unitId
	   * @return TeamInfo
	   * @throws SystemException
	   */
	public TeamInfoOld getUnitInfo(String unitId);
	
	/**
	   * 得到所有级别名称
	   * @return ArrayList of String
	   * @throws SystemException
	   */
	public List<String> getAllLevels() ;
	
	/**
	   * 获得用户最高级别
	   * @param userId
	   * @return String
	   * @throws SystemException
	   */
	public String getUserHighestLevel(String userId);
	
	/**
	   * 得到所有单位类别名称
	   * @return ArrayList of String
	   * @throws SystemException
	   */
	public List<String> getAllGroups();
	
	/**
	   * 根据单位类别查找单位列表
	   * @param group
	   * @return ArrayList of StrucUnit
	   * @throws SystemException
	   */
	public ArrayList getUnitsByGroup(String group);
	
	/**
	   * 删除单位
	   * @param unitId 单位标识
	   * @throws SystemException
	   */
	public void removeUnit(String unitId);
	
	/**
	   * 根据用户操作权限获得用户可进行此项操作的用户列表
	   * @param userId 用户标识
	   * @param opId 操作标识
	   * @return ArrayList of StrucUserInfo
	   * @throws SystemException
	   */
	public List getUsersByUserRight(String opId,String userId);
	
	/**
	   * 根据用户操作权限获得用户可进行此项操作的单位列表
	   * @param userId 用户标识
	   * @param opId 操作标识
	   * @return ArrayList of StrucUnit
	   * @throws SystemException
	   */
	public ArrayList getUnitsByUserRight(String opId,String userId) ;
	
	/**
	   * 获得单位所有用户列表
	   * @param unitId 单位标识
	   * @return ArrayList of StrucUserInfo
	   * @throws SystemException
	   */
	public ArrayList getUsersByUnit(String unitId);
	
	/**
	   * 获得用户最高编号
	   * @param userId
	   * @return int
	   * @throws SystemException
	   */
	public int getUserHighestLevelNo(String userId);
	
	/**
	   * 获得用户在某个单位中的级别编号
	   * @param unit
	   * @param userId
	   * @return int
	   * @throws SystemException
	   */
	public int getUserLevelNo(String unitId, String userId);
	
	/**
	   * 根据单位类型、单位名称查找单位
	   * @param unitName 单位名称
	   * @param group 单位类型
	   * @return StrucUnit
	   */
	public TeamInfoOld getUnitByUnitNameGroup(String unitName, String group);
	
	/**
	   * 在单位内根据用户级别查找用户列表
	   * @param level_no 级别  PlatformConst.LEADER_LEVEL_XXX
	   * @param unitId 单位标识
	   * @return ArrayList of StrucUserInfo
	   * @throws SystemException
	   */
	public ArrayList getUsersByLevelUnit(String level_no,String unitId);
	
	/**
	   * 判断用户是否为此单位成员
	   * @param unitId 单位标识
	   * @param userInfo 用户信息
	   * @return true or false
	   * @throws SystemException
	   */
	public boolean isUserInUnit(String unitId, String userId);
}
