package com.wonders.fzb.platform.services;

import com.wonders.fzb.platform.beans.NoticeContentInfo;
import com.wonders.fzb.platform.beans.NoticeModeInfo;
import com.wonders.fzb.platform.beans.NoticeTypeInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 提醒服务业务接口
 * 
 * @author scalffold
 * 
 */
public interface NoticeService {


	  /**
	   * 插入提醒类别
	   * @param noticeType
	   */
	  public void insertNoticeType(NoticeTypeInfo noticeType);

	  /**
	   * 修改提醒类别
	   * @param noticeType
	   */
	  public void updateNoticeType(NoticeTypeInfo noticeType);

	  /**
	   * 删除提醒类别
	   */
	  public void deleteNoticeType(String typeId);

	  /**
	   * 查询提醒类别信息
	   * @param typeId
	   * @return
	   */
	  public NoticeTypeInfo getNoticeType(String typeId);

	  /**
	   * 获得提醒类别信息列表
	   * @return 提醒类别信息列表，对象为NoticeTypeInfo
	   */
	  public List<NoticeTypeInfo> getAllNoticeType();

	  /**
	   * 新增提醒方式
	   * @param noticeMode
	   */
	  public void insertNoticeMode(NoticeModeInfo noticeMode);

	  /**
	   * 删除提醒方式
	   * @param noticeMode
	   */
	  public void deleteNoticeMode(NoticeModeInfo noticeMode);

	  /**
	   * 根据提醒类别查询提醒方式
	   * @param typeId 提醒类别
	   * @return 提醒方式列表，对象为NoticeModeInfo
	   */
	  public List<NoticeModeInfo> getNoticeModeListByNoticeType(String typeId);

	  /**
	   * 根据提醒方式查询
	   * @param noticeMode 提醒方式
	   * @return 提醒方式列表，对象为NoticeModeInfo
	   */
	  public List<NoticeModeInfo> getNoticeModeListByNoticeMode(String noticeMode);

	  /**
	   * 新增提醒内容
	   * @param noticeContent
	   */
	  public void insertNoticeContent(NoticeContentInfo noticeContent);

	  /**
	   * 修改提醒内容
	   * @param noticeContent
	   */
	  public void updateNoticeContent(NoticeContentInfo noticeContent);

	  /**
	   * 删除提醒内容
	   * @param seqId
	   */
	  public void deleteNoticeContent(String seqId);

	  /**
	   * 根据类别标识、内容标识删除提醒内容
	   * @param contentId
	   * @param typeId
	   */
	  public void deleteNoticeContentByContentIdTypeId(String contentId, String typeId);

	  /**
	   * 根据人员标识、类别标识、内容标识删除提醒内容
	   * @param contentId
	   * @param typeId
	   * @param userId
	   */
	  public void deleteNoticeContentByContentIdTypeIdUserId(String contentId, String typeId, String userId);

	  /**
	   * 根据类别标识、内容标识查询
	   * @param contentId
	   * @param typeId
	   * @return 提醒内容列表，对象为NoticeContentInfo
	   */
	  public List<NoticeContentInfo> getNoticeContentByContentIdTypeId(String contentId, String typeId);

	  /**
	   * 查询所有内容
	   * @return 提醒内容列表，对象为NoticeContentInfo
	   */
	  public List<NoticeContentInfo> getAllNoticeContent();

	  /**
	   * 根据用户标识、类别标识查询
	   * @param userId
	   * @param typeId
	   * @return 提醒内容列表，对象为NoticeContentInfo
	   */
	  public List<NoticeContentInfo> getNoticeContentByUserIdTypeId(String userId, String typeId);

	  /**
	   * 根据用户标识、类别标识查询
	   * @param userId
	   * @param mode
	   * @return 提醒内容列表，对象为NoticeContentInfo
	   */
	  public List<NoticeContentInfo> getNoticeContentByUserIdAndMode(String userId, String mode);

	  /**
	   * 根据用户标识、类别标识、时限、上次提醒时间查询
	   * @param userId
	   * @param typeId
	   * @param limitTime
	   * @param noticeTime
	   * @return 提醒内容列表，对象为NoticeContentInfo
	   */
	  public List<NoticeContentInfo> getNoticeContentByUserIdTypeIdLimitTime(String userId, String typeId, Date limitTime, Date noticeTime);

	  /**
	   * 根据类别标识、时限进行删除
	   * @param typeId
	   * @param limitTime
	   */
	  public void deleteNoticeContentByTypeIdTimeLimit(String typeId, Date limitTime);

	  /**
	   * 根据用户标识、内容标识查询当前提醒内容列表
	   * @param userId
	   * @param contentId
	   * @return 提醒内容列表，对象为StrucNoticeContent
	   */
	  public List<NoticeContentInfo> getCurrentNoticeContentByUserIdContentId(String userId, String contentId);

	  /**
	   * 根据用户标识、类别标识查询当前提醒内容列表
	   * @param userId
	   * @param typeId
	   * @return 提醒内容列表，对象为StrucNoticeContent
	   */
	  public List<NoticeContentInfo> getCurrentNoticeContentByUserIdTypeId(String userId, String typeId);

	  /**
	   * 根据用户标识、内容标识查询判断是否需要提醒
	   * @param userId
	   * @param contentId
	   * @return true or false
	   */
	  public boolean needWarning(String userId, String contentId);

	  /**
	   * 根据用户标识、提醒方式查询当前提醒内容列表
	   * @return 提醒内容列表，对象为StrucNoticeContent
	   */
	  public ArrayList<NoticeContentInfo> getCurrentNoticeContentByUserIdMode(String userId, String mode);

	  /**
	   * 删除过期提醒信息
	   * @return
	   */
	  public void deleteTimeoutNoticeContent();

	  /**
	   * 删除已经过期的信息
	   */
	  public void deleteNotice();
	  
	  /**
	   * 根据typeIdList删除所有提醒信息
	   */
	  public void deleteNoticeTypeList(ArrayList<String> typeIdList);
}
