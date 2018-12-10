package com.comfor.test;

import com.comfor.service.SomeService;
import com.comfor.service.Impl.SomeServiceImpl;
import com.comfor.service.Impl.SomeServicePoxy;
import com.comfor.service.Impl.SomeServiceTrimDecorator;
import com.comfor.service.Impl.SomeServiceUpperDecorator;

public class MyTestPoxy {

	public static void main(String[] args) {
		SomeService service2 = new SomeServicePoxy();;
		String result2=service2.doSome();
		System.out.println("去空格变大写增强："+result2);
		
	}

}
