package com.wonders.fzb.legislation;

public class LegislateConst {
	/*
	 * 草案发起范本类型(数据库存储)
	 */
	public static String Draft_Add_Model_key_01 = "报请审核规章草案的函";
	public static String Draft_Add_Model_key_02 = "草案文本";
	public static String Draft_Add_Model_key_03 = "起草说明";
	public static String Draft_Add_Model_key_04 = "拟定规章所依据的法律、法规目录";
	public static String Draft_Add_Model_key_05 = "征求各方面意见的情况和材料";
	public static String Draft_Add_Model_key_06 = "参与起草工作的专家对规章草案的意见";
	public static String Draft_Add_Model_key_07 = "需要报送的其他材料";

	public static String Draft_PUBLIC_OPINION_key_01 = "背景介绍";
	public static String Draft_PUBLIC_OPINION_key_02 = "上网公告";
	public static String Draft_PUBLIC_OPINION_key_03 = "新闻统发稿";
	public static String Draft_PUBLIC_OPINION_key_04 = "最后采纳情况";
	public static String Draft_PUBLIC_OPINION_key_05 = "公开征求意见工作单";
	
	
	public static String Draft_AuditMeeting_key_01 = "草案文本";
	public static String Draft_AuditMeeting_key_02 = "起草说明/审查报告";
	public static String Draft_AuditMeeting_key_04 = "审核意见";
	public static String Draft_AuditMeeting_key_05 = "采纳意见";
	
	/**
	 *听证会环节 
	 */
	//听证会前
	public static String Draft_HEARING_key_01 = "听证会公告";
	public static String Draft_HEARING_key_02 = "议题及背景说明";
	public static String Draft_HEARING_key_03 = "单位报名表";
	public static String Draft_HEARING_key_04 = "个人报名表";
	//听证会后
	public static String Draft_HEARING_key_06 = "听证会召开公告(附代表名单)";
	
	/*****草案环节*****/
	//起草
	public static String ACTIVITY_TYPE_Draft_ADD = "起草";
	public static String ACTIVITY_TYPE_Draft_PUBLIC_OPINION = "网上征求意见";
	public static String ACTIVITY_TYPE_Draft_AuditMeeting_start = "审核会议前";
	public static String ACTIVITY_TYPE_Draft_AuditMeeting_end = "审核会议后";
	public static String ACTIVITY_TYPE_Draft_HEARING_start = "立法听证前";
	public static String ACTIVITY_TYPE_Draft_HEARING_end = "立法听证后";
	public static String ACTIVITY_TYPE_Draft_DEMONSTRATION_start = "专家论证前";
	public static String ACTIVITY_TYPE_Draft_DEMONSTRATION_end = "专家论证后";
	public static String ACTIVITY_TYPE_Draft_TeamOpinion = "单位意见";
	
	public static String ACTIVITY_TYPE_Draft_PUBLIC_OPINION_DONE = "网上征求意见采纳情况";
	
	/*****立法计划*****/
	public static String ACTIVITY_TYPE_Plan_ADD = "立法发起";
	
	/*
	 * 立法计划发起范本类型(数据库存储)
	 */
	public static String Plan_Add_Model_key_01 = "规章制定计划项目申报表";
	public static String Plan_Add_Model_key_02 = "调研论证报告";
	public static String Plan_Add_Model_key_03 = "规章草案";
	public static String Plan_Add_Model_key_09 = "其它需要报送的材料";
	
	/*****处室编号*****/
	//综合处
	public static String TEAM_ID_ZHC = "U_3_1";
	//经济法规处
	public static String TEAM_ID_JJFGC = "U_3_5";
	//城建法规处
	public static String TEAM_ID_CJFGC = "U_3_6";
	//社会法规处
	public static String TEAM_ID_SHFGC = "U_3_7";
	
	/*****任务状态*****/
	//任务初始状态
	public static String TASK_STATUS_NEW = "0";
	//任务完成状态
	public static String TASK_STATUS_DONE = "1";
	
	/*****草案、计划状态*****/
	//初始状态
	public static String STATUS_INIT = "init";
	//上报
	public static String STATUS_SEND = "send";
	//接收
	public static String STATUS_RECEIVE = "receive";
	//分办
	public static String STATUS_DISE = "dise";
	//认领
	public static String STATUS_CLAIM = "claim";
	//办理
	public static String STATUS_PROCESS = "process";
	//完成
	public static String STATUS_DONE = "done";
	
	
	/*****单位意见反馈-状态*****/
	//发送
	public static String STATUS_TEAMOPINION_SEND = "send";
	//接收
	public static String STATUS_TEAMOPINION_RECEIVE = "receive";
	//反馈
	public static String STATUS_TEAMOPINION_FEEDBACK = "feedback";
	
	/*****立法平台-系统标示*****/
	public static String SYSTEM_MODULE = "MODULE_LEGISLATE";


}
