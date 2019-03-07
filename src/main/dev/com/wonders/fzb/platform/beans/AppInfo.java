package com.wonders.fzb.platform.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 操作业务实体
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WEGOV_P_APP_INFO")
public class AppInfo implements Serializable {

	/**
	 * 构造一个空的函数，每一项赋空的值
	 */
	public AppInfo() {
	}
	
	/**
     * APP_ID
     */
    @Id
    @GenericGenerator(name = "id", strategy = "assigned") 
    @GeneratedValue(generator = "id")  
    @Column(name = "APP_ID")
    public String appId;
    
    /**
     * APP_NAME
     */
    @Column(name = "APP_NAME")
    public String appName;
    
    /**
     * ADAPTER_CLASS
     */
    @Column(name = "ADAPTER_CLASS")
    public String adapterClass;
    
    /**
     * APP_URL
     */
    @Column(name = "APP_URL")
    public String appUrl;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAdapterClass() {
		return adapterClass;
	}

	public void setAdapterClass(String adapterClass) {
		this.adapterClass = adapterClass;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	
}