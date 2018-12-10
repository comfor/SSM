package com.comfor.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.comfor.beans.comfor.Student;
import com.comfor.dao.StudentDao;
import com.comfor.service.SomeService;
import com.comfor.service.StudentService;
import com.comfor.util.tools.Page;
import com.comfor.util.tools.PageData;

//静态代理类
//静态代理类与装饰者间的共同点：
//1.都要实现与目标类相同的业务接口
//2.这两个类中都要声明目标对象
//3.都可以在不修改目标类的前提下增强目标方法


//静态代理类与装饰者间的区别：
//1.使用目的不同：
//装饰者的使用目的是：就是增强目标对象
//静态代理的使用目的是：是为保护和隐藏目标对象
//2.对于目标对象的获取方式不同：
//装饰者类中目标对象的获取：通过带参构造器传入
//静态代理中目标对象的获取：在无参构造器中直接创建
//3.功能增强的实现者不同：
//装饰者设计模式中存在装饰者基类，其并不真正实现增强，而是由具体的装饰者进行功能增强的，所以存在一个“装饰者链”的概念
//静态代理设计模式中一般不存在父子类的关系，具体的增强，就是由代理类完成，无需其子类完成，所以不存在“链”的概念

@Service
public class SomeServicePoxy implements SomeService {

	private SomeService someService;
	
	public SomeServicePoxy() {
		someService = new SomeServiceImpl();
	}
	
	@Override
	public String doSome() {
		return someService.doSome().trim().toUpperCase();
	}
}
