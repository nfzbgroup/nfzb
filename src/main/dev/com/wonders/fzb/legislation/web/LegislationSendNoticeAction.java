package com.wonders.fzb.legislation.web;

import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislation.beans.LegislationSendNotice;
import com.wonders.fzb.legislation.beans.SendNoticeVO;
import com.wonders.fzb.legislation.services.LegislationSendNoticeService;
import com.wonders.fzb.simpleflow.beans.WegovSimpleNode;
import com.wonders.fzb.simpleflow.services.WegovSimpleNodeService;
import dm.jdbc.util.StringUtil;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * LegislationSendNotice action接口
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


	//LegislationSendNotice的修改
	@Action(value = "legislationSendNotice_add", results = {@Result(name = SUCCESS, location = "/LegislationSendNotice.jsp"), @Result(name = "List", location = "/legislationSendNotice_list.jsp")})
	public String legislationSendNotice_add() throws FzbDaoException {
//		System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<LegislationSendNotice> legislationSendNoticeList = new ArrayList<LegislationSendNotice>();
		LegislationSendNotice legislationSendNotice = new LegislationSendNotice();
		legislationSendNoticeService.add(legislationSendNotice);
		return SUCCESS;
	}

	
	
	//草案中，获取立法过程中意见与会签的各单位的任务。 lj
	@Actions({ @Action(value = "process_depart_notice_list", results = { @Result(name = SUCCESS, location = "/legislation/process_depart_notice_list.jsp"), @Result(name = "QueryTable", location = "/legislation/process_depart_notice_table.jsp") }) })
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

//				if (null != stNodeId && !"".equals(stNodeId)) {
					baseSql += "and t.ST_MODEL_NAME = '立法过程' and t.ST_NODE_NAME='"+nodeInfo.getStNodeName()+"'  ";
//				}
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
				}
				// baseSql += "and d.st_node_Id = 'NOD_0000000101' ";
//				baseSql += "and t.st_enable is null ";
//				if ("NOD_0000000101".equals(stNodeId)) {
					baseSql += "and t.st_team_Id = '" + session.getAttribute("unitCode") + "' ";
//				}

				String orderSql = " order by t.dt_open_date DESC";
				String tableName=nodeInfo.getStExtendOne();
				String columnNames="t.st_notice_id,d.st_doc_id, d.st_doc_name,'4','5',t.st_model_name,'7','8',t.dt_open_date,null";
				infoPage = legislationSendNoticeService.findSendNoticeList(baseSql + orderSql,tableName, columnNames, Integer.parseInt(pageNo), Integer.parseInt(pageSize));

				if (StringUtil.isEmpty(taskStatus)) {
						request.setAttribute("buttonStatus", "TODO");
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


	@Action(value = "openEditParticipants",results = {@Result(name = "openEditParticipants",location = "/legislation/legislationProcessManager_participantsEdit.jsp")})
	public String openEditParticipants(){
		String showName=request.getParameter("showName");
		String stPersonsId=request.getParameter("stPersonsId");
		String otherPersonsName=request.getParameter("otherPersonsName");
		List<Map<String, Object>> participantsList=legislationSendNoticeService.findParticipantsList(showName,stPersonsId);
		request.setAttribute("participantsList",participantsList);
		request.setAttribute("otherPersonsName",otherPersonsName);
		return "openEditParticipants";
	}
}
