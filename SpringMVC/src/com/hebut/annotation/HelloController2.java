package com.hebut.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class HelloController2 {
	//Ĭ����/hello.from,����׺��Ĭ��web.xml�еĺ�׺��
	@RequestMapping("/hello")
	public String execute(){
		return "hello";
	}
}
