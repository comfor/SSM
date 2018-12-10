package com.comfor.test;

import com.comfor.listener.observer.observerable.Some;
import com.comfor.listener.observer.observers.FirstObserver;
import com.comfor.listener.observer.observers.IObserver;
import com.comfor.listener.observer.observers.SecondObserver;

public class MyTestObserver {

	public static void main(String[] args) {
		// 创建多个观察者
		IObserver first = new FirstObserver();
		IObserver second = new SecondObserver();
		
		// 创建被观察者
		Some some = new Some();
		
		// 被观察者添加观察者
		some.addObserver(first);
		some.addObserver(second);
		
		// 被观察者向所有观察者发送消息
		some.notityObservers("全体注意，出发！");
		
		System.out.println("========================");
		
		// 被观察者删除指定的观察者
		some.removeObserver(first);
		
		// 被观察者向所有观察者发送消息
		some.notityObservers("全体注意，出发！");
	}

}
