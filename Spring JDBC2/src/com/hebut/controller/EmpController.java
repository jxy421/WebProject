package com.hebut.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hebut.dao.EmpDao;
import com.hebut.entity.Emp;

@Controller//������ɨ��
public class EmpController {
	
	@Resource
	private EmpDao dao;
	
	public EmpDao getDao() {
		return dao;
	}

	public void setDao(EmpDao dao) {
		this.dao = dao;
	}

	@RequestMapping("/emplist")
	public String showList(Model model){
		//��ȡEmp��¼
		List<Emp> list = dao.findAll();
		model.addAttribute("emps", list);
		return "emp_list";
	}
}
