package com.comfor.listener.listener.listenerable;

import com.comfor.listener.listener.events.ICurdEvent;
import com.comfor.listener.listener.listeners.IListener;

// 事件源接口
public interface IListenerable {
	// 为事件源注册监听器
	void setListener(IListener listener);
	// 触发监听器
	void triggerListener(ICurdEvent event);
}
