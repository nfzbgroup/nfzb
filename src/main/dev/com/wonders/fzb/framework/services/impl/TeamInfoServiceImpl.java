package com.wonders.fzb.framework.services.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.MOR;
import com.wonders.fzb.framework.beans.TeamInfo;
import com.wonders.fzb.framework.dao.PlatformDao;
import com.wonders.fzb.framework.services.TeamInfoService;
import com.wonders.fzb.platform.beans.TeamMemberInfo;
/**
 * 组织单位服务类
 */
@Service("teamInfoService")
@Transactional
public class TeamInfoServiceImpl implements TeamInfoService{

	@Autowired
	@Qualifier("platformDao")
	private PlatformDao platformDao;
	

	/**
	 * 新增一个单位
	 * <p>添加逻辑：<br />
	 * 1.如果在teamInfo表中查找到相同teamInfo.unitName且对应moduleId的系统模块以及对应parentTeamId的父节点下找到相同teamInfo.unitName则判断为重复添加 => 添加失败<br />
	 * 2.如果在teamInfo表中查找到相同teamInfo.unitName且在对应moduleId下挂载，则判断为多父级关联 => 在MODULE_ORG_RELATE关联表多关联一个相同moduleId的parentTeamId对应父节点<br />
	 * 3.如果在teamInfo表中未查找到相同teamInfo.unitName则为完全新开单位 => 新增TeamInfo并新增对应MODULE_ORG_RELATE关联表关联<br />
	 * 4.如果在teamInfo表中查找到相同teamInfo.unitName且不在要添加的moduleId下，则在本系统下多加一次关联新 => 新增对应MODULE_ORG_RELATE关联表关联<br />
	 * </p>
	 * @param moduleId 系统模块ID
	 * @param parentTeamId 上级单位ID
	 * @param teaminfo 封装的单位对象
	 * @return 添加后的单位UUID
	 */
	@Override
	public String addTeamInfo(String moduleId, String parentTeamId, TeamInfo teamInfo) {
		HashMap<String,Object> condMap = new HashMap<String, Object>();
		condMap.put("unitNameLike", teamInfo.getUnitName());
		List<TeamInfo> teams = platformDao.findTeamInfoList(condMap, null);
		//多层匹配确认单位是否存在
		if(teams.size() > 0){
			for(TeamInfo team : teams){
				if(team.getUnitName().equals(teamInfo.getUnitName())){
					TeamInfo moduleTeam = platformDao.findTeamInfoByTeamId(moduleId, team.getId());
					//判断系统下是否有该单位
					if(moduleTeam != null){
						for(MOR mor : moduleTeam.getMor()){
							if(mor.getTeamPid().equals(parentTeamId)){
								//情况1  添加失败
								return "Msg:该单位已被添加。";
							}
						}
						//情况 2
						MOR mor = new MOR();
						mor.setInsertDate(new Date());
						mor.setModuleId(moduleId); //将用户挂载在对应系统模块下
						mor.setTeamCid(team.getId());  //关联本级单位
						mor.setTeamPid(parentTeamId); //挂载父级单位
						//mor.setOrgType(orgType);  大平台中使用该字段来区分 下级单位和办公室
						platformDao.save(mor);
						
					}else{
						// 情况 4 
						MOR mor = new MOR();
						mor.setInsertDate(new Date());
						mor.setModuleId(moduleId); //将用户挂载在对应系统模块下
						mor.setTeamCid(team.getId());  //关联本级单位
						mor.setTeamPid(parentTeamId); //挂载父级单位
						platformDao.save(mor);
					}
					return team.getId();
				}
			}
			//情况3
			String teamId = platformDao.addTeamInfo(teamInfo);
			MOR mor = new MOR();
			mor.setInsertDate(new Date());
			mor.setModuleId(moduleId); //将用户挂载在对应系统模块下
			mor.setTeamCid(teamId);  //关联本级单位
			mor.setTeamPid(parentTeamId); //挂载父级单位
			platformDao.save(mor);
			
			return teamId;
		}else{
			//情况3
			String teamId = platformDao.addTeamInfo(teamInfo);
			MOR mor = new MOR();
			mor.setInsertDate(new Date());
			mor.setModuleId(moduleId); //将用户挂载在对应系统模块下
			mor.setTeamCid(teamId);  //关联本级单位
			mor.setTeamPid(parentTeamId); //挂载父级单位
			platformDao.save(mor);
			
			return teamId;
		}
	}

	@Override
	public String updateTeamInfo(TeamInfo teaminfo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void updateMor(MOR mor) {
		platformDao.updateMor(mor);
	}

	@Override
	public void deleteTeamInfo(TeamInfo teaminfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TeamInfo findTeamInfoByTeamId(String moduleId, String teamId) {
		return platformDao.findTeamInfoByTeamId(moduleId, teamId);
	}

	@Override
	public Page<TeamInfo> findTeamInfoPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo,
			int pageSize) throws FzbDaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TeamInfo> findTeamInfoList(Map<String, Object> condMap, Map<String, String> sortMap) {
		// TODO Auto-generated method stub
		return platformDao.findTeamInfoList(condMap, sortMap);
	}
	
	@Override
	public List<MOR> findMorList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return platformDao.findMorList(condMap, sortMap);
	}

	@Override
	public List<TeamInfo> findTeamInfoSubList(String moduleId, String pid) {
		return platformDao.findTeamInfoSubList(moduleId, pid);
	}
	
	@Override
	public List<MOR> findMorSubList(String moduleId, String pid) {
		return platformDao.findMorSubList(moduleId, pid);
	}

	@Override
	public List<MOR> findMorTypeList(String moduleId, String orgType) {
		return platformDao.findMorTypeList(moduleId, orgType);
	}	
	

	@Override
	public List<TeamInfo> findTeamInfoInModuleByType(String moduleId,
			String type) {
		return platformDao.findTeamInfoInModuleByType(moduleId,type);
	}

	@Override
	public List<TeamInfo> findTeamInfoSuperList(String moduleId, String cid) {
		return platformDao.findTeamInfoSuperList(moduleId, cid);
	}
	
	@Override
	public List<TeamInfo> findTeamInfoSuperListRi(String moduleId, String cid) {
		return platformDao.findTeamInfoSuperListRi(moduleId, cid);
	}

	@Override
	public List<TeamInfo> findTeamInfoByUserId(String moduleId, String userId) {
		return platformDao.findTeamInfoByUserId(moduleId, userId);
	}	
	
	@Override
	public List<MOR> findMorListByUserId(String moduleId, String userId) {
		// TODO Auto-generated method stub
		return platformDao.findMorListByUserId(moduleId, userId);
	}

	@Override
	public List<MOR> findMorListByUnitId(String moduleId, String unitId) {
		// TODO Auto-generated method stub
		return platformDao.findMorListByUnitId(moduleId, unitId);
	}

	@Override
	public List<Map<String, Object>> findTeamListByTypeArray(String moduleId, String orgType,String[] deptIds) {
		List<Map<String, Object>> result=new ArrayList<>();
		String[] typeArray=orgType.split(",");
		for (String s:typeArray) {
			Map<String, Object> map=new HashMap<>();
			map.put("typeName",s);
			List<TeamInfo> teamInfoList=findTeamInfoInModuleByType(moduleId,s);
			if(null!=deptIds){
				teamInfoList.forEach((TeamInfo teamInfo)->{
					for (String deptId:deptIds) {
						if(teamInfo.getId().equals(deptId)){
							teamInfo.setChecked(true);
							break;
						}
					}
				});
			}
			map.put("teamInfoList",teamInfoList);
			result.add(map);
		}
		return result;
	}
}


