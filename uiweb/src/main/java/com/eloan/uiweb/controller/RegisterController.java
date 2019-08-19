package com.eloan.uiweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eloan.business.domain.Logininfo;
import com.eloan.base.service.ILogininfoService;
import com.eloan.base.util.ResultJSON;

@Controller
public class RegisterController extends BaseController {

	@Autowired
	private ILogininfoService logininfoService;

	@RequestMapping("/register")
	@ResponseBody
	public ResultJSON register(String username, String password) {
		ResultJSON json = new ResultJSON();
		try {
			this.logininfoService.register(username, password);
			json.setSuccess(true);
		} catch (RuntimeException e) {
			json.setMsg(e.getMessage());
		}
		return json;
	}

	@RequestMapping("/checkUsername")
	@ResponseBody
	public ResultJSON checkUsername(String username) {
		ResultJSON json = new ResultJSON();
		json.setSuccess(this.logininfoService.checkUsername(username,Logininfo.USERTYPE_NORMAL));
		return json;
	}
	
}
