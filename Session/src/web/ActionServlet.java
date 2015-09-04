package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ActionServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		if(uri.equals("/login")){
			//��ȡ������
			String uname = request.getParameter("uname");
			String password = request.getParameter("pwd");
			if(uname.equals("Kitty")){//��֤ͨ��
				HttpSession session = request.getSession();
				session.setAttribute("uname", uname);
//				session.setMaxInactiveInterval(10);//����session��ʱʱ��
//				session.invalidate();//ɾ��session
				//�ض�����ҳ
				response.sendRedirect("index.jsp");
//				response.encodeRedirectURL("index.jsp");//����Cookie���ض���
			}else{//��֤ʧ��
				request.setAttribute("msg", "�û������������");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}else if(uri.equals("/logout")){
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("login.jsp");
		}else if(uri.equals("/login2")){
			String inCode = request.getParameter("textCode");//�û��������֤��
			//��ȡsessio����ʵ����֤��
			HttpSession session = request.getSession();
			String tCode = session.getAttribute("c").toString();
			if(inCode.equalsIgnoreCase(tCode)){
				response.sendRedirect("login.jsp");
			}else{
				response.sendRedirect("regist.jsp");
			}
		}
		out.close();
	}

}
