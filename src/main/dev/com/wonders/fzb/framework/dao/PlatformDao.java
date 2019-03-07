package com.wonders.fzb.framework.dao;

import java.util.List;
import java.util.Map;
import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.MOR;
import com.wonders.fzb.framework.beans.OUR;
import com.wonders.fzb.framework.beans.TeamInfo;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.framework.beans.vo.UserInfoBean;

/**
 * 监管平台持久化层接口
 * @author ZSW
 */
public abstract interface PlatformDao extends BaseDao {

	/**
	 * 添加TeamInfo
	 */
	public String addTeamInfo(TeamInfo teaminfo);

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
	public TeamInfo findTeamInfoByTeamId(String moduleId, String teamId);

	/**
	 * 分页查找TeamInfo
	 */
	public Page<TeamInfo> findTeamInfoPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException;

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
	public List<TeamInfo> findTeamInfoSubList(String moduleId, String pid);

	/**
	 * 查询下级单位MOR
	 * @param moduleId 系统模块ID
	 * @param pid 上级单位ID
	 */
	public List<MOR> findMorSubList(String moduleId, String pid);

	/**
	 * 查询某类型单位MOR
	 * @param moduleId 系统模块ID
	 * @param orgType 单位Type
	 */
	public List<MOR> findMorTypeList(String moduleId, String orgType);

	/**
	 * 查询上级单位
	 * @param moduleId 系统模块ID
	 * @param cid 本级单位ID
	 */
	public List<TeamInfo> findTeamInfoSuperList(String moduleId, String cid);

	/**
	 * 查询上级单位
	 * @param moduleId 系统模块ID
	 * @param cid 本级单位ID
	 */
	public List<TeamInfo> findTeamInfoSuperListRi(String moduleId, String cid);

	/**
	 * 查询用户所在的单位
	 * @param moduleId 系统模块ID
	 * @param userId 用户ID
	 */
	public List<TeamInfo> findTeamInfoByUserId(String moduleId, String userId);

	/**
	 * 新增一个用户
	 */
	public String addUser(UserInfo userInfo);

	/**
	 * MOR
	 */
	public String addMor(MOR mor);

	/**
	 * 更新用户信息
	 * @param userInfo 封装好的用户对象
	 */
	public void updateUser(UserInfo userInfo);

	/**
	 * 查询单位下所有用户
	 * @param moduleId 系统模块ID
	 * @param teamId 单位ID
	 * @return 用户列表
	 */
	public List<UserInfo> findUsersByTeam(String moduleId, String teamId);

	/**
	 * 查询系统下所有用户
	 * @param moduleId 系统模块ID
	 * @return 用户列表
	 */
	public List<UserInfo> findUsersByModule(String moduleId);

	/**
	 * 根据条件查询UserVO
	 * @param sql WHERE语句和OrderBy语句
	 * @return UserInfoVO
	 */
	public Page<UserInfoBean> findUserListView(String sql, int pageNo, int pageSize);

	/**
	 * 根据UserId查询UserInfo对象
	 * @param userId 单位ID
	 */
	public UserInfo findUserInfoByUserId(String userId);

	/**
	 * 根据UserId查询在moduleId系统模块下的UserInfo对象
	 * @param account 帐号
	 */
	public UserInfo findUserInfoByAccount(String account);

	/**
	 * 根据登陆用户 以及 密码key 获取 人员 信息 
	 * @param account 用户账号 
	 * @param salt  系统标识 密钥KEY
	 * @return
	 */
	public UserInfo findUserInfoByAccount(String account, String salt);

	/**
	 * 分页查找UserInfo
	 */
	public Page<UserInfo> findUserInfoPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException;

	/**
	 * 根据条件查询List
	 */
	public List<UserInfo> findUserInfoList(Map<String, Object> condMap, Map<String, String> sortMap);

	/**
	 * 查询一个人员的模块单位MOR
	 * @param moduleId 系统模块ID
	 * @param userId 用户ID
	 */
	public List<MOR> findMorListByUserId(String moduleId, String userId);

	/**
	 * 根据人员和系统模块ID查询人员单位关联列表
	 * @param moduleId 系统模块ID
	 * @param userId 用户ID
	 */
	public List<OUR> findOurListByUserId(String moduleId, String userId);

	/**
	 * 查询模块下的某个单位MOR
	 * @param moduleId 系统模块ID
	 * @param unitId 单位ID
	 */
	public List<MOR> findMorListByUnitId(String moduleId, String unitId);

	public List<OUR> findAllOur(String condtion);

	// public List<UserInfo> findUserInfoTest(String string);

	/**
	 * 获得所有用户
	 * @return
	 */
	public List<UserInfo> findAll();

	List<OUR> findOurByMorId(String condtion);

	/**
	 * 执法公示人员组织树添加
	 * @param mor
	 * @return
	 */
	public String addPublicityMor(MOR mor);

}