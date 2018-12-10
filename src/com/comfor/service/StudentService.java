package com.comfor.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.comfor.beans.comfor.Student;
import com.comfor.util.tools.Page;
import com.comfor.util.tools.PageData;


public interface StudentService {

	//业务层主要是与页面业务交互的，但这里现没什么业务，所以业务层与DAO的基本一样的，但要清楚service与dao的区别
	void insertStudent(Student student);
	void deleteById(@Param("id")int id);
	void updateStudent(Student student);
	
	List<String> selectAllStudentNames();
	String selectStudentNameById(@Param("id")int id);
	
	List<Student> selectAllStudents();
	Student selectStudentById(@Param("id")int id);
	
	PageData selectStudentByIdPd(PageData pd);
	List<PageData> selectStudentslistPage(Page page);
	
}
