package com.wonders.fzb.framework.services.impl;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.base.kit.StringKit;
import com.wonders.fzb.framework.beans.MOR;
import com.wonders.fzb.framework.beans.OUR;
import com.wonders.fzb.framework.beans.TeamInfo;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.framework.beans.vo.UserInfoBean;
import com.wonders.fzb.framework.dao.PlatformDao;
import com.wonders.fzb.framework.services.UserInfoService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 用户服务类
 */
@Service("userInfoService")
@Transactional
public class UserInfoServiceImpl extends BaseSupportDao implements UserInfoService {

	@Autowired
	@Qualifier("platformDao")
	private PlatformDao platformDao;

	@Override
	public void addUser(UserInfo userInfo) {
		platformDao.addUser(userInfo);
	}

	@Override
	public void addMor(MOR mor) {
		platformDao.addMor(mor);
	}

	/**
	 * 新增一个用户
	 * <p>添加逻辑：<br />
	 * 如果对应系统模块的对应单位下查找到了想同的用户名则不进行添加（重复添加） =》添加失败<br />
	 * 如果对应系统模块中查找到了相同用户名而对应单位下没有找到（本系统下其他单位，关联多单位帐号）=》新增ORG_USER_RELATE记录将此用户多关联一个单位<br />
	 * 如果对应系统中没有找到相同用户名帐号且参数userInfo主键为空（新·帐号）=》新增用户并关联对应系统模块以及单位<br />
	 * 如果对应系统模块中没有找到相同用户名的帐号但userInfo主键不为空（其他系统夸系统帐号，大平台统一帐号，单点登录）=》新增MODULE_ORG_RELATE和ORG_USER_RELATE记录将此用户跨系统关联
	 * </p>
	 * @param moduleId 系统模块ID
	 * @param teamId 单位组织ID
	 * @param userInfo 封装的用户对象
	 * @return 添加后的用户UUID
	 */
	@Override
	public String addUser(String moduleId, String teamId, UserInfo userInfo) {
		List<UserInfo> teamUsers = platformDao.findUsersByTeam(moduleId, teamId);
		for (UserInfo user : teamUsers) {
			// 用户帐号相同且用户名字相同，认定为同一帐号
			if (null != user && user.getAccount().equals(userInfo.getAccount()) && user.getName().equals(userInfo.getName())) {
				return "Msg:该帐号已被添加。";
			}
		}

		List<UserInfo> moduleUsers = platformDao.findUsersByModule(moduleId);
		for (UserInfo user : moduleUsers) {
			// 用户帐号相同且用户名字相同，认定为同一帐号
			if (null != user && user.getAccount().equals(userInfo.getAccount()) && user.getName().equals(userInfo.getName())) {
				// 查询要添加的单位信息
				TeamInfo team = platformDao.findTeamInfoByTeamId(moduleId, teamId);
				for (MOR mor : team.getMor()) {
					// 找到对应系统下被绑定的单位关联用户
					if (mor.getModuleId().equals(moduleId)) {
						OUR our = new OUR();
						our.setInsertDate(new Date());
						our.setMorId(mor.getId()); // 将组织用户关联结构挂在系统组织关联结构上
						our.setUserId(user.getUserId());// 将用户挂在组织用户关联结构上
						our.setType("");

						platformDao.save(our);
						break;
					}
				}
				return user.getUserId();
			}
		}

		// 使用UUID方式生成一个SALT，并利用SALT取出原有密码进行加密
		UUID uuid = UUID.randomUUID();
		userInfo.setSalt(uuid.toString().toUpperCase().replaceAll("-", ""));// 使用相同的椒盐加密、解密
		userInfo.setPassword(StringKit.AESEncode(userInfo.getPassword(), "fzb_user_" + userInfo.getSalt()));
		userInfo.setCreateTime(new Date());
		String result = platformDao.addUser(userInfo);

		TeamInfo team = platformDao.findTeamInfoByTeamId(moduleId, teamId);
		for (MOR mor : team.getMor()) {
			// 找到对应系统下被绑定的单位关联用户
			if (mor.getModuleId().equals(moduleId)) {
				OUR our = new OUR();
				our.setInsertDate(new Date());
				our.setMorId(mor.getId()); // 将组织用户关联结构挂在系统组织关联结构上
				our.setUserId(result);// 将用户挂在组织用户关联结构上
				our.setType("");

				platformDao.save(our);
				break;
			}
		}

		return result;
	}

	@Override
	public void updateUser(UserInfo userInfo) {
		platformDao.updateUser(userInfo);
	}

	@Override
	public List<UserInfo> findUsersByTeam(String moduleId, String teamId) {
		return platformDao.findUsersByTeam(moduleId, teamId);
	}

	@Override
	public List<UserInfo> findUsersByModule(String moduleId) {
		return platformDao.findUsersByModule(moduleId);
	}

	@Override
	public UserInfo adminLogin(String userId) {
		// TODO Auto-generated method stub
		UserInfo userInfo = platformDao.findUserInfoByUserId(userId);
		return userInfo;
	}

	@Override
	public UserInfo adminLogin(String userId, String moduleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInfo login(String username, String password) {
		UserInfo userInfo = platformDao.findUserInfoByAccount(username);
		if (userInfo != null) {
			if (userInfo.getPassword() != null) {
				String testPwd = StringKit.AESDecode(userInfo.getPassword(), "fzb_user_" + userInfo.getSalt());
				if (password.equals(testPwd)) {
					return userInfo;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public UserInfo login(String moduleId, String username, String password) {
		UserInfo userInfo = platformDao.findUserInfoByAccount(username, moduleId);
		if (userInfo != null) {
//			if (userInfo.getPassword() != null) {
//				if (password.equals(StringKit.AESDecode(userInfo.getPassword(), "fzb_user_" + userInfo.getSalt()))) {
//					return userInfo;
//				} else {
//					return null;
//				}
//			} else {
//				return null;
//			}
            return userInfo;
		} else {
			return null;
		}
	}

	@Override
	public Page<UserInfoBean> findUserListView(String sql, int pageNo, int pageSize) {
		return platformDao.findUserListView(sql, pageNo, pageSize);
	}

	public void removeOur(String ourId) {
		OUR our = new OUR();
		our.setId(ourId);
		platformDao.delete(our);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public UserInfoBean findById(String id) {
		return (UserInfoBean) platformDao.load(id);
	}

	/**
	 * list
	 * @param condMap
	 * @param sortMap
	 * @return
	 */
	@Override
	public List<UserInfo> findUserInfoList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return platformDao.findUserInfoList(condMap, sortMap);
	}

	public String updateUpdatePassword(String uid, String oldPassword, String newPassword) {
		UserInfo userInfo = platformDao.findUserInfoByUserId(uid);
		String oldPwd = StringKit.AESDecode(userInfo.getPassword(), "fzb_user_" + userInfo.getSalt());
		if (oldPwd == null || oldPassword.equals(oldPwd)) {
			String newPwd = StringKit.AESEncode(newPassword, "fzb_user_" + userInfo.getSalt());
			userInfo.setPassword(newPwd);
			this.platformDao.updateUser(userInfo);
			return "修改成功！";
		} else {
			return "原密码错误！";
		}
	}

	@Override
	public OUR findOurListByUserId(String moduleId, String morId, String userId) {
		List<OUR> ours = platformDao.findOurListByUserId(moduleId, userId);

		OUR result = null;
		for (OUR item : ours) {
			if (null != morId && morId.equals(item.getMorId())) {
				result = item;
				break;
			}
		}

		return result;
	}

	@Override
	public List<OUR> findAllOur(String condtion) {
		// TODO Auto-generated method stub
		List<OUR> results = platformDao.findAllOur(condtion);
		return results;
	}

	@Override
	public List<UserInfo> findAll() {
		return platformDao.findAll();
		// return userInfoDao.findByList(null, null);
	}

	@Override
	public List<OUR> findOurByMorId(String condtion) {
		// TODO Auto-generated method stub
		return platformDao.findOurByMorId(condtion);
	}

	@Override
	public List<UserInfo> findByHQL(String hql) {
		Session session = getSession();
		Query query = session.createQuery(hql);
		List<UserInfo> list=query.list();
		session.flush();
		return list;
	}

	@Override
	public UserInfo findByUserId(String id) {
		return  platformDao.findUserInfoByUserId(id);
	}


	// @Override
	// public List<UserInfo> findUserInfoTest(String string) {
	// // TODO Auto-generated method stub
	// return platformDao.findUserInfoTest(string);
	// }

}
