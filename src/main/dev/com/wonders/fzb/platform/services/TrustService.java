package com.wonders.fzb.platform.services;

import com.wonders.fzb.platform.exceptions.TrustException;

/**
 * WFC_CODE_INFO业务接口
 * 
 * @author scalffold
 * 
 */
public interface TrustService {
	/**
	* 获得领导登录模式
	* @param leaderId Stirng  用户标识
	* @return 登录模式 String ，参见PlatformConst.FORM_***,用户不是领导时返回null
	*/
	public String getLearderLogMode(String leaderId);
	
	/**
	   判断用户是否有该功能权限
	   @param userId - 委托人标识
	   @param trustId - 委托标识
	   @param funcId - 功能标识(模块标识)
	   @return boolean
	        false：当用户在业务系统中没有该功能的权限；
	        true ：不满足false条件，且满足下列条件之一
	           1)委托标识（trustId）为空;
	           或
	           2)委托标识（trustId）不为空但该委托中存在参数funcId对应的功能（模块）
	   @roseuid 3F456EEC03DA
	    */
	 public boolean hasRight(String userId, String trustId, String funcId) throws TrustException;
	 
}
