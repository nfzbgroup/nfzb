package com.wonders.fzb.framework.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.framework.services.TeamInfoService;
import com.wonders.fzb.framework.services.UserInfoService;

@Namespace("/testing")
@Controller
@Scope("prototype")
public class TestAction extends BaseAction {

	private static final long serialVersionUID = -9124826379456265421L;

	@Autowired
	@Qualifier("teamInfoService")
	private TeamInfoService teamService;
	
	@Autowired
	@Qualifier("userInfoService")
	private UserInfoService userService;

	@Action(value = "view", results = {@Result(name = SUCCESS, location = "/framework/testing.jsp")})
	public String execute() {
//		TeamInfo teamInfo = service.findTeamInfoByTeamId("9305670A8F684C2CB483542E9B9A5214", "CITY_15");
//		
//		System.out.println(service.findTeamInfoByTeamId(teamInfo.getMor().get(0).getModuleId(), teamInfo.getMor().get(0).getTeamPid()).getTeamName());

		//List<TeamInfo> list = service.findTeamInfoSuperList("9305670A8F684C2CB483542E9B9A5214", "JD_01");
		
//		List<TeamInfo> list = service.findTeamInfoByUserId("9305670A8F684C2CB483542E9B9A5214", "IMP_786");
//		for(TeamInfo team : list){
//			System.out.println(team.getTeamName());
//		}
//		
//		List<UserInfo> lst = userService.findUsersByTeam("9305670A8F684C2CB483542E9B9A5214", "SJ_01_23");
//		
//		for(UserInfo user : lst){
//			System.out.println(user.getAbbrName());
//		}
		
//		UserInfo userInfo = new UserInfo();
//		userInfo.setPassword("test Scret_!!");
//		userInfo.setAccount("TEST");
//		userInfo.setName("TEST");
//		userInfo.setSex("F");
//		userInfo.setEmail("1@qq.com");
//		userInfo.setAccountState("y");
//		userInfo.setLoginStatus("0");
//		userService.addUser("", "", userInfo);
		
		request.setAttribute("teamList",  teamService.findTeamInfoList(null, null));
		
		return SUCCESS;
	}
}