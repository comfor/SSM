package com.comfor.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

public class MyRequestAttributeListener implements ServletRequestAttributeListener {

	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		//ServletRequestAttributeEvent事件的getName：获取属性名，getValue:获取属性值
		//当向request域中 添加 属性时会触发此方法
		System.out.println("向request域中添加一个属性："+srae.getName()+" = "+srae.getValue());
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		//当向request域中 删除 属性时会触发此方法
		System.out.println("向request域中删除一个属性："+srae.getName()+" = "+srae.getValue());
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		//当向request域中 修改 属性时会触发此方法
		System.out.println("向request域中修改一个属性："+srae.getName()+" = "+srae.getValue());
	}

}
