package com.comfor.test;

import com.comfor.service.SomeService;
import com.comfor.service.Impl.SomeServiceImpl;
import com.comfor.service.Impl.SomeServiceTrimDecorator;
import com.comfor.service.Impl.SomeServiceUpperDecorator;

public class MyTestDecorator {

	public static void main(String[] args) {
		SomeService service1 = new SomeServiceImpl();
		String result1=service1.doSome();
		System.out.println("增强前："+result1);
		
		SomeService service2 = new SomeServiceTrimDecorator(service1);
		String result2=service2.doSome();
		System.out.println("去空格增强："+result2);
		
		SomeService service3 = new SomeServiceUpperDecorator(service2);
		String result3=service3.doSome();
		System.out.println("变大写增强："+result3);
	}

}
