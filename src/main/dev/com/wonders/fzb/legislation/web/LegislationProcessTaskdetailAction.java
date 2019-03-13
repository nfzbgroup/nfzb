package com.wonders.fzb.legislation.web;

import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislation.beans.LegislationProcessTaskdetail;
import com.wonders.fzb.legislation.services.LegislationProcessTaskdetailService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * LegislationProcessTaskdetail action接口
 * @author scalffold created by lj
 */
 
@Namespace("/legislationProcessTaskdetail")
@Controller
@Scope("prototype")
public class LegislationProcessTaskdetailAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationProcessTaskdetailService")
	private LegislationProcessTaskdetailService legislationProcessTaskdetailService;

	private int pageNo = 1;
	private int pageSize = 10;


	//LegislationProcessTaskdetail的修改
	@Action(value = "legislationProcessTaskdetail_add", results = {@Result(name = SUCCESS, location = "/LegislationProcessTaskdetail.jsp"), @Result(name = "List", location = "/legislationProcessTaskdetail_list.jsp")})
	public String legislationProcessTaskdetail_add() throws FzbDaoException {
//		System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<LegislationProcessTaskdetail> legislationProcessTaskdetailList = new ArrayList<LegislationProcessTaskdetail>();
		LegislationProcessTaskdetail legislationProcessTaskdetail = new LegislationProcessTaskdetail();
		legislationProcessTaskdetailService.add(legislationProcessTaskdetail);
		return SUCCESS;
	}

}
