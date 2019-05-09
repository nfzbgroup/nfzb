package com.wonders.fzb.assess.web;

import com.wonders.fzb.assess.beans.LegislationAssess;
import com.wonders.fzb.assess.beans.LegislationAssessTask;
import com.wonders.fzb.assess.services.LegislationAssessService;
import com.wonders.fzb.assess.services.LegislationAssessTaskService;
import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislation.beans.LegislationFiles;
import com.wonders.fzb.legislation.services.LegislationFilesService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LegislationAssess action接口
 * @author scalffold created by lj
 */
 
@Namespace("/legislationAssess")
@Controller
@Scope("prototype")
public class LegislationAssessAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationAssessService")
	private LegislationAssessService legislationAssessService;

	@Autowired
	@Qualifier("legislationAssessTaskService")
	private LegislationAssessTaskService legislationAssessTaskService;

	@Autowired
	@Qualifier("legislationFilesService")
	private LegislationFilesService legislationFilesService;

	private int pageNo = 1;
	private int pageSize = 10;


	//LegislationAssess的修改
	@Action(value = "legislationAssess_add", results = {@Result(name = SUCCESS, location = "/LegislationAssess.jsp"), @Result(name = "List", location = "/legislationAssess_list.jsp")})
	public String legislationAssess_add() throws FzbDaoException {
//		System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<LegislationAssess> legislationAssessList = new ArrayList<LegislationAssess>();
		LegislationAssess legislationAssess = new LegislationAssess();
		legislationAssessService.add(legislationAssess);
		return SUCCESS;
	}

	@Action(value = "assess_plan_info", results = { @Result(name = "openAssessAddPage", location = "/assess/legislationAssess_form.jsp"),
			@Result(name = "openAssessEditPage", location = "/assess/legislationAssess_form.jsp"),
			@Result(name = "openAssessInfoPage", location = "/assess/legislationAssess_form.jsp")
	})
	public String legislationAssess() throws Exception {
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

	/**
	 * 跳转评估规划发起页面
	 * @return
	 */
	private String openAssessAddPage(){
		request.setAttribute("legislationAssess",new LegislationAssess());
		request.setAttribute("legislationAssessTask",new LegislationAssessTask());
		return pageController();
	}

	/**
	 * 跳转评估规划编辑页面
	 * @return
	 */
	private String openAssessEditPage(){
		String stTaskId=request.getParameter("stTaskId");
		LegislationAssessTask legislationAssessTask=legislationAssessTaskService.findById(stTaskId);
		LegislationAssess legislationAssess=legislationAssessService.findById(legislationAssessTask.getStParentId());
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId", legislationAssess.getStAssessId());
		condMap.put("stNodeId", "NOD_0000000251");
		sortMap.put("dtPubDate", "ASC");
		List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
		request.setAttribute("legislationFilesList",legislationFilesList);
		request.setAttribute("legislationAssess",legislationAssess);
		request.setAttribute("legislationAssessTask",legislationAssessTask);
		return pageController();
	}

	/**
	 * 跳转评估规划详情页面
	 * @return
	 */
	private String openAssessInfoPage(){
		return openAssessEditPage();
	}

	/**
	 * 保存评估规划
	 * @return
	 */
	private String saveLegislationAssess(){
		legislationAssessService.saveLegislationAssess(request,session);
		return null;
	}
}
