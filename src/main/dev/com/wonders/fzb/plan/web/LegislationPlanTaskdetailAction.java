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
 * LegislationPlanTaskdetail action接口
 * @author scalffold created by lj
 */
 
@Namespace("/legislationPlanTaskdetail")
@Controller
@Scope("prototype")
public class LegislationPlanTaskdetailAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationPlanTaskdetailService")
	private LegislationPlanTaskdetailService legislationPlanTaskdetailService;

	private int pageNo = 1;
	private int pageSize = 10;


	//LegislationPlanTaskdetail的修改
	@Action(value = "legislationPlanTaskdetail_add", results = {@Result(name = SUCCESS, location = "/LegislationPlanTaskdetail.jsp"), @Result(name = "List", location = "/legislationPlanTaskdetail_list.jsp")})
	public String legislationPlanTaskdetail_add() throws FzbDaoException {
//		System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<LegislationPlanTaskdetail> legislationPlanTaskdetailList = new ArrayList<LegislationPlanTaskdetail>();
		LegislationPlanTaskdetail legislationPlanTaskdetail = new LegislationPlanTaskdetail();
		legislationPlanTaskdetailService.add(legislationPlanTaskdetail);
		return SUCCESS;
	}

}
