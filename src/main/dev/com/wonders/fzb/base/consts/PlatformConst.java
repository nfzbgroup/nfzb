package com.wonders.fzb.base.consts;

/**
 * <p>
 * Title: 市府核心系统——业务监管平台
 * </p>
 * <p>
 * Description: 业务监管平台常量
 * </p>
 * 
 * @author scalffold
 */

public class PlatformConst {

	// 是
	public static final String Y = "1";

	// 否
	public static final String N = "0";

	public final static String ID_USER = "P";
	public final static String ID_TEAM = "T";
	
	/**
	 * 应用配置项
	 */
	/** 应用标识 **/
	public static final String APP_ID = "PLATFORM";
	/** 根team标识 **/
	public static final String BASE_TEAM = "BASE_TEAM";
	/** 根代码标识 **/
	public static final String BASE_CODE = "BASE_CODE";
	/** 单位类型代码标识 **/
	public static final String CODE_BASE_GROUP = "CODE_BASE_GROUP";
	/** 资源类型代码标识 **/
	public static final String CODE_BASE_RESOURCE = "CODE_BASE_RESOURCE";
	/** 资源类型代码标识 **/
	public static final String CODE_BASE_PORTAL = "CODE_BASE_PORTAL";
	/** 人员级别代码标识 **/
	public static final String CODE_BASE_LEVEL = "CODE_BASE_LEVEL";

	/** 系统配置源 */
	public static final String PLATFORM_CONFIG = "PLATFORM_CONFIG";
	/** 公共配置项 */
	public static final String CONFIG_COMMON = "CONFIG_COMMON";
	/** 领导排列序号 */
	public static final String CONFIG_LEADER = "CONFIG_LEADER";
	/** 超级查询处室 */
	public static final String CONFIG_SUPER_ORG = "CONFIG_SUPER_ORG";

	/**
	 * 公共配置项子项常量
	 */
	/** 收文默认办理时限 */
	public static final String CFG_BLSX = "CFG_BLSX";
	/** 安排表加晚上 */
	public static final String CFG_TB_NT = "CFG_TB_NT";
	/** 周几弹出活动安排 */
	public static final String CFG_ACT_WEEK = "CFG_ACT_WEEK";
	/** 提前几天会议提醒 */
	public static final String CFG_MEET_REMIND = "CFG_MEET_REMIND";
	/** 启动弹出公告功能 */
	public static final String CFG_OUT_BLT = "CFG_OUT_BLT";
	/** 公告默认弹出天数 */
	public static final String CFG_BLT_OUT_DAY = "CFG_BLT_OUT_DAY";
	/** 收文流转记录各环节滞留时限 */
	public static final String CFG_HJSX = "CFG_HJSX";
	/** 列表页面每页显示记录的数量(条) */
	public static final String CFG_COUNT_PER_PAGE = "CFG_COUNT_PER_PAGE";
	/** 从上级领导往下级领导流转收文的类别（类别间以逗号隔开） */
	public static final String CFG_REC_TYPE_FORMUP = "CFG_REC_TYPE_FORMUP";
	/** 从下级领导往上级领导流转收文的类别（类别间以逗号隔开） */
	public static final String CFG_REC_TYPE_FORMDOWN = "CFG_REC_TYPE_FORMDOWN";
	/** 档案系统地址 */
	public static final String CFG_PROFILE_URL = "CFG_PROFILE_URL";
	/**
	 * 单位类型常量
	 */
	/** 国资委 **/
	public static final String GROUP_XZCS = "行政处室";
	public static final String GROUP_DWCS = "党委处室";
	public static final String GROUP_LD = "领导";
	public static final String GROUP_GOV = "市府办公厅";
	public static final String GROUP_DIS = "区县";
	public static final String GROUP_COM = "委办局";
	public static final String GROUP_LEVEL = "级别";
	public static final String GROUP_UNIT = "业务处室";
	public static final String GROUP_FZB = "市法制办";
	/**
	 * System Properties配置项
	 */
	public static final String DEBUG_MODE = "WEGOV.PLATFORM.DEBUG_MODE";
	public static final String DEBUG_EVENT = "WEGOV.PLATFORM.DEBUG_EVENT";
	public static final String DEBUG_SOURCE = "WEGOV.PLATFORM.DEBUG_SOURCE";
	/**
	 * 常量
	 */
	/** 日志事件源 **/
	public static final String C_SOURCE = "事件源";
	public static final String C_THEME = "主题词";
	/** 数据源 **/
	public static final String C_DATASOURCE = "wegovDataSource";
	/**
	 * 平台事件名称
	 */
	public static final String C_GETLOGS = "日志查看";
	public static final String C_REMOVELOGS = "日志删除";
	public static final String C_USERLOGIN = "用户登录";
	public static final String C_USERLOGOUT = "用户登出";
	public static final String C_ADDUSER = "新增用户";
	public static final String C_REMOVEUSER = "注销用户";
	public static final String C_MODIFYUSER = "修改用户信息";
	public static final String C_USERMODIFYPWD = "用户修改密码";
	public static final String C_ADDUNIT = "新增单位";
	public static final String C_REMOVEUNIT = "删除单位";
	public static final String C_MODIFYUNIT = "修改单位信息";
	public static final String C_ADDUSERTOUNIT = "向单位添加用户";
	public static final String C_REMOVEUSERFROMUNIT = "从单位删除用户";
	public static final String C_MODIFYUSERLEVEL = "修改用户级别";
	public static final String C_ADDROLE = "新增角色";
	public static final String C_REMOVEROLE = "删除角色";
	public static final String C_MODIFYROLE = "修改角色定义";
	public static final String C_ASSIGNROLE = "角色分配";
	public static final String C_ADDCONFIGITEM = "新增应用配置项";
	public static final String C_REMOVECONFIGITEM = "删除应用配置项";
	public static final String C_MODIFYCONFIGITEM = "修改应用配置项";
	public static final String C_ADDCODEITEM = "新增代码项";
	public static final String C_REMOVECODEITEM = "删除代码项";
	public static final String C_MODIFYCODEITEM = "修改代码项";
	// 日志事件优先级
	public final static byte C_PRIORITY_HIGH = 0; // 高优先级
	public final static byte C_PRIORITY_MEDIUM = 1; // 中优先级
	public final static byte C_PRIORITY_LOW = 2; // 低优先级
	public final static byte C_PRIORITY_NORMAL = 3; // 普通事件
	/**
	 * 提醒方式
	 */
	public static final String NOTICE_MODE_ROLLBAR = "01";
	public static final String NOTICE_MODE_POPWIN = "02";
	/**
	 * 用户标识前缀
	 */
	public static final String USER_ID_PREFIX = "P";
	/**
	 * 单位标识前缀
	 */
	public static final String UNIT_ID_PREFIX = "U";

	public static final String NOTICE_OBJECT_UNIT = UNIT_ID_PREFIX;
	public static final String NOTICE_OBJECT_USER = USER_ID_PREFIX;

	static public final String QUERY_OP_LIKE = "Like";
	static public final String QUERY_OP_EQUAL = "equal";

	/**
	 * 分隔符
	 */
	public static final char PLATFORM_SPLIT = '#';

	/** 级别常量定义 */

	/**
	 * 国家级领导
	 */
	public static final String LEADER_LEVEL_NATION = "国家级领导";
	/**
	 * 市长
	 */
	public static final String LEADER_LEVEL_MAYOR = "市长";
	/**
	 * 副市长
	 */
	public static final String LEADER_LEVEL_VICE_MAYOR = "副市长";
	/**
	 * 市级领导
	 */
	public static final String LEADER_LEVEL = "市级领导";
	/**
	 * 正厅级
	 */
	public static final String LEADER_LEVEL_OFFICE = "正厅级";
	/**
	 * 副厅级
	 */
	public static final String LEADER_LEVEL_VICE_OFFICE = "副厅级";
	/**
	 * 正处级
	 */
	public static final String LEADER_LEVEL_DEPART = "正处级";
	/**
	 * 副处级
	 */
	public static final String LEADER_LEVEL_VICE_DEPART = "副处级";
	/**
	 * 正科级
	 */
	public static final String LEADER_LEVEL_SECTION = "正科级";
	/**
	 * 副科级
	 */
	public static final String LEADER_LEVEL_VICE_SECTION = "副科级";

	/**
	 * 工作人员
	 */
	public static final String LEADER_LEVEL_WORKER = "工作人员";

	public static final String LEADER_LEVEL_GZB = "办级领导";

	public static final String LEADER_LEVEL_UNIT = "处长";

	public static final String LEADER_LEVEL_UNIT_VICE = "副处长";
	/** 页面定义 **/
	/**
	 * 档案管理归档
	 */
	public static final String URL_PROFILE_ARCHIVE = "档案管理归档";
	/**
	 * 档案管理撤销归档
	 */
	public static final String URL_PROFILE_ARCHIVE_WITHDRAW = "档案管理撤销归档";
	/**
	 * 添加到我的收藏夹
	 */
	public static final String URL_PORTAL_ADDTO_FAVORITE = "添加到我的收藏夹";
	/**
	 * 添加到我的事件跟踪列表
	 */
	public static final String URL_PORTAL_ADDTO_TRACE = "添加到我的事件跟踪列表夹";

	/** 关系定义 **/
	/**
	 * B是A的秘书
	 */
	public static final String RE_SECRET = "SC";
	/**
	 * B是A的分管处室
	 */
	public static final String RE_SECTION_MNG = "SM";
	/**
	 * B是A的分管委办局
	 */
	public static final String RE_DISTRICT_MNG = "DM";

	/*
	 * * --------------系统运行模式（党委或者行政）------------------ add by aning ok,20030718
	 * 党委模式
	 */
	public static final String FORM_PARTY = "PARTY";

	/**
	 * 行政模式
	 */
	public static final String FORM_ADMIN = "ADMIN";

	/*
	 * * --------------工作角色（党委、双角色）------------------ /** 双角色
	 */
	public static final String ROLE_MODE_DB = "DB_MODE";

	/**
	 * 党委角色
	 */
	public static final String ROLE_MODE_DW = "DW_MODE";

}