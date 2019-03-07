package com.wonders.fzb.platform.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.platform.beans.UserInfoOld;
import com.wonders.fzb.platform.exceptions.InvalidUserException;



/**
 * WEGOV_ORG_USER业务接口
 *
 * @author scalffold
 *
 */
public interface UserInfoService {

    /**
     * 添加实体对象
     *
     * @param userInfo
     */
    public void add(UserInfoOld userInfo);

    /**
     * 更新实体对象
     *
     * @param userInfo
     */
    public void update(UserInfoOld userInfo);

    /**
     * 删除实体对象
     *
     * @param id
     */
    public void delete(String id);

    /**
     * 逻辑删除
     * @param id 主键
     */
    public void remove (String id);

    /**
     * 恢复用户
     * @param id 主键
     */
    public void unremove (String id);

    /**
     *
     * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
     *
     * @param id 主键
     * @return
     */
    public UserInfoOld findById(String id);

    /**
     * 根据分页参数进行分页查询.
     *
     * @param pageNo
     *            当前页码
     * @param pageSize
     *            每页显示记录数.
     * @return
     */
    public Page<UserInfoOld> findByPage(int pageNo, int pageSize);

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
    public Page<UserInfoOld> findByPage(Map<String, Object> condMap,
                                        Map<String, String> sortMap, int pageNo, int pageSize);


    /**
     * 根据Map中过滤条件、排序条件进行查询.
     *
     * @param condMap
     *            过滤条件<propertyName,properyValue>
     * @param sortMap
     *            排序条件<propertyName,properyValue>
     * @return
     */
    public List<UserInfoOld> findByList(Map<String, Object> condMap,
                                        Map<String, String> sortMap);

    /**
     * 添加用户
     * @param userInfo 用户信息
     * @return true 成功； false 失败
     * */
    public boolean addUser(UserInfoOld userInfo);

    /**
     * 逻辑删除用户<br>
     * @param  userId<br>
     * @return  true 成功； false 失败<br>
     */
    public boolean removeUser(String userId);

    /**
     * 修改用户信息
     * @param userInfo 用户信息
     * @return  true 成功； false 失败
     */
    public boolean modifyUser(UserInfoOld userInfo);

    /**
     * 修改用户密码<br>
     * @param userId 用户标识<br>
     * @param newPwd 新密码<br>
     * @return  true 成功； false 失败<br>
     */
    public boolean modifyPassword(String userId, String newPwd);

    /**
     * 修改用户密码<br>
     * @param userId 用户标识<br>
     * @param newPwd 新密码<br>
     * @param oldPwd 旧密码<br>
     * @return  true 成功； false 失败<br>
     */
    public boolean modifyPassword(String userId, String newPwd, String oldPwd);

    /**
     * 根据用户标识查找用户信息<br>
     * @param userId 用户标识<br>
     * @return  UserInfo<br>
     */
    public UserInfoOld findUserByUserID(String userId);

    /**
     * 根据用户账号查找用户信息<br>
     * @param account 用户账号<br>
     * @return  UserInfo<br>
     */
    public UserInfoOld findUserByAccount(String account);

    /**
     * 根据用户账号是否可用状态查找用户信息<br>
     * @param isValid 账号是否可用状态：PlatformConst.Y：可用；PlatformConst.N：不可用<br>
     * @param pageNo  当前页码<br>
     * @param pageSize 每页显示记录数<br>
     * @return 分页对象
     */
    public Page findUserByIsValid(String isValid, int pageNo, int pageSize);

    /**
     * 根据用户账号是否删除查找用户信息<br>
     * @param isDeleted 账号是否删除：PlatformConst.Y：已删除；PlatformConst.N：未删除<br>
     * @param pageNo  当前页码<br>
     * @param pageSize 每页显示记录数<br>
     * @return 分页对象
     */
    public Page findUserByIsDeleted(String isDeleted, int pageNo, int pageSize);

    /**
     * 根据用户登录状态查找用户信息<br>
     * @param loginStatus 用户登录状态：UserInfo.LOGIN_STATUS_ON：已登录；UserInfo.LOGIN_STATUS_OFF：未登录<br>
     * @param pageNo  当前页码<br>
     * @param pageSize 每页显示记录数<br>
     * @return 分页对象
     */
    public Page findUserByLoginStatus(String loginStatus, int pageNo, int pageSize);

    /**
     * 根据用户性别查找用户信息<br>
     * @param sex 用户性别：UserInfo.SEX_MALE：男；UserInfo.SEX_FEMALE：女<br>
     * @param pageNo  当前页码<br>
     * @param pageSize 每页显示记录数<br>
     * @return 分页对象
     */
    public Page findUserBySex(String sex, int pageNo, int pageSize);

    /**
     * 查找所有人员信息<br>
     * @param pageNo  当前页码<br>
     * @param pageSize 每页显示记录数<br>
     * @return 分页对象
     */
    public Page findAll(int pageNo, int pageSize);

    /**
     * 根据角色查找用户列表<br>
     * @return  StrucUserInfo列表<br>
     */
    public List<UserInfoOld> findUserByRole(String roleId);

    /**
     * 根据操作查找用户列表<br>
     * @return  StrucUserInfo列表<br>
     */
    public List<UserInfoOld> findUserByOp(String opId);

    /**
     * 得到不属于任何组织的用户<br>
     * @param pageNo  当前页码<br>
     * @param pageSize 每页显示记录数<br>
     * @return 分页对象
     */
    public Page findUserNotInUnit(int pageNo, int pageSize);

    /**
     * 根据用户账号是否可用状态查找用户信息<br>
     * @param isValid 账号是否可用状态：PlatformConst.Y：可用；PlatformConst.N：不可用<br>
     * @return  UserInfo列表<br>
     */
    public List<UserInfoOld> findUserByIsValid(String isValid);

    /**
     * 根据用户账号是否删除查找用户信息<br>
     * @param isDeleted 账号是否删除：PlatformConst.Y：已删除；PlatformConst.N：未删除<br>
     * @return  UserInfo列表<br>
     */
    public List<UserInfoOld> findUserByIsDeleted(String isDeleted);

    /**
     * 根据用户登录状态查找用户信息<br>
     * @param loginStatus 用户登录状态：UserInfo.LOGIN_STATUS_ON：已登录；UserInfo.LOGIN_STATUS_OFF：未登录<br>
     * @return  UserInfo列表<br>
     */
    public List<UserInfoOld> findUserByLoginStatus(String loginStatus);

    /**
     * 根据用户性别查找用户信息<br>
     * @param sex 用户性别：UserInfo.SEX_MALE：男；UserInfo.SEX_FEMALE：女<br>
     * @return  UserInfo列表<br>
     */
    public List<UserInfoOld> findUserBySex(String sex);

    /**
     * 查找所有人员信息<br>
     * @return  UserInfo列表<br>
     */
    public List<UserInfoOld> findAll();


    /**
     * 根据查询条件过滤用户列表<br>
     * @param userList 用户列表<br>
     * @param userCondition 查询条件<br>
     * @return  UserInfo列表<br>
     */
    public List<UserInfoOld> queryUserInfo(List<UserInfoOld> userList,UserInfoOld userCondition);

    /**
     * 得到不属于任何组织的用户<br>
     * @param userList 用户列表<br>
     * @return  UserInfo列表<br>
     */
    public List<UserInfoOld> findUserNotInUnit(List<UserInfoOld> userList);

    /**
     * 得到不属于任何组织的用户<br>
     * @return  UserInfo列表<br>
     */
    public List<UserInfoOld> findUserNotInUnit();

    /**
     * 根据单位标识查找用户列表
     * @param unitId 单位标识
     * @return List<UserInfo>
     */
    public List<UserInfoOld> getUsersByUnit(String unitId);

    /**
     * 根据单位标识列表查找用户列表
     * @param unitIdList 单位标识列表
     * @return List<UserInfo>
     */
    public List<UserInfoOld> getUsersByUnitIdList(List<String> unitIdList);

    /**
     * 根据单位类别查找用户列表
     * @param group 单位类别
     * @return List<UserInfo>
     */
    public List<UserInfoOld> getUsersByGroup(String group);

    /**
     * 根据上次登录时间得到用户列表
     * @param startTime 开始时间

     * @param endTime 结束时间
     * @return 用户列表
     */
    public List<UserInfoOld> getUsersByTime(Date startTime, Date endTime);

    /**
     * 得到领导的秘书的列表
     * @param leaderId 领导的用户标识

     * @return 秘书的列表

     */
    public List<UserInfoOld> findSecretsByLeader(String leaderId);

    /**
     * 得到秘书所服务的领导的列表
     * @param userId 秘书的用户标识

     * @return 领导的列表

     */
    public List<UserInfoOld> findLeadersBySecret(String userId);

    /**
     * 用户登录<br>
     * @param account 帐号
     * @param ip 登录IP地址
     * @param host 登录主机名

     * @param password 密码
     * @return boolean 是否成功<br>
     * <p>
     */
    public boolean login(String account, String ip, String host, String password) throws InvalidUserException;

    /**
     * 用户登录（根据ip地址找到用户账号，然后登录）
     * @param ip 登录IP地址
     * @param host 登录主机名

     * @return 用户账号<br>
     * <p>
     */
    public String login(String ip, String host);

    /**
     * 注销用户登录<br>
     * @param  userId<br>
     * @param  needLog 是否需要记录日志<br>
     * @return  true 成功； false 失败<br>
     */
    public boolean logout(String userId, boolean needLog);

    /**
     * 注销用户登录<br>
     * @param  userId<br>
     * @return  true 成功； false 失败<br>
     */
    public boolean logout(String userId);


    /**
     * 新建方法
     * @param userId
     * @return
     */
    public UserInfoOld findByUserId(String userId);

    /**
     * 对Collection中所包含的Info对象进行单条件的查询过滤
     * @param col 包含Info对象的Collection ,例如ArrayList ,可以为空
     * @param fieldName 需要查询的Info对象的字段,可以为空
     * @param op 查询操作符 参见PlatformConst QUERY_OP_XXX,可以为空
     * @param value 查询的值，<b>现在只支持String 类型</b>
     * @return 符合查询条件的Info对象的类标
     */
    public List<UserInfoOld> queryInformList(String condField,String condValue,String op);
}
