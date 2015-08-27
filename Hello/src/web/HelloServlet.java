package web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * ����
 * 1. ��дweb.HelloServletԴ�ļ�
 * 2. ��дweb.xml�ļ�
 * 3. ����Ӧ��
 * 4. ��������
 */
public class HelloServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		String url = req.getRequestURI();
		System.out.println(url);
		PrintWriter out = resp.getWriter();
		out.println("<h1>Hello World!���</h1>");
		out.close();
	}
}
