package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeDAO;
import dao.UserDao;
import entity.Employee;
import entity.User;
/**
 * Ա����Ϣ����ϵͳ
 * @author hdoop
 * 1. ��ѯ����
 * 	1.1 ��ȡURI
 *  1.2 ��ȡURI����
 *  1.3 dao��ȡ����
 *  1.4 ������
 *  1.5 ת�����ݵ�listemp.jsp
 * 2. ���ӹ���
 *  2.1 �޸�listEmp.jsp��"����Ա��"��ť������
 *  2.2 �½�addEmp.jspҳ��
 *  2.3 �޸ı���actionΪ"addemp.do"
 *  2.4 �޸ı��и����ı����е�����
 *  2.5 ��ActionServlet�����"addemp"��֧
 *  2.6 ��ȡ���ύ�����ݣ���װ��emp������
 *  2.7 ʵ����dao���󣬵���save����
 *  2.8 �ض���listemp.do
 * 3. ɾ������
 * 	3.1  ��listEmp.jspҳ��"���","ɾ��"�޸Ĳ���
 *  3.2 �޸�ɾ���޸����ӣ��ֱ��������id
 *  3.3 ��ActionServlet����delemp��֧
 *  3.4 ��ȡ�����������ɾ����¼id
 *  3.5 ����dao,����delete()����ɾ����¼
 *  3.6 �ض���listemp.do
 * 4. �޸Ĺ���
 * 	4.1 �����޸���Ϣ
 * 		4.1.1 ����UpdateEmp.jspҳ�棬��ʾ������Ϣ
 * 		4.1.2 �޸�UpdateEmp.jspҳ���actionΪpost
 *      4.1.3 ��UpdateEmp.jsp�������ر�����id
 *      4.1.4 ��ActionServlet����loademp��֧
 *      4.1.5 ���������id
 *      4.1.6 �½�dao����findEmpById(id)����ȡ�޸ļ�¼��Ϣ
 *      4.1.7 ת����UpdatEmp.jspҳ����ʾ
 *  4.2 �����޸���Ϣ
 *  	4.2.1 ��ȡUpdateEmp.jspҳ���ύ�ı�����
 *  	4.2.2 �½�dao����modify(id)���޸�д�����ݿ�
 *  	4.2.3 �ض���listemp.do
 */
public class ActionServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/"), uri.indexOf("."));
		if(uri.equals("/listemp")){
			try{
				EmployeeDAO dao = new EmployeeDAO();
				List<Employee> emps = dao.findAll();
				request.setAttribute("allEmp", emps);
				//һ��������ת���������ض���
				request.getRequestDispatcher("listEmp.jsp").forward(request, response);
			}catch(Exception e){
				e.printStackTrace();
				request.setAttribute("msg", "ϵͳ��æ");
				request.getRequestDispatcher("err.jsp").forward(request, response);
			}
		}else if(uri.equals("/addemp")){
			String name = request.getParameter("name");
			double salary = Double.parseDouble(request.getParameter("salary"));
			int age = Integer.parseInt(request.getParameter("age"));
			Employee emp = new Employee(0, name, salary, age);
			EmployeeDAO dao = new EmployeeDAO();
			try {
				dao.save(emp);
				response.sendRedirect("listemp.do");
			} catch (Exception e) {
				e.printStackTrace();
				//���쳣�׸�����
				throw new ServletException(e);
			}
		}else if(uri.equals("/delemp")){
			int id = Integer.parseInt(request.getParameter("id"));
			EmployeeDAO dao = new EmployeeDAO();
			try {
				dao.delete(id);
				response.sendRedirect("listemp.do");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(uri.equals("/loademp")){
			int id = Integer.parseInt(request.getParameter("id"));
			EmployeeDAO dao = new EmployeeDAO();
			try {
				Employee emp = dao.findById(id);
				request.setAttribute("EmpInfo", emp);
				request.getRequestDispatcher("UpdateEmp.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(uri.equals("/modifyemp")){
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(uri.equals("/regist")){
			String incode = request.getParameter("incode");
			HttpSession session = request.getSession();
			String checkcode = session.getAttribute("checkcode").toString();
			if(!incode.equalsIgnoreCase(checkcode)){
				request.setAttribute("checkmsg", "��֤�����");
				request.getRequestDispatcher("regist.jsp").forward(request, response);
				return ;
			}
			String username = request.getParameter("username");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			UserDao dao = new UserDao();
			try {
				User user = dao.findByUserName(username);
				if(user != null){
					request.setAttribute("regist_error", "�û����Ѿ����ڣ�");
					request.getRequestDispatcher("regist.jsp").forward(request, response);
				}else{
					user = new User(username, pwd, name, gender);
					dao.save(user);
					response.sendRedirect("login.jsp");
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
		}else if(uri.equals("/login")){
			String incode = request.getParameter("incode");
			HttpSession session = request.getSession();
			String checkcode = session.getAttribute("checkcode").toString();
			if(!incode.equalsIgnoreCase(checkcode)){
				request.setAttribute("checkmsg", "��֤�����");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return ;
			}
			String username = request.getParameter("username");
			String password = request.getParameter("pwd");
			UserDao dao = new UserDao();
			User user = null;
			try {
				user = dao.findByUserName(username);
				if(user == null||!user.getPwd().equals(password)){
					request.setAttribute("login_error", "�û������������");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}else{
					session.setAttribute("uname", username);
					response.sendRedirect("listemp.do");
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
		}else if(uri.equals("/logout")){
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("login.jsp");
		}
	}

}
