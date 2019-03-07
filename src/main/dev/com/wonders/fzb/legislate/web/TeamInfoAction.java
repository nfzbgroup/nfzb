package com.wonders.fzb.legislate.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wonders.fzb.base.actions.FileManageAction;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.vo.UserInfoBean;
import com.wonders.fzb.framework.services.TeamInfoService;
import com.wonders.fzb.legislate.beans.vo.TeamInfoBean;
import com.wonders.fzb.legislate.beans.vo.TeamTreeNode;
import com.wonders.fzb.legislate.services.CommonService;

/**
 * 
 * 立法平台-组织机构-用于(审核会议 选择人员,单位意见选择单位)
 * @author FZB
 *
 */
@Namespace("/legislate")
@Controller("Controller01")
@Scope("prototype")
public class TeamInfoAction extends FileManageAction {
	
	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	
	protected List<TeamInfoBean> teams;
	
	protected String state;
	
	protected String teamOpinionId;
	@Autowired
	CommonService commonService;
	
	@Autowired
	TeamInfoService teamInfoService;
	
	

	
	
	@Action(value = "meetingOrgList" , results = {
			@Result(name = "auditMeeting_add", location = "/legislate/auditMeeting/shhy_txl.jsp"),
			@Result(name = "teamOpinion_list", location = "/legislate/teamOpinion/dwyj-txl.jsp")
		})
	public String meetingOrgList() throws FzbDaoException {

		//TODO 需要修改成异步加载
		
/*		List<TeamInfoBean> teams = commonService.findTeamInfoList("MODULE_LEGISLATE", "市法制办处室");
		ArrayList<TeamTreeNode> nodes = new ArrayList<TeamTreeNode>();
		for(TeamInfoBean team : teams){
			TeamTreeNode node = new TeamTreeNode();
			node.setId(team.getOrgId());
			node.setName(team.getUnitName());
			node.setpId(team.getOrgPid());
			node.setIsParent(true);
			node.setNocheck(false);
			nodes.add(node);
			
			//TODO 写死处长  主任 需要修改
			List<UserInfoBean> userLists = commonService.findUserInfoListForOrgId("MODULE_LEGISLATE",team.getMorId());
			for(UserInfoBean userInfo : userLists){
				TeamTreeNode a = new TeamTreeNode();
				a.setId(userInfo.getUserId());
				a.setName(userInfo.getName());
				a.setpId(team.getOrgId());
				a.setIsParent(false);
				a.setNocheck(false);
				nodes.add(a);
			}
		}*/
		
		teams  = commonService.findTeamInfoList("MODULE_LEGISLATE", "市法制办处室");
		
		//teamInfoService.findMorSubList("MODULE_LEGISLATE", "U0");
		for(TeamInfoBean team : teams){

			
			//TODO 写死处长  主任 需要修改
			List<UserInfoBean> userLists = commonService.findUserInfoListForOrgId("MODULE_LEGISLATE",team.getMorId());
			System.out.println(userLists.size());
			team.setList(userLists);
			
		}
		if("auditMeeting_add".equals(state)){ 
			return "auditMeeting_add";
		}else{
			return "teamOpinion_list";
		}
	}
	
	@Action(value = "teamOpinionOrgList")
	public void teamOpinionOrgList() throws FzbDaoException {

		//TODO 需要修改成异步加载
		
		List<TeamInfoBean> teams = commonService.findTeamInfoList("MODULE_LEGISLATE", "市法制办处室");
		ArrayList<TeamTreeNode> nodes = new ArrayList<TeamTreeNode>();
		for(TeamInfoBean team : teams){
			TeamTreeNode node = new TeamTreeNode();
			node.setId(team.getOrgId());
			node.setName(team.getUnitName());
			node.setpId(team.getOrgPid());
			node.setIsParent(true);
			node.setNocheck(false);
			nodes.add(node);
		}
		
		try {
			response.getWriter().println(JSONArray.fromObject(nodes).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<TeamInfoBean> getTeams() {
		return teams;
	}

	public void setTeams(List<TeamInfoBean> teams) {
		this.teams = teams;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTeamOpinionId() {
		return teamOpinionId;
	}

	public void setTeamOpinionId(String teamOpinionId) {
		this.teamOpinionId = teamOpinionId;
	}
	
	
	
}
