package com.wonders.fzb.platform.services;

/**
 * WFC_CODE_INFO业务接口
 * 
 * @author scalffold
 * 
 */
public interface ConfigService {
	/**
	* 获得领导登录模式
	* @param leaderId Stirng  用户标识
	* @return 登录模式 String ，参见PlatformConst.FORM_***,用户不是领导时返回null
	*/
	public String getLearderLogMode(String leaderId);
	
}
