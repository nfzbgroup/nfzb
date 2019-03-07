package com.wonders.fzb.platform.beans;

import javax.persistence.Column;

@SuppressWarnings("serial")
public class AppConfigPK implements java.io.Serializable {
	@Column(name = "APP_ID")
	private String appId;

	@Column(name = "CONFIG_NAME")
	private String configName;

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

	public AppConfigPK() {
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof AppConfigPK) {
			AppConfigPK pk = (AppConfigPK) o;
			if (this.appId == pk.getAppId() && this.configName.equals(pk.getConfigName())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 29 * this.appId.hashCode() + this.configName.hashCode();
	}
}
