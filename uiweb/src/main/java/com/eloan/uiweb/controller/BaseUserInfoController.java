package com.eloan.uiweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eloan.base.util.ResultJSON;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.service.IUserService;
import com.eloan.uiweb.interceptor.RequiredLogin;

/**
 * 用户基本信息controller
 * @author Administrator
 *
 */
@Controller
public class BaseUserInfoController extends BaseController {

	@Autowired
	private IUserService userService;


	@RequiredLogin
	@RequestMapping("basicInfo")
	public String basicInfo(Model model) {
		Userinfo userinfo = this.userService.get(UserContext.getCurrent()
				.getId());
		model.addAttribute("userinfo", userinfo);
		return "userInfo";
	}

	/**
	 * 填写个人资料
	 */
	@RequestMapping("basicInfo_save")
	@ResponseBody
	public ResultJSON save(Userinfo userinfo) {
		ResultJSON json = new ResultJSON();
		try {
			this.userService.updateBasicInfo(userinfo);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		return json;
	}

}
