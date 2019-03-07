package com.wonders.fzb.framework.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.framework.beans.TeamInfo;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.framework.beans.vo.TeamTreeNode;
import com.wonders.fzb.framework.beans.vo.UserInfoBean;
import com.wonders.fzb.framework.consts.PlatformConsts;
import com.wonders.fzb.framework.services.TeamInfoService;
import com.wonders.fzb.framework.services.UserInfoService;

import net.sf.json.JSONArray;

@Namespace("/admin")
@Controller
@Scope("prototype")
public class PlatformAction extends BaseAction {

	private static final long serialVersionUID = -9124826379456265421L;

	@Autowired
	@Qualifier("teamInfoService")
	private TeamInfoService teamService;
	
	@Autowired
	@Qualifier("userInfoService")
	private UserInfoService userService;
	
	private UserInfo userInfo;
	
	@Action(value = "/admin/admin_login", results = {
			@Result(name = SUCCESS, location = "/admin/simple_add.jsp")
	})
	public String superAdminLogin() {
		
		return SUCCESS;
	}
	
	@Action(value = "/admin/dispatcher", results = {
			@Result(name = "CONSOLE_USR_LIST", location = "user/to_list",type = "redirectAction"),
			@Result(name = "CONSOLE_TEAM_LIST", location = "/admin/team/list.jsp",type="redirectAction")
	})
	public String dispatcher() {
		String action = request.getParameter("p");
		if(null == action || action.equals("module") || action.equals("system")){
			return "";
		}else if(null != action && (action.equals("team") || action.equals("org") || action.equals("unit"))){
			return "CONSOLE_TEAM_LIST";
		}else if(null != action && (action.equals("user") || action.equals("person") || action.equals("password"))){
			return "CONSOLE_USR_LIST";
		}
		return "CONSOLE_USR_LIST";
	}

	@Action(value = "/admin/user/user_add", results = {@Result(name = SUCCESS, type="redirectAction", location = "to_add")})
	public String execute() {
		request.setAttribute("teamList",  teamService.findTeamInfoList(null, null));
		String moduleId = request.getParameter("moduleId");
		String teamId = request.getParameter("teamId");
		userInfo.setSex(1); //默认值
		userInfo.setEmail("admin@wonders.com");
		//增加用户
		for(String id : teamId.split(",")){
			userService.addUser(moduleId, id, userInfo);
		}
		
		//清空表单数据
		userInfo = new UserInfo();
		return SUCCESS;
	}
	
	@Action(value = "/admin/user/to_list", results = {@Result(name = SUCCESS, location = "/admin/user/list.jsp")})
	public String toUserList() {
		//初始化数据
		LinkedHashMap<String,String> moduleMap = new LinkedHashMap<String, String>();
		moduleMap.put("全部","");
		moduleMap.put("具体行政行为报送系统", PlatformConsts.BAHIVOR_MODULE_ID);
		moduleMap.put("规范性文件上报系统", PlatformConsts.NORMALDOC_MODULE_ID);
		moduleMap.put("执法人员信息管理系统", PlatformConsts.EXECLAW_MODULE_ID);
		moduleMap.put("行政复议", PlatformConsts.REVIEW_MODULE_ID);
		
		LinkedHashMap<String,String> accountStatusMap = new LinkedHashMap<String, String>();
		accountStatusMap.put("全部", "");
		accountStatusMap.put("有效", "y");
		accountStatusMap.put("停用", "n");
		
		//页面传递值接受
		String condModule = request.getParameter("condModule");
		String accountStatus = request.getParameter("accountStatus");
		String account = request.getParameter("account");
		String unit = request.getParameter("unit");
		String contact = request.getParameter("contact");
		String phone = request.getParameter("phone");
		
		//条件拼装
		String baseSql = "WHERE 1=1 ";
		if(null != condModule && !"".equals(condModule)){
			baseSql += "and sm.name like '%"+condModule+"%' ";
		}
		if(null != accountStatus && !"".equals(accountStatus)){
			baseSql += "and usr.accountStatus = '"+accountStatus+"' ";
		}
		if(null != account && !"".equals(account)){
			baseSql += "and usr.account like '%"+account+"%' ";
		}
		if(null != unit && !"".equals(unit)){
			baseSql += "and team.unitname like '%"+unit+"%' ";
		}
		if(null != contact && !"".equals(contact)){
			baseSql += "and usr.abbr like '%"+contact+"%' ";
		}
		if(null != phone && !"".equals(phone)){
			baseSql += "and usr.phone like '%"+phone+"%' ";
		}
		
		String orderSql = " order by account ";
		Page<UserInfoBean> userPage = userService.findUserListView(baseSql + orderSql, 0, 0);
		
		//数据填充传递
		request.setAttribute("account", account);
		request.setAttribute("unit", unit);
		request.setAttribute("contact", contact);
		request.setAttribute("phone", phone);
		request.setAttribute("condModule", condModule);
		request.setAttribute("accountStatus", accountStatus);
		request.setAttribute("moduleMap", moduleMap);
		request.setAttribute("accountStatusMap", accountStatusMap);
		request.setAttribute("naviga", "user");
		request.setAttribute("userList",userPage);
		return SUCCESS;
	}
	
	@Action(value = "/admin/user/to_add", results = {@Result(name = SUCCESS, location = "/admin/user/add.jsp")})
	public String toUserAdd() {
		Map<String,String> moduleMap = new LinkedHashMap<String, String>();
		moduleMap.put(PlatformConsts.BAHIVOR_MODULE_ID, "具体行政行为报送系统");
		moduleMap.put(PlatformConsts.NORMALDOC_MODULE_ID, "规范性文件上报系统");
		moduleMap.put(PlatformConsts.REVIEW_MODULE_ID, "行政复议系统");
		
		request.setAttribute("moduleList", moduleMap);
		request.setAttribute("teamList",  teamService.findTeamInfoList(null, null));
		
		return SUCCESS;
	}
	
	@Action(value = "/admin/user/remove")
	public void userRemove() {
		String ourId = request.getParameter("ourId");
		
		userService.removeOur(ourId);
	}
	
	@Action(value = "/admin/user/delete")
	public void userDelete() {
		String userId = request.getParameter("userId");
		
	}
	
	@Action(value = "/admin/team/async")
	public void async(){
		String id = request.getParameter("id");
		String param = request.getParameter("param");
		System.out.println(id+","+param);
		List<TeamInfo> teams = teamService.findTeamInfoSubList(PlatformConsts.BAHIVOR_MODULE_ID, id);
		ArrayList<TeamTreeNode> nodes = new ArrayList<TeamTreeNode>();
		for(TeamInfo team : teams){
			TeamTreeNode node = new TeamTreeNode();
			node.setId(team.getId());
			node.setName(team.getUnitName());
			node.setPid(id);
			node.setAlt(team.getTeamName());
			node.setIsParent(teamService.findTeamInfoSubList(PlatformConsts.BAHIVOR_MODULE_ID, team.getId()).size() > 0);
			node.setNocheck(false);
			nodes.add(node);
		}
		
		try {
			response.getWriter().println(JSONArray.fromObject(nodes).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
}