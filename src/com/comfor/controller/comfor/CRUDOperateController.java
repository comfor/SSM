package com.comfor.controller.comfor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.comfor.beans.comfor.Student;
import com.comfor.service.Impl.StudentServiceImpl;
import com.comfor.util.tools.Page;
import com.comfor.util.tools.PageData;

import net.sf.json.JSONObject;
/**
 * 测试增删改查
 * @author 三米阳光 2018年8月19日
 */
@Controller
@RequestMapping("CRUDOperateController")		
public class CRUDOperateController {
	
	//@Autowired
	@Resource
	private StudentServiceImpl studentService;  
		
	@RequestMapping("operate.do")
	public ModelAndView operate(HttpServletRequest request,HttpServletResponse response){
		
		ModelAndView mv = new ModelAndView();		
		
		Student student = new Student("Tomn",30,90.5);
		
		//studentService.insertStudent(student);
		
		//studentService.deleteById(29);
		
		//student.setId(30);
		//studentService.updateStudent(student);		
		
		/*
		List<String> names = studentService.selectAllStudentNames();
		for(String name:names)
		{
			System.out.println("name:"+name);
		}
		*/
		
		/*
		String name = studentService.selectStudentNameById(33);
		System.out.println("name:"+name);
		*/
		
		/*
		List<Student> students = studentService.selectAllStudents();
		for(Student studentf:students)
		{
			System.out.println(studentf);
		}
		*/
		
		/*
		Student studentp = studentService.selectStudentById(30);
		System.out.println(studentp);
		*/
		
		/*
		PageData pd = new PageData();
		pd.put("id", 31);
		PageData pdName = studentService.selectStudentByIdPd(pd);
		System.out.println("pdName:"+pdName+"   name:"+pdName.get("name").toString());
		*/
		
		
		PageData pds = new PageData();
		Page page = new Page();
		pds.put("name", "o");
		page.setPd(pds);
		List<PageData> listpd = studentService.selectStudentslistPage(page);
		for(PageData studentss:listpd)
		{
			System.out.println(studentss);
		}
		
		
		mv.setViewName("comfor/success");
		return mv;		
	}
	
}






















