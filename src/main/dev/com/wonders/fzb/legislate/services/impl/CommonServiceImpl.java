package com.wonders.fzb.legislate.services.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.MOR;
import com.wonders.fzb.framework.beans.TeamInfo;
import com.wonders.fzb.framework.beans.vo.UserInfoBean;
import com.wonders.fzb.framework.dao.PlatformDao;
import com.wonders.fzb.framework.services.TeamInfoService;
import com.wonders.fzb.legislate.beans.vo.TeamInfoBean;
import com.wonders.fzb.legislate.dao.CommonDao;
import com.wonders.fzb.legislate.services.CommonService;
/**
 * 组织单位服务类
 */
@Service("commonService")
@Transactional
public class  CommonServiceImpl implements CommonService{

	@Autowired
	@Qualifier("commonDao")
	private CommonDao commonDao;

	@Override
	public List<TeamInfoBean> findTeamInfoList(String moduleId, String pid) {
		// TODO Auto-generated method stub
		return commonDao.findTeamInfoList(moduleId, pid);
	}

	@Override
	public List<UserInfoBean> findUserInfoListForOrgId(String moduleId,
			String orgId) {
		// TODO Auto-generated method stub
		return commonDao.findUserInfoListForOrgId(moduleId,orgId);
	}

	
}


