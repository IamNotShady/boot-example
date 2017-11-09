package com.boot.util.base;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;

/**
 * 基础Controller供每个Controller继承
 */
public class BaseController {
	/**
	 * 系统异常返回页面
	 */
	protected static final String EXCEPTION_PAGE = "err_page";

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	/**
	 * 每个controller方法执行前被执行
	 * 被@ModelAttribute注释的方法会在此controller每个方法执行前被执行
	 */
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}

	/**
	 * 取得session中的值
	 * 
	 * @param name
	 *            session主键
	 * @return 主键对应的值
	 */
	public Object getSession(String name) {
		return session.getAttribute(name);
	}

	/**
	 * 往session中添加键值对
	 * 
	 * @param name
	 *            session主键
	 * @param obj
	 *            session值
	 */
	public void setSession(String name, Object obj) {
		session.setAttribute(name, obj);
	}

	/**
	 * 添加cookie
	 * 
	 * @param key cookie主键
	 * @param value cookie值
	 */
	public void addCookie(String key, String value) {
		Cookie cookie = new Cookie(key, value);
		cookie.setPath("/");// 这个要设置
		cookie.setMaxAge(60 * 60 * 24 * 30);// 保留一个月 以秒为单位
		response.addCookie(cookie);
	}

	/**
	 * 删除cookie
	 * 
	 * @param key cookie主键
	 */
	public void deleteCookie(String key) {
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(key)) {
					Cookie cookie = new Cookie(key, null);
					cookie.setPath("/");// 设置成跟写入cookies一样的
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
	}

	/**
	 * 取得cookie的值
	 * 
	 * @param key cookie主键
	 */
	public String getCookieValue(String key) {
		String cookieVal = null;
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals(key)) {
				try {
					cookieVal = URLDecoder.decode(cookie.getValue(), "UTF-8");
				} catch (Exception e) {
				}
				break;
			}
		}
		return cookieVal;
	}

	/**
	 * 取得访问项目的url
	 */
	public String getBaseUrl() {
		return request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath();
	}

}