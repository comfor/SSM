package com.comfor.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		//session创建时触发此方法
		System.out.println("session创建");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		//session销毁时触发此方法
		System.out.println("session被销毁");
	}

}
