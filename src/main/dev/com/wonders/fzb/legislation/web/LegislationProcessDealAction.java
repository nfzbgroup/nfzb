package com.wonders.fzb.legislation.web;

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
import com.wonders.fzb.legislation.beans.*;
import com.wonders.fzb.legislation.services.*;
//import com.wonders.fzb.zfry.module.platform.beans.TeamInfo;
//import com.wonders.fzb.zfry.module.platform.beans.UserInfo;

/**
 * LegislationProcessDeal action接口
 * @author scalffold created by lj
 */
 
@Namespace("/legislationProcessDeal")
@Controller
@Scope("prototype")
public class LegislationProcessDealAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationProcessDealService")
	private LegislationProcessDealService legislationProcessDealService;

	private int pageNo = 1;
	private int pageSize = 10;


	//LegislationProcessDeal的修改
	@Action(value = "legislationProcessDeal_add", results = {@Result(name = SUCCESS, location = "/LegislationProcessDeal.jsp"), @Result(name = "List", location = "/legislationProcessDeal_list.jsp")})
	public String legislationProcessDeal_add() throws FzbDaoException {
//		System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<LegislationProcessDeal> legislationProcessDealList = new ArrayList<LegislationProcessDeal>();
		LegislationProcessDeal legislationProcessDeal = new LegislationProcessDeal();
		legislationProcessDealService.add(legislationProcessDeal);
		return SUCCESS;
	}

}
