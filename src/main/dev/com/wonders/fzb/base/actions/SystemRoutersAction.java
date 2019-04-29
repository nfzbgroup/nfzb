package com.wonders.fzb.base.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wonders.fzb.base.consts.FZBConst;

/**
 * 系统路由器Action
 * 用于控制转发系统
 * @author ZSW
 */
@Namespace("/")
@Controller
@Scope("prototype")
public class SystemRoutersAction extends BaseAction {

	private static final long serialVersionUID = -2747389183820494042L;

	@Action(value="index",results={
			@Result(name = FZBConst.SystemModule.ZFRY, location = "/"+FZBConst.SystemModule.ZFRY+"/index.html",type="redirect") ,
			@Result(name = FZBConst.SystemModule.TESTING, location = "/"+FZBConst.SystemModule.TESTING+"/index.html",type="redirect") 
	})
	public String routing(){
		session.setAttribute("modulePath", "/"+request.getParameter("module") + "/" + request.getParameter("module"));
		//System.out.println("/"+request.getParameter("module")+"/index.html");
		//System.out.println(request.getParameter("module"));
		return request.getParameter("module");
	}
}