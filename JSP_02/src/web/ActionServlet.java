package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet/Jsp��ʾ����
 * @author hdoop
 * 1. ��ʾ����
 * 2. ��ȡת����
 * 3. ת��
 */
public class ActionServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String str = "��ӭ��......";
		request.setAttribute("msg", str);
		RequestDispatcher rd = request.getRequestDispatcher("JSP_02/index.jsp");
		rd.forward(request, response);
	}

}
