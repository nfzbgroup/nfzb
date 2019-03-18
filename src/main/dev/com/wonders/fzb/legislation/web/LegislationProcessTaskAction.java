package com.wonders.fzb.legislation.web;

import com.alibaba.fastjson.JSONObject;
import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.legislation.beans.LegislationExample;
import com.wonders.fzb.legislation.beans.LegislationFiles;
import com.wonders.fzb.legislation.beans.LegislationProcessDoc;
import com.wonders.fzb.legislation.beans.LegislationProcessTask;
import com.wonders.fzb.legislation.services.*;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * LegislationProcessTask action接口
 *
 * @author scalffold created by lj
 */

@Namespace("/legislationProcessTask")
@Controller
@Scope("prototype")
public class LegislationProcessTaskAction extends BaseAction {

    private static final long serialVersionUID = -5236871814191219582L;
    @Autowired
    @Qualifier("legislationProcessTaskService")
    private LegislationProcessTaskService legislationProcessTaskService;


    @Autowired
    @Qualifier("wegovSimpleNodeService")
    private WegovSimpleNodeService wegovSimpleNodeService;

    @Autowired
    @Qualifier("legislationProcessDocService")
    private LegislationProcessDocService legislationProcessDocService;
    @Autowired
    @Qualifier("legislationExampleService")
    private LegislationExampleService legislationExampleService;
    @Autowired
    @Qualifier("legislationFilesService")
    private LegislationFilesService legislationFilesService;
    @Autowired
    @Qualifier("legislationProcessTaskdetailService")
    private LegislationProcessTaskdetailService legislationProcessTaskdetailService;

    Page<LegislationProcessDoc> infoPage;

    @Action(value = "legislationProcessTask_add", results = {@Result(name = SUCCESS, location = "/LegislationProcessTask.jsp"), @Result(name = "List", location = "/legislationProcessTask_list.jsp")})
    public String legislationProcessTask_add() throws FzbDaoException {
        LegislationProcessTask legislationProcessTask = new LegislationProcessTask();
        legislationProcessTaskService.add(legislationProcessTask);
        return SUCCESS;
    }

    @Actions({
        @Action(value = "draft_dist_info", results = {@Result(name = SUCCESS, location = "/legislation/legislationProcessManager_list.jsp"), @Result(name = "QueryTable", location = "/legislation/legislationProcessManager_table.jsp")}),
        @Action(value = "draft_create_info", results = {@Result(name = SUCCESS, location = "/legislation/legislationProcessManager_list.jsp"), @Result(name = "QueryTable", location = "/legislation/legislationProcessManager_table.jsp")}),
        @Action(value = "draft_promeet_info", results = {@Result(name = SUCCESS, location = "/legislation/legislationProcessManager_list.jsp"), @Result(name = "QueryTable", location = "/legislation/legislationProcessManager_table.jsp")}),
        @Action(value = "draft_deal_info", results = {@Result(name = SUCCESS, location = "/legislation/legislationProcessManager_list.jsp"), @Result(name = "QueryTable", location = "/legislation/legislationProcessManager_table.jsp")}),
        @Action(value = "draft_deal_hearing_deal", results = {@Result(name = SUCCESS, location = "/legislation/legislationProcessManager_list.jsp"), @Result(name = "QueryTable", location = "/legislation/legislationProcessManager_table.jsp")}),
        @Action(value = "draft_deal_hearing_start", results = {@Result(name = SUCCESS, location = "/legislation/legislationProcessManager_list.jsp"), @Result(name = "QueryTable", location = "/legislation/legislationProcessManager_table.jsp")}),
        @Action(value = "draft_deal_expert_start", results = {@Result(name = SUCCESS, location = "/legislation/legislationProcessManager_list.jsp"), @Result(name = "QueryTable", location = "/legislation/legislationProcessManager_table.jsp")}),
        @Action(value = "draft_deal_expert_deal", results = {@Result(name = SUCCESS, location = "/legislation/legislationProcessManager_list.jsp"), @Result(name = "QueryTable", location = "/legislation/legislationProcessManager_table.jsp")}),
        @Action(value = "draft_check_meeting", results = {@Result(name = SUCCESS, location = "/legislation/legislationProcessManager_list.jsp"), @Result(name = "QueryTable", location = "/legislation/legislationProcessManager_table.jsp")}),
        @Action(value = "draft_deal_deptopinion_start", results = {@Result(name = SUCCESS, location = "/legislation/legislationProcessManager_list.jsp"), @Result(name = "QueryTable", location = "/legislation/legislationProcessManager_table.jsp")}),
        @Action(value = "draft_deal_deptopinion_send", results = {@Result(name = SUCCESS, location = "/legislation/legislationProcessManager_list.jsp"), @Result(name = "QueryTable", location = "/legislation/legislationProcessManager_table.jsp")}),
        @Action(value = "draft_deal_deptopinion_input", results = {@Result(name = SUCCESS, location = "/legislation/legislationProcessManager_list.jsp"), @Result(name = "QueryTable", location = "/legislation/legislationProcessManager_table.jsp")})
    })
    public String listMethodManager() throws Exception {
        String methodStr = request.getParameter("method");
        if (StringUtil.isEmpty(methodStr)) {
            String stNodeId = request.getParameter("stNodeId");
            request.setAttribute("requestUrl", request.getRequestURI());
            request.setAttribute("nodeId", stNodeId);
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
    private String queryTable() {
        if("NOD_0000000140".equals(request.getParameter("stNodeId"))||"NOD_0000000141".equals(request.getParameter("stNodeId"))
                ||"NOD_0000000150".equals(request.getParameter("stNodeId"))||"NOD_0000000151".equals(request.getParameter("stNodeId"))){
            queryTaskDetail();
        }else if("NOD_0000000120".equals(request.getParameter("stNodeId"))||"NOD_0000000121".equals(request.getParameter("stNodeId"))||"NOD_0000000122".equals(request.getParameter("stNodeId"))){
            queryUnitOpinion();
        }else if("NOD_0000000170".equals(request.getParameter("stNodeId"))){
            queryCheckMeeting();
        }else{
            queryDoc();
        }
        return "QueryTable";
    }

    private void queryCheckMeeting() {
        String pageSize = request.getParameter("pageSize");
        String pageNo = request.getParameter("pageNo");
        String stNodeId = request.getParameter("stNodeId");
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
        if (null != stDocName && !"".equals(stDocName)) {
            baseSql += "and d.st_doc_name like '%" + stDocName + "%' ";
        }
        if (null != taskStatus && !"".equals(taskStatus)) {
            baseSql += "and t.st_task_status = '" + taskStatus + "' ";
        } else {
            baseSql += "and t.st_task_status = 'TODO' ";
        }
        baseSql += "and d.st_node_Id = '"+stNodeId+"'";
        baseSql += "and t.st_enable is null ";
        baseSql += "and t.st_team_Id = '" + session.getAttribute("unitCode") + "' ";


        String orderSql = " order by d.dt_create_date DESC";
        infoPage = legislationProcessTaskService.findCheckMeetingByNodeId(baseSql + orderSql, Integer.parseInt(pageNo), Integer.parseInt(pageSize));

        List<LegislationProcessDoc> resultList = new ArrayList<>();
        List<LegislationProcessDoc> docList = infoPage.getResult();
        for(LegislationProcessDoc legislationProcessDoc:docList){
            StringBuffer stDocSourceBuffer = new StringBuffer();
            String stDocSource = legislationProcessDoc.getStDocSource();  //标书id拼凑 DFT_0000000000000141#DFT_0000000000000140
            for(String stDocId:stDocSource.split("#")){
                stDocSourceBuffer.append(legislationProcessDocService.findById(stDocId).getStDocName()).append("<br>");
            }

            legislationProcessDoc.setStDocSource(stDocSourceBuffer.toString());

            if(StringUtil.isNotEmpty(taskStatus) && ("INPUT").equals(taskStatus)){
                String docId = legislationProcessDoc.getStDocId();
                List<LegislationProcessTask> tasks = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where t.stDocId='"+docId+"' and t.stNodeId='"+stNodeId+"'");
                LegislationProcessTask legislationProcessTask = tasks.get(0);
                if (legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where 1=1 and t.stTaskId='" + legislationProcessTask.getStTaskId() + "' and t.stTaskStatus='TODO-RETURN'").size() > 0) {
                    legislationProcessDoc.setHasReturn(true);
                }
            }
            resultList.add(legislationProcessDoc);
        }
        infoPage.setResult(resultList);
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

    private void queryUnitOpinion(){
        String pageSize = request.getParameter("pageSize");
        String pageNo = request.getParameter("pageNo");
        String stNodeId = request.getParameter("stNodeId");
        String opinionType = request.getParameter("opinionType");
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
            baseSql += "and t.DT_OPEN_DATE >= TO_DATE('" + startTime + "','yyyy-mm-dd')";
        }
        if (StringUtil.isNotEmpty(endTime)) {
            baseSql += "and t.DT_OPEN_DATE <= TO_DATE('" + endTime + "','yyyy-mm-dd')";
        }
        if (null != opinionType && !"".equals(opinionType)) {
            baseSql += "and t.ST_BAK_ONE like '%" + opinionType + "%' ";
        }
        if (null != stDocName && !"".equals(stDocName)) {
            baseSql += "and t.st_flow_id like '%" + stDocName + "%' ";
        }
        if (null != taskStatus && !"".equals(taskStatus)) {
            baseSql += "and t.st_task_status = '" + taskStatus + "' ";
        } else {
            baseSql += "and t.st_task_status = 'TODO' ";
        }
        baseSql += "and t.st_enable is null ";
        if(stNodeId.equals("NOD_0000000122")){
            baseSql += "and t.st_parent_Id = '" + session.getAttribute("unitCode") + "' ";
        }

        String orderSql = " order by t.dt_open_date DESC";
        Page<LegislationProcessTask> infoPage = legislationProcessTaskService.findTaskByNodeId(baseSql + orderSql, Integer.parseInt(pageNo), Integer.parseInt(pageSize));

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

    private void queryTaskDetail(){
        String pageSize = request.getParameter("pageSize");
        String pageNo = request.getParameter("pageNo");
        String stNodeId = request.getParameter("stNodeId");
        String address = request.getParameter("address");
        String title = request.getParameter("title");
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
            baseSql += "and t.DT_BAK_DATE >= TO_DATE('" + startTime + "','yyyy-mm-dd')";
        }
        if (StringUtil.isNotEmpty(endTime)) {
            baseSql += "and t.DT_BAK_DATE <= TO_DATE('" + endTime + "','yyyy-mm-dd')";
        }
        if (null != address && !"".equals(address)) {
            baseSql += "and t.ST_BAK_TWO like '%" + address + "%' ";
        }
        if (null != title && !"".equals(title)) {
            baseSql += "and t.ST_BAK_ONE like '%" + title + "%' ";
        }
        if (null != stDocName && !"".equals(stDocName)) {
            baseSql += "and t.st_flow_id like '%" + stDocName + "%' ";
        }
        if (null != taskStatus && !"".equals(taskStatus)) {
            baseSql += "and t.st_task_status = '" + taskStatus + "' ";
        } else {
            baseSql += "and t.st_task_status = 'TODO' ";
        }
        baseSql += "and t.st_enable is null ";
        baseSql += "and t.st_team_Id = '" + session.getAttribute("unitCode") + "' ";

        String orderSql = " order by t.dt_open_date DESC";
        Page<LegislationProcessTask> infoPage = legislationProcessTaskService.findTaskByNodeId(baseSql + orderSql, Integer.parseInt(pageNo), Integer.parseInt(pageSize));

        if (StringUtil.isEmpty(taskStatus)) {
            request.setAttribute("buttonStatus", "TODO");
        } else {
            request.setAttribute("buttonStatus", taskStatus);
        }
        if(stNodeId.equals("NOD_0000000141")) {
            if (infoPage.getResult() != null && infoPage.getResult().size() > 0) {
                List<LegislationProcessTask> list = new ArrayList<>();
                for (LegislationProcessTask legislationProcessTask : infoPage.getResult()) {
                    String taskId = legislationProcessTask.getStTaskId();
                    if (legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where 1=1 and t.stTaskId='" + taskId + "' and t.stTaskStatus='SEND-RETURN'").size() > 0) {
                        legislationProcessTask.setHasSendReturn(true);
                    }
                    if (legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where 1=1 and t.stTaskId='" + taskId + "' and t.stTaskStatus='GATHER-RETURN'").size() > 0) {
                        legislationProcessTask.setHasGatherReturn(true);
                    }
                    list.add(legislationProcessTask);
                }
                infoPage.setResult(list);
            }
        }
        request.setAttribute("nodeInfo", nodeInfo);
        request.setAttribute("pageNo", pageNo);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("retPage", infoPage);
        request.setAttribute("nodeId", stNodeId);
    }
    private void queryDoc(){
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
            baseSql += "and t.st_task_status = 'TODO' ";
        }
        baseSql += "and d.st_node_Id = 'NOD_0000000101' ";
        baseSql += "and t.st_enable is null ";
        if ("NOD_0000000101".equals(stNodeId)) {
            baseSql += "and t.st_team_Id = '" + session.getAttribute("unitCode") + "' ";
        }
        if ("NOD_0000000103".equals(stNodeId)) {
            baseSql += "and t.st_deal_Id = '" + session.getAttribute("unitCode") + "' ";
        }

        String orderSql = " order by d.dt_create_date DESC";
        infoPage = legislationProcessTaskService.findTaskDocListByNodeId(baseSql + orderSql, Integer.parseInt(pageNo), Integer.parseInt(pageSize));

        if (StringUtil.isEmpty(taskStatus)) {
            request.setAttribute("buttonStatus", "TODO");
        } else {
            request.setAttribute("buttonStatus", taskStatus);
        }
        if(stNodeId.equals("NOD_0000000104")) {
            if (infoPage.getResult() != null && infoPage.getResult().size() > 0) {
                List<LegislationProcessDoc> list = new ArrayList<>();
                for (LegislationProcessDoc legislationProcessDoc : infoPage.getResult()) {
                    String docId = legislationProcessDoc.getStDocId();
                    List<LegislationProcessTask> tasks = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where t.stDocId='"+docId+"' and t.stNodeId='NOD_0000000104'");
                    LegislationProcessTask legislationProcessTask = tasks.get(0);
                    if (legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where 1=1 and t.stTaskId='" + legislationProcessTask.getStTaskId() + "' and t.stTaskStatus='TODO-RETURN'").size() > 0) {
                        legislationProcessDoc.setHasReturn(true);
                    }
                    list.add(legislationProcessDoc);
                }
                infoPage.setResult(list);
            }
        }
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
    @Action(value = "nextProcess")
    private String nextProcess() throws FzbDaoException, IOException {
        String stDocId = request.getParameter("stDocId");
        String stNodeId = request.getParameter("stNodeId");
        String buttonId=request.getParameter("buttonId");
        legislationProcessTaskService.nextProcess(stDocId,stNodeId,session);
        UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
        String teamId=currentPerson.getTeamInfos().get(0).getId();
        Boolean removeDisabled=false;
        if("unitEdit".equals(buttonId)&&"U_3_1".equals(teamId)){
            removeDisabled=true;
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("removeDisabled",removeDisabled);
        jsonObject.put("success",true);
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(jsonObject);
        return null;
    }

    /**
     * 退回（共用）
     * @return
     * @throws FzbDaoException
     */
    private String returnProcess() throws FzbDaoException {
        UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
        String userRoleId =session.getAttribute("userRoleId").toString();
        String userRole =session.getAttribute("userRole").toString();

        String stDocId = request.getParameter("stDocId");
        String stNodeId = request.getParameter("stNodeId");
        legislationProcessTaskService.returnProcess(stDocId,stNodeId,userRoleId,userRole,currentPerson);

        return null;
    }
    /**
     * 次节点流转（公共）
     *
     * @return
     * @throws FzbDaoException
     */
    private String nextChildProcess() throws FzbDaoException {
        UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
        String userRoleId =session.getAttribute("userRoleId").toString();
        String userRole =session.getAttribute("userRole").toString();
        String stDocId = request.getParameter("stDocId");
        String stNodeId = request.getParameter("stNodeId");

        legislationProcessTaskService.nextChildProcess(stDocId,stNodeId,userRoleId,userRole,currentPerson);

        return null;
    }

    public Page<LegislationProcessDoc> getInfoPage() {
        return infoPage;
    }


    public void setInfoPage(Page<LegislationProcessDoc> infoPage) {
        this.infoPage = infoPage;
    }

    /**
     * 草案上报必填项校验
     * @throws Exception
     */
    @Action(value = "uploadReport")
    public void uploadReport() throws Exception {
        JSONObject jsonObject=new JSONObject();
        String stDocId = request.getParameter("stDocId");
        String stNode = request.getParameter("stNode");

        Map<String, Object> condMap = new HashMap<>();
        condMap.put("stNode", stNode);
        condMap.put("stNeed", "NEED");
        List<LegislationExample> legislationExampleList = legislationExampleService.findByList(condMap, null);
        List<LegislationFiles> docList = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId ='"+stDocId+"' and t.stNodeId='"+stNode+"'");
        if(docList.size()<legislationExampleList.size()){
            jsonObject.put("success",false);

        }else{
            jsonObject.put("success",true);
        }
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

}
