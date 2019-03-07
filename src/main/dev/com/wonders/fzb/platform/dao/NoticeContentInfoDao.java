package com.wonders.fzb.platform.dao;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.platform.beans.NoticeContentInfo;

import java.util.List;
import java.util.Map;

/**
 * WEGOV_P_NOTICE_CONTENT dao接口
 * @author scalffold
 */
public abstract interface NoticeContentInfoDao extends BaseDao {

    public Page<NoticeContentInfo> findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo,
                                              int pageSize) throws FzbDaoException;

    public List<NoticeContentInfo> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

    public List<NoticeContentInfo> getNoticeContentByUserIdAndMode(String mode, String userId);

    public List<NoticeContentInfo> getNoticeContentByUserIdContentId(String userId, String contentId);
}