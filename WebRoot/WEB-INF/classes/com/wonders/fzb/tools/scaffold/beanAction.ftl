package ${packageStr}.web;

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
import ${packageStr}.beans.*;
import ${packageStr}.services.*;
import com.wonders.fzb.platform.beans.TeamInfo;
import com.wonders.fzb.platform.beans.UserInfo;

/**
 * ${table.tableTransName} action接口
 * @author scalffold created by lj
 */
 
@Namespace("/${table.tableTransNameLow}")
@Controller
@Scope("prototype")
public class ${table.tableTransName}Action extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("${table.tableTransNameLow}Service")
	private ${table.tableTransName}Service ${table.tableTransNameLow}Service;

	private int pageNo = 1;
	private int pageSize = 10;


	//${table.tableTransName}的修改
	@Action(value = "${table.tableTransName}_add", results = {@Result(name = SUCCESS, location = "/${table.tableTransName}.jsp"), @Result(name = "List", location = "/${table.tableTransName}_list.jsp")})
	public String person_add() throws FzbDaoException {
//		System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<String> unitTeamList = new ArrayList<String>();
		${table.tableTransName} ${table.tableTransNameLow} = new ${table.tableTransName}();
//		reviewCaseInfo.setStCause("scaffold create data");
//		System.out.println("use service....");
		${table.tableTransNameLow}Service.saveOrUpdate(${table.tableTransNameLow});
//		System.out.println("End....");
		return SUCCESS;
	}

}
