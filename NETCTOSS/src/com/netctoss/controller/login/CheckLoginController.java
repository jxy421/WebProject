package com.netctoss.controller.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netctoss.dao.AdminInfoMapperDao;
import com.netctoss.entity.AdminInfo;

@Controller
@RequestMapping("/login")
public class CheckLoginController {
	@Resource
	private AdminInfoMapperDao dao;
	
	@RequestMapping("/login")
	public String login(AdminInfo admin,Model model,HttpSession session){
		//�����֤��
		String scode = (String) session.getAttribute("scode");
		if(!admin.getCode().equalsIgnoreCase(scode)){
			//��֤�����
			model.addAttribute("codeError", "��֤�����");
			return "login";
		}
		AdminInfo info = dao.findByAdminCodeAndPwd(admin);
		if(info!=null){//�м�¼
			session.setAttribute("user", admin.getCode());
			return "index";
		}else{//�޼�¼
			model.addAttribute("error", "�û������������������...");
			return "login";
		}
	}
}
