package com.comfor.listener.listener.events;

import com.comfor.listener.listener.listenerable.IListenerable;

// 定义增删改查事件
// C：Create，增加
// U：Update，修改
// R：Retrieve，检索
// D：Delete，删除

// 通常，对于事件对象，我们一般是需要从事件对象中获取到事件源对象的
public interface ICurdEvent {
	// 声明事件类型
	String CRE_EVENT = "create event";
	String UPD_EVENT = "update event";
	String RET_EVENT = "retrieve event";
	String DEL_EVENT = "delete event";
	
	// 获取事件源对象
	IListenerable getEventSource();
	// 获取事件类型
	String getEventType();
}










