package com.comfor.listener.observer.observerable;

import com.comfor.listener.observer.observers.IObserver;

// 被观察者接口，即“可被观察的”接口
public interface IObserverable {
	// 添加观察者
	void addObserver(IObserver observer);
	// 删除观察者
	void removeObserver(IObserver observer);
	// 向观察者发送信息
	void notityObservers(String message);
}
