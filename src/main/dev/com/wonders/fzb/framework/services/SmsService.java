package com.wonders.fzb.framework.services;

import java.util.Date;

public interface SmsService {
		
	/**
	 * SMS平台登录
	 * @throws Exception 
	 */
	public String login() throws Exception;
	
	/**
	 * 短信平台注销
	 * @param sessionId
	 */
	public void logoff(String sessionId) throws Exception;
	
	/**
	 * 发送即时短信
	 * @param sessionId
	 * @param receiveNumbers 发送目标号码，可以发送多个目标，用半角逗号分隔
	 * @param content 发送短信内容
	 */
	public String sendProxySMS(String sessionId, String receiveNumbers, String content) throws Exception;
	
	/**
	 * 发送定时短信
	 * @param sessionId
	 * @param receiveNumbers 发送目标号码，可以发送多个目标，用半角逗号分隔
	 * @param sendTime 发送时间，可以定时发送，如果值为null则认定为立刻发送
	 * @param content
	 */
	public String sendSMS(String sessionId, String receiveNumbers, Date sendTime,String content) throws Exception;
	
}