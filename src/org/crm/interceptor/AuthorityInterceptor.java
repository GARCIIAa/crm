package org.crm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthorityInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String currentURL = request.getRequestURI();
		// 截取到当前文件名用于比较
		String targetURL = currentURL.substring(currentURL.indexOf("/", 1), currentURL.length());
		// 如果session不为空就返回该session，如果为空就返回null
		System.out.println(targetURL + "###");
		System.out.println(currentURL + "####");
		System.out.println(request.getContextPath() + "#####");
		if (!"/".equals(targetURL)) {
			HttpSession session = request.getSession(false);
			// 判断当前页面是否是重定向后的登陆页面，如果是就不做session的判断，防止死循环
			if (session == null || session.getAttribute("userId") == null) {
				// 如果session为空表示用户没有登陆就重定向到login.jsp页面
				System.out.println("1###");
				response.sendRedirect(request.getContextPath());
				return false;
			}
		}
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
