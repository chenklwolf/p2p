package com.eloan.uiweb.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.eloan.base.util.UserContext;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod hm = (HandlerMethod) handler;
			RequiredLogin rl = hm.getMethodAnnotation(RequiredLogin.class);
			if (rl != null) {
				if (request.getSession().getAttribute(
						UserContext.LOGIN_IN_SESSION) == null) {
					response.sendRedirect("/login.html");
					return false;
				}
			}
		}
		return super.preHandle(request, response, handler);
	}

}
