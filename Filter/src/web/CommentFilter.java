package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommentFilter implements Filter {
	private String keyword = null;
	/**
	 * ���������ٹ�����ʵ��ʱ���õķ���
	 * ֵ����һ��
	 */
	public void destroy() {

	}
	/**
	 * �������ù��˵ľ����߼����÷�������������
	 * 1. arg0: ��������ʱ��ȡ��������󣬼�request
	 * 2. arg1: ����������������Ӧ���󣬼�response
	 * 3. arg2: ������������FilterChain,�ö�����Ծ����ǽ����󴫸�������servlet������ֱ����Ӧ����ֹ��������
	 */
	public void doFilter(
			ServletRequest arg0, 
			ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		//ת������ΪHttpServlet����
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡ�����ύ��������Ϣ
		String comm = request.getParameter("comment");
		//�ж��������Ǹ��Ƿ������������
		PrintWriter out = response.getWriter();
		if(comm.indexOf(keyword)!=-1){
			out.println("�����Ѿ��ر�");
		}else{
			/**
			 * ���û�����дʣ������������������������
			 * ������doFilter�������
			 */
			arg2.doFilter(request, response);
			out.println("���ܲ�");
		}
	}
	/**
	 * ����������ʱ��������������Filter��ʵ��
	 * ����ʵ���󣬻���Ѿ�׼���õ�FilterConfig���󴫸�init()����
	 * ֵ����һ��init����
	 * ͨ��FilterConfig���Ի�ȡ�������йظù�������һЩ��ʼ������
	 */
	public void init(FilterConfig arg0) throws ServletException {
			keyword = arg0.getInitParameter("word");
	}

}
