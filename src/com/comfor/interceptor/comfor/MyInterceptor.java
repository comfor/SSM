package com.comfor.interceptor.comfor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 4 SpringMVC 核心技术--拦截器
 * 拦截器:
 * 	1 主要作用是拦截指定的用户请求， 并进行相应的预处理与后处理
 * 	2 拦截的时间点在“处理器映射器根据用户提交的请求映射出了所要执行的处理器类， 并且也找到了要执行该处理器类的处理器适配器，在处理器适配器执行处理器之前”
 * 	3 自定义拦截器，需要实现 HandlerInterceptor 接口,并要在容器中注册
 * 	4 拦截器主要可以用于登录
 * @author 三米阳光 2018年8月20日
 */
public class MyInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("执行MyInterceptor ----- preHandle()方法");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("执行MyInterceptor ----- postHandle()方法");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("执行MyInterceptor ----- afterCompletion()方法");
	}

}
