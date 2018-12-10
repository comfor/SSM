package com.comfor.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//当服务器关闭时就会销毁servletContext,就会触发此方法
		System.out.println("servletContext被销毁");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		//当应用布署时就会创建servletContext,就会触发此方法
		System.out.println("servletContext创建");
	}

}
