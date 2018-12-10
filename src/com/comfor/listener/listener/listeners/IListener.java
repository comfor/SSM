package com.comfor.listener.listener.listeners;

import com.comfor.listener.listener.events.ICurdEvent;

// 监听器接口
public interface IListener {
	// 处理事件
	void handle(ICurdEvent event);
}
