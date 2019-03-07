package com.wonders.fzb.framework.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.framework.beans.CodeItem;
import com.wonders.fzb.framework.dao.CodeItemDao;

/**
 * WFC_CODE_INFO dao实现
 * 
 * @author scalffold
 */
@SuppressWarnings("unchecked")
@Repository("codeItemDao")
public class CodeItemDaoImpl extends BaseSupportDao implements CodeItemDao {

	@Override
	public void save(Object object) {
		CodeItem opInfo = (CodeItem) object;
		opInfo.setStTid(super.getId("seq_wegov_pla_op_id", "OP_", 6));
		super.save(opInfo);
	}

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		sortMap.put("stTid", CommonConst.ORDER_ASC);
		return null;
		// return super.findByPage(condMap, sortMap, pageNo, pageSize,
		// "OpInfo");
	}

	@Override
	public List<CodeItem> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		sortMap.put("stTid", CommonConst.ORDER_ASC);
		return super.findByList(condMap, sortMap, "CodeItem");
	}

}