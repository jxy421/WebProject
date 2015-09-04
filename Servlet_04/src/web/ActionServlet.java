package web;
import javax.servlet.*;
import javax.servlet.http.*;

import dao.EmployeeDAO;
import entity.Employee;

import java.io.*;
import java.util.List;
/**
 * servelt ����ַ�
 * @author hdoop
 * ����.do��β�����󶼽�����service��������
 * ����Ҫ��ȡURI,�ж��������
 * ���зַ�
 */
public class ActionServlet extends HttpServlet{
	public void service(
			HttpServletRequest request,
			HttpServletResponse response
			)throws ServletException,IOException{
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		//��ȡuri/appName/addemp.do
		String uri = request.getRequestURI();
		//��ȡ��׺doǰ���ַ�
		uri = uri.substring(
				uri.lastIndexOf("/"),
				uri.lastIndexOf(".")
				);
		//��ͬ�����ַ�
		if(uri.equals("/listemp")){
			//��ѯ����
			EmployeeDAO dao =null;
			List<Employee> emps = null; 
			
			try {
				dao = new EmployeeDAO();
				emps = dao.findAll();
			} catch (Exception e) {
				e.printStackTrace();
			}
			//��ʾ������ݵ����
			out.println("<html><head>");
			out.println("</head><body>");
			out.println("<table>");
			out.println("<tr>");
			out.println("<td>���</td>");
			out.println("<td>����</td>");
			out.println("<td>����</td>");
			out.println("<td>����</td>");
			out.println("<td>����</td>");
			out.println("</tr>");
			for (Employee emp : emps) {
				out.println("<tr>");
				out.println("<td>"+emp.getId()+"</td>");
				out.println("<td>"+emp.getName()+"</td>");
				out.println("<td>"+emp.getSalary()+"</td>");
				out.println("<td>"+emp.getAge()+"</td>");
				out.println("<td>"+
							"<a href='delemp.do?id="+emp.getId()+"'>ɾ��</a></td>");
				out.println("<td>"+
						"<a href='loademp.do?id="+emp.getId()+"'>�޸�</a></td>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("</body></html>");
		}else if(uri.equals("/addemp")){
			//���Ӳ���
			//��ȡ���ύ����
			String name = request.getParameter("name");
			double salary = Double.parseDouble(request.getParameter("salary"));
			int age = Integer.parseInt(request.getParameter("age"));
			/**
			 * 1. ����������һ��Employee����
			 * 2. ʵ����һ��DAO����
			 * 3. ����dao�����save����ʵ�ֱ���
			 * 4. ʹ���ض�����ת��/listemp.do
			 */
			try{
				Employee emp = new Employee();
				emp.setName(name);
				emp.setSalary(salary);
				emp.setAge(age);
				
				EmployeeDAO dao = new EmployeeDAO();
				dao.save(emp);
				response.sendRedirect("listemp.do");
				//���ӹ��ܴ�addemp.htmlҳ�濪ʼ
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(uri.equals("/delemp")){
			//��ȡ��ַ���е�����id
			int id = Integer.parseInt(request.getParameter("id"));
			//����dao����ִ��ɾ������
			try {
				EmployeeDAO dao = new EmployeeDAO();
				dao.delete(id);
				response.sendRedirect("listemp.do");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(uri.equals("/loademp")){
			//��ȡ��ַ���е�����id
			int id = Integer.parseInt(request.getParameter("id"));
			//����dao����ִ��ɾ������
			try {
				EmployeeDAO dao = new EmployeeDAO();
				Employee emp = dao.findById(id);
				//��ʾ��
				out.println("<html><head></head><body>" +
						"<form action='modifyemp.do' method='post'>");
				out.println("��ţ�"+id+"<br/>");
				//���ر���
				out.println("<input type='hidden' name='id' value='"+id+"'/><br/>");
				out.println("������<input name='name' value='"+emp.getName()+"'/><br/>");
				out.println("нˮ��<input name='salary' value='"+emp.getSalary()+"'/><br/>");
				out.println("���䣺<input name='age' value='"+emp.getAge()+"'/><br/>");
				out.println("���䣺<input type='submit' value='����'/><br/>");
				out.println("</form></body></html>");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(uri.equals("/modifyemp")){
			/**
			 * ��ȡ������
			 * ��װ��emp����
			 * ����dao��modify����
			 * �޸ĺ���Ҫ�ض��򵽲�ѯ���
			 */
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			double salary = Double.parseDouble(request.getParameter("salary"));
			int age = Integer.parseInt(request.getParameter("age"));
			Employee emp = new Employee(id, name, salary, age);
			EmployeeDAO dao = new EmployeeDAO();
			try {
				dao.modify(emp);
				response.sendRedirect("listemp.do");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
