package com.wonders.fzb.base.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 初始化设置上下文路径
 * @author ZSW
 * 2016-08-04
 */
public class InitializationListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		String basePath = arg0.getServletContext().getContextPath();
		arg0.getServletContext().setAttribute("basePath", basePath);
	}

}