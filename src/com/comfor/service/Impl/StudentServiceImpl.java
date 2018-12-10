package com.comfor.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.comfor.beans.comfor.Student;
import com.comfor.dao.StudentDao;
import com.comfor.service.StudentService;
import com.comfor.util.tools.Page;
import com.comfor.util.tools.PageData;

@Service
public class StudentServiceImpl implements StudentService {
	
	//业务层主要是与页面业务交互的，但这里现没什么业务，所以业务层与DAO的基本一样的，但要清楚service与dao的区别
	@Resource
	private StudentDao studentDao;
	
	@Override
	public void insertStudent(Student student) {
		studentDao.insertStudent(student);
	}

	@Override
	public void deleteById(int id) {
		studentDao.deleteById(id);
	}

	@Override
	public void updateStudent(Student student) {
		studentDao.updateStudent(student);
	}

	@Override
	public List<String> selectAllStudentNames() {
		return studentDao.selectAllStudentNames();
	}

	@Override
	public String selectStudentNameById(int id) {
		return studentDao.selectStudentNameById(id);
	}

	@Override
	public List<Student> selectAllStudents() {
		return studentDao.selectAllStudents();
	}

	@Override
	public Student selectStudentById(int id) {
		return studentDao.selectStudentById(id);
	}

	@Override
	public PageData selectStudentByIdPd(PageData pd) {
		return studentDao.selectStudentByIdPd(pd);
	}

	@Override
	public List<PageData> selectStudentslistPage(Page page) {
		return studentDao.selectStudentslistPage(page);
	}

}
