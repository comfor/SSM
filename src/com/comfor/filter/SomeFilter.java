package com.comfor.filter;

/**
 * 
 * 	<filter>
		<filter-name>SomeFilter</filter-name>
		<filter-class>com.comfor.filter.SomeFilter</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>SomeFilter</filter-name>
		<url-pattern>/filter/*</url-pattern>
	</filter-mapping>
 * 
 */

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SomeFilter implements Filter {

	public SomeFilter() {
		System.out.println("SomeFilter被创建");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("SomeFilter被初始化");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("SomeFilter过滤用户提交的请求  -- before --");
		//转向下一个组件
		chain.doFilter(request, response);
		System.out.println("SomeFilter过滤服务器回送的响应 -- after --");
	}
	
	@Override
	public void destroy() {
		System.out.println("SomeFilter被销毁");
	}


}
