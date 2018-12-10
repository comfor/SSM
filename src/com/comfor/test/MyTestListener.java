package com.comfor.test;

import com.comfor.listener.listener.events.CurdEvent;
import com.comfor.listener.listener.events.ICurdEvent;
import com.comfor.listener.listener.listenerable.Some;
import com.comfor.listener.listener.listeners.CurdListener;
import com.comfor.listener.listener.listeners.IListener;

public class MyTestListener {

	public static void main(String[] args) {
		
		// 定义监听器
		IListener listener = new CurdListener();
		
		// 定义事件源
		Some some = new Some();
		
		// 定义事件对象
		ICurdEvent event = new CurdEvent(some, "saveStudent");
		
		// 事件源注册监听器
		some.setListener(listener);
		
		// 事件源触发监听器
		some.triggerListener(event);
	}

}
