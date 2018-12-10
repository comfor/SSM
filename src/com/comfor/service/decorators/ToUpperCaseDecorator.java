package com.comfor.service.decorators;

import com.comfor.service.SomeService;

public class ToUpperCaseDecorator extends SomeServiceWrapper {
	
	public ToUpperCaseDecorator() {
		super();
	}

	public ToUpperCaseDecorator(SomeService someService) {
		super(someService);
	}

	@Override
	public String doSome() {
		return super.doSome().toUpperCase();
	}

}
