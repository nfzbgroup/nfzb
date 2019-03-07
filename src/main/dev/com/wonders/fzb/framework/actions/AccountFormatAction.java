package com.wonders.fzb.framework.actions;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.kit.StringKit;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.framework.services.TeamInfoService;
import com.wonders.fzb.framework.services.UserInfoService;

@Namespace("/test")
@Controller
@Scope("prototype")
public class AccountFormatAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6805940410186960436L;

	@Autowired
	@Qualifier("teamInfoService")
	private TeamInfoService teamService;
	
	@Autowired
	@Qualifier("userInfoService")
	private UserInfoService userService;

	@Action(value = "account_format", results = {@Result(name = SUCCESS, location = "/demo/test.jsp")})
	public String account_format() {
		List<UserInfo> userList = userService.findUsersByModule("MODULE_NORMALDOC");
		System.out.println("------该Module下所有用户数量-----"+userList.size());
		for(int i=0;i<userList.size();i++){
			UserInfo userInfo = (UserInfo)userList.get(i);
			System.out.println("-----用户---"+userInfo.getName());
			//密码与账号一致
			String newPwd = StringKit.AESEncode(userInfo.getAccount(), "fzb_user_" + userInfo.getSalt());
			userInfo.setPassword(newPwd);
			userService.updateUser(userInfo);
		}
		
		return SUCCESS;
	}
}