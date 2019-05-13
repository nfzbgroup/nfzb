package com.wonders.fzb.legislation.services;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislation.beans.LegislationProcessTask;
import com.wonders.fzb.legislation.beans.LegislationSendNotice;
import com.wonders.fzb.legislation.beans.SendNoticeVO;

import java.text.ParseException;
import java.util.List;
import java.util.Map;


/**
 * LegislationSendNotice service接口
 * @author scalffold created by lj
 */
public interface LegislationSendNoticeService{
	/**
	 * 添加或修改实体对象
	 */
	public void saveOrUpdate(LegislationSendNotice info);
	
	/**
	 * 添加实体对象
	 */
	public void add(LegislationSendNotice info);
	
	/**
	 * 添加实体对象并返回主键ID
	 */
	public String addObj(LegislationSendNotice info);
	
	/**
	 * 更新实体对象
	 */
	public void update(LegislationSendNotice info);
	
	/**
	 * 删除实体对象
	 */
	public void delete(LegislationSendNotice info);
	
	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	public LegislationSendNotice findById(String id);
	
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
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException;

	/**
	 * 根据Map中过滤条件、排序条件进行查询.
	 * 
	 * @param condMap
	 *            过滤条件<propertyName,properyValue>
	 * @param sortMap
	 *            排序条件<propertyName,properyValue>
	 * @return
	 */
	public List<LegislationSendNotice> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

	/**
	 * 根据HQL进行查询.
	 */
	public List<LegislationSendNotice> findByHQL(String hql);
	
	Page<SendNoticeVO> findSendNoticeList(String wheresql, String mainTableName,
			String columnNames, int pageNo, int pageSize) throws ParseException;
	
	Page<SendNoticeVO> findSendNoticeCitymeetingList(String wheresql, String mainTableName,
			String columnNames, int pageNo, int pageSize) throws ParseException;

	List<Map<String,Object>> findParticipantsList(String showName,String stPersonsId);
	/**
	 * 发送人员
	 */
	public void sendNotice(String personsId, String module, String stDocId, String nodeName);
}
