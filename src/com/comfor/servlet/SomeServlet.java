package com.comfor.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SomeServlet extends HttpServlet {
	private static final long serialVersionUID = 242341L;
       
    public SomeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("执行SomeServlet");
		//request.getRequestDispatcher("/otherServlet").include(request, response);
		//request.getRequestDispatcher("/otherServlet").forward(request, response);
	}

}
