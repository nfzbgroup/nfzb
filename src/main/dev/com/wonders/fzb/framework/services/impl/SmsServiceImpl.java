package com.wonders.fzb.framework.services.impl;

import java.util.Date;

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.framework.services.SmsService;


@Service("smsService")
@Transactional
public class SmsServiceImpl implements SmsService {
	
	private static String wsdlUrl = "http://10.200.254.21:9090/ucp/webservices/UCPCXFService?wsdl";
//	private static String wsdlUrl = "http://10.200.254.21:9090/ucp/webservices/UCPCXFService?wsdl=UCPCXFService.wsdl";
	private static String account = "fzbsms";
	private static String password = "111111b";
	
	/**
	 * 短信平台登录
	 * @return sessionId
	 */
	public String login() throws Exception {
		String sessionId = "";
		QName SERVICE_NAME = new QName("http://cxf.service.webucp.wondersgroup.com/","login");
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient(wsdlUrl);
		Object[] objects = client.invoke(SERVICE_NAME, account , password);
		sessionId = (String)objects[0];
		return sessionId;
	}
	
	/**
	 * 短信平台注销
	 * @param sessionId
	 */
	public void logoff(String sessionId) throws Exception {
		QName SERVICE_NAME = new QName("http://cxf.service.webucp.wondersgroup.com/","logoff");
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient(wsdlUrl);
		Object[] objects = client.invoke(SERVICE_NAME, sessionId);
	}
	
	/**
	 * 发送即时短信
	 * @param sessionId
	 * @param receiveNumbers 发送目标号码，可以发送多个目标，用半角逗号分隔
	 * @param content 发送短信内容
	 */
	public String sendProxySMS(String sessionId, String receiveNumbers, String content) throws Exception {
		QName SERVICE_NAME = new QName("http://cxf.service.webucp.wondersgroup.com/","sendProxySMS");
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient(wsdlUrl);
		Object[] objects = client.invoke(SERVICE_NAME, sessionId , receiveNumbers,content);
		String smsTaskId = (String)objects[0];
		return smsTaskId;
	}
	
	/**
	 * 发送定时短信
	 * @param sessionId
	 * @param receiveNumbers 发送目标号码，可以发送多个目标，用半角逗号分隔
	 * @param sendTime 发送时间，可以定时发送，如果值为null则认定为立刻发送
	 * @param content
	 */
	public String sendSMS(String sessionId, String receiveNumbers, Date sendTime,String content) throws Exception {
		QName SERVICE_NAME = new QName("http://cxf.service.webucp.wondersgroup.com/","sendSMS");
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient(wsdlUrl);
		Object[] objects = client.invoke(SERVICE_NAME, sessionId, "NORM",receiveNumbers, sendTime,content,  null, false);
		String smsTaskId = (String)objects[0];
		return smsTaskId;
	}
	
}