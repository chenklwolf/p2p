package com.eloan.uiweb.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.eloan.business.service.SystemDictionaryUtil;

public class AddGlobalUtilInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private SystemDictionaryUtil systemDicUtil;

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView != null) {
			modelAndView.addObject("_DicUtil", systemDicUtil);
		}
		super.postHandle(request, response, handler, modelAndView);
	}

}
