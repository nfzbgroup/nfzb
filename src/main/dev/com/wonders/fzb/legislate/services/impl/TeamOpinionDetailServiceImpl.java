package com.wonders.fzb.legislate.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.AuditMeeting;
import com.wonders.fzb.legislate.beans.TeamOpinionDetail;
import com.wonders.fzb.legislate.dao.AuditMeetingDao;
import com.wonders.fzb.legislate.dao.TeamOpinionDetailDao;
import com.wonders.fzb.legislate.services.AuditMeetingService;
import com.wonders.fzb.legislate.services.TeamOpinionDetailService;

/**
 * AuditMeeting service接口
 * @author scalffold created by lj
 */
@Service("teamOpinionDetailService")
@Transactional
public class TeamOpinionDetailServiceImpl implements TeamOpinionDetailService{
	@Autowired
	TeamOpinionDetailDao teamOpinionDetailDao;

	/**
	 * 添加实体对象
	 */
	@Override
	public void add(TeamOpinionDetail info) {
		teamOpinionDetailDao.save(info);
	}
	

	/**
	 * 更新实体对象
	 */
	@Override
	public void update(TeamOpinionDetail info) {
		teamOpinionDetailDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(TeamOpinionDetail info) {
		teamOpinionDetailDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public TeamOpinionDetail findById(String id) {
		return (TeamOpinionDetail) teamOpinionDetailDao.load(id);
	}

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
	 * @throws FzbDaoException
	 */
	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException {
		return teamOpinionDetailDao.findByPage(condMap, sortMap, pageNo, pageSize);
	}

	/**
	 * 根据Map中过滤条件、排序条件进行查询.
	 * 
	 * @param condMap
	 *            过滤条件<propertyName,properyValue>
	 * @param sortMap
	 *            排序条件<propertyName,properyValue>
	 * @return
	 */
	@Override
	public List<TeamOpinionDetail> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return teamOpinionDetailDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(TeamOpinionDetail info) {
		teamOpinionDetailDao.saveOrUpdate(info);
	}
}
