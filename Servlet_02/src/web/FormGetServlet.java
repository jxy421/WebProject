package web;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
/**
 * ��Get��ʽ�ύ���ַ���
 * @author hdoop
 * 1. ����������ݸ�ʽ
 * 2. ������Ϣ���뷽ʽ
 * 3. ��ȡ�����
 * 4. ��ȡ��������ύ������
 * 5. ���get��ʽ�е����Ľ��б�������
 * 6. ��ҳ���������
 */
public class FormGetServlet extends HttpServlet{
	public void service(
			HttpServletRequest request,
			HttpServletResponse response
			)throws ServletException,IOException{
		//1.�趨�������UTF-8��ʽ�򿪣�֧������
		response.setContentType("text/html;charset=utf-8");
		//2.
		request.setCharacterEncoding("UTF-8");
		//3.
		PrintWriter out = response.getWriter();
		//4.
		String namestr = request.getParameter("textName");
		String agestr = request.getParameter("textAge");
		//5.
		String name = new String(namestr.getBytes("iso-8859-1"),"UTF-8");
		int age = Integer.parseInt(agestr);
		//6.
		out.println("������"+name+"<br/>���䣺"+age);
		out.close();
	}
}
