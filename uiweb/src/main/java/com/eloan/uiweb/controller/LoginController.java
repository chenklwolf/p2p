package com.eloan.uiweb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eloan.business.domain.Logininfo;
import com.eloan.base.service.ILogininfoService;
import com.eloan.base.util.ResultJSON;

@Controller
public class LoginController extends BaseController {

	@Autowired
	private ILogininfoService loginInfoService;

	@RequestMapping("/login")
	@ResponseBody
	public ResultJSON login(String username, String password,HttpServletRequest request) {
		ResultJSON json = new ResultJSON();
		Logininfo login = this.loginInfoService.login(username, password,
				Logininfo.USERTYPE_NORMAL,request.getRemoteAddr());
		if (login != null) {
			json.setSuccess(true);
		} else {
			json.setMsg("用户名或者密码错误!");
		}
		return json;
	}

}
