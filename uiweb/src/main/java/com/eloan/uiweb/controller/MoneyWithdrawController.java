package com.eloan.uiweb.controller;

import com.eloan.base.util.ResultJSON;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.service.IAccountService;
import com.eloan.business.service.IMoneyWithdrawService;
import com.eloan.business.service.IUserBankinfoService;
import com.eloan.business.service.IUserService;
import com.eloan.uiweb.interceptor.RequiredLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

/**
 * 提现相关，用户申请提现
 * 
 * @author Administrator
 * 
 */
@Controller
public class MoneyWithdrawController {

	@Autowired
	private IMoneyWithdrawService moneyWithdrawService;
	
	@Autowired
	private IUserService userinfoService;

	@Autowired
	private IUserBankinfoService userBankinfoService;

	@Autowired
	private IAccountService accountService;

	/**
	 * 导向到提现申请界面
	 */
	@RequiredLogin
	@RequestMapping("moneyWithdraw")
	public String moenyWithdraw(Model model) {
		Userinfo current = this.userinfoService.get(UserContext.getCurrent().getId());
		model.addAttribute("haveProcessing", current.getHasWithdrawProcess());
		model.addAttribute("bankInfo",
				this.userBankinfoService.getByUser(current.getId()));
		model.addAttribute("account", this.accountService.get(UserContext.getCurrent().getId()));
		return "moneyWithdraw_apply";
	}
	
	/*8
	 * 提现申请
	 */
	@RequiredLogin
	@RequestMapping("/moneyWithdraw_apply")
	@ResponseBody
	public ResultJSON apply(BigDecimal moneyAmount){
		this.moneyWithdrawService.apply(moneyAmount);
		return new ResultJSON("提现申请");
	}
	
}
