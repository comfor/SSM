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

import com.comfor.beans.comfor.Student;

import net.sf.json.JSONObject;
/**
 * 4 SpringMVC 核心技术--请求转发与重定向
 * 此部分只说 请求转发与重定向，说明请看文件夹 
 * 当处理器对请求处理完毕后，向其它资源进行跳转时，有两种跳转方式：请求转发与重定向。
 * 而根据所要跳转的资源类型，又可分为两类：跳转到页面与跳转到其它处理器
 * 
 * 注：对于请求转发的页面是可以WEB-INF中页面；而重定向是不可以中WEB-INF中页面的。
 * 	因为重定向相当用户再次发出一次请求，而用户是不能直接访问WEB-INF中的资源的	
 * 
 * @author 三米阳光 2018年8月19日
 */
@Controller
@RequestMapping("ForwardAndRedirectController")		
public class ForwardAndRedirectController {
	
	/*
	请求转发与重定向一共分如下种情况：
		4.1.1 请求转发--返回 ModelAndView--请求转发到页面
		4.1.2 请求转发--返回 ModelAndView--转发到其它 Controller
		4.2.1 重定向--返回 ModelAndView--重定向到页面--通过 ModelAndView 的 Model 携带参数
		4.2.2 重定向--返回 ModelAndView--重定向到页面--使用 HttpSession携带参数
		4.2.3 重定向--返回 ModelAndView--重定向到 Controller--通过 ModelAndView 的 Model 携带参数
		4.2.4 重定向--返回 ModelAndView--重定向到 Controller--使用 HttpSession 携带参数
		4.3.1 请求转发--返回 String--重定向到 Controller--使用 HttpSession 携带参数
		4.4.1 重定向--返回 String--重定向到页面--通过 ModelAndView形参携带参数
		4.4.2 重定向--返回 String--重定向到 Controller--通过 Model 形参携带参数
		4.5.1 请求转发--返回 void--请求转发页面
		4.6.1 重定向--返回 void--重定向页面
	页面取值一共分如下种情况：
		重定向：${student}   ${sessionScope.student}（使用 HttpSession携带参数）
		请求转发：${param.pname}
	*/	
		

	// 4.1.1 请求转发--返回 ModelAndView--请求转发到页面
	/*
	1 默认情况下， 当处理器方法返回 ModelAndView 时，跳转到指定的 View，使用的是请求转发。
		但也可显式的进行指出。 此时需在 setViewName()指定的视图前添加 forward:，且此时的视图不会再与视图解析器中的前辍与后辍进行拼接。即必须写出相对于项目根的路径。
	2 若需要向下传递数据，除了可以使用 request， session 外，还可以将数据存放于 ModelAndView 中的 Model 中。页面通过 EL 表达式可直接访问该数据。
	*/
	@RequestMapping(value="/jump1.do")
	public ModelAndView jump1(Student student){
		ModelAndView mv = new ModelAndView();
		mv.addObject("student", student);
		mv.setViewName("forward:/WEB-INF/jsp/jump.jsp");
		return mv;		
	}
	
	// 4.1.2 请求转发--返回 ModelAndView--转发到其它 Controller
	/*
	若需要向下传递数据，除了可以使用 request， session 外，还可以将数据存放于 ModelAndView 中的 Model 中。页面通过 EL 表达式可直接访问该数据。
	*/
	@RequestMapping(value="/jump2.do")
	public ModelAndView jump2(Student student){
		ModelAndView mv = new ModelAndView();
		mv.addObject("student", student);
		mv.setViewName("forward:other.do");
		return mv;		
	}
	@RequestMapping(value="/other.do")
	public ModelAndView other(Student student){
		ModelAndView mv = new ModelAndView();
		mv.addObject("student", student);
		mv.setViewName("forward:/WEB-INF/jsp/other.jsp");
		return mv;		
	}
	
	// 4.2.1 重定向--返回 ModelAndView--重定向到页面--通过 ModelAndView 的 Model 携带参数
	/*
	1 返回 ModelAndView 时的重定向，需在 setViewName()指定的视图前添加 redirect:，且此时的视图不会再与视图解析器中的前辍与后辍进行拼接。
	2 http://10.10.23.115:8877/SpringMVC/jump.jsp?pname=comfor&page=23,可以看出重定向到其他页面时是以get的方式传参数的
	3 由于视图解析器会将 Map 的 value 放入到 URL 后作为请求参数传递出去，所以无论什么类型的 value，均会变为 String。
		故此， 放入到 Model 中的 value，只能是基本数据类型与 String，不能是自定义类型的对象数据
	4 重定向的面页中是无法从 request 中读取数据的。但由于 map 中的 key 与 value，以请求参数的形式放到了请求的 URL 后，
		所以，页面可以通过 EL 表达式中的请求参数 param读取
	5 重定向的页面不能是/WEB-INF 中的页面
	*/
	@RequestMapping(value="/jump3.do")
	public ModelAndView jump3(Student student){
		ModelAndView mv = new ModelAndView();
		mv.addObject("pname", student.getName());
		mv.addObject("page", student.getAge());
		mv.setViewName("redirect:/jump.jsp");
		return mv;		
	}
	
	// 4.2.2 重定向--返回 ModelAndView--重定向到页面--使用 HttpSession携带参数
	/*
	1 返回 ModelAndView 时的重定向，需在 setViewName()指定的视图前添加 redirect:，且此时的视图不会再与视图解析器中的前辍与后辍进行拼接。
	2 http://10.10.23.115:8877/SpringMVC/jump.jsp,session层传值的
	4 jsp页面取值为：${sessionScope.student}
	*/
	@RequestMapping(value="/jump4.do")
	public ModelAndView jump4(Student student,HttpSession session){
		session.setAttribute("student", student);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/jump.jsp");
		return mv;		
	}
	
	// 4.2.3 重定向--返回 ModelAndView--重定向到 Controller--通过 ModelAndView 的 Model 携带参数
	/*
	1 目标 Controller 在接收这些参数时， 只要保证目标 Controller 的方法形参名称与发送Controller发送的参数名称相同即可接收。
		当然，目标 Controller也可以进行参数的整体接收。只要保证参数名称与目标 Controller 接收参数类型的属性名相同即可
	*/
	@RequestMapping(value="/jump5.do")
	public ModelAndView jump5(Student student){
		ModelAndView mv = new ModelAndView();
		mv.addObject("pname", student.getName());
		mv.addObject("page", student.getAge());
		//mv.setViewName("redirect:other2.do");
		mv.setViewName("redirect:other3.do");
		return mv;		
	}
	// http://10.10.23.115:8877/SpringMVC/myController10/other2.do?pname=comfor&page=23
	@RequestMapping(value="/other2.do")
	public ModelAndView other2(String pname,int page){
		ModelAndView mv = new ModelAndView();
		mv.addObject("student", new Student(pname, page,80));
		mv.setViewName("forward:/WEB-INF/jsp/other.jsp");
		return mv;		
	}
	// http://10.10.23.115:8877/SpringMVC/myController10/other3.do?pname=comfor&page=23
	@RequestMapping(value="/other3.do")
	public ModelAndView other3(Student student){
		ModelAndView mv = new ModelAndView();
		mv.addObject("student", student);
		mv.setViewName("forward:/WEB-INF/jsp/other.jsp");
		return mv;		
	}
	
	// 4.2.4 重定向--返回 ModelAndView--重定向到 Controller--使用 HttpSession 携带参数
	/*
	1 目标 Controller 在接收这些参数时， 只要保证目标 Controller 的方法形参名称与发送Controller发送的参数名称相同即可接收。
		当然，目标 Controller也可以进行参数的整体接收。只要保证参数名称与目标 Controller 接收参数类型的属性名相同即可
	*/
	@RequestMapping(value="/jump6.do")
	public ModelAndView jump6(Student student,HttpSession session){
		ModelAndView mv = new ModelAndView();
		session.setAttribute("student", student);
		mv.setViewName("redirect:other4.do");
		return mv;		
	}
	// http://10.10.23.115:8877/SpringMVC/myController10/other4.do
	@RequestMapping(value="/other4.do")
	public ModelAndView other4(HttpSession session){
		Student student = (Student) session.getAttribute("student");
		System.out.println("可以使用获取值：student="+student);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("forward:/WEB-INF/jsp/other.jsp");
		return mv;		
	}
	
	// 4.3.1 请求转发--返回 String--重定向到 Controller--使用 HttpSession 携带参数
	/*
	1 当处理器方法返回 String 时，该 String 即为要跳转的视图。在其前面加上前辍 forward:，则可显式的指定跳转方式为请求转发。 
		同样，视图解析器不会对其进行前辍与后辍的拼接。请求转发的目标资源无论是一个页面，还是一个 Controller，用法相同。
	2  对于处理器方法返回字符串的情况， 当处理器接收到请求中的参数后，发现用于接收这些参数的处理器方法形参为包装类对象，
		则除了会将参数封装为对象传递给形参外，还会存放到 request 域属性中,直接带到下个页面
	 */
	@RequestMapping(value="/jump7.do")
	public String jump7(Student student){
		return "forward:/WEB-INF/jsp/other.jsp";		
	}
	
	// 4.4.1 重定向--返回 String--重定向到页面--通过 ModelAndView形参携带参数
	/*
	1 在处理器方法返回的视图字符串的前面添加前辍 redirect:，则可实现重定向跳转
	2 若需要向下传递参数值，可以直接通过请求 URL 携带参数，还可以通过 HttpSession携带参数
	3 通过 ModelAndView形参携带参数，该方式同样也是将参数拼接到了重定向请求的 URL 后，所以放入其中的数据只能是基本类型数据，不能是自定义类型
	 */
	@RequestMapping(value="/jump8.do")
	public String jump8(Student student,ModelAndView mv){
		mv.addObject("pname", student.getName());
		mv.addObject("page", student.getAge());
		return "redirect:/jump.jsp";		
	}	
	
	// 4.4.2 重定向--返回 String--重定向到页面--通过形参 RedirectAttributes的addAttribute()携带参数
	/*
	1 在处理器方法返回的视图字符串的前面添加前辍 redirect:，则可实现重定向跳转
	2 RedirectAttributes是一个继承自 Model 的接口，其底层仍然使用 ModelMap实现。
		通过 addAttribute()方法会将参数名及参数值放入该 Map中，然后视图解析器会将其拼接到请求的 URL 中。 
		所以，这种携带参数的方式，不能携带自定义对象。
	 */
	@RequestMapping(value="/jump9.do")
	public String jump9(Student student,RedirectAttributes ra){
		ra.addAttribute("pname", student.getName());
		ra.addAttribute("page", student.getAge());
		return "redirect:/jump.jsp";		
	}
	
	// 4.4.2 重定向--返回 String--重定向到 Controller--通过 Model 形参携带参数
	/*
	1 在处理器方法返回的视图字符串的前面添加前辍 redirect:，则可实现重定向跳转
	2 重定向到 Controller 时，携带参数的方式，除了可以使用请求 URL 后携带方式，HttpSession携带方式， 
		Model 形参携带方式，及 RedirectAttributes 形参的 addAttibute()携带方式外，
		还可以使用 RedirectAttributes 形参的 addFlushAttibute()携带方式
	 */
	@RequestMapping(value="/jump10.do")
	public String jump10(Student student,ModelAndView mv){
		mv.addObject("name", student.getName());
		mv.addObject("age", student.getAge());
		return "redirect:other5.do";		
	}
	@RequestMapping(value="/other5.do")
	public String other5(String name,String age){
		return "forward:/WEB-INF/jsp/other.jsp";		
	}
	
	// 4.5.1 请求转发--返回 void--请求转发页面
	/*
	1 当处理器方法返回 void 时，若要实现请求转发，则需要使用 HttpServletRequest的请求转发方法。 无论下一级资源是页面，还是 Controller，用法相同
	2 若有数据需要向下一级资源传递，则需要将数据放入到 request或 session 中。 不能将数据放到 Model、 RedirectAttributes 中
		因为它们中的数据都是通过拼接到处理器方法的返回值中，作为请求的一部分出现向下传递的。这里没有返回值，所以它们中的数据便无法向下传递了。
	 */
	@RequestMapping(value="/jump11.do")
	public void jump11(Student student,HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setAttribute("student", student);
		request.getRequestDispatcher("/WEB-INF/jsp/other.jsp").forward(request, response);
	}
	
	// 4.6.1 重定向--返回 void--重定向页面
	/*
	1 当处理器方法返回 void 时，若要实现请求转发，则需要使用 HttpServletRequest的请求转发方法。 无论下一级资源是页面，还是 Controller，用法相同
	2 当处理器方法返回 void 时，若要实现重定向，则需要使用 HttpServletResponse 的重定向方法 sendRedirect()。
	3 若有数据需要向下一级资源传递，则需要将数据放入到 session中
	 */
	@RequestMapping(value="/jump12.do")
	public void jump12(Student student,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception{
		session.setAttribute("student", student);
		response.sendRedirect(request.getContextPath()+"/jump.jsp");
	}
	

}






















