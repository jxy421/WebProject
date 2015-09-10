package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//���ص����ݸ�ʽ
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		if(uri.equals("/getInfo")){
			out.println("Hello moto!");
		}else if(uri.equals("/postInfo")){
			String str = request.getParameter("uname");
			out.println("hello"+str);
		}else if(uri.equals("/checkName")){
			String name = request.getParameter("name");
			name = new String(name.getBytes("iso-8859-1"),"utf-8");
			System.out.println(name);
			if(name.equals("����")){
				out.println("�û����������");
			}else{
				out.println("����ʹ��");
			}
		}else if(uri.equals("/getArea")){
			String cityName = request.getParameter("cityName");
			if(cityName.equals("bj")){
				//���ص���Ϣ����������
				//�����������Ϣ��value����
				out.println("����,hd;����,cy");
			}else if(cityName.equals("sh")){
				out.println("����,ja;�ֶ�,pd");
			}else{
				out.println("����,by;��خ,py");
			}
		}
		out.close();
	}

}
