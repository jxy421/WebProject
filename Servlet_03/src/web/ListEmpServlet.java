package web;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * �г�Ա����Ϣ
 * @author hdoop
 * 
 */
public class ListEmpServlet extends HttpServlet{
	public void service(
			HttpServletRequest request,
			HttpServletResponse response
			)throws ServletException,IOException{
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","hadoop");
			pstmt = conn.prepareStatement("SELECT * FROM t_emp");
			/**
			 * ���Ա���б�
			 * 1. ���һ����html���
			 */
			
			out.println(
				"<html><head></head>"+
				"<caption>Ա����Ϣ</caption>"+
				"<table><tr><td>���<td/><td>����<td/>" +
				"<td>����<td/><td>����</td><td>����</td></tr>"
					);
			ResultSet res = pstmt.executeQuery();
			while(res.next()){
				int id = res.getInt("id");
				String name = res.getString("name");
				double salary = res.getDouble("salary");
				int age = res.getInt("age");
				out.println("<tr>");
				out.println("<td>"+id+"<td/>");
				out.println("<td>"+name+"<td/>");
				out.println("<td>"+salary+"<td/>");
				out.println("<td>"+age+"<td/>");
				//���ɾ��ʱ����Ҫ����ÿһ����¼��ID
				out.println("<td>"+
						"<a href='delemp?id="+id+"' onclick=\"return confirm('�Ƿ�ȷ��ɾ��"+name+"��')\">ɾ��</a>"+
						"<td/>");
				out.println("<a href='load?id="+id+"'>�޸�</a>");
				out.println("</tr>");
			}
			out.println("</table></body>"+
					"<a href='addemp.html'>����Ա��</a>"
					);
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
