package com.comfor.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Some2Filter implements Filter {

	public Some2Filter() {
		System.out.println("Some2Filter被创建");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Some2Filter被初始化");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Some2Filter过滤用户提交的请求  -- before --");
		//转向下一个组件
		chain.doFilter(request, response);
		System.out.println("Some2Filter过滤服务器回送的响应 -- after --");
	}
	
	@Override
	public void destroy() {
		System.out.println("Some2Filter被销毁");
	}


}
