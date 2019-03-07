package com.wonders.fzb.base.dao.generator;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SnowflakeIdGenerator implements Configurable,IdentifierGenerator {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//前置符
	private String prefix;
	
	//间隔符
	private String symbol;
	
	//工作ID
	private long workerId = 10L;
	
	//数据中心ID
	private long datacenterId = 10L;
	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		SnowflakeIdWorker worker = new SnowflakeIdWorker(workerId, datacenterId);
		String id =  this.prefix + this.symbol + worker.nextId();
		logger.info("编号生成：｛{}｝，参数-前置符：{}，参数-间隔符：{}，参数-工作编号：{}，参数-数据中心编号：{}",id,prefix,symbol,workerId,datacenterId);
		return id;
	}

	@Override
	public void configure(Type type, Properties params, ServiceRegistry sr) throws MappingException {
		this.prefix = params.getProperty("prefix",""); 
		this.symbol = params.getProperty("symbol","");
		this.workerId =  Long.parseLong(params.getProperty("workerId", "1"));
		this.datacenterId = Long.parseLong(params.getProperty("datacenterId","1"));
	}

}