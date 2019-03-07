package com.wonders.fzb.platform.kit;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

public class HttpRequestHelper {
	public HttpRequestHelper() {
	}

	/**
	 * 将request中的参数写成<input type='hidden' name=%paraname% value=%paramvalue%>的形式
	 * 
	 * @param request
	 *            http request
	 * @return
	 */
	public static String requestRelayString(HttpServletRequest request) {
		String ret = "";
		Enumeration e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String param = (String) e.nextElement();
			String[] values = request.getParameterValues(param);
			for (int i = 0; i < values.length; i++) {
				ret += "<input type=hidden name=" + param + " value=" + values[i] + ">";
			}
		}
		return ret;
	}
}
