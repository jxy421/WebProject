package mytag;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
/**
 * 
 * @author hdoop
 * ���������ǩ�е���������ͬ
 * Ϊ�����ṩget��set����
 */
public class HelloTag extends SimpleTagSupport{
	private String str;
	private int count;
	@Override
	public void doTag() throws JspException, IOException {
		/**
		 * ʵ�ְ�conut�ƶ����������str����
		 */
		PageContext ctx = (PageContext)getJspContext();
		JspWriter out = ctx.getOut();//��ȡjspҳ��������
		for(int i=0;i<count;i++){
			out.println(str);
		}
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
