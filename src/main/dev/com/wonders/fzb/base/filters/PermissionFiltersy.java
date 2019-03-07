package com.wonders.fzb.base.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wonders.fzb.platform.beans.FzbUserInfo;

//import com.wonders.drs.modules.core.beans.OrgInfo;
//import com.wonders.drs.modules.core.enums.SysConst;

public class PermissionFiltersy implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse rep, FilterChain filter) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) rep;
		// System.out.println("url--->" + request.getRequestURI());
		// System.out.println("是否是允许页面1aaaaaa" + request.getRequestURI().indexOf("log_loading.jsp"));
		// System.out.println("是否是允许页面2aaaaaaleft.jsp" + request.getRequestURI().indexOf("moduleth.html"));
		// 如果不是登录页面
		if (request.getRequestURI().indexOf("log_loading.jsp") < 0 && request.getRequestURI().indexOf("log.jsp") < 0 && request.getRequestURI().indexOf("normaldoc_opinion_iframe.do") < 0 && request.getRequestURI().indexOf("shLaw_list.do") < 0 && request.getRequestURI().indexOf("SHLaw_iframe.do") < 0) {
			// 如果是.do结尾
			if (request.getRequestURI().indexOf(".do") != -1 || request.getRequestURI().indexOf("left.jsp") != -1 || request.getRequestURI().indexOf("moduleo.html") != -1 || request.getRequestURI().indexOf("moduleth.html") != -1) {
				FzbUserInfo fzbPerson = (FzbUserInfo) request.getSession().getAttribute("fzbPerson");
				if (null == fzbPerson) {
					String path = request.getContextPath();
					String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
					response.sendRedirect(basePath + "/execlaw/index.html");
					return;
				}
			}
		}
		filter.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
