package com.comfor.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class MySessionAttributeListener implements HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		//session域中添加属性时触发此方法
		System.out.println("session域中添加一个属性："+arg0.getName()+" = "+arg0.getValue());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		//session域中删除属性时触发此方法
		System.out.println("session域中删除一个属性："+arg0.getName()+" = "+arg0.getValue());
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		//session域中修改属性时触发此方法
		System.out.println("session域中修改一个属性："+arg0.getName()+" = "+arg0.getValue());
	}

}
