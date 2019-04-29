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
 * LegislationReportDeal action接口
 * @author scalffold created by lj
 */
 
@Namespace("/legislationReportDeal")
@Controller
@Scope("prototype")
public class LegislationReportDealAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationReportDealService")
	private LegislationReportDealService legislationReportDealService;

	private int pageNo = 1;
	private int pageSize = 10;


	//LegislationReportDeal的修改
	@Action(value = "legislationReportDeal_add", results = {@Result(name = SUCCESS, location = "/LegislationReportDeal.jsp"), @Result(name = "List", location = "/legislationReportDeal_list.jsp")})
	public String legislationReportDeal_add() throws FzbDaoException {
//		System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<LegislationReportDeal> legislationReportDealList = new ArrayList<LegislationReportDeal>();
		LegislationReportDeal legislationReportDeal = new LegislationReportDeal();
		legislationReportDealService.add(legislationReportDeal);
		return SUCCESS;
	}

}
