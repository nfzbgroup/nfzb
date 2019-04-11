package com.wonders.fzb.plan.web;

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

import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.plan.beans.*;
import com.wonders.fzb.plan.services.*;

/**
 * LegislationPlanTask action接口
 * @author scalffold created by lj
 */
 
@Namespace("/legislationPlanTask")
@Controller
@Scope("prototype")
public class LegislationPlanTaskAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationPlanTaskService")
	private LegislationPlanTaskService legislationPlanTaskService;

	private int pageNo = 1;
	private int pageSize = 10;


	//LegislationPlanTask的修改
	@Action(value = "legislationPlanTask_add", results = {@Result(name = SUCCESS, location = "/LegislationPlanTask.jsp"), @Result(name = "List", location = "/legislationPlanTask_list.jsp")})
	public String legislationPlanTask_add() throws FzbDaoException {
//		System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<LegislationPlanTask> legislationPlanTaskList = new ArrayList<LegislationPlanTask>();
		LegislationPlanTask legislationPlanTask = new LegislationPlanTask();
		legislationPlanTaskService.add(legislationPlanTask);
		return SUCCESS;
	}

}
