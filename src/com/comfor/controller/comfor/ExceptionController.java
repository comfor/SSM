package com.comfor.controller.comfor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.comfor.exception.comfor.AgeException;
import com.comfor.exception.comfor.NameException;
import com.comfor.exception.comfor.StudentException;

import net.sf.json.JSONObject;
/**
 * 4 SpringMVC 核心技术--异常处理  
 * 此部分一般在实现开发中不会如此处理异常的，此部分只做了解
 * 常用的 SpringMVC 异常处理方式主要有三种：
 * 		使用系统定义好的异常处理器 SimpleMappingExceptionResolver
 * 		使用自定义异常处理器
 * 		使用异常处理注解
 * @author 三米阳光 2018年8月19日
 */
@Controller
@RequestMapping("exceptionController")		
public class ExceptionController {
	
	/**
	 * 4.2.1 SimpleMappingExceptionResolver 异常处理器
	 * 该方式只需要在 SpringMVC 配置文件中注册该异常处理器 Bean 即可。该 Bean 比较特殊，没有 id 属性，
	 * 无需显式调用或被注入给其它<bean/>，当异常发生时会自动执行该类
	 * @throws StudentException 
	 */
	@RequestMapping("exception.do")
	public ModelAndView exception(String name,int age) throws StudentException{
		if(!"comfor".equals(name))
		{
			throw new NameException("用户错误！");
		}
		if(50<age)
		{
			throw new AgeException("年龄太大！");
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("name", name);
		mv.addObject("age", age);
		mv.setViewName("comfor/success");
		return mv;		
	}
	

}






















