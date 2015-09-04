package web;
import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Ϊ�˼���ѡ�е�Ҫ�޸ĵ�������Ҫ
 * ����url�е�id��ѯ��Ӧ������
 * ��ʾ�ڱ��У��ṩ�޸�
 */
public class LoadEmpServlet extends HttpServlet{
	public void service(
			HttpServletRequest request,
			HttpServletResponse response
			)throws ServletException,IOException{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		int id = Integer.parseInt(request.getParameter("id"));
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","hadoop");
			pstmt = conn.prepareStatement("SELECT * FROM t_emp WHERE id=?");
			pstmt.setInt(1, id);
			res = pstmt.executeQuery();
			while(res.next()){
				int eid = res.getInt("id");
				String name = res.getString("name");
				double salary = res.getDouble("salary");
				int age = res.getInt("age");
				out.println("<html><head>");
				out.println("</head><body>");
				out.println("<form action='modifyemp' method='post'>");
				out.println("��ţ�"+id+"<br/>");
				//���id�����ر���
				out.println("<input type='hidden' name='id' value='"+id+"'>");
				out.println("������<input name='name' value='"+name+"'/><br/>");
				out.println("нˮ��<input name='salary' value='"+salary+"'/><br/>");
				out.println("���䣺<input name='age' value='"+age+"'/><br/>");
				out.println("<input type='submit' value='����'/>");
				out.println("</form></body></html>");
			}
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
