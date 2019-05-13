package com.wonders.fzb.legislation.services.impl;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.MOR;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.framework.services.TeamInfoService;
import com.wonders.fzb.framework.services.UserInfoService;
import com.wonders.fzb.legislation.beans.LegislationProcessTask;
import com.wonders.fzb.legislation.beans.LegislationSendNotice;
import com.wonders.fzb.legislation.beans.SendNoticeVO;
import com.wonders.fzb.legislation.dao.LegislationSendNoticeDao;
import com.wonders.fzb.legislation.services.LegislationProcessDocService;
import com.wonders.fzb.legislation.services.LegislationSendNoticeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;


/**
 * LegislationSendNotice service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationSendNoticeService")
@Transactional
public class LegislationSendNoticeServiceImpl implements LegislationSendNoticeService {

	@Autowired
	private LegislationSendNoticeDao legislationSendNoticeDao;

	@Autowired
	private TeamInfoService teamInfoService;

	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private LegislationProcessDocService legislationProcessDocService;
	
	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationSendNotice info) {
		legislationSendNoticeDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationSendNotice info) {
		return legislationSendNoticeDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationSendNotice info) {
		legislationSendNoticeDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationSendNotice info) {
		legislationSendNoticeDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationSendNotice findById(String id) {
		return (LegislationSendNotice) legislationSendNoticeDao.load(id);
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
		return legislationSendNoticeDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<LegislationSendNotice> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationSendNoticeDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationSendNotice info) {
		legislationSendNoticeDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationSendNotice> findByHQL(String hql) {
		List<LegislationSendNotice> legislationSendNoticeList = legislationSendNoticeDao.findByHQL(hql);
		return legislationSendNoticeList;
	}
	
	
	@Override
	public Page<SendNoticeVO> findSendNoticeList(String wheresql,
			String mainTableName, String columnNames, int pageNo,
														 int pageSize) throws ParseException {
		return legislationSendNoticeDao.findSendNoticeList(wheresql, mainTableName, columnNames, pageNo, pageSize);
	}

	@Override
	public List<Map<String, Object>> findParticipantsList(String showName,String stPersonsId) {
		List<Map<String, Object>> result=new ArrayList<>();
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("moduleId","MODULE_LEGISLATE");
		condMap.put("orgType","市司法局处室");
		if(StringUtils.isNotEmpty(showName)){
			List showNameList=Arrays.asList(showName.split(","));
			condMap.put("showNameList",showNameList);
		}
		sortMap.put("sort","ASC");
		List<MOR> morList=teamInfoService.findMorList(condMap,sortMap);
		morList.forEach((MOR mor)->{
			Map<String, Object> map=new HashMap<>();
			map.put("teamId",mor.getId());
			map.put("teamName",mor.getShowName());
			List<UserInfo> userInfoList=userInfoService.findByHQL("select user from MOR mor LEFT JOIN  OUR our ON our.morId = mor.id " +
					"LEFT JOIN UserInfo user ON user.userId = our.userId where mor.id='"+mor.getId()+"'");
			if(StringUtils.isNotEmpty(stPersonsId)){
				String[] userIdArray=stPersonsId.split(",");
				userInfoList.forEach((UserInfo userInfo)->{
					for (String s:userIdArray) {
						if(userInfo.getUserId().equals(s)){
							userInfo.setChecked(true);
							break;
						}
					}
				});
			}
			map.put("userInfoList",userInfoList);
			result.add(map);
		});
		return result;
	}
	@Override
	public Page<SendNoticeVO> findSendNoticeCitymeetingList(String wheresql, String mainTableName, String columnNames, int pageNo, int pageSize) throws ParseException {
		return legislationSendNoticeDao.findSendNoticeCitymeetingList(wheresql, mainTableName, columnNames, pageNo, pageSize);
	}

	@Override
	public void sendNotice(String personsId, String module, String stDocId, String nodeName) {
		// 根据id字符串获取人员信息集合
		List<UserInfo> userInfoList = legislationProcessDocService.findUserInfoListByString(personsId);
		// 发送领导
		if (personsId.indexOf(",") > -1) {
			String[] personIArray = personsId.split(",");
			for (int i = 0; i < personIArray.length; i++) {
				LegislationSendNotice legislationSendNotice = new LegislationSendNotice();
				legislationSendNotice.setStDocId(stDocId);
				legislationSendNotice.setStNoticeStatus("已发送");
				legislationSendNotice.setDtOpenDate(new Date());
				legislationSendNotice.setStUserId(userInfoList.get(i).getUserId());
				legislationSendNotice.setStUserName(userInfoList.get(i).getName());
				legislationSendNotice.setStModelName(module);
				legislationSendNotice.setStNodeName(nodeName);
				add(legislationSendNotice);
			}
		} else {
			LegislationSendNotice legislationSendNotice = new LegislationSendNotice();
			legislationSendNotice.setStDocId(stDocId);
			legislationSendNotice.setStNoticeStatus("已发送");
			legislationSendNotice.setDtOpenDate(new Date());
			legislationSendNotice.setStUserId(userInfoList.get(0).getUserId());
			legislationSendNotice.setStUserName(userInfoList.get(0).getName());
			legislationSendNotice.setStModelName(module);
			legislationSendNotice.setStNodeName(nodeName);
			add(legislationSendNotice);
		}
	}
}
