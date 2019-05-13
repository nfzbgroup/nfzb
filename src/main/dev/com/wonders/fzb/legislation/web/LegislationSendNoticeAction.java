package com.wonders.fzb.legislation.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.legislation.beans.*;
import com.wonders.fzb.legislation.services.*;
import com.wonders.fzb.simpleflow.beans.WegovSimpleNode;
import com.wonders.fzb.simpleflow.services.WegovSimpleNodeService;

import dm.jdbc.util.StringUtil;

/**
 * LegislationSendNotice action接口
 * 
 * @author scalffold created by lj
 */

@Namespace("/legislationSendNotice")
@Controller
@Scope("prototype")
public class LegislationSendNoticeAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationSendNoticeService")
	private LegislationSendNoticeService legislationSendNoticeService;

	@Autowired
	@Qualifier("wegovSimpleNodeService")
	private WegovSimpleNodeService wegovSimpleNodeService;

	private int pageNo = 1;
	private int pageSize = 10;

	Page<SendNoticeVO> infoPage;

	// LegislationSendNotice的修改
	@Action(value = "legislationSendNotice_add", results = { @Result(name = SUCCESS, location = "/LegislationSendNotice.jsp"), @Result(name = "List", location = "/legislationSendNotice_list.jsp") })
	public String legislationSendNotice_add() throws FzbDaoException {
		// System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<LegislationSendNotice> legislationSendNoticeList = new ArrayList<LegislationSendNotice>();
		LegislationSendNotice legislationSendNotice = new LegislationSendNotice();
		legislationSendNoticeService.add(legislationSendNotice);
		return SUCCESS;
	}

	// 草案中，获取立法过程中意见与会签的各单位的任务。 lj
	@Actions({ @Action(value = "process_depart_notice_list", results = { @Result(name = SUCCESS, location = "/legislation/process_depart_notice_list.jsp"),
			@Result(name = "QueryTable", location = "/legislation/process_depart_notice_table.jsp") }) })
	public String listMethodManager() throws Exception {
		String method = request.getParameter("method");
		if ("list".equals(method)) {
			String stNodeId = request.getParameter("stNodeId");
			request.setAttribute("requestUrl", request.getRequestURI());
			request.setAttribute("nodeId", stNodeId);
			boolean isZhc = (boolean) session.getAttribute("isZhc");
			request.setAttribute("isZhc", isZhc);
			request.setAttribute("stTodoNameList", wegovSimpleNodeService.queryButtonInfo(stNodeId));
			return SUCCESS;
		} else {
			boolean isZhc = (boolean) session.getAttribute("isZhc");
			String pageSize = request.getParameter("pageSize");
			String pageNo = request.getParameter("pageNo");
			String stNodeId = request.getParameter("stNodeId");
			String stUserName = request.getParameter("stUserName");
			String taskStatus = request.getParameter("taskStatus");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			String stDocName = request.getParameter("stDocName");

			if (null == pageSize || "".equals(pageSize)) {
				pageSize = "10";
			}
			if (null == pageNo || "".equals(pageNo)) {
				pageNo = "1";
			}

			WegovSimpleNode nodeInfo = wegovSimpleNodeService.findById(stNodeId);
			String baseSql = "WHERE 1=1 ";

			// if (null != stNodeId && !"".equals(stNodeId)) {
			baseSql += "and t.ST_MODEL_NAME = '立法过程' and t.ST_NODE_NAME='" + nodeInfo.getStNodeName() + "'  ";
			// }
			if (null != startTime && !"".equals(startTime)) {
				baseSql += "and t.dt_open_date >= TO_DATE('" + startTime + "','yyyy-mm-dd')";
			}
			if (StringUtil.isNotEmpty(endTime)) {
				baseSql += "and t.dt_open_date <= TO_DATE('" + endTime + "','yyyy-mm-dd')";
			}
			if (null != stUserName && !"".equals(stUserName)) {
				baseSql += "and d.ST_USER_NAME like '%" + stUserName + "%' ";
			}
			if (null != stDocName && !"".equals(stDocName)) {
				baseSql += "and d.st_doc_name like '%" + stDocName + "%' ";
			}
			if (null != taskStatus && !"".equals(taskStatus)) {
				baseSql += "and t.ST_NOTICE_STATUS = '" + taskStatus + "' ";
			} else {
				baseSql += "and t.ST_NOTICE_STATUS = '已发送'  ";
			}
			// baseSql += "and d.st_node_Id = 'NOD_0000000101' ";
			// baseSql += "and t.st_enable is null ";
			// if ("NOD_0000000101".equals(stNodeId)) {
			baseSql += "and t.st_team_Id = '" + session.getAttribute("unitCode") + "' ";
			// }

			String orderSql = " order by t.dt_open_date DESC";
			String tableName = nodeInfo.getStExtendOne();
			String columnNames = "t.st_notice_id,d.st_doc_id, d.st_doc_name,'4','5',t.st_model_name,'7','8',t.dt_open_date,null";
			infoPage = legislationSendNoticeService.findSendNoticeList(baseSql + orderSql, tableName, columnNames, Integer.parseInt(pageNo), Integer.parseInt(pageSize));

			if (StringUtil.isEmpty(taskStatus)) {
				request.setAttribute("buttonStatus", "已发送");
			} else {
				request.setAttribute("buttonStatus", taskStatus);
			}
			request.setAttribute("isZhc", isZhc);
			request.setAttribute("nodeInfo", nodeInfo);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("retPage", infoPage);
			request.setAttribute("nodeId", stNodeId);
		}

		return "QueryTable";
	}
	
	
	    // 草案中，获取立法过程中送审反馈
		@Actions({ @Action(value = "process_person_notice_list", results = { @Result(name = SUCCESS, location = "/legislation/process_person_notice_list.jsp"),
				@Result(name = "QueryTable", location = "/legislation/process_person_notice_table.jsp") }) })
		public String processPersonNoticeList() throws Exception {
			String method = request.getParameter("method");
			String stNodeName = request.getParameter("stNodeId");
			if ("list".equals(method)) {
			   //String stNodeId = request.getParameter("stNodeId");
				request.setAttribute("requestUrl", request.getRequestURI());
				//request.setAttribute("nodeId", stNodeId);
				boolean isZhc = (boolean) session.getAttribute("isZhc");
				request.setAttribute("isZhc", isZhc);
				List<Object> buttonNameList = new ArrayList<>();
				HashMap<String, String> nameMap = new HashMap<>();
				nameMap.put("buttonName", stNodeName);
				nameMap.put("buttonClass", "btn btn-w-m btn-default");
				buttonNameList.add(nameMap);
				request.setAttribute("stTodoNameList", buttonNameList);
				request.setAttribute("stNodeName", stNodeName);
				return SUCCESS;
			} else {
				boolean isZhc = (boolean) session.getAttribute("isZhc");
				String pageSize = request.getParameter("pageSize");
				String pageNo = request.getParameter("pageNo");
				//String stNodeId = request.getParameter("stNodeId");
				String stUserName = request.getParameter("stUserName");
				String taskStatus = request.getParameter("taskStatus");
				String startTime = request.getParameter("startTime");
				String endTime = request.getParameter("endTime");
				String stDocName = request.getParameter("stDocName");

				if (null == pageSize || "".equals(pageSize)) {
					pageSize = "10";
				}
				if (null == pageNo || "".equals(pageNo)) {
					pageNo = "1";
				}

				//WegovSimpleNode nodeInfo = wegovSimpleNodeService.findById(stNodeId);
				String baseSql = "WHERE 1=1 ";

				// if (null != stNodeId && !"".equals(stNodeId)) {
				baseSql += "and t.ST_MODEL_NAME = '立法过程' and t.ST_NODE_NAME='" + stNodeName + "'  ";
				// }
				if (null != startTime && !"".equals(startTime)) {
					baseSql += "and t.dt_open_date >= TO_DATE('" + startTime + "','yyyy-mm-dd')";
				}
				if (StringUtil.isNotEmpty(endTime)) {
					baseSql += "and t.dt_open_date <= TO_DATE('" + endTime + "','yyyy-mm-dd')";
				}
				if (null != stUserName && !"".equals(stUserName)) {
					baseSql += "and d.ST_USER_NAME like '%" + stUserName + "%' ";
				}
				if (null != stDocName && !"".equals(stDocName)) {
					baseSql += "and d.st_doc_name like '%" + stDocName + "%' ";
				}
				if (null != taskStatus && !"".equals(taskStatus)) {
					baseSql += "and t.ST_NOTICE_STATUS = '" + taskStatus + "' ";
				} else {
					baseSql += "and t.ST_NOTICE_STATUS = '已发送'  ";
				}
				// baseSql += "and d.st_node_Id = 'NOD_0000000101' ";
				// baseSql += "and t.st_enable is null ";
				// if ("NOD_0000000101".equals(stNodeId)) {
				baseSql += "and t.st_team_Id = '" + session.getAttribute("unitCode") + "' ";
				// }

				String orderSql = " order by t.dt_open_date DESC";
				String tableName = "LEGISLATION_PROCESS_DOC";
				String columnNames = "t.st_notice_id,d.st_doc_id, d.st_doc_name,'4','5',t.st_model_name,'7','8',t.dt_open_date,null";
				infoPage = legislationSendNoticeService.findSendNoticeList(baseSql + orderSql, tableName, columnNames, Integer.parseInt(pageNo), Integer.parseInt(pageSize));

				if (StringUtil.isEmpty(taskStatus)) {
					request.setAttribute("buttonStatus", "已发送");
				} else {
					request.setAttribute("buttonStatus", taskStatus);
				}
				request.setAttribute("isZhc", isZhc);
				//request.setAttribute("nodeInfo", nodeInfo);
				request.setAttribute("pageNo", pageNo);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("retPage", infoPage);
				request.setAttribute("nodeId", "NOD_0000000162");
			}

			return "QueryTable";
		}

	// 各处室获取常务会议发送的议题任务。 lj
	@Actions({ @Action(value = "citymeeting_notice_list", results = { @Result(name = SUCCESS, location = "/legislation/citymeeting_notice_list.jsp"),
			@Result(name = "QueryTable", location = "/legislation/citymeeting_notice_table.jsp") }) })
	public String citymeetingListMethodManager() throws Exception {
		String method = request.getParameter("method");
		if ("list".equals(method)) {
			String stNodeId = request.getParameter("stNodeId");
			request.setAttribute("requestUrl", request.getRequestURI());
			request.setAttribute("nodeId", stNodeId);
			boolean isZhc = (boolean) session.getAttribute("isZhc");
			request.setAttribute("isZhc", isZhc);
			request.setAttribute("stTodoNameList", wegovSimpleNodeService.queryButtonInfo(stNodeId));
			return SUCCESS;
		} else {
			boolean isZhc = (boolean) session.getAttribute("isZhc");
			String pageSize = request.getParameter("pageSize");
			String pageNo = request.getParameter("pageNo");
			String stNodeId = request.getParameter("stNodeId");
			String stUserName = request.getParameter("stUserName");
			String taskStatus = request.getParameter("taskStatus");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			String stDocName = request.getParameter("stDocName");

			if (null == pageSize || "".equals(pageSize)) {
				pageSize = "10";
			}
			if (null == pageNo || "".equals(pageNo)) {
				pageNo = "1";
			}

			WegovSimpleNode nodeInfo = wegovSimpleNodeService.findById(stNodeId);
			String baseSql = "WHERE 1=1 ";

			// if (null != stNodeId && !"".equals(stNodeId)) {
			baseSql += "and t.ST_MODEL_NAME = '常务会议' and t.ST_NODE_NAME='" + nodeInfo.getStNodeName() + "'  ";
			// }
			if (null != startTime && !"".equals(startTime)) {
				baseSql += "and t.dt_open_date >= TO_DATE('" + startTime + "','yyyy-mm-dd')";
			}
			if (StringUtil.isNotEmpty(endTime)) {
				baseSql += "and t.dt_open_date <= TO_DATE('" + endTime + "','yyyy-mm-dd')";
			}
			if (null != stUserName && !"".equals(stUserName)) {
				baseSql += "and d.ST_USER_NAME like '%" + stUserName + "%' ";
			}
			if (null != stDocName && !"".equals(stDocName)) {
				baseSql += "and d.st_topic_name like '%" + stDocName + "%' ";
			}
			if (null != taskStatus && !"".equals(taskStatus)) {
				baseSql += "and t.ST_NOTICE_STATUS = '" + taskStatus + "' ";
			} else {
				baseSql += "and t.ST_NOTICE_STATUS = '已发送'  ";
			}
			// baseSql += "and d.st_node_Id = 'NOD_0000000101' ";
			// baseSql += "and t.st_enable is null ";
			// if ("NOD_0000000101".equals(stNodeId)) {
			baseSql += "and t.st_team_Id = '" + session.getAttribute("unitCode") + "' ";
			// }

			String orderSql = " order by t.dt_open_date DESC";
			String tableName = "LEGISLATION_CITYMEETING";
			String columnNames = "t.st_notice_id,d.st_topic_id, d.st_topic_name,'4','5',t.st_model_name,'7','8',t.dt_open_date,null";
			infoPage = legislationSendNoticeService.findSendNoticeCitymeetingList(baseSql + orderSql, tableName, columnNames, Integer.parseInt(pageNo), Integer.parseInt(pageSize));

			if (StringUtil.isEmpty(taskStatus)) {
				request.setAttribute("buttonStatus", "已发送");
			} else {
				request.setAttribute("buttonStatus", taskStatus);
			}
			request.setAttribute("isZhc", isZhc);
			request.setAttribute("nodeInfo", nodeInfo);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("retPage", infoPage);
			request.setAttribute("nodeId", stNodeId);
		}

		return "QueryTable";
	}

	@Action(value = "openEditParticipants", results = { @Result(name = "openEditParticipants", location = "/legislation/legislationProcessManager_participantsEdit.jsp") })
	public String openEditParticipants() {
		String showName = request.getParameter("showName");
		String stPersonsId = request.getParameter("stPersonsId");
		String module = request.getParameter("module");
		String otherPersonsName = request.getParameter("otherPersonsName");
		List<Map<String, Object>> participantsList = legislationSendNoticeService.findParticipantsList(showName, stPersonsId);
		if(null!=module) {
			//立法过程弹窗关闭控制
			request.setAttribute("process", "process");
		}
		request.setAttribute("participantsList", participantsList);
		request.setAttribute("otherPersonsName", otherPersonsName);
		return "openEditParticipants";
	}
}
