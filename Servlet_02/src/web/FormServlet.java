package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * ��ȡ��Ӧ��
 * @author hdoop
 * 1. �������������
 * 2. ��ȡ�����
 * 3. ��ȡ�ύ���ı����е�����
 * 4. ���ָ����Ϣ
 * 5. �ر���
 */
public class FormServlet extends HttpServlet{
	public void service(
			HttpServletRequest request,
			HttpServletResponse response
			)throws ServletException,IOException{
		//����������ݸ�ʽ
		response.setContentType("text/html;charset=utf-8");
		//���ý����ʽ
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("textName");
		out.println("<h1>Hello "+name+"</h1>");
		
		String[] hobbys = request.getParameterValues("hobby");
		if(hobbys!=null){
			out.println("Hobbys��");
			for (String str : hobbys) {
				out.println(str+"<br/>");
			}
		}
		out.close();
	}

}
