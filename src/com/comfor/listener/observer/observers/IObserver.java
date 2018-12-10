package com.comfor.listener.observer.observers;

// 观察者接口
public interface IObserver {
	// 处理被观察者发送来的信息
	void handleNotify(String message);
}
