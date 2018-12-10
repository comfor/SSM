package com.comfor.listener.observer.observers;

//定义二号观察者
public class SecondObserver implements IObserver {

	@Override
	public void handleNotify(String message) {
		System.out.println("二号观察者接收到消息【" + message + "】，正在处理消息。。。");
	}

}
