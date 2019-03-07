package com.wonders.fzb.platform.dao;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.platform.beans.NoticeTypeInfo;

import java.util.List;
import java.util.Map;

/**
 * WEGOV_P_NOTICE_TYPE dao接口
 * @author scalffold
 */
public abstract interface NoticeTypeInfoDao extends BaseDao {

    public Page<NoticeTypeInfo> findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo,
                                           int pageSize) throws FzbDaoException;

    public List<NoticeTypeInfo> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

    public List<NoticeTypeInfo> getNoticeTypeListByNoticeMode(String noticeMode);
}