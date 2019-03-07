package com.wonders.fzb.legislate.dao;

import java.util.List;

import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.framework.beans.vo.UserInfoBean;
import com.wonders.fzb.legislate.beans.vo.TeamInfoBean;

/**
 * 监管平台持久化层接口
 * @author ZSW
 */
public abstract interface CommonDao extends BaseDao {
	
	/**
	 * 查询下级单位
	 * @param moduleId 系统模块ID
	 * @param pid 上级单位ID
	 */
	public List<TeamInfoBean> findTeamInfoList(String moduleId,String pid);

	public List<UserInfoBean> findUserInfoListForOrgId(String moduleId,
			String orgId);
}