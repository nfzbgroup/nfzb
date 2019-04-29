package com.wonders.fzb.checkmeeting.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeeting;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeetingTask;
import com.wonders.fzb.checkmeeting.services.LegislationCheckmeetingTaskService;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.legislation.beans.LegislationExample;
import com.wonders.fzb.legislation.beans.LegislationFiles;
import com.wonders.fzb.legislation.services.LegislationExampleService;
import com.wonders.fzb.legislation.services.LegislationFilesService;
import com.wonders.fzb.simpleflow.beans.WegovSimpleNode;
import com.wonders.fzb.simpleflow.services.WegovSimpleNodeService;

import dm.jdbc.util.StringUtil;

/**
 * LegislationCheckmeetingTask action接口
 * 
 * @author scalffold created by lj
 */

@Namespace("/legislationCheckmeetingTask")
@Controller
@Scope("prototype")
public class LegislationCheckmeetingTaskAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationCheckmeetingTaskService")
	private LegislationCheckmeetingTaskService legislationCheckmeetingTaskService;

	@Autowired
	@Qualifier("wegovSimpleNodeService")
	private WegovSimpleNodeService wegovSimpleNodeService;

	@Autowired
	@Qualifier("legislationExampleService")
	private LegislationExampleService legislationExampleService;

	@Autowired
	@Qualifier("legislationFilesService")
	private LegislationFilesService legislationFilesService;

	private int pageNo = 1;
	private int pageSize = 10;

	Page<LegislationCheckmeeting> infoPage;

	// LegislationCheckmeetingTask的修改
	@Action(value = "legislationCheckmeetingTask_add", results = { @Result(name = SUCCESS, location = "/LegislationCheckmeetingTask.jsp"), @Result(name = "List", location = "/legislationCheckmeetingTask_list.jsp") })
	public String legislationCheckmeetingTask_add() throws FzbDaoException {
//		System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<LegislationCheckmeetingTask> legislationCheckmeetingTaskList = new ArrayList<LegislationCheckmeetingTask>();
		LegislationCheckmeetingTask legislationCheckmeetingTask = new LegislationCheckmeetingTask();
		legislationCheckmeetingTaskService.add(legislationCheckmeetingTask);
		return SUCCESS;
	}

	// 所有模块的所有菜单的列表，统一一下请求，请不要改动。根据nodeid返回默认状态的任务列表。 lj
	@Actions({ @Action(value = "checkmeeting_task_list", results = { @Result(name = SUCCESS, location = "/checkmeeting/meeting_list.jsp"), 
			@Result(name = "QueryTable", location = "/checkmeeting/meeting_table.jsp") }) })
	public String listMethodManager() throws Exception {
		String methodStr = request.getParameter("method");
		if (StringUtil.isEmpty(methodStr)) {
			String stNodeId = request.getParameter("stNodeId");
			request.setAttribute("requestUrl", request.getRequestURI());
			request.setAttribute("nodeId", stNodeId);

			boolean isZhc = (boolean) session.getAttribute("isZhc");

			request.setAttribute("isZhc", isZhc);
			request.setAttribute("stTodoNameList", wegovSimpleNodeService.queryButtonInfo(stNodeId));
			return SUCCESS;
		} else {
			java.lang.reflect.Method method = this.getClass().getDeclaredMethod(methodStr);
			Object object = method.invoke(this);
			return object == null ? null : object.toString();
		}
	}

	/**
	 * table查询
	 *
	 * @return
	 */
	private String queryTable() throws ParseException {
		if ("NOD_0000000140".equals(request.getParameter("stNodeId")) || "NOD_0000000141".equals(request.getParameter("stNodeId")) || "NOD_0000000150".equals(request.getParameter("stNodeId")) || "NOD_0000000151".equals(request.getParameter("stNodeId"))) {
			queryTaskDetail();
		} else if ("NOD_0000000120".equals(request.getParameter("stNodeId")) || "NOD_0000000121".equals(request.getParameter("stNodeId")) || "NOD_0000000122".equals(request.getParameter("stNodeId")) || "NOD_0000000162".equals(request.getParameter("stNodeId"))) {
			queryUnitOpinion();
		} else if ("NOD_0000000170".equals(request.getParameter("stNodeId"))) {
			queryCheckMeeting();
		} else {
			queryDoc();
		}
		return "QueryTable";
	}

	private void queryCheckMeeting() throws ParseException {
		String pageSize = request.getParameter("pageSize");
		String pageNo = request.getParameter("pageNo");
		String stNodeId = request.getParameter("stNodeId");
		String taskStatus = request.getParameter("taskStatus");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String stMeetingName = request.getParameter("stMeetingName");

		if (null == pageSize || "".equals(pageSize)) {
			pageSize = "10";
		}
		if (null == pageNo || "".equals(pageNo)) {
			pageNo = "1";
		}

		WegovSimpleNode nodeInfo = wegovSimpleNodeService.findById(stNodeId);
		String baseSql = "WHERE 1=1 ";

		if (null != stNodeId && !"".equals(stNodeId)) {
			baseSql += "and t.st_node_Id = '" + stNodeId + "' ";
		}
		if (null != startTime && !"".equals(startTime)) {
			baseSql += "and c.DT_CREATE_DATE >= TO_DATE('" + startTime + "','yyyy-mm-dd')";
		}
		if (StringUtil.isNotEmpty(endTime)) {
			baseSql += "and c.DT_CREATE_DATE <= TO_DATE('" + endTime + "','yyyy-mm-dd')";
		}
		if (null != stMeetingName && !"".equals(stMeetingName)) {
			baseSql += "and c.st_meeting_name like '%" + stMeetingName + "%' ";
		}
		if (null != taskStatus && !"".equals(taskStatus)) {
			baseSql += "and t.st_task_status = '" + taskStatus + "' ";
		} else {
			baseSql += "and t.st_task_status = 'TODO' ";
		}
		baseSql += "and c.st_node_Id = '" + stNodeId + "'";
		baseSql += "and t.st_enable is null ";
		baseSql += "and t.st_team_Id = '" + session.getAttribute("unitCode") + "' ";

		String orderSql = " order by c.dt_create_date DESC";
		orderSql = "";
		//System.out.println("baseSql + orderSql------------" + baseSql + orderSql);
		infoPage = legislationCheckmeetingTaskService.findCheckMeetingByNodeId(baseSql + orderSql, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
 
 
		
		// List<LegislationCheckmeeting> resultList = new ArrayList<>();
		// List<LegislationCheckmeeting> docList = infoPage.getResult();
		// infoPage.setResult(resultList);
		if (StringUtil.isEmpty(taskStatus)) {
			request.setAttribute("buttonStatus", "TODO");
		} else {
			request.setAttribute("buttonStatus", taskStatus);
		}
		request.setAttribute("nodeInfo", nodeInfo);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("retPage", infoPage);
		request.setAttribute("nodeId", stNodeId);
	}

	private void queryUnitOpinion() throws ParseException {
		String pageSize = request.getParameter("pageSize");
		String pageNo = request.getParameter("pageNo");
		String stNodeId = request.getParameter("stNodeId");
		String opinionType = request.getParameter("opinionType");
		String taskStatus = request.getParameter("taskStatus");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String stMeetingName = request.getParameter("stMeetingName");

		if (null == pageSize || "".equals(pageSize)) {
			pageSize = "10";
		}
		if (null == pageNo || "".equals(pageNo)) {
			pageNo = "1";
		}

		WegovSimpleNode nodeInfo = wegovSimpleNodeService.findById(stNodeId);
		String baseSql = "WHERE 1=1 ";

		if (null != stNodeId && !"".equals(stNodeId)) {
			baseSql += "and t.st_node_Id = '" + stNodeId + "' ";
		}
		if (null != startTime && !"".equals(startTime)) {
			baseSql += "and t.DT_OPEN_DATE >= TO_DATE('" + startTime + "','yyyy-mm-dd')";
		}
		if (StringUtil.isNotEmpty(endTime)) {
			baseSql += "and t.DT_OPEN_DATE <= TO_DATE('" + endTime + "','yyyy-mm-dd')";
		}
		/*
		 * if (null != opinionType && !"".equals(opinionType)) { baseSql +=
		 * "and t.ST_BAK_ONE like '%" + opinionType + "%' "; }
		 */
		if (null != stMeetingName && !"".equals(stMeetingName)) {
			baseSql += "and t.st_flow_id like '%" + stMeetingName + "%' ";
		}
		if (null != taskStatus && !"".equals(taskStatus)) {
			baseSql += "and t.st_task_status = '" + taskStatus + "' ";
		} else {
			baseSql += "and t.st_task_status = 'TODO' ";
		}
		baseSql += "and t.st_enable is null ";
		if (stNodeId.equals("NOD_0000000122") || stNodeId.equals("NOD_0000000162")) {
			baseSql += "and t.st_parent_Id = '" + session.getAttribute("unitCode") + "' ";
		}

		String orderSql = " order by t.dt_open_date DESC";
		Page<LegislationCheckmeetingTask> infoPage = legislationCheckmeetingTaskService.findTaskByNodeId(baseSql + orderSql, Integer.parseInt(pageNo), Integer.parseInt(pageSize));

		if (StringUtil.isEmpty(taskStatus)) {
			request.setAttribute("buttonStatus", "TODO");
		} else {
			request.setAttribute("buttonStatus", taskStatus);
		}
		request.setAttribute("nodeInfo", nodeInfo);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("retPage", infoPage);
		request.setAttribute("nodeId", stNodeId);
	}

	private void queryTaskDetail() throws ParseException {
		String pageSize = request.getParameter("pageSize");
		String pageNo = request.getParameter("pageNo");
		String stNodeId = request.getParameter("stNodeId");
		String address = request.getParameter("address");
		String title = request.getParameter("title");
		String taskStatus = request.getParameter("taskStatus");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String stMeetingName = request.getParameter("stMeetingName");

		if (null == pageSize || "".equals(pageSize)) {
			pageSize = "10";
		}
		if (null == pageNo || "".equals(pageNo)) {
			pageNo = "1";
		}

		WegovSimpleNode nodeInfo = wegovSimpleNodeService.findById(stNodeId);
		String baseSql = "WHERE 1=1 ";

		if (null != stNodeId && !"".equals(stNodeId)) {
			baseSql += "and t.st_node_Id = '" + stNodeId + "' ";
		}
		/*
		 * if (null != startTime && !"".equals(startTime)) { baseSql +=
		 * "and t.DT_BAK_DATE >= TO_DATE('" + startTime + "','yyyy-mm-dd')"; } if
		 * (StringUtil.isNotEmpty(endTime)) { baseSql +=
		 * "and t.DT_BAK_DATE <= TO_DATE('" + endTime + "','yyyy-mm-dd')"; }
		 */
		if (null != stMeetingName && !"".equals(stMeetingName)) {
			baseSql += "and t.st_flow_id like '%" + stMeetingName + "%' ";
		}
		if (null != taskStatus && !"".equals(taskStatus)) {
			baseSql += "and t.st_task_status = '" + taskStatus + "' ";
		} else {
			baseSql += "and t.st_task_status = 'TODO' ";
		}
		baseSql += "and t.st_enable is null ";
		baseSql += "and t.st_team_Id = '" + session.getAttribute("unitCode") + "' ";

		String orderSql = " order by t.dt_open_date DESC";
		Page<LegislationCheckmeetingTask> infoPage = legislationCheckmeetingTaskService.findTaskByNodeId(baseSql + orderSql, Integer.parseInt(pageNo), Integer.parseInt(pageSize));

		if (StringUtil.isEmpty(taskStatus)) {
			request.setAttribute("buttonStatus", "TODO");
		} else {
			request.setAttribute("buttonStatus", taskStatus);
		}
		request.setAttribute("nodeInfo", nodeInfo);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("retPage", infoPage);
		request.setAttribute("nodeId", stNodeId);
	}

	private void queryDoc() throws ParseException {
		boolean isZhc = (boolean) session.getAttribute("isZhc");

		String pageSize = request.getParameter("pageSize");
		String pageNo = request.getParameter("pageNo");
		String stNodeId = request.getParameter("stNodeId");
		String stUserName = request.getParameter("stUserName");
		String taskStatus = request.getParameter("taskStatus");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String stMeetingName = request.getParameter("stMeetingName");

		if (null == pageSize || "".equals(pageSize)) {
			pageSize = "10";
		}
		if (null == pageNo || "".equals(pageNo)) {
			pageNo = "1";
		}

		WegovSimpleNode nodeInfo = wegovSimpleNodeService.findById(stNodeId);
		String baseSql = "WHERE 1=1 ";

		if (null != stNodeId && !"".equals(stNodeId)) {
			baseSql += "and t.st_node_Id = '" + stNodeId + "' ";
		}
		if (null != startTime && !"".equals(startTime)) {
			baseSql += "and c.DT_CREATE_DATE >= TO_DATE('" + startTime + "','yyyy-mm-dd')";
		}
		if (StringUtil.isNotEmpty(endTime)) {
			baseSql += "and c.DT_CREATE_DATE <= TO_DATE('" + endTime + "','yyyy-mm-dd')";
		}
		if (null != stUserName && !"".equals(stUserName)) {
			baseSql += "and c.ST_USER_NAME like '%" + stUserName + "%' ";
		}
		if (null != stMeetingName && !"".equals(stMeetingName)) {
			baseSql += "and c.st_meeting_name like '%" + stMeetingName + "%' ";
		}
		if (null != taskStatus && !"".equals(taskStatus)) {
			baseSql += "and t.st_task_status = '" + taskStatus + "' ";
		} else {
			if ("NOD_0000000103".equals(stNodeId)) {
				if (isZhc) {
					baseSql += "and t.st_task_status = 'DOING' ";
				} else {
					baseSql += "and t.st_task_status = 'TODO' ";
				}
			} else {
				baseSql += "and t.st_task_status = 'TODO' ";
			}
		}
//        baseSql += "and c.st_node_Id = 'NOD_0000000101' ";
		baseSql += "and t.st_enable is null ";
		if ("NOD_0000000101".equals(stNodeId)) {
			baseSql += "and t.st_team_Id = '" + session.getAttribute("unitCode") + "' ";
		}
		if ("NOD_0000000103".equals(stNodeId)) {
			if (!isZhc) {
				baseSql += "and t.st_deal_Id = '" + session.getAttribute("unitCode") + "' ";
			}
		}

		String orderSql = " order by c.dt_create_date DESC";
		infoPage = legislationCheckmeetingTaskService.findTaskDocListByNodeId(baseSql + orderSql, Integer.parseInt(pageNo), Integer.parseInt(pageSize));

		if (StringUtil.isEmpty(taskStatus)) {
			if ("NOD_0000000103".equals(stNodeId)) {
				if (isZhc) {
					request.setAttribute("buttonStatus", "DOING");
				} else {
					request.setAttribute("buttonStatus", "TODO");
				}
			} else {
				request.setAttribute("buttonStatus", "TODO");
			}
		} else {
			request.setAttribute("buttonStatus", taskStatus);
		}
		if (stNodeId.equals("NOD_0000000104")) {
			if (infoPage.getResult() != null && infoPage.getResult().size() > 0) {
				List<LegislationCheckmeeting> list = new ArrayList<>();
				for (LegislationCheckmeeting LegislationCheckmeeting : infoPage.getResult()) {
					String docId = LegislationCheckmeeting.getStMeetingId();
					List<LegislationCheckmeetingTask> tasks = legislationCheckmeetingTaskService.findByHQL("from LegislationCheckmeetingTask t where t.stMeetingId='" + docId + "' and t.stNodeId='NOD_0000000104'");
					LegislationCheckmeetingTask legislationCheckmeetingTask = tasks.get(0);
					/*
					 * if (legislationCheckmeetingTaskdetailService.
					 * findByHQL("from LegislationCheckmeetingTaskdetail t where 1=1 and t.stTaskId='"
					 * + legislationCheckmeetingTask.getStTaskId() +
					 * "' and t.stTaskStatus='TODO-RETURN'").size() > 0) {
					 * LegislationCheckmeeting.setHasReturn(true); }
					 */
					list.add(LegislationCheckmeeting);
				}
				infoPage.setResult(list);
			}
		}
		request.setAttribute("isZhc", isZhc);
		request.setAttribute("nodeInfo", nodeInfo);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("retPage", infoPage);
		request.setAttribute("nodeId", stNodeId);
	}

	/**
	 * 主节点流转（共用）
	 *
	 * @return
	 * @throws FzbDaoException
	 */
	private String nextCheckmeeting() throws FzbDaoException, IOException {
		String stMeetingId = request.getParameter("stMeetingId");
		String stNodeId = request.getParameter("stNodeId");
		String buttonId = request.getParameter("buttonId");
		legislationCheckmeetingTaskService.nextCheckmeeting(stMeetingId, stNodeId, session);
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String teamId = currentPerson.getTeamInfos().get(0).getId();
		Boolean removeDisabled = false;
		if ("expertBefore".equals(buttonId) && "U_3_7".equals(teamId)) {
			removeDisabled = true;
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("removeDisabled", removeDisabled);
		jsonObject.put("success", true);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonObject);
		return null;
	}

	/**
	 * 退回（共用）
	 * 
	 * @return
	 * @throws FzbDaoException
	 */
	private String returnCheckmeeting() throws FzbDaoException {
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String userRoleId = session.getAttribute("userRoleId").toString();
		String userRole = session.getAttribute("userRole").toString();

		String stMeetingId = request.getParameter("stMeetingId");
		String stNodeId = request.getParameter("stNodeId");
		legislationCheckmeetingTaskService.returnCheckmeeting(stMeetingId, stNodeId, userRoleId, userRole, currentPerson);
		return null;
	}

	/**
	 * 次节点流转（公共）
	 *
	 * @return
	 * @throws FzbDaoException
	 */
	private String nextChildCheckmeeting() throws FzbDaoException, IOException {
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String userRoleId = session.getAttribute("userRoleId").toString();
		String userRole = session.getAttribute("userRole").toString();
		String stMeetingId = request.getParameter("stMeetingId");
		String stNodeId = request.getParameter("stNodeId");

		legislationCheckmeetingTaskService.nextChildCheckmeeting(stMeetingId, stNodeId, userRoleId, userRole, currentPerson);

		String buttonId = request.getParameter("buttonId");
		String teamId = currentPerson.getTeamInfos().get(0).getId();
		JSONObject jsonObject = new JSONObject();
		Boolean removeDisabled = false;
		Boolean addDisabled = false;
		if ("legislationReceive".equals(buttonId) && "U_3_1".equals(teamId)) {
			removeDisabled = true;
			addDisabled = true;
		}
		if ("legislationCensorship".equals(buttonId) && "U_3_1".equals(teamId)) {
			removeDisabled = true;
		}
		if ("legislationRelease".equals(buttonId) && "U_3_1".equals(teamId)) {
			removeDisabled = true;
		}
		if ("legislationRegistration".equals(buttonId) && "U_3_1".equals(teamId)) {
			removeDisabled = true;
		}
		if ("onlineSeal".equals(buttonId) && "U_3_1".equals(teamId)) {
			removeDisabled = true;
			addDisabled = true;
		}
		if ("onlineCensorship".equals(buttonId) && "U_3_1".equals(teamId)) {
			removeDisabled = true;
		}
		if ("onlineRelease".equals(buttonId) && "U_3_1".equals(teamId)) {
			removeDisabled = true;
		}
		if ("onlineSummary".equals(buttonId) && "U_3_1".equals(teamId)) {
			removeDisabled = true;
		}
		jsonObject.put("success", true);
		jsonObject.put("removeDisabled", removeDisabled);
		jsonObject.put("addDisabled", addDisabled);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonObject);
		return null;
	}

	public Page<LegislationCheckmeeting> getInfoPage() {
		return infoPage;
	}

	public void setInfoPage(Page<LegislationCheckmeeting> infoPage) {
		this.infoPage = infoPage;
	}

}
