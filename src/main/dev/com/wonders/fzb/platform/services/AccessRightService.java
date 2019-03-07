package com.wonders.fzb.platform.services;

import java.util.ArrayList;

import com.wonders.fzb.platform.beans.UserInfoOld;

/**
 * WFC_CODE_INFO业务接口
 * 
 * @author scalffold
 * 
 */
public interface AccessRightService {
	/**
	   * 比较level大小（如level相等，且两者都有no，则再判断no大小）
	   * @param level_no1，形如level+{Split}+No
	   * @param level_no2，形如level+{Split}+No
	   * @return >0 level1>level2; =0 level1=level2; <0 level1<level2
	   */
	public int compareLevel(String level_no1,String level_no2);
	
	/**
	   * 获得用户级别对应数值
	   * @param level
	   * @return
	   * @throws SystemException
	   */
	public long getLevelValue(String level);
	
	/**
	   * 根据操作标识查找拥有此操作的角色
	   * @param opId 操作标识
	   * @return ArrayList of StrucUserInfo
	   * @throws SystemException
	   */
	public ArrayList getRoleInfoByOpId(String opId);
	
	/**
	   * 判断用户是否具有指定操作的权限
	   * @param opId
	   * @param userInfo
	   * @return true or false
	   */
	public boolean hasRight(String opId, UserInfoOld userInfo);
	
	/**
	   * 判断用户是否高于某一级别（含此级别）
	   * @param level 级别
	   * @param userInfo 用户信息
	   * @return true or false
	   * @throws SystemException
	   */
	public boolean isUpperLevel(String level,UserInfoOld userInfo);
	
}
