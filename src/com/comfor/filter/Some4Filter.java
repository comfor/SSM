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

public class Some4Filter implements Filter {

	public Some4Filter() {
		System.out.println("Some4Filter被创建");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Some4Filter被初始化");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Some4Filter过滤用户提交的请求  -- chain before --");
		//转向下一个组件
		chain.doFilter(request, response);
		System.out.println("Some4Filter过滤服务器回送的响应 -- chain after --");
	}
	
	@Override
	public void destroy() {
		System.out.println("Some4Filter被销毁");
	}


}
