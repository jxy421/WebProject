package com.hebut.annotation.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginIntercept implements HandlerInterceptor{

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}

	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		// ����Ƿ��½������½Controller�Ƿ����name
		Object user = arg0.getSession().getAttribute("name");
		if(user!=null ){
			//�е�½��Ϣ�������������
			System.out.println("ksjfksk");
			return true;
		}else{
			//û�е�½��Ϣ�����������
			System.out.println("OOOOOOO");
			arg1.sendRedirect("/SpringMVC/login11/toLogin3.from");
			return false;
		}
	}

}
