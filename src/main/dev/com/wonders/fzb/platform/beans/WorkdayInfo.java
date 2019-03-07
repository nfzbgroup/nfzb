package com.wonders.fzb.platform.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.hibernate.annotations.GenericGenerator;

/**
 * 工作日业务实体
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "PORTAL_WORKDAY")
public class WorkdayInfo implements Serializable {
	 
	public WorkdayInfo() {
	}
	/**
     * 工作日
     */
    @Id
    @GenericGenerator(name = "id", strategy = "assigned") 
    @GeneratedValue(generator = "id")  
    @Column(name = "ST_DAY")
    public String stDay;
	
    /**
     * 是否休息日
     */
    @Column(name = "ST_WORK")
    public String stWork;
    
    /**
     * 描述
     */
    @Column(name = "ST_DESC")
    public String stDesc;

	public String getStDay() {
		return stDay;
	}

	public void setStDay(String stDay) {
		this.stDay = stDay;
	}

	public String getStWork() {
		return stWork;
	}

	public void setStWork(String stWork) {
		this.stWork = stWork;
	}

	public String getStDesc() {
		return stDesc;
	}

	public void setStDesc(String stDesc) {
		this.stDesc = stDesc;
	}

	
}