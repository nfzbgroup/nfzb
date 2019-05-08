package com.wonders.fzb.framework.services;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.framework.beans.MOR;
import com.wonders.fzb.framework.beans.OUR;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.framework.beans.vo.UserInfoBean;

import java.util.List;
import java.util.Map;

public interface UserInfoService {

	/**
	 * 管理员登陆
	 * 通过userId直接登陆
	 * 结果为全平台通用
	 * (目前法制办大平台登录 传递用户ID值 跳转到各系统使用方法)
	 */
	public UserInfo adminLogin(String userId);

	/**
	 * 管理员登陆
	 * 通过userId直接登陆
	 * 结果仅在moduleId对应系统模块下
	 */
	public UserInfo adminLogin(String userId, String moduleId);

	/**
	 * 用户名密码登陆（平台通用）
	 * @param username 用户名
	 * @param password 密码
	 * @return 校验是否成功
	 */
	public UserInfo login(String username, String password);

	/**
	 * 用户名密码登陆（指定系统模块）
	 * @param moduleId 系统模块ID
	 * @param username 用户名
	 * @param password 密码
	 * @return 校验是否成功
	 */
	public UserInfo login(String moduleId, String username, String password);

	/**
	 * 新增一个用户
	 * <p>添加逻辑：<br />
	 * 如果对应系统模块的对应单位下查找到了想同的用户名则不进行添加（重复添加） =》添加失败<br />
	 * 如果对应系统模块中查找到了相同用户名而对应单位下没有找到（本系统下其他单位，关联多单位帐号）=》新增ORG_USER_RELATE记录将此用户多关联一个单位<br />
	 * 如果对应系统中没有找到相同用户名帐号且参数userInfo主键为空（新帐号）=》新增用户并关联对应系统模块以及单位<br />
	 * 如果对应系统模块中没有找到相同用户名的帐号但userInfo主键不为空（其他系统夸系统帐号，大平台统一帐号，单点登录）=》新增MODULE_ORG_RELATE和ORG_USER_RELATE记录将此用户跨系统关联
	 * </p>
	 * @param moduleId 系统模块ID
	 * @param teamId 单位组织ID
	 * @param userInfo 封装的用户对象
	 * @return 添加后的用户UUID
	 */
	public String addUser(String moduleId, String teamId, UserInfo userInfo);

	/**
	 * 更新用户信息
	 * @param userInfo 封装好的用户对象
	 */
	public void updateUser(UserInfo userInfo);

	/**
	 * 查询单位下所有用户
	 * @param moduleId 系统模块ID
	 * @param teamId 组织ID
	 * @return 用户列表
	 */
	public List<UserInfo> findUsersByTeam(String moduleId, String teamId);

	/**
	 * 查询单位下所有用户
	 * @param moduleId 系统模块ID
	 * @return 用户列表
	 */
	public List<UserInfo> findUsersByModule(String moduleId);

	/**
	 * 根据条件查询UserVO
	 * @param sql 查询或者排序SQL
	 * @param pageNo 页码
	 * @param pageSize 每页条目数
	 */
	public Page<UserInfoBean> findUserListView(String sql, int pageNo, int pageSize);

	/**
	 * 删除关联
	 * @param ourId 关联ID
	 */
	public void removeOur(String ourId);

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	*/
	public UserInfoBean findById(String id);

	/**
	 * list
	 * @param condMap
	 * @param sortMap
	 * @return
	 */
	public List<UserInfo> findUserInfoList(Map<String, Object> condMap, Map<String, String> sortMap);

	/**
	 *  修改密码
	 *  
	 * @param uid
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	public String updateUpdatePassword(String uid, String oldPassword, String newPassword);

	/**
	 * 查询人员在对应系统对应模块单位关联中的关联信息
	 * @param moduleId 系统模块ID
	 * @param userId 用户ID
	 */
	public OUR findOurListByUserId(String moduleId, String morId, String userId);

	void addUser(UserInfo userInfo);

	public void addMor(MOR mor);

	public List<OUR> findAllOur(String condtion);

	// public List<UserInfo> findUserInfoTest(String string);

	/**
	 * 获得所有的用户 根据单位编号排序
	 */
	public List<UserInfo> findAll();

	public List<OUR> findOurByMorId(String condtion);

	List<UserInfo> findByHQL(String hql);

	UserInfo findByUserId(String id);

	
}
