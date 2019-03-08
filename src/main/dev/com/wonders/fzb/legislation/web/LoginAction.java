package com.wonders.fzb.legislation.web;

import com.alibaba.druid.util.StringUtils;
import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.OUR;
import com.wonders.fzb.framework.beans.TeamInfo;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.framework.dao.PlatformDao;
import com.wonders.fzb.framework.services.TeamInfoService;
import com.wonders.fzb.framework.services.UserInfoService;
import com.wonders.fzb.legislation.LegislateConst;
import com.wonders.fzb.legislation.services.OrgUtils;
import com.wonders.fzb.simpleflow.beans.WegovSimpleNode;
import com.wonders.fzb.simpleflow.services.WegovSimpleNodeService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Namespace("/legislation")
@Controller
@Scope("prototype")
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Resource
	UserInfoService userInfoService;
	
	@Resource
	TeamInfoService teamInfoService;
	
	@Autowired
	@Qualifier("wegovSimpleNodeService")
	private WegovSimpleNodeService wegovSimpleNodeService;
	
	@Autowired
	@Qualifier("platformDao")
	private PlatformDao platformDao;
	
	private String username;
	private String password;
	
	private String stNodeName;
	private String stNodeId;
	

	@Action(value = "login", results = { @Result(name = SUCCESS, location = "/legislation/index.jsp"), @Result(name = LOGIN, location = "/legislation/login.jsp") })
	public String login() throws FzbDaoException {

		UserInfo currentPerson = new UserInfo();
		String account = request.getParameter("username");
		System.out.println("account:"+account);
		if(StringUtils.isEmpty(account)){
			return LOGIN;
		}
		String password = request.getParameter("password");
		System.out.println("password:"+password);
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
					request.setAttribute("isLfc", isLfc);

					session.setAttribute("isZhc", isZhc);/// 是否综合处
					session.setAttribute("isFzb", OrgUtils.isFzb(teamInfoService, unitCode));/// 是法制办
					if ("a".equals(password)) {
						request.setAttribute("message2", "请尽快修改您的初始密码！");
					}
					//当前人的角色
					String userRole="";
					List<OUR> ourList=platformDao.findOurListByUserId("MODULE_LEGISLATE", currentPerson.getUserId());
					if(ourList.size()>0) userRole=ourList.get(0).getType();

					System.out.println(unitName+"-"+unitCode+"-"+userRole);
					session.setAttribute("unitCode", unitCode);//
					session.setAttribute("unitName", unitName);//
					session.setAttribute("userRole", userRole);//

					//---当前人的菜单列表--
					Map<String, String> paramMap = new HashMap<String, String>();
					Map<String, Object> condMap = new HashMap<String, Object>();
					Map<String, String> sortMap = new LinkedHashMap<String, String>();
					condMap.put("stSubmitRole", userRole);
					condMap.put("stFlowName", "立法过程");
					sortMap.put("stNodeId", "ASC");
					List<WegovSimpleNode> nodeList = wegovSimpleNodeService.findByList(
							condMap, sortMap);
					System.out.println("查询到的节点个数：" + nodeList.size());
					request.setAttribute("nodeList", nodeList);
					
					
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getStNodeName() {
		return stNodeName;
	}

	public void setStNodeName(String stNodeName) {
		this.stNodeName = stNodeName;
	}

	public String getStNodeId() {
		return stNodeId;
	}

	public void setStNodeId(String stNodeId) {
		this.stNodeId = stNodeId;
	}
	
	
}
