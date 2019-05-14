package com.wonders.fzb.clean.web;

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
import com.wonders.fzb.clean.beans.*;
import com.wonders.fzb.clean.services.*;

/**
 * LegislationCleanItem action接口
 * @author scalffold created by lj
 */
 
@Namespace("/legislationCleanItem")
@Controller
@Scope("prototype")
public class LegislationCleanItemAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationCleanItemService")
	private LegislationCleanItemService legislationCleanItemService;

	private int pageNo = 1;
	private int pageSize = 10;


	//LegislationCleanItem的修改
	@Action(value = "legislationCleanItem_add", results = {@Result(name = SUCCESS, location = "/LegislationCleanItem.jsp"), @Result(name = "List", location = "/legislationCleanItem_list.jsp")})
	public String legislationCleanItem_add() throws FzbDaoException {
//		System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<LegislationCleanItem> legislationCleanItemList = new ArrayList<LegislationCleanItem>();
		LegislationCleanItem legislationCleanItem = new LegislationCleanItem();
		legislationCleanItemService.add(legislationCleanItem);
		return SUCCESS;
	}

}
