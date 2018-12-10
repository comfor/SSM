package com.comfor.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyRequestListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		System.out.println("请求对象销毁！");
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		System.out.println("请求对象被创建");
	}

}
