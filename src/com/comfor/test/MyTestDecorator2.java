package com.comfor.test;

import com.comfor.service.SomeService;
import com.comfor.service.Impl.SomeServiceImpl;
import com.comfor.service.Impl.SomeServiceTrimDecorator;
import com.comfor.service.Impl.SomeServiceUpperDecorator;
import com.comfor.service.decorators.SomeServiceWrapper;
import com.comfor.service.decorators.ToUpperCaseDecorator;
import com.comfor.service.decorators.TrimDecorator;

public class MyTestDecorator2 {

	public static void main(String[] args) {
		
		SomeService service1 = new SomeServiceImpl();
		String result1 = service1.doSome();
		System.out.println("装饰者 增强前："+result1);
		
		SomeServiceWrapper service2 = new TrimDecorator(service1);
		String result2 = service2.doSome();
		System.out.println("装饰者 去空格增强："+result2);
		
		SomeServiceWrapper service3 = new ToUpperCaseDecorator(service2);
		String result3 = service3.doSome();
		System.out.println("装饰者 变大写增强："+result3);
		
	}

}
