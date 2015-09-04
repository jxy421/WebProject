package web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	// appname��Ӧ��������ʹ�øù�����ʱ����Ӧ������Ϊʵ�ʲ���ʱ��Ӧ����
	private static String appname = "/Servlet_05";

	/**
	 * ���һ��cookie��Ҫ���Ǳ�������
	 * @param name     Cookie����
	 * @param value    Cookieֵ
	 * @param age      Cookie��������
	 * @param response ��Ӧ����
	 * @throws UnsupportedEncodingException
	 */
	public static void addCookie(String name, String value,int age,HttpServletResponse response)
			throws UnsupportedEncodingException {
		Cookie c = new Cookie(name, URLEncoder.encode(value, "utf-8"));
		c.setMaxAge(age);
		c.setPath(appname);
		response.addCookie(c);
	}

	/**
	 * ����cookie���Ʒ���cookie��ֵ���Ҳ����򷵻�null
	 * @param name
	 * @param request
	 * @return
	 */
	public static String findCookie(String name, HttpServletRequest request) {
		String value = null;
		Cookie[] cooikes = request.getCookies();
		if (cooikes != null) {
			for (int i = 0; i < cooikes.length; i++) {
				Cookie c = cooikes[i];
				if (c.getName().equals(name)) {
					value = c.getValue();
				}
			}
		}
		return value;
	}

	/**
	 * ɾ��һ��cookie
	 * @param name
	 * @param response
	 */
	public static void deleteCookie(String name, HttpServletResponse response) {
		Cookie c = new Cookie(name, "");
		c.setMaxAge(0);
		response.addCookie(c);
	}

}
