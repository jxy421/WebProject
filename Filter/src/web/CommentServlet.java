package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * ���������е����д�
 * @author hdoop
 * 1. ʹ�ñ��ύ���ۣ�servlet���ύ��������ʾ
 * 2. ��дʵ��Filter�ӿڵ�ʵ����
 * 		2.1 init()
 *      2.2 destory()
 *      2.3 doFilter()
 *  3. ע�����������web.xml���Filter�ڵ�
 */
public class CommentServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//��ȡ���ύ������
		String comm = request.getParameter("comment");
		out.println("�ύ�������ǣ�"+comm);
	}

}
