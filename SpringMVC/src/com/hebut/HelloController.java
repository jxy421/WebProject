package com.hebut;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.ControllerEventListener;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HelloController implements Controller{
	//Ĭ�ϴ�������ķ���
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		//����ҵ���߼������Ե���Dao
	    //����View��ͼ�������hello.jspΪhello
		ModelAndView mav = new ModelAndView("hello");
		return mav;
	}
	
}
