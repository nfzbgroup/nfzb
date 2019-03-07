package com.wonders.fzb.platform.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.hibernate.annotations.GenericGenerator;

/**
 * 操作业务实体
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WEGOV_P_APP_CONFIG")
@IdClass(AppConfigPK.class)
public class AppConfig implements Serializable {

	public AppConfig() {
	}

	/**
     * APP_ID
     */
    @Id
    public String appId;
    
    /**
     * CONFIG_NAME
     */
    @Id
    public String configName;
    
    /**
     * CONFIG_VALUE
     */
    @Column(name = "CONFIG_VALUE")
    public String configValue;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
    
    
}