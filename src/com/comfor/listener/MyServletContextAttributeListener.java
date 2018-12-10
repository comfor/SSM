package com.comfor.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class MyServletContextAttributeListener implements ServletContextAttributeListener {

	@Override
	public void attributeAdded(ServletContextAttributeEvent arg0) {
		//当向serletContext域中添加属性时触发此方法
		System.out.println("向servletContext域中添加一个属性："+arg0.getName()+" = "+arg0.getValue());
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent arg0) {
		//当向serletContext域中删除属性时触发此方法
		System.out.println("向servletContext域中删除一个属性："+arg0.getName()+" = "+arg0.getValue());
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent arg0) {
		//当向serletContext域中修改属性时触发此方法
		System.out.println("向servletContext域中修改一个属性："+arg0.getName()+" = "+arg0.getValue());
	}

}
