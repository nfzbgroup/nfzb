package com.wonders.fzb.simpleflow.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislation.beans.LegislationProcessDoc;
import com.wonders.fzb.simpleflow.beans.*;
import com.wonders.fzb.simpleflow.services.*;
//import com.wonders.fzb.zfry.module.platform.beans.TeamInfo;
//import com.wonders.fzb.zfry.module.platform.beans.UserInfo;

/**
 * WegovSimpleNode action接口
 * 
 * @author scalffold created by lj
 */

@Namespace("/wegovSimpleNode")
@Controller
@Scope("prototype")
public class WegovSimpleNodeAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("wegovSimpleNodeService")
	private WegovSimpleNodeService wegovSimpleNodeService;

	private int pageNo = 1;
	private int pageSize = 10;
	List<WegovSimpleNode> nodeList;
	String stNodeName;
	String stNodeId;

	// WegovSimpleNode的修改
	@Action(value = "wegovSimpleNode_add", results = {
			@Result(name = SUCCESS, location = "/WegovSimpleNode.jsp"),
			@Result(name = "List", location = "/wegovSimpleNode_list.jsp") })
	public String wegovSimpleNode_add() throws FzbDaoException {
		// System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<WegovSimpleNode> wegovSimpleNodeList = new ArrayList<WegovSimpleNode>();
		WegovSimpleNode wegovSimpleNode = new WegovSimpleNode();
		wegovSimpleNode.setId(Integer.parseInt("666"));
		wegovSimpleNodeService.add(wegovSimpleNode);
		return SUCCESS;
	}

	@Action(value = "wegovSimpleNode_list", results = { @Result(name = SUCCESS, location = "/legislation/index.jsp") })
	// @ResponseBody
	public String choose_plan_list() throws FzbDaoException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String pageSize = request.getParameter("pageSize");
		String pageNo = request.getParameter("pageNo");
		String stNodeId = request.getParameter("stNodeId");
		Map<String, String> paramMap = new HashMap<String, String>();
		Map<String, Object> condMap = new HashMap<String, Object>();
		Map<String, String> sortMap = new LinkedHashMap<String, String>();
		// 正常参加
		condMap.put("stFlowName", "立法过程");
		sortMap.put("stFlowName", "ASC");
		nodeList = wegovSimpleNodeService.findByList(
				condMap, sortMap);
		System.out.println("查询到的节点个数：" + nodeList.size());
		request.setAttribute("retPage", nodeList);
		return SUCCESS;
	}

	public List<WegovSimpleNode> getNodeList() {
		return nodeList;
	}

	public void setNodeList(List<WegovSimpleNode> nodeList) {
		this.nodeList = nodeList;
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
