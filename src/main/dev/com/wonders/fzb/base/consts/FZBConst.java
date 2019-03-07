package com.wonders.fzb.base.consts;

import java.util.HashMap;

/**
 * 法制办通用常量
 * @author ZSW
 */
public class FZBConst {
	/**
	 * 法制办大平台下系统模块相关常量类
	 */
	public static class SystemModule{
		
		/**
		 * 行政执法人员信息管理系统
		 */
		public static final String ZFRY = "execlaw";
		
		/**
		 * 行政复议信息管理系统
		 */
		public static final String XZFY = "reviewcase";
		
		/**
		 * 测试系统
		 */
		public static final String TESTING = "testing";
	}
	
	public  static final String BASE_LOGIN_INFO = "currentPerson";
	
	public static final HashMap<String,String> SYSTEM_NAME = new HashMap<String, String>();
	public static final HashMap<String,String> LOGIN_INFO = new HashMap<String, String>();
	
	static {
		SYSTEM_NAME.put(SystemModule.ZFRY, "行政执法人员信息管理系统");
		LOGIN_INFO.put(SystemModule.ZFRY, SystemModule.ZFRY+"."+BASE_LOGIN_INFO);
		
		SYSTEM_NAME.put(SystemModule.TESTING, "测试系统");
		LOGIN_INFO.put(SystemModule.TESTING, SystemModule.TESTING+"."+BASE_LOGIN_INFO);
	}
	
	/**法制办信息网-网站地址*/
	public static String WEB_ADD_PUB="http://www.shanghailaw.gov.cn:999/fzb/";
	
	
	/**法制办信息网-英文网站地址*/
	public static String WEB_ADD_ENG="http://www.sial.sh.cn/fzbEnglish/index.html";
	
	/**法制研究所*/
	public static String WEB_ADD_YJS="http://www.sial.sh.cn/yjsChinese/index.jsp";
	
}