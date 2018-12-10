package com.comfor.controller.comfor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * 测试框架所用
 * @author 三米阳光 2018年8月22日
 */
public class MyController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		System.out.println("实现控制层 controller 业务！");
		
		mv.setViewName("/comfor/welcome");
		return mv;
	}

}
