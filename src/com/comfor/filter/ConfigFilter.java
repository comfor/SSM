package com.comfor.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ConfigFilter implements Filter {
	
	private FilterConfig filterConfig;

	public ConfigFilter() {
		System.out.println("SomeFilter被创建");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig=filterConfig;
		System.out.println("SomeFilter被初始化");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("SomeFilter过滤用户提交的请求  -- before --");
		//获取ServletContext
		ServletContext sc=filterConfig.getServletContext();
		System.out.println("ServletContext = "+sc);
		//获取filterName
		String filterName=filterConfig.getFilterName();
		System.out.println("filterName = "+filterName);
		//获取指定的初始化参数值
		String schoolPar=filterConfig.getInitParameter("school");
		System.out.println("schoolPar = "+schoolPar);
		//获取全部初始化参数名称
		Enumeration parNames=filterConfig.getInitParameterNames();
		while(parNames.hasMoreElements())
		{
			String name = (String) parNames.nextElement();
			String value = filterConfig.getInitParameter(name);
			System.out.println("name = "+name+" value = "+value);
		}
		//转向下一个组件
		chain.doFilter(request, response);
		System.out.println("SomeFilter过滤服务器回送的响应 -- after --");
	}
	
	@Override
	public void destroy() {
		System.out.println("SomeFilter被销毁");
	}


}
