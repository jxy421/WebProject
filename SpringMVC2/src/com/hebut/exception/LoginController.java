package com.hebut.exception;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestScope;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
@SessionAttributes("name")
public class LoginController extends BasedException{
	
	@RequestMapping("/toLogin")
	public String toLogin(){
		return "login";
	}
	
	@RequestMapping("/login")
	public String checkLogin3(User user,ModelMap model){
		System.out.println("check");
		String s = null;
		s.length();
		if("sun".equals(user.getUsername())&&"123".equals(user.getPassword())){
			//��ȷ������welcome.jspҳ��
			model.addAttribute("name",user.getUsername());
			return "welcome";
		}else{
			//���󣬽���login.jsp
			return "login";
		}
	}
	//�÷�ʽ�����ȼ���ߣ�������˭��ǰ˭��ִ��
//	@ExceptionHandler
//	public String myException(HttpServletRequest req,Exception ex){
//		//��LoginController���쳣����¼�쳣����ת��error.jsp
//		System.out.println("��¼���쳣��Ϣ"+ex);
//		return "error";
//	}

}
