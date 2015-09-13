package web;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.omg.CORBA.Request;
/**
 * ����session����������ͳ��
 * @author hdoop
 * ʵ��ͳ�Ƶ���session����
 */
public class CountListener implements HttpSessionListener{
	private int count;
	public void sessionCreated(HttpSessionEvent se) {
		/*
		 * �ͻ����������jspҳ�棬��ô�������Զ�����һ��session����
		 * jspҳ������ܶ�ϵͳΪ�ͻ����Զ�������������
		 * session�������Զ���������������
		 */
		count++;
		/*
		 * ������
		 * request,session,servletContext
		 */
		HttpSession session = se.getSession();
		session.getServletContext().setAttribute("count", count);
		
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		count--;
		HttpSession session = se.getSession();
		session.getServletContext().setAttribute("count", count);
	}

}
