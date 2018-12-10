package com.comfor.listener.listener.listenerable;

import com.comfor.listener.listener.events.ICurdEvent;
import com.comfor.listener.listener.listeners.IListener;

// 定义事件源类
public class Some implements IListenerable {
	private IListener listener;
	
	// 注册监听器
	@Override
	public void setListener(IListener listener) {
		this.listener = listener;
	}

	// 触发监听器
	@Override
	public void triggerListener(ICurdEvent event) {
		listener.handle(event);
	}

}
