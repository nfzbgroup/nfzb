package com.wonders.fzb.framework.actions;

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

import com.wonders.fzb.base.actions.FileManageAction;
import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.base.kit.Base64;
import com.wonders.fzb.framework.beans.Contact;
import com.wonders.fzb.framework.beans.MOR;
import com.wonders.fzb.framework.beans.OUR;
import com.wonders.fzb.framework.beans.TeamInfo;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.framework.services.ContactService;
import com.wonders.fzb.framework.services.TeamInfoService;
import com.wonders.fzb.framework.services.UserInfoService;

@Namespace("/contact")
@Controller
@Scope("prototype")
public class ContactAction extends FileManageAction {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	@Qualifier("contactService")
	private ContactService contactService;
	
	@Autowired
	@Qualifier("teamInfoService")
	private TeamInfoService teamInfoService;
	
	@Autowired
	@Qualifier("userInfoService")
	private UserInfoService userInfoService;
	
	@Action(value = "getModuleContact", results = {
			@Result(name = SUCCESS, location = "/platform/contact/stat_all.jsp")
	})
	public String getModuleContact() {//返回list
		UserInfo currentUser = getLoginUser();
		//moduleId,测试使用执法人员信息管理平台 MODULE_EXECLAW
		TeamInfo currentTeam = new TeamInfo();
		if(currentUser.getTeamInfos().size()>0) {
			currentTeam = currentUser.getTeamInfos().get(0);
		}
		String unitId = currentTeam.getId();
		Map<String,Object> condMap = new HashMap<String,Object>();
		Map<String,String> sortMap = new LinkedHashMap<String,String>();
		if(!"U0".equals(unitId)) {//市法制办查询所有   不是zTree
			condMap.put("teamId"+"Like", unitId);//默认看到本单位及以下单位联系人	
		}
		condMap.put("userStatus", "Y");//有效
		condMap.put("contactType","个人");//通讯录分两级（三级最好）：一级单位，二级人员(或者一、二级单位、三级人员)
		sortMap.put("position","DESC");
		List<Contact> contactList = contactService.findByList(condMap, sortMap, "MODULE_EXECLAW");
		request.setAttribute("list", contactList);
		return SUCCESS;
	}
	
	@Action(value = "getContactDetail", results = {
			@Result(name = SUCCESS, location = "/platform/contact/contact_detail.jsp")
	})
	public String getContactDetail() throws NumberFormatException, FzbDaoException {//返回list或者Page
		String pageSize = request.getParameter("pageSize");
		String pageNo = request.getParameter("pageNo");
		if (null == pageSize || "".equals(pageSize)) {
			pageSize = "10";
		}
		if (null == pageNo || "".equals(pageNo)) {
			pageNo = "1";
		}
		Map<String,Object> condMap = new HashMap<String,Object>();
		Map<String,String> sortMap = new LinkedHashMap<String,String>();
		UserInfo currentUser = getLoginUser();
		TeamInfo currentTeam = null;
		if(currentUser.getTeamInfos().size()>0) {
			currentTeam = currentUser.getTeamInfos().get(0);
		}else {
			currentTeam = new TeamInfo();
		}
		String cuTeamId =  currentTeam.getId();
		if(!"U0".equals(cuTeamId)) {
			condMap.put("teamId", cuTeamId);
		}
		condMap.put("userStatus", "Y");//有效
		condMap.put("contactType","个人");//通讯录分两级（三级最好）：一级单位，二级人员(或者一、二级单位、三级人员)
		sortMap.put("position", "DESC");
		//本单位
		List<Contact> cuContacts = contactService.findByList(condMap, sortMap, "MODULE_EXECLAW");
		//平级及下级
		condMap.clear();//可以不clear
		if(!"U0".equals(cuTeamId)) {
			condMap.put("teamId" + "Like", cuTeamId);
		}
		condMap.put("userStatus", "Y");//有效
		condMap.put("contactType","个人");//通讯录分两级（三级最好）：一级单位，二级人员(或者一、二级单位、三级人员)
		sortMap.put("position", "DESC");
		//本单位及下级单位
		List<Contact> contacts = contactService.findByList(condMap, sortMap, "MODULE_EXECLAW");
		Page<Contact> retPage = contactService.findContactPage(condMap, sortMap, Integer.parseInt(pageNo), Integer.parseInt(pageSize), "MODULE_EXECLAW");
		request.setAttribute("name", currentTeam.getTeamName());
		request.setAttribute("cuContacts", cuContacts);
		request.setAttribute("contacts", contacts);
		request.setAttribute("retPage", retPage);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
		return SUCCESS;
	}
	
	@Action(value = "getPersonDetail", results = {
			@Result(name = SUCCESS, location = "/platform/contact/person_detail.jsp")
	})
	public String getPersonDetail() {//返回list
		String contactId = request.getParameter("contactId")==null?"":request.getParameter("contactId");
		Contact contact = contactService.findById(contactId);
		if(contact == null) {
			contact = new Contact();
		}
		request.setAttribute("contact", contact);
		return SUCCESS;
	}
	
	@Action(value = "personModify", results = {
			@Result(name = SUCCESS, location = "/platform/contact/person_detail.jsp")
	})
	public String personModify() {//返回list
		String contactId = request.getParameter("contactId")==null?"":request.getParameter("contactId");
		String vartar = request.getParameter("vartar")==null?"":request.getParameter("vartar");
		Contact contact = contactService.findById(contactId);
		UserInfo currentUser = getLoginUser();
		TeamInfo currentTeam = null;
		if(currentUser.getTeamInfos().size()>0) {
			currentTeam = currentUser.getTeamInfos().get(0);
		}else {
			currentTeam = new TeamInfo();
		}
		String teamId = currentTeam.getId();
		if(contact == null) {
			contact = new Contact();
		}
		if("vartar".equals(vartar)&&teamId.equals(contact.getTeamId())) {//修改头像
			byte[] file = upload(); //对字节数组Base64编码
	        StringBuilder builder = new StringBuilder();
	        builder.append(Base64.encode(file));
	        contact.setAvatar(builder.toString()); //返回Base64编码过的字节数组字符串	
	        contactService.updateContact(contact);
	        request.setAttribute("msg", "修改成功！");
		}else if("NotVarTar".equals(vartar)){//修改基本信息
			String name = request.getParameter("name")==null?"":request.getParameter("name");
			String teamName = request.getParameter("teamName")==null?"":request.getParameter("teamName");
			String memo = request.getParameter("memo")==null?"":request.getParameter("memo");
			String landLine = request.getParameter("landLine")==null?"":request.getParameter("landLine");
			String mobilePhone = request.getParameter("mobilePhone")==null?"":request.getParameter("mobilePhone");
			String position = request.getParameter("position")==null?"10000":request.getParameter("position");
			String status = request.getParameter("status")==null?"":request.getParameter("status");
			contact.setName(name);
			contact.setTeamName(teamName);
			contact.setMemo(memo);
			contact.setLandLine(landLine);
			contact.setMobilePhone(mobilePhone);
			contact.setPosition(Integer.parseInt(position));
			if("有效".equals(status)) {
				status = "Y";
			}else if("失效".equals(status)){
				status = "N";
			}
			contact.setUserStatus(status);
			contactService.updateContact(contact);//更新
		}else {
			request.setAttribute("msg", "抱歉，您没有修改权限，默认本单位修改本单位通讯录信息");
		}
		request.setAttribute("contact", contact);
		return SUCCESS;
	}
	//添加的需要另外写方法
	@Action(value = "contactAdd", results = {
			@Result(name = SUCCESS, location = "/platform/contact/contact_add.jsp")
	})
	public String contactAdd() {
		String name = request.getParameter("name")==null?"":request.getParameter("name");
		String teamName = request.getParameter("teamName")==null?"":request.getParameter("teamName");
		String memo = request.getParameter("memo")==null?"":request.getParameter("memo");
		String landLine = request.getParameter("landLine")==null?"":request.getParameter("landLine");
		String mobilePhone = request.getParameter("mobilePhone")==null?"":request.getParameter("mobilePhone");
		String position = request.getParameter("position")==null?"":request.getParameter("position");
		if("".equals(position)) {
			position = "0";
		}
		String status = request.getParameter("status")==null?"":request.getParameter("status");
		String parentId = request.getParameter("parentId")==null?"":request.getParameter("parentId");
		String contactType = request.getParameter("contactType")==null?"":request.getParameter("contactType");
		String email = request.getParameter("email")==null?"":request.getParameter("email");
		String vartar = request.getParameter("vartar")==null?"":request.getParameter("vartar");
		Map<String,Object> condMap = new HashMap<String,Object>();
		condMap.put("showName", teamName);
		condMap.put("moduleId", "MODULE_EXECLAW");//执法人员
		List<MOR> morList =  teamInfoService.findMorList(condMap, null);
		MOR mor = new MOR();
		if(morList.size()>0) {
			mor = morList.get(0);
		}
		String morelateId = mor.getId();
		String teamId = mor.getTeamCid();
		List<OUR> ourList = userInfoService.findOurByMorId(morelateId);
		OUR our = new OUR();
		if(ourList.size()>0) {
			our = ourList.get(0);
		}
		Contact contact = new Contact();
		if("NotVarTar".equals(vartar)) {
			String userId = our.getUserId();
			contact.setName(name);
			contact.setTeamName(teamName);
			contact.setTeamId(teamId);
			contact.setContactType(contactType);
			contact.setEmail(email);
			contact.setLandLine(landLine);
			contact.setMemo(memo);
			contact.setMobilePhone(mobilePhone);
			contact.setModuleId("MODULE_EXECLAW");
			contact.setMorelateId(morelateId);
			contact.setParentId(parentId);
			contact.setPosition(Integer.parseInt(position));
			contact.setUserId(userId);
			if("有效".equals(status)) {
				status = "Y";
			}else if("失效".equals(status)){
				status = "N";
			}
			contact.setUserStatus(status);
			byte[] file = upload(); //对字节数组Base64编码
			if(file!=null) {
		        StringBuilder builder = new StringBuilder();
		        builder.append(Base64.encode(file));
		        contact.setAvatar(builder.toString()); //返回Base64编码过的字节数组字符串	
			}
			contactService.addContact(contact);
		}
		request.setAttribute("contact", contact);
		return SUCCESS;
	}
}
