package com.comfor.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.comfor.beans.comfor.Student;
import com.comfor.util.tools.Page;
import com.comfor.util.tools.PageData;

public interface StudentDao {
	
	// dao层是与数据库交互层
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
