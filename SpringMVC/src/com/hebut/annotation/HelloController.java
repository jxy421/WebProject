package com.hebut.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	//Ĭ����/hello.from,����׺��Ĭ��web.xml�еĺ�׺��
	@RequestMapping("/hello")
	public String execute(){
		return "hello";
	}
}
