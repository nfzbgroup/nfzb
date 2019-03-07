package com.wonders.fzb.legislate.web;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.TeamInfo;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.framework.services.TeamInfoService;
import com.wonders.fzb.framework.services.UserInfoService;
import com.wonders.fzb.legislate.LegislateConst;
import com.wonders.fzb.legislate.services.OrgUtils;

@Namespace("/legislate")
@Controller
@Scope("prototype")
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Resource
	UserInfoService userInfoService;
	
	@Resource
	TeamInfoService teamInfoService;

	@Action(value = "login", results = { @Result(name = SUCCESS, location = "/legislate/index.jsp"), @Result(name = LOGIN, location = "/legislate/login.jsp") })
	public String login() throws FzbDaoException {

		UserInfo currentPerson = new UserInfo();
		String account = request.getParameter("username");
		System.out.println(account);
		String password = request.getParameter("password");
		System.out.println(password);
		String moduleUserId = request.getParameter("moduleUserId") == null ? "" : request.getParameter("moduleUserId");
		String plantformId = request.getParameter("plantformId") == null ? "" : request.getParameter("plantformId");
		if (!"".equals(plantformId)) {
			session.setAttribute("plantformId", plantformId);
		}

		try {
			//单点登录
			if(!"".equals(moduleUserId)){
				currentPerson =userInfoService.adminLogin(moduleUserId);
			}
			
			if ((account != null && !"".equals(account) && password != null && !"".equals(password)) || currentPerson != null) {
				if ("".equals(moduleUserId)) {
					currentPerson = userInfoService.login("legislate_user", account, password);
				}
				if (currentPerson != null) {
					List<TeamInfo> userTeamsList = teamInfoService.findTeamInfoByUserId("MODULE_LEGISLATE", currentPerson.getUserId());
					currentPerson.setTeamInfos(userTeamsList);
					session.setAttribute("currentPerson", currentPerson);
					String unitName = ((TeamInfo) currentPerson.getTeamInfos().get(0)).getTeamName();
					String unitCode = ((TeamInfo) currentPerson.getTeamInfos().get(0)).getId();
					// 当前登录人员所属单位的判断。
					boolean isLfc = false;// 是否立法处
					boolean isZhc = false;// 是否综合处
					if (unitCode != null && (unitCode.equals(LegislateConst.TEAM_ID_JJFGC) || unitCode.equals(LegislateConst.TEAM_ID_CJFGC) || unitCode.equals(LegislateConst.TEAM_ID_SHFGC))) {
						isLfc = true;
					}
					if (unitName != null && (unitName.endsWith("综合业务处"))) {
						isZhc = true;
					}
					session.setAttribute("isLfc", isLfc);// 是否立法处
					session.setAttribute("isZhc", isZhc);/// 是否综合处
					session.setAttribute("isFzb", OrgUtils.isFzb(teamInfoService, unitCode));/// 是法制办
					if ("a".equals(password)) {
						request.setAttribute("message2", "请尽快修改您的初始密码！");
					}
					return SUCCESS;
				} else {
					request.setAttribute("errorMessage", "用户名或密码错误！");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return LOGIN;
	}

	@Action(value = "updatePassword", results = { @Result(name = SUCCESS, location = "/legislate/updatePassword.jsp") })
	public String updatePassword() throws FzbDaoException {
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		UserInfo user = getLoginUser();
		String message = userInfoService.updateUpdatePassword(user.getUserId(), oldPassword, newPassword);
		request.setAttribute("message", message);
		return SUCCESS;
	}
}
