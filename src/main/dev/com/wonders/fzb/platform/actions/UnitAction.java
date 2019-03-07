package com.wonders.fzb.platform.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.components.tree.beans.TreeNode;
import com.wonders.fzb.framework.beans.TeamInfo;
import com.wonders.fzb.framework.services.TeamInfoService;
//import com.wonders.fzb.zfry.module.execlaw.services.ExeclawUnitService;

import net.sf.json.JSONArray;

@Namespace("/unit")
@Controller
@Scope("prototype")
public class UnitAction extends BaseAction {

	private static final long serialVersionUID = -8464669963819426487L;

	private String initTree;

	private String id;
	
//	@Autowired
//	@Qualifier("execlawUnitService")
//	ExeclawUnitService execlawUnitService;
	
	@Autowired
	@Qualifier("teamInfoService")
	TeamInfoService teamInfoService;

	@Action(value = "tree", results = {@Result(name = SUCCESS, location = "/execlaw/WeGovPlatform/admin/user/user_left.jsp")})
	public String execute() {
		//父级树
		List<TreeNode> trees = new LinkedList<TreeNode>();
		TreeNode baseNode = new TreeNode();
		baseNode.setId("U0");
		baseNode.setIsParent(true);
		baseNode.setLabel("市法制办");
		trees.add(baseNode);
		initTree = JSONArray.fromObject(trees).toString();

		return SUCCESS;
	}

	//点击前方加号触发时间
	@Action(value = "load")
	public void load() throws IOException {
		List<TreeNode> trees = new LinkedList<TreeNode>();
		

		List<TeamInfo> teams = teamInfoService.findTeamInfoSubList("MODULE_EXECLAW", id);
		
		if(null != teams && teams.size() > 0){
			for(TeamInfo teamInfo : teams){
				TreeNode teamNode = new TreeNode();
				teamNode.setId(teamInfo.getId());//
				teamNode.setLabel(teamInfo.getTeamName());
				teamNode.setPid(id);
				
				List<TeamInfo> checkChilds = teamInfoService.findTeamInfoSubList("MODULE_EXECLAW",teamInfo.getId());
				//判断拿到的单位列表是否是最节点 如果不是添加+号
				teamNode.setIsParent(null != checkChilds && checkChilds.size() > 0);
				trees.add(teamNode);
			}
		}
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(JSONArray.fromObject(trees).toString());
		out.flush();
	}
	
//	@Action(value = "certificate_unit", results = {@Result(name = SUCCESS, location = "/execlaw/execlaw/statistics/certificate.jsp")})
//	public String certificate_unit() {
//		//父级树
//		List<TreeNode> trees = new LinkedList<TreeNode>();
//
//		trees = execlawUnitService.unitTreeLoad(" u.unitid = '" + getLoginUser().getTeamInfos().get(0).getId() + "' "  );
//		
//		initTree = JSONArray.fromObject(trees).toString();
//		
//		return SUCCESS;
//	}
	
//	@Action(value = "certificate_load")
//	public void certificate_load() throws IOException {
//		List<TreeNode> trees = new LinkedList<TreeNode>();
//		trees = execlawUnitService.unitTreeLoad(" u.up_unitid = '" + id + "' "  );
//		response.setContentType("application/json;charset=UTF-8");
//		response.setCharacterEncoding("UTF-8");
//		PrintWriter out = response.getWriter();
//		out.print(JSONArray.fromObject(trees).toString());
//		out.flush();
//	}

	public String getInitTree() {
		return initTree;
	}

	public void setInitTree(String initTree) {
		this.initTree = initTree;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}