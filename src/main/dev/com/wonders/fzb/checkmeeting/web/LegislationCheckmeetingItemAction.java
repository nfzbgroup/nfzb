package com.wonders.fzb.checkmeeting.web;

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
import com.wonders.fzb.checkmeeting.beans.*;
import com.wonders.fzb.checkmeeting.services.*;

/**
 * LegislationCheckmeetingItem action接口
 * @author scalffold created by lj
 */
 
@Namespace("/legislationCheckmeetingItem")
@Controller
@Scope("prototype")
public class LegislationCheckmeetingItemAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationCheckmeetingItemService")
	private LegislationCheckmeetingItemService legislationCheckmeetingItemService;

	private int pageNo = 1;
	private int pageSize = 10;


	//LegislationCheckmeetingItem的修改
	@Action(value = "legislationCheckmeetingItem_add", results = {@Result(name = SUCCESS, location = "/LegislationCheckmeetingItem.jsp"), @Result(name = "List", location = "/legislationCheckmeetingItem_list.jsp")})
	public String legislationCheckmeetingItem_add() throws FzbDaoException {
//		System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<LegislationCheckmeetingItem> legislationCheckmeetingItemList = new ArrayList<LegislationCheckmeetingItem>();
		LegislationCheckmeetingItem legislationCheckmeetingItem = new LegislationCheckmeetingItem();
		legislationCheckmeetingItemService.add(legislationCheckmeetingItem);
		return SUCCESS;
	}

}
