package com.wonders.fzb.demo.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.framework.services.TeamInfoService;
import com.wonders.fzb.framework.services.UserInfoService;

@Namespace("/demo")
@Controller
@Scope("prototype")
public class DemoAction extends BaseAction {

	private static final long serialVersionUID = 6649961308267765778L;

	@Action(value = "charts", results = {@Result(name = SUCCESS, location = "/demo/charts.jsp")})
	public String execute() {
		
		return SUCCESS;
	}
	
	
	@Action(value = "select", results = {@Result(name = SUCCESS, location = "/demo/select.jsp")})
	public String select() {
		
		return SUCCESS;
	}
}