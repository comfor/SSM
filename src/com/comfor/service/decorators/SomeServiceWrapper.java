package com.comfor.service.decorators;

import com.comfor.service.SomeService;

public class SomeServiceWrapper implements SomeService {

	private SomeService someService;
	
	public SomeServiceWrapper() {
		super();
	}

	public SomeServiceWrapper(SomeService someService) {
		super();
		this.someService = someService;
	}

	@Override
	public String doSome() {
		return someService.doSome();
	}

}
