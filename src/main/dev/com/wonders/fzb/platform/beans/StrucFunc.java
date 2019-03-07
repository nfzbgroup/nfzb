package com.wonders.fzb.platform.beans;

import java.lang.Comparable;

public class StrucFunc implements Comparable {
	/**
	 * 应用标识
	 */
	public String app_id = null;
	/**
	 * 功能标识
	 */
	public String func_id = null;
	/**
	 * 功能名称
	 */
	public String func_name = null;
	/**
	 * 描述
	 */
	public String desc = null;
	/**
	 * 类型
	 */
	public char type = 'p';

	public StrucFunc() {
	}

	@Override
	public int compareTo(Object o) {
		StrucFunc r = (StrucFunc)o;
	    int ret = func_name.compareTo(r.func_name);
	    return ret;
	}

}
