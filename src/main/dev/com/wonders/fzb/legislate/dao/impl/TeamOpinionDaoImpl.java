package com.wonders.fzb.legislate.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.TeamOpinion;
import com.wonders.fzb.legislate.dao.TeamOpinionDao;

@Repository("teamOpinionDao")
public class TeamOpinionDaoImpl extends BaseSupportDao implements TeamOpinionDao {
	@Override
	public void save(Object object) {
		TeamOpinion info = (TeamOpinion) object;
		super.save(info);
	}

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		sortMap.put("createTime", CommonConst.ORDER_DESC);
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"TeamOpinion");
	}

	@Override
	public List<TeamOpinion> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		sortMap.put("createTime", CommonConst.ORDER_DESC);
		return super.findByList(condMap, sortMap, "AuditMeeting");
	}

	@Override
	public void saveOrUpdate(TeamOpinion info) {
		// TODO Auto-generated method stub
		super.saveOrUpdate(info);
	}

}
