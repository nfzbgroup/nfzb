package com.wonders.fzb.platform.dao;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.platform.beans.NoticeModeInfo;

import java.util.List;
import java.util.Map;

/**
 * WEGOV_P_NOTICE_MODE dao接口
 * @author scalffold
 */
public abstract interface NoticeModeInfoDao extends BaseDao {

    public Page<NoticeModeInfo> findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo,
                                           int pageSize) throws FzbDaoException;

    public List<NoticeModeInfo> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

}