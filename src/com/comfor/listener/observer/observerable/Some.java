package com.comfor.listener.observer.observerable;

import java.util.ArrayList;
import java.util.List;

import com.comfor.listener.observer.observers.IObserver;


// 定义被观察者
public class Some implements IObserverable {
	// 声明观察者集合
	private List<IObserver> observers;
	
	public Some() {
		// 在被观察者对象创建的同时，就将观察者集合创建
		observers = new ArrayList<>();
	}

	@Override
	public void addObserver(IObserver observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(IObserver observer) {
		observers.remove(observer);
	}

	@Override
	public void notityObservers(String message) {
		// 通知每一个观察者
		for (IObserver observer : observers) {
			observer.handleNotify(message);
		}
	}

}
