package com.wonders.fzb.legislate.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.ModelFile;
import com.wonders.fzb.legislate.beans.ProcessInstance;
import com.wonders.fzb.legislate.dao.ProcessInstanceDao;

@Repository("processInstanceDao")
public class ProcessInstanceDaoImpl extends BaseSupportDao implements ProcessInstanceDao{
	@Override
	public void save(Object object) {
		ProcessInstance info = (ProcessInstance) object;
		super.save(info);
	}

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		sortMap.put("createTime", CommonConst.ORDER_DESC);
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"ProcessInstance");
	}

	@Override
	public List<ProcessInstance> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		sortMap.put("createTime", CommonConst.ORDER_DESC);
		return super.findByList(condMap, sortMap, "ProcessInstance");
	}

	@Override
	public void saveOrUpdate(ProcessInstance info) {
		// TODO Auto-generated method stub
		super.saveOrUpdate(info);
	}

}
