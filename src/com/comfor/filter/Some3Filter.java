package com.comfor.filter;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Some3Filter implements Filter {

	public Some3Filter() {
		System.out.println("Some3Filter被创建");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Some3Filter被初始化");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Some3Filter过滤用户提交的请求  -- before --");
		request.setAttribute("name", "comfor");
		request.setAttribute("age", "30");
		//转向下一个组件
		chain.doFilter(request, response);
		System.out.println("Some3Filter过滤服务器回送的响应 -- after --");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("----------Some3Filter----------- </br>");
		//out.close();
	}
	
	@Override
	public void destroy() {
		System.out.println("Some3Filter被销毁");
	}


}
