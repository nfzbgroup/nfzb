package com.wonders.fzb.platform.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.platform.beans.WorkdayInfo;
import com.wonders.fzb.platform.dao.WorkdayInfoDao;
import com.wonders.fzb.platform.services.WorkdayInfoService;
//import com.wonders.fzb.platform.services.impl.WorkdayInfoServiceImpl;
//import com.wonders.fzb.reviewsh.litigation.beans.Litigate;
//import com.wonders.fzb.reviewsh.litigation.services.LitigateService;
//import com.wonders.fzb.reviewsh.portal.services.ReviewPortalCodeService;

/**
 * `
 */
@Controller

@Namespace("/workday")
@Scope("prototype")
public class WorkdayAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;

	@Autowired
	@Qualifier("workdayInfoService")
	private WorkdayInfoService workdayInfoService;

	private int pageNo = 1;
	private int pageSize = 10;

	@Action(value = "workday", results = { @Result(name = SUCCESS, location = "/reviewsh/day/workday_list.jsp") })
	public String workday() throws FzbDaoException, ParseException {
		return SUCCESS;
	}

	// 查询
	@Action(value = "workdayList")
	public void findAll() throws IOException {
		Map<String, Object> condMap = new HashMap<String, Object>();
		Map<String, String> sortMap = new LinkedHashMap<String, String>();
		sortMap.put("stDay", "ASC");
		JSONArray result = JSONArray.fromObject(workdayInfoService.findByList(condMap, sortMap));
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(result.toString());
	}

	// 添加
	@Action(value = "workdayAdd")
	public void workdayAdd() throws IOException, ParseException {
		String result = "";
		String stDay = request.getParameter("day") == null ? "" : request.getParameter("day");
		WorkdayInfo workday = new WorkdayInfo();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		workday.setStDay(stDay);
		boolean ok = workdayInfoService.saveChange(workday);
		if (ok) {
			result = "Y";
		} else {
			result = "N";
		}

		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(result);
	}
}
