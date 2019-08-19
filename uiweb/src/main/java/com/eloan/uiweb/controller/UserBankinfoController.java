package com.eloan.uiweb.controller;

import com.eloan.base.util.UserContext;
import com.eloan.business.domain.UserBankinfo;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.service.IUserBankinfoService;
import com.eloan.business.service.IUserService;
import com.eloan.uiweb.interceptor.RequiredLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 用户绑定银行卡
 * 
 * @author Administrator
 * 
 */
@Controller
public class UserBankinfoController {

	@Resource
	private IUserBankinfoService userBankinfoService;

	@Autowired
	private IUserService userinfoService;

	/**
	 * 导向到绑定银行卡界面
	 */
	@RequiredLogin
	@RequestMapping("bankInfo")
	public String bankInfo(Model model) {
		Userinfo current = userinfoService.get(UserContext.getCurrent().getId());
		if (!current.getIsBindBank()) {
			// 没有绑定，到绑定银行卡页面
			model.addAttribute("userinfo", current);
			return "bankInfo";
		} else { //已经绑定,显示绑定的银行卡信息
			model.addAttribute("bankInfo",
					userBankinfoService.getByUser(current.getId()));
			return "bankInfo_result";
		}
	}

	/**
	 * 执行绑定
	 */
	@RequiredLogin
	@RequestMapping("bankInfo_save")
	public String bankInfoSave(UserBankinfo bankInfo) {
		this.userBankinfoService.bind(bankInfo);
		return "redirect:/bankInfo.do";
	}
}
