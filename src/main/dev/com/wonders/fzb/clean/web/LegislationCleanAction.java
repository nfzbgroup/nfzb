package com.wonders.fzb.clean.web;

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
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wonders.fzb.assess.beans.LegislationAssess;
import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.clean.beans.*;
import com.wonders.fzb.clean.services.*;
import com.wonders.fzb.legislation.services.LegislationExampleService;
import com.wonders.fzb.simpleflow.beans.WegovSimpleNode;
import com.wonders.fzb.simpleflow.services.WegovSimpleNodeService;

/**
 * LegislationClean action接口
 * @author scalffold created by lj
 */
 
@Namespace("/legislationClean")
@Controller
@Scope("prototype")
public class LegislationCleanAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationCleanService")
	private LegislationCleanService legislationCleanService;

	@Autowired
	@Qualifier("wegovSimpleNodeService")
	private WegovSimpleNodeService wegovSimpleNodeService;
	
	@Autowired
	@Qualifier("legislationExampleService")
	private LegislationExampleService legislationExampleService;
	
	
	private int pageNo = 1;
	private int pageSize = 10;


	//LegislationClean的修改
	@Action(value = "legislationClean_add", results = {@Result(name = SUCCESS, location = "/LegislationClean.jsp"), @Result(name = "List", location = "/legislationClean_list.jsp")})
	public String legislationClean_add() throws FzbDaoException {
//		System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<LegislationClean> legislationCleanList = new ArrayList<LegislationClean>();
		LegislationClean legislationClean = new LegislationClean();
		legislationCleanService.add(legislationClean);
		return SUCCESS;
	}
	
	@Action(value = "clean_plan_info", results = {
			/*@Result(name = "openAssessAddPage", location = "/assess/legislationAssess_form.jsp"),
			@Result(name = "openAssessEditPage", location = "/assess/legislationAssess_form.jsp"),
			@Result(name = "openAssessInfoPage", location = "/assess/legislationAssess_form.jsp"),
			@Result(name = "openAssessProjectInfoPage", location = "/assess/legislationAssess_projectInfo.jsp"),
			@Result(name = "openAssessDistributePage", location = "/assess/legislationAssess_distribute.jsp"),
			@Result(name = "openAssessFeedbackPage", location = "/assess/legislationAssess_feedback.jsp"),*/
			@Result(name = "legislation_clean_flow", location = "/clean/legislation_clean_flow.jsp")
	})
	public String legislationClean() throws Exception {
		String methodStr = request.getParameter("method");
		java.lang.reflect.Method method = this.getClass().getDeclaredMethod(methodStr);
		Object object = method.invoke(this);
		return object == null ? null : object.toString();
	}

	/**
	 * 页面控制
	 *
	 * @return
	 */
	private String pageController() {
		String methodStr = request.getParameter("method");
		String stNodeId = request.getParameter("stNodeId");
		String stTaskId = request.getParameter("stTaskId");

		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("stTaskId", stTaskId);
		request.setAttribute("requestUrl", request.getRequestURI());
		return methodStr;
	}

	//打开立法清理样本流程图
	private String legislation_clean_flow() {
		 LegislationClean legislationClean = new LegislationClean();
		request.setAttribute("legislationAssess", legislationClean);
		request.setAttribute("requestUrl", request.getRequestURI());
		return pageController();
	}
	//立法清理样本流程图材料样本查询
	   private String openCleanAttachNum_ajax() throws IOException {
		    JSONObject retJson = new JSONObject();
			JSONArray nodeInfoArray = new JSONArray();
						
		    Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stFlowName", "立法清理");
			sortMap.put("dtExtendDate", "ASC");
			// 立法清理节点信息
			List<WegovSimpleNode> nodeList = wegovSimpleNodeService.findByList(condMap, sortMap);
			for (WegovSimpleNode wegovSimpleNode : nodeList) {
				JSONObject nodeChange = new JSONObject();
				String stNodeId = wegovSimpleNode.getStNodeId();
				nodeChange.put("node", stNodeId);	
				String sql="SELECT COUNT(1) FROM LEGISLATION_EXAMPLE WHERE ST_NODE='"+stNodeId+"' AND ST_STATUS='USED'";
				int queryExampleNum = legislationExampleService.queryExampleNum(sql);
				nodeChange.put("num", queryExampleNum);
				nodeInfoArray.add(nodeChange);
			}
			
			retJson.put("success", true);
			retJson.put("nodeInfoArray", nodeInfoArray);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().print(retJson);
		   return null;
	   }
	
	

}
