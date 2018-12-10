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

public class Some5Filter implements Filter {

	public Some5Filter() {
		System.out.println("Some5Filter被创建");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Some5Filter被初始化");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Some5Filter过滤用户提交的请求  -- chain before --");
		//解决POST请求参数中文乱码问题
		request.setCharacterEncoding("UTF-8");
		//解决响应中文乱码问题
		response.setContentType("text/html;charset=UTF-8");
		//转向下一个组件
		chain.doFilter(request, response);
		System.out.println("Some5Filter过滤服务器回送的响应 -- chain after --");
	}
	
	@Override
	public void destroy() {
		System.out.println("Some5Filter被销毁");
	}


}
