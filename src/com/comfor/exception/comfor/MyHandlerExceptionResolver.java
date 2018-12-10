package com.comfor.exception.comfor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("ex", ex);
		mv.setViewName("/comfor/error");
		
		if(ex instanceof NameException) {
			System.out.println("处理用户错误的业务");
			mv.setViewName("/comfor/nameError");
		}
		
		if(ex instanceof AgeException) {
			System.out.println("处理年龄太大的业务");
			mv.setViewName("/comfor/ageError");
		}
		
		return mv;
	}

}
