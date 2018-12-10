package com.comfor.controller.comfor;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 4 SpringMVC 核心技术--文件上传
 * 文件上传:
 * 	1 数据检验分前端检验与后台检验
 * 	2 数据检验一般开发中只做前端数据检验
 *  3 此处所说有数据检验只是针对实体检验，也是对实体中每个属性的基础检验，不关数据业务的检验
 *  4 数据业务的后台检验一般用的是ajax检验，一般也不会做对实体的检验
 * @author 三米阳光 2018年8月20日
 */
@Controller    
@RequestMapping("FileUploadController")    
public class FileUploadController {

	/**
	 * A、 处理器方法的形参用于接收表单元素所提交参数的处理器方法的形参类型不是 File，而是 MultipartFile。并且jsp表单位中要添加：enctype="multipart/form-data"
	 * 		MultipartFile 为一个接口，专门用于处理文件上传问题。该接口中具有很多有用的方法，例如 获 取 参 数 名 称 getName() ， 本 例 指 的 是 homework ； 
	 * 		获 取 文 件 的 原 始 名 称getOriginalFilename()； 获取文件大小 getSize()； 判断文件是否为空 isEmpty()； 文件上传transferTo()等。
	 * B、 未选择上传文件若用户未选择上传的文件就直接提交了表单，此时处理器方法的 MultipateFile 形参所接收到的实参值并非为 null，而是一个内容为 empty 的文件。
	 * 		所以，对于未选择上传文件的情况的处理，其判断条件为 file.isEmpty()，而非 file == null。
	 * C、 上传文件类型SpringMVC 的文件上传功能并未有直接的用于限定文件上传类型的方法或属性，需要对获取到的文件名后辍加以判断。此时使用 String 的 endWith()方法较为简捷。
	 * D、 上传方法对于上传单个文件，直接使用 MultipartFile 的 transferTo()方法，就可以完成上传功能。
	 * 		但是，需要注意的是，该方法要求服务端用于存放客户上传文件的目录必须存在，否则报错。即其不会自己创建该目标目录。如本例中，必须手工创建 images 目录。
	 * 
	 * 
	 */
	//单个文件上传
	@RequestMapping("fileupload.do")
	public ModelAndView fileupload(MultipartFile img,HttpSession session) throws IllegalStateException, IOException {
		
		//判断图片是否为空
		if(!img.isEmpty())
		{
			// 服务器端的images目录需要先创建好
			String path = session.getServletContext().getRealPath("/images");
			// 获取原始文件名
			String fileName = img.getOriginalFilename();
			// 限制文件上传类型（如果不限制则什么文件都可以上传）
			if(fileName.endsWith(".jpg") || fileName.endsWith(".png"))
			{
				// 获取文件对象
				File file = new File(path,fileName);
				// 完成文件上传
				img.transferTo(file);
			}
			else
			{
				return new ModelAndView("/fail.jsp");
			}
		}
		return new ModelAndView("/comfor/success");
	}
	
	//多个文件上传
	@RequestMapping("fileuploadmulti.do")
	public ModelAndView fileuploadmulti(@RequestParam MultipartFile[] img,HttpSession session) throws IllegalStateException, IOException {
		
		// 服务器端的images目录需要先创建好
		String path = session.getServletContext().getRealPath("/images");
		for(int i=0;i<img.length;i++)
		{
			//判断图片是否为空
			if(!img[i].isEmpty())
			{
				// 获取原始文件名
				String fileName = img[i].getOriginalFilename();
				// 限制文件上传类型（如果不限制则什么文件都可以上传）
				if(fileName.endsWith(".jpg") || fileName.endsWith(".png"))
				{
					// 获取文件对象
					File file = new File(path,fileName);
					// 完成文件上传
					img[i].transferTo(file);
				}
				else
				{
					return new ModelAndView("/comfor/fail");
				}
			}
		}
		
		return new ModelAndView("/comfor/success");
	}
	

}



















