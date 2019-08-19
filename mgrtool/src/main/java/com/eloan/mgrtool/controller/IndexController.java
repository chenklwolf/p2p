package com.eloan.mgrtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eloan.business.service.IAccountService;

@Controller
public class IndexController extends BaseController {
	
	@Autowired
	private IAccountService accountService;

	@RequestMapping("/index")
	public String index() {
		return "main";
	}
	
	/*@RequestMapping("/createAbstractInfo")
	public void createAbstractInfo(){
		accountService.recreateAbstractInfo();
	}*/
}
