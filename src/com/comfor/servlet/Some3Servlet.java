package com.comfor.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Some3Servlet extends HttpServlet {
	private static final long serialVersionUID = 242341L;
       
    public Some3Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("执行Some3Servlet");
		Enumeration names=request.getAttributeNames();
		while(names.hasMoreElements())
		{
			String name=(String) names.nextElement();
			String value=(String) request.getAttribute(name);
			System.out.println("name="+name+",value="+value);
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("--------Some3Servlet-------- </br>");
		//out.close();
		
	}

}
