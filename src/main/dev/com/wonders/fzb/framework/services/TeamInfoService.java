package com.wonders.fzb.framework.services;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.MOR;
import com.wonders.fzb.framework.beans.TeamInfo;
import com.wonders.fzb.platform.beans.TeamMemberInfo;

/**
 * 组织单位Service
 * @author ZSW
 */
public interface TeamInfoService {
	
	/**
	 * 新增一个单位
	 * <p>添加逻辑：<br />
	 * 如果在teamInfo表中查找到相同teamInfo.unitName且对应moduleId的系统模块以及对应parentTeamId的父节点下找到相同teamInfo.unitName则判断为重复添加 => 添加失败<br />
	 * 如果在teamInfo表中查找到相同teamInfo.unitName且在对应moduleId下挂载，则判断为多父级关联 => 在MODULE_ORG_RELATE关联表多关联一个相同moduleId的parentTeamId对应父节点<br />
	 * 如果在teamInfo表中未查找到相同teamInfo.unitName则为完全新开单位 => 新增TeamInfo并新增对应MODULE_ORG_RELATE关联表关联<br />
	 * 如果在teamInfo表中查找到相同teamInfo.unitName且不在要添加的moduleId下，则在本系统下多加一次关联新 => 新增对应MODULE_ORG_RELATE关联表关联<br />
	 * </p>
	 * @param moduleId 系统模块ID
	 * @param parentTeamId 上级单位ID
	 * @param teaminfo 封装的单位对象
	 * @return 添加后的单位UUID
	 */
	public String addTeamInfo(String moduleId,String parentTeamId,TeamInfo teaminfo);
	
	/**
	 * 修改TeamInfo
	 */
	public String updateTeamInfo(TeamInfo teaminfo);
	
	/**
	 * 修改MOR
	 */
	public void updateMor(MOR mor);
	
	/**
	 * 删除TeamInfo
	 */
	public void deleteTeamInfo(TeamInfo teaminfo);
	
	/**
	 * 根据TeamId查询在moduleId系统模块下的TeamInfo对象
	 * @param moduleId 系统模块ID
	 * @param teamId 单位ID
	 */
	public TeamInfo findTeamInfoByTeamId(String moduleId,String teamId);
	
	/**
	 * 分页查找TeamInfo
	 */
	public Page<TeamInfo> findTeamInfoPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo,
			int pageSize) throws FzbDaoException;
	
	/**
	 * 根据条件查询List
	 */
	public List<TeamInfo> findTeamInfoList(Map<String, Object> condMap, Map<String, String> sortMap);
	
	/**
	 * 根据条件查询List
	 */
	public List<MOR> findMorList(Map<String, Object> condMap, Map<String, String> sortMap);
	
	/**
	 * 查询下级单位
	 * @param moduleId 系统模块ID
	 * @param pid 上级单位ID
	 */
	public List<TeamInfo> findTeamInfoSubList(String moduleId,String pid);
	
	/**
	 * 查询下级单位MOR
	 * @param moduleId 系统模块ID
	 * @param pid 上级单位ID
	 */
	public List<MOR> findMorSubList(String moduleId,String pid);
	
	/**
	 * 查询某类型单位MOR
	 * @param moduleId 系统模块ID
	 * @param orgType 单位Type,不传则显示所有该moduleId的单位
	 */
	public List<MOR> findMorTypeList(String moduleId, String orgType);
	
	/**
	 * 查询上级单位
	 * @param moduleId 系统模块ID
	 * @param cid 本级单位ID
	 */
	public List<TeamInfo> findTeamInfoSuperList(String moduleId,String cid);
	
	/**
	 * 查询上级单位
	 * @param moduleId 系统模块ID
	 * @param cid 本级单位ID
	 */
	public List<TeamInfo> findTeamInfoSuperListRi(String moduleId,String cid);
	/**
	 * 查询用户所在的单位
	 * @param moduleId 系统模块ID
	 * @param userId 用户ID
	 */
	public List<TeamInfo> findTeamInfoByUserId(String moduleId,String userId);
	
	/**
	 * 查询一个用户的单位MOR
	 * @param moduleId 系统模块ID
	 * @param userId 用户ID
	 */
	public List<MOR> findMorListByUserId(String moduleId,String userId);
	
	/**
	 * 查询模块下的某个单位MOR
	 * @param moduleId 系统模块ID
	 * @param unitId 单位ID
	 */
	public List<MOR> findMorListByUnitId(String moduleId,String unitId);
}
