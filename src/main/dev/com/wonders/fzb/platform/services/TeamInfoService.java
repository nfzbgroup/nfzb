package com.wonders.fzb.platform.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.platform.beans.TeamInfoOld;
import com.wonders.fzb.platform.beans.TeamMemberInfo;
import com.wonders.fzb.platform.beans.UserInfoOld;

public interface TeamInfoService {
	/**
	 * 添加实体对象
	 * 
	 * @param TeamInfo
	 */
	public String add(TeamInfoOld TeamInfo);

	/**
	 * 更新实体对象
	 * 
	 * @param TeamInfo
	 */
	public void update(TeamInfoOld TeamInfo);

	/**
	 * 删除实体对象
	 * 
	 * @param TeamInfoOld
	 */
	public void delete(String id);

	/**
	 * 
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public TeamInfoOld findById(String id);

	
	/**
	 * 根据用户ID查询所在单位
	 * @param userId
	 * @return
	 */
	public List<TeamInfoOld> findByUserId(String userId);
	
	
	/**
	 * 根据分页参数进行分页查询.
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示记录数.
	 * @return
	 */
	public Page findByPage(int pageNo, int pageSize);

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
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize);

	/**
	 * 根据Map中过滤条件、排序条件进行查询.
	 * 
	 * @param condMap
	 *            过滤条件<propertyName,properyValue>
	 * @param sortMap
	 *            排序条件<propertyName,properyValue>
	 * @return
	 */
	public List<TeamInfoOld> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

	/**
	 * 根据成员用户标示和团队标识查找团队成员列表<br>
	 * return： TeamMember列表; null:查询失败<br>
	 */
	public List<TeamMemberInfo> findMemberByTeamIDandMemIdUser(String teamId, String memIdUser);

	/**
	 * 根据团队标识和团队成员类型查找团队成员列表<br>
	 * return： TeamMember列表; null:查询失败<br>
	 */
	public List<TeamMemberInfo> findMemberByTeamIDandMemType(String teamId, String memType);
	
	/**
	  * 根据团队标识和团队成员名称(like)查找团队成员列表<br>
	  * return： TeamMember列表; null:查询失败<br>
	  */
	public List<TeamMemberInfo> findMemberByTeamIDandMemNameLike(String teamId,String memName);
	
	/**
	 * 根据成员团队标示和团队标识查找团队成员列表<br>
	 * return： TeamMember列表; null:查询失败<br>
	 */
	public List<TeamMemberInfo> findMemberByTeamIDandMemIdTeam(String teamId, String memIdTeam);
	 
	 /**
	  * 删除团队,若被删除团队是另一团队的成员，则将成员团队标识置空<br>
	  * return： true 成功； false 失败<br>
	  */
	public boolean removeTeam (String teamId);
	
	/**
	 * 根据成员团队标示查找团队成员列表<br>
	 * return： TeamMember列表; null:查询失败<br>
	 */
	public List<TeamMemberInfo> findMemberByMemIdTeam(String memIdTeam);
	
	/**
	 * 根据团队标识查找团队信息<br>
	 * return： TeamInfo; null:查询失败<br>
	 */
	public TeamInfoOld findTeamByTeamID(String teamId);
	
	/**
	   * 向单位中添加人员
	   * @param unitId 单位标识
	   * @param level 人员级别
	   * @param userId 用户标识
	   * @throws SystemException
	   */
	public void addPersonToUnit(String unitId, String level,String no, String userId);
	
	/**
	 * 添加团队成员<br>
	 * parameter：memType="T" addId=团队标识；memType="P" addId=用户标识<br>
	 * return： true 成功； false 失败<br>
	 */
	public boolean addMemberToTeam (String name,String no,String memType, String teamId, String addId);
	
	/**
	   * 从单位中删除人员
	   * @param unitId 单位标识
	   * @param level 人员级别
	   * @param userId 用户标识
	   * @throws SystemException
	   */
	public void removePersonFromUnit(String unitId, String level,String no, String userId);
	
	/**
	 * 根据团队名称查找团队信息<br>
	 * return： TeamInfo列表; null:查询失败<br>
	 */
	public List findTeamByTeamName(String teamName);
	
	/**
	 * 根据团队标识查找用户列表<br>
	 * return： UserInfo列表; null:查询失败<br>
	 */
	public List<UserInfoOld> findUserByTeamID(String teamId);
	
	/**
	 * 查找所有团队信息<br>
	 * return： TeamInfo列表<br>
	 */
	public List<TeamInfoOld> findAllTeam();
	
	/**
	 * 团队是否属于团队和其下属团队<br>
	 * return： true:属于； false:不属于<br>
	 */
	public boolean TeamBelongToTeamandSubteam(String teamId_f, String teamId_s);
	
	/**
	 * 根据团队标识查找团队成员列表<br>
	 * return： TeamMember列表; null:查询失败<br>
	 */
	public ArrayList findMemberByTeamID(String teamId);
	
	/**
	   * 新增单位
	   * @param unit 单位信息
	   * @return 单位标识
	   * @throws SystemException
	   */
	public String addUnit(TeamInfoOld unit);
	
	/**
	 * 用户是否属于团队和其下属团队<br>
	 * return： true:属于； false:不属于<br>
	 */
	public boolean UserBelongToTeamandSubteam(String teamId, String UserId);
	
	/**
	   * 修改单位中人员级别
	   * @param unitId 单位标识
	   * @param level 人员级别
	   * @param userId 用户标识
	   * @param newlevel
	   * @throws SystemException
	   */
	public void modifyPersonLevel(String unitId, String level, String userId, String newlevel );
	
	public List<TeamInfoOld> findByTeamGroup(String teamGroup);
	
	
	
	/**
	 * 获得本单位的下级单位编号
	 * @param unitId 本单位ID
	 * @return
	 */
	public List<TeamInfoOld> getDownUnit(String unitId);
	
}
