package com.comfor.service.decorators;

import com.comfor.service.SomeService;

public class TrimDecorator extends SomeServiceWrapper {
	
	public TrimDecorator() {
		super();
	}

	public TrimDecorator(SomeService someService) {
		super(someService);
	}

	@Override
	public String doSome() {
		return super.doSome().trim();
	}

}
