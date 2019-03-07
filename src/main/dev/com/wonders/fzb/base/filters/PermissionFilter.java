package com.wonders.fzb.base.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wonders.fzb.framework.beans.UserInfo;

public class PermissionFilter implements Filter {

	@Override
	public void destroy() {

	}

	@SuppressWarnings("unused")
	@Override
	public void doFilter(ServletRequest req, ServletResponse rep, FilterChain filter) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) rep;
		// System.out.println("url--->"+request.getRequestURI());
		UserInfo UserInfo = (UserInfo) request.getSession().getAttribute("currentPerson");
		// System.out.println("UserInfo.getName()--->"+UserInfo.getName());
		List<String> U0List = new ArrayList<String>();
		U0List.add("/execlaw/card_person_stat_pie.do");
		U0List.add("/execlaw/person_stat.do");
		U0List.add("/execlaw/doing_card_query.do");
		U0List.add("/execlaw/card_stat.do");
		U0List.add("/execlaw/guo_card_stat.do");
		U0List.add("/execlawDealwith/dealwithCardGive.do");
		U0List.add("/execlawDealwith/dealwith_alert_list.do");
		U0List.add("/execlawDealwith/dealwith_task_detail.do");
		U0List.add("/execlawDealwith/query_dealwith.do");
		U0List.add("/execlaw/unit_check.do");
		U0List.add("/execlaw/unit_changeleader_list.do");
		U0List.add("/execlaw/pay_sms_read.do");
		U0List.add("/execlaw/pay_applyleader_list.do");
		U0List.add("/execlaw/pay_applycheck_list.do");
		List<String> downUnitList = new ArrayList<String>();
		downUnitList.add("/execlaw/qx_card_stat.do");
		downUnitList.add("/execlaw/pay_dealwith_notify.do");
		downUnitList.add("/execlaw/pay_apply_list.do");
		boolean flag = true;
		// if (request.getRequestURI().indexOf("/modules/login.do") < 0) { // 非登录请求
		if (request.getRequestURI().indexOf("log_loading.jsp") < 0 && request.getRequestURI().indexOf("log.jsp") < 0 && request.getRequestURI().indexOf("normaldoc_opinion_iframe.do") < 0 && request.getRequestURI().indexOf("shLaw_list.do") < 0 && request.getRequestURI().indexOf("SHLaw_iframe.do") < 0) {
			if (request.getRequestURI().indexOf(".do") != -1) {// .do请求
				if (null == UserInfo) {
					String path = request.getContextPath();
					String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
					response.sendRedirect(basePath + "/execlaw/logout.jsp");
					return;
				} else {
					if (!"上海市人民政府法制办公室".equals(UserInfo.getName())) {// 非admin
						for (int i = 0; i < U0List.size(); i++) {
							String dos = U0List.get(i);
							if (request.getRequestURI().indexOf(dos) < 0) {
								flag = true;
							} else {
								flag = false;
								break;
							}
						}
					} else {// admin
						for (int i = 0; i < downUnitList.size(); i++) {
							String dos = downUnitList.get(i);
							if (request.getRequestURI().indexOf(dos) < 0) {// 不包含
								flag = true;
							} else {
								flag = false;
								break;
							}
						}
					}
					if (!flag) {// 不包含
						String path = request.getContextPath();
						String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
						response.sendRedirect(basePath + "/execlaw/logout.jsp");
						return;
					}
				}
			}
		}
		filter.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
