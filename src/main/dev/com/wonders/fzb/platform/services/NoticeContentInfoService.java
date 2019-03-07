package com.wonders.fzb.platform.services;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.platform.beans.NoticeContentInfo;

import java.util.List;
import java.util.Map;


/**
 * WEGOV_P_NOTICE_CONTENT业务接口
 *
 * @author scalffold
 *
 */
public interface NoticeContentInfoService {

    /**
     * 添加实体对象
     *
     * @param noticeContentInfo
     */
    public void add(NoticeContentInfo noticeContentInfo);

    /**
     * 更新实体对象
     *
     * @param noticeContentInfo
     */
    public void update(NoticeContentInfo noticeContentInfo);

    /**
     * 删除实体对象
     *
     * @param id
     */
    public void delete(String id);

    /**
     *
     * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
     *
     * @param id 主键
     * @return
     */
    public NoticeContentInfo findById(String id);

    /**
     * 根据分页参数进行分页查询.
     *
     * @param pageNo
     *            当前页码
     * @param pageSize
     *            每页显示记录数.
     * @return
     */
    public Page<NoticeContentInfo> findByPage(int pageNo, int pageSize) throws FzbDaoException;

    /**
     * 根据Map中过滤条件、排序条件和分页参数进行分页查询.
     *
     * @param condMap
     *            过滤条件<propertyName,properyValue>
     * @param sortMap
     *            排序条件<propertyName,properyValue>
     * @param pageNo
     *            当前页码
     * @param pageSize
     *            每页显示记录数.
     * @return
     */
    public Page<NoticeContentInfo> findByPage(Map<String, Object> condMap,
                                              Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException;


    /**
     * 根据Map中过滤条件、排序条件进行查询.
     *
     * @param condMap
     *            过滤条件<propertyName,properyValue>
     * @param sortMap
     *            排序条件<propertyName,properyValue>
     * @return
     */
    public List<NoticeContentInfo> findByList(Map<String, Object> condMap,
                                              Map<String, String> sortMap);

    public List<NoticeContentInfo> getNoticeContentByUserIdAndMode(String mode, String userId);

    public List<NoticeContentInfo> getNoticeContentByUserIdContentId(String userId, String contentId);
}
