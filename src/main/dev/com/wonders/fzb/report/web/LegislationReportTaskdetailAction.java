package com.wonders.fzb.report.web;

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
import com.wonders.fzb.report.beans.*;
import com.wonders.fzb.report.services.*;

/**
 * LegislationReportTaskdetail action接口
 * @author scalffold created by lj
 */
 
@Namespace("/legislationReportTaskdetail")
@Controller
@Scope("prototype")
public class LegislationReportTaskdetailAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationReportTaskdetailService")
	private LegislationReportTaskdetailService legislationReportTaskdetailService;

	private int pageNo = 1;
	private int pageSize = 10;


	//LegislationReportTaskdetail的修改
	@Action(value = "legislationReportTaskdetail_add", results = {@Result(name = SUCCESS, location = "/LegislationReportTaskdetail.jsp"), @Result(name = "List", location = "/legislationReportTaskdetail_list.jsp")})
	public String legislationReportTaskdetail_add() throws FzbDaoException {
//		System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<LegislationReportTaskdetail> legislationReportTaskdetailList = new ArrayList<LegislationReportTaskdetail>();
		LegislationReportTaskdetail legislationReportTaskdetail = new LegislationReportTaskdetail();
		legislationReportTaskdetailService.add(legislationReportTaskdetail);
		return SUCCESS;
	}

}
