package com.comfor.task;

import java.text.SimpleDateFormat;

public class MyTask {
	
	private int count=0;
	
	public void task() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		System.out.println("框架测试定时任务：时间  "+df.format(System.currentTimeMillis()));  
	}

}
