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

/**
 * LegislationCitymeeting action接口
 * @author scalffold created by lj
 */
 
@Namespace("/legislationCitymeeting")
@Controller
@Scope("prototype")
public class LegislationCitymeetingAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationCitymeetingService")
	private LegislationCitymeetingService legislationCitymeetingService;

	private int pageNo = 1;
	private int pageSize = 10;


	//LegislationCitymeeting的修改
	@Action(value = "legislationCitymeeting_add", results = {@Result(name = SUCCESS, location = "/LegislationCitymeeting.jsp"), @Result(name = "List", location = "/legislationCitymeeting_list.jsp")})
	public String legislationCitymeeting_add() throws FzbDaoException {
//		System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<LegislationCitymeeting> legislationCitymeetingList = new ArrayList<LegislationCitymeeting>();
		LegislationCitymeeting legislationCitymeeting = new LegislationCitymeeting();
		legislationCitymeetingService.add(legislationCitymeeting);
		return SUCCESS;
	}

}
