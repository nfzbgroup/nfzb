package com.wonders.fzb.platform.dao;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.platform.beans.UserInfoOld;

import java.util.List;
import java.util.Map;

/**
 * WEGOV_ORG_USER dao接口
 * @author scalffold
 */
public abstract interface UserInfoDao extends BaseDao {

    public Page<UserInfoOld> findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo,
                                        int pageSize);

    public List<UserInfoOld> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

    public List<UserInfoOld> findByTeamIdList(List<String> teamIdList);

    public List<UserInfoOld> findByGroup(String group);

    public List<UserInfoOld> findByRelationAndMainId(String relation, String mainId);

    public List<UserInfoOld> findByRelationAndSubId(String relation, String subId);

    public List<UserInfoOld> findByRoleId(String roleId);

    public List<UserInfoOld> findByOpId(String opId);

    public UserInfoOld findByUserId(String userId);

    public void setInjectedEntity(String injectedEntity) ;
}