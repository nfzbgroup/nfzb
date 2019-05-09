package com.wonders.fzb.assess.web;

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
import com.wonders.fzb.assess.beans.*;
import com.wonders.fzb.assess.services.*;

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

}
