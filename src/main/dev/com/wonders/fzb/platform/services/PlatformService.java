package com.wonders.fzb.platform.services;

import java.util.List;

/**
 * 业务监管平台接口
 * 
 * @author scalffold
 * 
 */
public interface PlatformService {
	
	/**
	* 获得用户登录模式
	* @param userId Stirng  用户标识、
	* @return 登录模式 String 
	*/
	public String getUserLogMode(String userId);
	
	/**
	* 判断用户是否拥有双工作角色
	* @param userId Stirng  用户标识、
	* @return true 是，false 不是
	*/
	public boolean isDoubleModeRole(String userId);
	
	/**
	* 判断用户是否党委工作角色
	* @param userId Stirng  用户标识、
	* @return true 是，false 不是
	*/
	public boolean isDWModeRole(String userId);
	
	/**
	 * 获得系统某公共配置项的值
	 * @param String 配置项标识
	 * @return String 对应配置项的值
	 */
	public String getCommonConfigValue(String configId);
	
	public List queryInformList(String condField,String condValue,String op);
	
}
