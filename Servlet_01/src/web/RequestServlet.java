package web;
import java.io.IOError;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.imageio.*;
/**
 * Request ��ȡ������е�����
 * @author hdoop
 *
 */
public class RequestServlet extends HttpServlet{
	public void service(
			HttpServletRequest request,
			HttpServletResponse response
			)throws ServletException,IOException{
		//�����������
		response.setContentType("text/html;charset=utf-8");
		
		//��ȡ���е���Ϣͷ
		Enumeration enu = request.getHeaderNames();
		while(enu.hasMoreElements()){
			//���λ�ȡÿ����Ϣͷ������
			String name = enu.nextElement().toString();
			String value = request.getHeader(name);
			System.out.println(name+":"+value);
		}
		//��ȡURI:������Դ·��
		String uri = request.getRequestURI();
		//��ȡURL:����URI
		StringBuffer url = request.getRequestURL();
		System.out.println("url:"+url+"\n"+"uri:"+uri);
	}
}
