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

@Service
public class SomeServiceImpl implements SomeService {

	@Override
	public String doSome() {
		return " comfor ";
	}
}
