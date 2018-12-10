package com.comfor.beans.comfor;

import java.io.Serializable;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StudentActivation implements HttpSessionActivationListener,Serializable {
	
	private Integer id;
	
	@NotNull(message="姓名不能为空")
	@Size(min=2, max=8, message="姓名长度应在{min}-{max}个字符")
	private String name;
	
	@Min(value=0, message="年龄不能小于{value}")
	@Max(value=150, message="年龄不能大于{value}")
	private int age;
	
	@Min(value=0, message="成绩不能小于{value}")
	@Max(value=100, message="成绩不能大于{value}")
	private double score;	
	
	private static final long serialVersionUID = 25782727543999L;
	
	public StudentActivation() {
		super();
	}
	public StudentActivation(String name, int age, double score) {
		super();
		this.name = name;
		this.age = age;
		this.score = score;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", score=" + score + "]";
	}
	@Override
	public void sessionDidActivate(HttpSessionEvent arg0) {
		//当当前类的对象被活化（硬盘中数据恢复到内存中）时会触发此方法
		System.out.println("StudentActivation已经活化");
	}
	@Override
	public void sessionWillPassivate(HttpSessionEvent arg0) {
		//当当前类的对象被钝化（内存中数据写入到硬盘中）时会触发此方法
		System.out.println("StudentActivation已经钝化");
	}


}
