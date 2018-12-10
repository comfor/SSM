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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.comfor.beans.comfor.Student;

import net.sf.json.JSONObject;
/**
 * 3 SpringMVC 的注解式开发是指，处理器是基于注解的类的开发
 * 
 * 1 springmvc中注册组件扫描器
 * 2 此时的处理器类无需继承任何父类，实现任何接口。只需在类上与方法上添加相应注解即可
 * 3 @Controller：表示当前类为处理器
 * 	 @RequestMapping：如果在处理器类中则为命名空间，如果在方法上则为URL
 * 
 * @author 三米阳光 2018年8月19日
 */

@Controller
@RequestMapping("AnnotationController")		
/*@RequestMapping("/AnnotationController")*/						//1 此处命名空间前可以加 / 也可以不加，不加会自动添加的，与前面说的参照路经不一样的
/*@RequestMapping(value={"AnnotationController","/AnnotationController2"})*/  //2 命名空间是查源码可以看到是String[]因此可以添加多个
/*@RequestMapping("/my/AnnotationController")*/						//3 也可以设置多层路经
public class AnnotationController {

	//4 controller不是通过实现或继承的，方法可多个，方法名不限,不能忘了结尾的 .do
	@RequestMapping(value="/doSome1.do")
	public ModelAndView doSome1(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Hell SpringMVC!");
		mv.setViewName("welcome");
		return mv;		
	}
	
	//5 同时可以设置请求方式   此处无法显示，因为浏览器是get提交的
	@RequestMapping(value="/doSome2.do",method=RequestMethod.POST)
	public ModelAndView doSome2(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Hell SpringMVC!");
		mv.setViewName("welcome");
		return mv;		
	}
	
	// 6 资源名称中使用通配符
	//6.1 资源名称中使用通配符 如下表示url中以doSome3开头的都可以访问
	@RequestMapping("doSome3*.do")
	public ModelAndView doSome3(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Hell SpringMVC!");
		mv.setViewName("welcome");
		return mv;		
	}
	
	//6.2 资源名称中使用通配符 如下表示url中以doSome4结尾的都可以访问
	@RequestMapping("*doSome4.do")
	public ModelAndView doSome4(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Hell SpringMVC!");
		mv.setViewName("welcome");
		return mv;		
	}
	
	//7 资源路径中使用通配符
	//7.1 路径级数的精确匹配 表示在doSome5.do的资源名称前面，只能有两级路径，第一级必须是/xxx，而第二级随意。 这种称为路径级数的精确匹配
	@RequestMapping("/xxx/*/doSome5.do")
	public ModelAndView doSome5(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Hell SpringMVC!");
		mv.setViewName("welcome");
		return mv;		
	}
	
	//7 资源路径中使用通配符
	//7.2 路径级数的可变匹配 表示在doSome6.do的资源名称前面，必须以/xxx 路径开头，而其它级的路径是否包含，
	//若包含又包含几级，各级又叫什么名称，均随意。这种称为路径级数的可变匹配。
	@RequestMapping("/xxx/**/doSome6.do")
	public ModelAndView doSome6(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Hell SpringMVC!");
		mv.setViewName("welcome");
		return mv;		
	}
	
	//8 对请求提交方式的定义(首页有表单提交)  
	//8.1 POST方式
	@RequestMapping(value="doSome7.do",method=RequestMethod.POST)
	public ModelAndView doSome7(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Hell SpringMVC!");
		mv.setViewName("welcome");
		return mv;		
	}
	
	//8 对请求提交方式的定义(首页有表单提交)  
	//8.2 GET方式
	@RequestMapping(value="doSome8.do",method=RequestMethod.GET)
	public ModelAndView doSome8(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Hell SpringMVC!");
		mv.setViewName("welcome");
		return mv;		
	}
	
	//9对请求中携带参数的定义(首页有表单提交) 
	//9.1 @RequestMapping(value=”/xxx.do”, params={“name”,”age”}) ：要求请求中必须携带请求参数 name 与 age
	@RequestMapping(value="doSome9.do",params={"name","age"})
	public ModelAndView doSome9(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Hell SpringMVC!");
		mv.setViewName("welcome");
		return mv;		
	}
	
	//9对请求中携带参数的定义(首页有表单提交) 
	//9.2 @RequestMapping(value=”/xxx.do”, params={“!name”,”age”}) ：要求请求中必须携带请求参数 age，但必须不能携带参数 name
	@RequestMapping(value="doSome10.do",params={"!name","age"},method=RequestMethod.GET)
	public ModelAndView doSome10(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Hell SpringMVC!");
		mv.setViewName("welcome");
		return mv;		
	}
	
	//9对请求中携带参数的定义(首页有表单提交) 
	//9.3 @RequestMapping(value=”/xxx.do”, params={“name=zs”,”ag=23”}) ：要求请求中必须携带请求参数 name，且其值必须为 zs；必须携带参数 age，其其值必须为 23
	@RequestMapping(value="doSome11.do",params={"name=zs","ag=23"})
	public ModelAndView doSome11(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Hell SpringMVC!");
		mv.setViewName("welcome");
		return mv;		
	}
	
	//9对请求中携带参数的定义(首页有表单提交) 
	//9.4 @RequestMapping(value=”/xxx.do”, params=“name!=zs”) ：要求请求中必须携带请求参数name，且其值必须不能为 zs
	@RequestMapping(value="doSome12.do",params={"name!=zs"},method=RequestMethod.GET)
	public ModelAndView doSome12(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Hell SpringMVC!");
		mv.setViewName("welcome");
		return mv;		
	}
	
	//10 处理器方法的参数(首页有表单提交) 	
	/*
	处理器方法可以包含以下五类参数，这些参数会在系统调用时由系统自动赋值，即程序员可在方法内直接使用。
	 HttpServletRequest
	 HttpServletResponse
	 HttpSession
	 用于承载数据的 Model
	     请求中所携带的请求参数
	*/
	//10.1 逐个参数接收
	@RequestMapping(value="doSome13.do")
	public ModelAndView doSome13(String name,int age){
		ModelAndView mv = new ModelAndView();
		mv.addObject("name", name);
		mv.addObject("age", age);
		mv.setViewName("show");
		return mv;		
	}
	
	//10.2 请求参数中文乱码问题 在web.xml中注册字符集过滤器
	@RequestMapping(value="doSome14.do")
	public ModelAndView doSome14(String name,int age){
		ModelAndView mv = new ModelAndView();
		mv.addObject("name", name);
		mv.addObject("age", age);
		mv.setViewName("show");
		return mv;		
	}
	
	//10.3 校正请求参数名 指若请求 URL 所携带的参数名称与处理方法中指定的参数名不相同时， 则需在处理方法参数前添加一个注解@RequestParam(“请求参数名”)，指定请求URL所携带参数的名称
	@RequestMapping(value="doSome15.do")
	public ModelAndView doSome15(@RequestParam(name="rname") String name,
								@RequestParam(name="rage") int age){
		ModelAndView mv = new ModelAndView();
		mv.addObject("name", name);
		mv.addObject("age", age);
		mv.setViewName("show");
		return mv;		
	}
	
	//10.4 整体参数接收 必须参数名称与实体类相同
	@RequestMapping(value="doSome16.do")
	public ModelAndView doSome16(Student student){
		ModelAndView mv = new ModelAndView();
		mv.addObject("student", student);
		mv.setViewName("show");
		return mv;		
	}
	
	//10.4 域属性参数的接收 必须参数名称与实体类相同
	@RequestMapping(value="doSome17.do")
	public ModelAndView doSome17(Student student){
		ModelAndView mv = new ModelAndView();
		mv.addObject("student", student);
		mv.setViewName("show");
		return mv;		
	}
	
	//10.5 路径变量@PathVariable
	@RequestMapping(value="/{pname}/{age}/doSome18.do")
	public ModelAndView doSome18(@PathVariable("pname") String name, 
								@PathVariable int age){
		ModelAndView mv = new ModelAndView();
		mv.addObject("name", name);
		mv.addObject("age", age);
		mv.setViewName("show");
		return mv;		
	}
	
	//11 处理器方法的返回值
	/*
	使用@Controller 注解的处理器的处理器方法，其返回值常用的有四种类型：
	 第一种： ModelAndView
	 第二种： String
	 第三种：无返回值 void
	 第四种：返回自定义类型对象
	*/
	//11.1 返回 ModelAndView
	//若处理器方法处理完后，需要跳转到其它资源，且又要在跳转的资源间传递数据， 处理器方法返回 ModelAndView 比较好
	@RequestMapping(value="doSome19.do")
	public ModelAndView doSome19(String name,int age){
		ModelAndView mv = new ModelAndView();
		mv.addObject("name", name);
		mv.addObject("age", age);
		mv.setViewName("show");
		return mv;		
	}
	
	//11.2 返回 String 
	//两种情况：返回内部资源逻辑视图名与ajax请求
	//11.2.1 返回内部资源逻辑视图名    跳转的资源为内部资源
	@RequestMapping(value="doSome20.do")
	public String doSome20(HttpServletRequest request, Student student){
		request.setAttribute("student", student);
		//此处就直接返回其内部资源
		return "show";		
	}
	
	//11.2.1 返回 View 对象名    跳转的资源为内部或外部资源  要修改springmvc有视图处理器 BeanNameViewResolver
	@RequestMapping(value="doSome21.do")
	public String doSome21(){
		//此处就直接返回其内部资源id
		return "myInternalView";
		//return "baidu";
		//return "hao123";
	}
	
	//11.3 返回 void 
	//11.3.1 通过 ServletAPI 传递数据并完成跳转
	//1 通过在处理器方法的参数中放入的 ServletAPI 参数，来完成资源跳转时所要传递的数据及跳转。
	//2 可在方法参数中放入 HttpServletRequest 或 HttpSession，使方法中可以直接将数据放入到 request、 session 的域中，
	//	也可通过 request.getServletContext()获取到 ServletContext，从	而将数据放入到 application 的域中
	//3 可在方法参数中放入 HttpServletRequest 与 HttpServletResponse，使方法可以完成请求转发与重定向。 注意，重定向是无法完成对/WEB-INF/下资源的访问的
	//4 请求转发： request.getRequestDispatcher(“目标页面”).forward(request,response);
	//5 重定向： response.sendRedirect(“目标页面”);
	@RequestMapping(value="doSome22.do")
	public void doSome22(HttpServletRequest request, HttpServletResponse response,Student student) throws ServletException, IOException{
		request.setAttribute("student", student);
		request.getRequestDispatcher("/WEB-INF/jsp/show.jsp").forward(request, response);
	}
	
	//11.3.2 ajax请求
	@RequestMapping(value="doSome23.do")
	public void doSome23(String name,int age,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pname", name);
		map.put("page", age);
		
		JSONObject  myJson = JSONObject.fromObject(map);
		String jsonStr = myJson.toString();
		
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.close();
		
	}
	
	//12.4 返回Object    返回 Object 对象，需要使用@ResponseBody 注解， 将转换后的 JSON 数据放入到响应体中。
	//12.4.1 返回数值型对象
	@RequestMapping(value="doSome24.do")
	@ResponseBody
	public Object doSome24(){
		return 121.12;
	}
	
	//12.4.2 返回字符串对象
	// 返回的字 符 串 中 带 有 中 文 字 符 ， 则 接 收 方 页 面 将 会 出 现 乱 码 。 此 时 需 要 使 用@RequestMapping 的 produces 属性指定字符集
	// produces，产品，结果，即该属性用于设置输出结果类型,表我是以text/html形式，字符集为charset=utf-8返回给浏览器，如果浏览器接受此格式就将结果返回给浏览器，如果不接受则不返回值
	@RequestMapping(value="doSome25.do",produces="text/html;charset=utf-8",method=RequestMethod.GET)
	@ResponseBody
	public Object doSome25(){
		return "武汉comfor";
	}
	
	//12.4.3 返回自定义类型对象
	// 不能以对象的形式直接返回给客户端浏览器，而是将对象转换为 JSON 格式的数据发送给浏览器的
	@RequestMapping(value="doSome26.do")
	@ResponseBody
	public Object doSome26(){
		return new Student("comfor",30,80.5);
	}
	
	//12.4.4 返回 Map 集合
	// 不能以对象的形式直接返回给客户端浏览器，而是将对象转换为 JSON 格式的数据发送给浏览器的
	@RequestMapping(value="doSome27.do")
	@ResponseBody
	public Object doSome27(){
		Map<String,Student> students = new HashMap<String,Student>();
		students.put("stu1", new Student("comfor",30,98.5));
		students.put("stu2", new Student("angel",29,50.1));
		return students;
	}
	
	//12.4.4 返回List集合
	// 不能以对象的形式直接返回给客户端浏览器，而是将对象转换为 JSON 格式的数据发送给浏览器的
	@RequestMapping(value="doSome28.do")
	@ResponseBody
	public Object doSome28(){
		List<Student> students = new ArrayList<Student>();
		students.add(new Student("comfor",30,55));
		students.add(new Student("angel",29,87));
		return students;
	}
	
	

}






















