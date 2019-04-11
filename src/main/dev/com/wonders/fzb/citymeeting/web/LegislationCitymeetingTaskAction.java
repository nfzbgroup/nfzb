package com.wonders.fzb.citymeeting.web;

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

import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.citymeeting.beans.*;
import com.wonders.fzb.citymeeting.services.*;
import com.wonders.fzb.legislation.beans.LegislationProcessDoc;
import com.wonders.fzb.legislation.beans.LegislationProcessTask;
import com.wonders.fzb.simpleflow.beans.WegovSimpleNode;
import com.wonders.fzb.simpleflow.services.WegovSimpleNodeService;

import dm.jdbc.util.StringUtil;

/**
 * LegislationCitymeetingTask action接口
 * @author scalffold created by lj
 */
 
@Namespace("/legislationCitymeetingTask")
@Controller
@Scope("prototype")
public class LegislationCitymeetingTaskAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationCitymeetingTaskService")
	private LegislationCitymeetingTaskService legislationCitymeetingTaskService;

	
    @Autowired
    @Qualifier("wegovSimpleNodeService")
    private WegovSimpleNodeService wegovSimpleNodeService;
    
    @Autowired
    @Qualifier("legislationCitymeetingService")
    private LegislationCitymeetingService legislationCitymeetingService;
    
    @Autowired
    @Qualifier("legislationCitymeetingDealService")
    private LegislationCitymeetingDealService legislationCitymeetingDealService;
    
    
    
	private int pageNo = 1;
	private int pageSize = 10;


	//LegislationCitymeetingTask的修改
	@Action(value = "legislationCitymeetingTask_add", results = {@Result(name = SUCCESS, location = "/LegislationCitymeetingTask.jsp"), @Result(name = "List", location = "/legislationCitymeetingTask_list.jsp")})
	public String legislationCitymeetingTask_add() throws FzbDaoException {
//		System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<LegislationCitymeetingTask> legislationCitymeetingTaskList = new ArrayList<LegislationCitymeetingTask>();
		LegislationCitymeetingTask legislationCitymeetingTask = new LegislationCitymeetingTask();
		legislationCitymeetingTaskService.add(legislationCitymeetingTask);
		return SUCCESS;
	}

	
	
    //所有模块的所有菜单的列表，统一一下请求，请不要改动。根据nodeid返回默认状态的任务列表。 lj
    @Actions({
        @Action(value = "citymeeting_task_list", results = {
        		@Result(name = SUCCESS, location = "/citymeeting/meeting_list.jsp"),
        		@Result(name = "QueryTable", location = "/citymeeting/meeting_table.jsp")
        		})
    })
    public String citymeeting_task_list() throws Exception {
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

        queryDoc();

        return "QueryTable";
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
        String stDocName = request.getParameter("stDocName");

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
            baseSql += "and d.DT_CREATE_DATE >= TO_DATE('" + startTime + "','yyyy-mm-dd')";
        }
        if (StringUtil.isNotEmpty(endTime)) {
            baseSql += "and d.DT_CREATE_DATE <= TO_DATE('" + endTime + "','yyyy-mm-dd')";
        }
        if (null != stUserName && !"".equals(stUserName)) {
            baseSql += "and d.ST_USER_NAME like '%" + stUserName + "%' ";
        }
        if (null != stDocName && !"".equals(stDocName)) {
            baseSql += "and d.st_doc_name like '%" + stDocName + "%' ";
        }
        if (null != taskStatus && !"".equals(taskStatus)) {
            baseSql += "and t.st_task_status = '" + taskStatus + "' ";
        } else {
            if ("NOD_0000000103".equals(stNodeId)) {
                if(isZhc){
                    baseSql += "and t.st_task_status = 'DOING' ";
                }else{
                    baseSql += "and t.st_task_status = 'TODO' ";
                }
            }else{
                baseSql += "and t.st_task_status = 'TODO' ";
            }
        }
        baseSql += "and d.st_node_Id = 'NOD_0000000101' ";
        baseSql += "and t.st_enable is null ";
        if ("NOD_0000000101".equals(stNodeId)) {
            baseSql += "and t.st_team_Id = '" + session.getAttribute("unitCode") + "' ";
        }
        if ("NOD_0000000103".equals(stNodeId)) {
            if(!isZhc){
                baseSql += "and t.st_deal_Id = '" + session.getAttribute("unitCode") + "' ";
            }
        }

        String orderSql = " order by d.dt_create_date DESC";
        Page<LegislationCitymeeting> infoPage = legislationCitymeetingTaskService.findTaskByNodeId(baseSql + orderSql, Integer.parseInt(pageNo), Integer.parseInt(pageSize));

        if (StringUtil.isEmpty(taskStatus)) {
            if ("NOD_0000000103".equals(stNodeId)) {
                if(isZhc){
                    request.setAttribute("buttonStatus", "DOING");
                }else{
                    request.setAttribute("buttonStatus", "TODO");
                }
            }else{
                request.setAttribute("buttonStatus", "TODO");
            }
        } else {
            request.setAttribute("buttonStatus", taskStatus);
        }
        if(stNodeId.equals("NOD_0000000104")) {}
        request.setAttribute("isZhc", isZhc);
        request.setAttribute("nodeInfo", nodeInfo);
        request.setAttribute("pageNo", pageNo);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("retPage", infoPage);
        request.setAttribute("nodeId", stNodeId);
    }
}
