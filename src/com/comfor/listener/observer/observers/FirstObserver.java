package com.comfor.listener.observer.observers;

// 定义一号观察者
public class FirstObserver implements IObserver {

	@Override
	public void handleNotify(String message) {
		System.out.println("一号观察者接收到消息【" + message + "】，正在处理消息。。。");
	}

}
