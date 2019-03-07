package com.wonders.fzb.platform.dao;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.platform.beans.UuRelate;

public abstract interface UuRelateDao extends BaseDao {

	public void saveOrUpdate(UuRelate uuRelate);

	public List<UuRelate> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

}