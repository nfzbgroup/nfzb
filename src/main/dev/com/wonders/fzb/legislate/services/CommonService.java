package com.wonders.fzb.legislate.services;

import java.util.List;

import com.wonders.fzb.framework.beans.TeamInfo;
import com.wonders.fzb.framework.beans.vo.UserInfoBean;
import com.wonders.fzb.legislate.beans.vo.TeamInfoBean;

/**
 * 组织单位Service
 * @author ZSW
 */
public interface CommonService {
	
	
	/**
	 * 查询下级单位
	 * @param moduleId 系统模块ID
	 * @param pid 上级单位ID
	 */
	public List<TeamInfoBean> findTeamInfoList(String moduleId,String pid);
	
	
	/**
	 * 查询下级单位
	 * @param moduleId 系统模块ID
	 * @param pid 上级单位ID
	 */
	public List<UserInfoBean> findUserInfoListForOrgId(String moduleId,String orgId);

}
