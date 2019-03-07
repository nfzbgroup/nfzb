package com.wonders.fzb.legislation.web;

import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.legislation.beans.LegislationProcessDoc;
import com.wonders.fzb.legislation.beans.LegislationProcessTask;
import com.wonders.fzb.legislation.services.LegislationProcessDocService;
import com.wonders.fzb.legislation.services.LegislationProcessTaskService;
import com.wonders.fzb.simpleflow.beans.WegovSimpleNode;
import com.wonders.fzb.simpleflow.services.WegovSimpleNodeService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

//import com.wonders.fzb.zfry.module.platform.beans.TeamInfo;
//import com.wonders.fzb.zfry.module.platform.beans.UserInfo;

/**
 * LegislationProcessDoc action接口
 * @author scalffold created by lj
 */
 
@Namespace("/legislationProcessDoc")
@Controller
@Scope("prototype")
public class LegislationProcessDocAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationProcessDocService")
	private LegislationProcessDocService legislationProcessDocService;

	
	
	@Autowired
	@Qualifier("legislationProcessTaskService")
	private LegislationProcessTaskService legislationProcessTaskService;
	
	@Autowired
	@Qualifier("wegovSimpleNodeService")
	private WegovSimpleNodeService wegovSimpleNodeService;
	
	
	private int pageNo = 1;
	private int pageSize = 10;


	//LegislationProcessDoc的测试
	@Action(value = "legislationProcessDoc_add", results = {@Result(name = SUCCESS, location = "/LegislationProcessDoc.jsp"), @Result(name = "List", location = "/legislationProcessDoc_list.jsp")})
	public String legislationProcessDoc_add() throws FzbDaoException {
//		System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<LegislationProcessDoc> legislationProcessDocList = new ArrayList<LegislationProcessDoc>();
		LegislationProcessDoc legislationProcessDoc = new LegislationProcessDoc();
		legislationProcessDocService.add(legislationProcessDoc);
		return SUCCESS;
	}

	@Action(value = "legislationProcessDoc_form", results = {@Result(name = SUCCESS, location = "/legislation/legislationProcess_form.jsp")})
	public String legislationProcessDoc_form() throws FzbDaoException {
		String stDocId=request.getParameter("stDocId");
		String type=request.getParameter("type");
		String stNodeId = request.getParameter("stNodeId");
		request.setAttribute("nodeId",stNodeId);
		request.setAttribute("stDocId",stDocId);
		request.setAttribute("type",type);
		return SUCCESS;
	}

	@RequestMapping("legislationProcessDoc_save")
	@ResponseBody
	public Map legislationProcessDoc_save(){
		Map map=new HashMap();
		map.put("success",true);
		map.put("message","发布草案成功");
		return map;
	}

	//LegislationProcessDoc的处理
	@Action(value = "draft_create_info", results = {
			@Result(name = SUCCESS, location = "/LegislationProcessDoc.jsp"), 
			@Result(name = "LIST", location = "/legislationProcessDoc_list.jsp")})
	public String draft_create_info() throws FzbDaoException {
//		System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String action = request.getParameter("action");
		String taskId = request.getParameter("taskId");
		String docId = request.getParameter("docId");
		String docName = request.getParameter("docName");
		String stComent = request.getParameter("stComent");
		String stNodeId=request.getParameter("stNodeId");
		String stNodeName=request.getParameter("stNodeName");
		LegislationProcessDoc legislationProcessDoc = new LegislationProcessDoc();
		legislationProcessDoc.setStDocName(docName);
		legislationProcessDoc.setStComent(stComent);
		legislationProcessDoc.setStNodeId(stNodeId);
		legislationProcessDoc.setStNodeName(stNodeName);
		legislationProcessDoc.setDtCreateDate(new Date());
		if("add".equals(action)){
			String stDocId=legislationProcessDocService.addObj(legislationProcessDoc);
			LegislationProcessTask newTask= new LegislationProcessTask();
			newTask.setStDocId(stDocId);
			newTask.setStFlowId(docName);
			newTask.setStNodeId(stNodeId);
			newTask.setStNodeName(stNodeName);
			newTask.setStTaskStatus("TODO");
			newTask.setDtOpenDate(new Date());
			UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
			newTask.setStUserId(currentPerson.getUserId());
			newTask.setStUserName(currentPerson.getName());
			newTask.setStRoleId(session.getAttribute("userRole").toString());
			newTask.setStRoleName(session.getAttribute("userRole").toString());
			newTask.setStTeamId((currentPerson.getTeamInfos().get(0)).getId());
			newTask.setStTeamName((currentPerson.getTeamInfos().get(0)).getTeamName());
			legislationProcessTaskService.add(newTask);
		}
		else if("edit".equals(action)){
			legislationProcessDoc.setStDocId(docId);
			legislationProcessDoc.setStDocName(docName);
			legislationProcessDoc.setStComent(stComent);
			legislationProcessDocService.update(legislationProcessDoc);
		}
		else if("info".equals(action)){
			legislationProcessDoc=legislationProcessDocService.findById(docId);
			request.setAttribute("docInfo", legislationProcessDoc);
		}
		else if("submit".equals(action)){
			legislationProcessDoc=legislationProcessDocService.findById(docId);
			LegislationProcessTask legislationProcessTask = legislationProcessTaskService.findById(taskId);
			legislationProcessTask.setStTaskStatus("DONE");
			WegovSimpleNode node = wegovSimpleNodeService.findById(legislationProcessTask.getStNodeId());
			WegovSimpleNode nextNode = wegovSimpleNodeService.findById(node.getStNextNode());
			LegislationProcessTask newTask= new LegislationProcessTask();
			newTask.setStDocId(docId);
			newTask.setStRoleId(nextNode.getStSubmitRole());
			newTask.setStNodeId(nextNode.getStNodeId());
			newTask.setStNodeName(nextNode.getStNodeName());
			legislationProcessTaskService.add(newTask);
			request.setAttribute("actionResult", "submit_ok");
		}
		return SUCCESS;
	}

}
