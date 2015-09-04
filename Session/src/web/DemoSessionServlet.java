package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DemoSessionServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//����Session
		HttpSession session = request.getSession();
		System.out.println(session.getId());
		//������
		String uname = (String)session.getAttribute("uname");
		if(uname == null){//��һ�η���
			System.out.println("��һ�η���");
			session.setAttribute("uname", "Kitty");
		}else{//�ǵ�һ�η���
			System.out.println("uname="+uname);
		}
		out.close();
	}

}

