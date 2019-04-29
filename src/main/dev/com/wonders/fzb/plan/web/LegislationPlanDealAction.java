package com.wonders.fzb.plan.web;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.plan.beans.LegislationPlanDeal;
import com.wonders.fzb.plan.services.LegislationPlanDealService;

/**
 * LegislationPlanDeal action接口
 * @author scalffold created by lj
 */
 
@Namespace("/legislationPlanDeal")
@Controller
@Scope("prototype")
public class LegislationPlanDealAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationPlanDealService")
	private LegislationPlanDealService legislationPlanDealService;

//	private int pageNo = 1;
//	private int pageSize = 10;


	//LegislationPlanDeal的修改
	@Action(value = "legislationPlanDeal_add", results = {
			@Result(name = SUCCESS, location = "/LegislationPlanDeal.jsp"),
			@Result(name = "List", location = "/legislationPlanDeal_list.jsp") })
	public String legislationPlanDeal_add() throws FzbDaoException {
		// System.out.println("Begin....");
		// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// List<LegislationPlanDeal> legislationPlanDealList = new  ArrayList<LegislationPlanDeal>();
		LegislationPlanDeal legislationPlanDeal = new LegislationPlanDeal();
		legislationPlanDealService.add(legislationPlanDeal);
		return SUCCESS;
	}

}
