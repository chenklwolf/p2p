package com.eloan.mgrtool.controller;

import com.eloan.base.util.ResultJSON;
import com.eloan.business.query.MoneyWithdrawQueryObject;
import com.eloan.business.service.IMoneyWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 提现申请审核
 * 
 * @author Administrator
 * 
 */
@Controller
public class MoneyWithdrawController {

	@Autowired
	private IMoneyWithdrawService moneyWithdrawService;

	/**
	 * 提现审核页面
	 * @param qo
	 * @param model
	 * @return
	 */
	@RequestMapping("moneyWithdraw")
	public String moneyWithdrawList(
			@ModelAttribute("qo") MoneyWithdrawQueryObject qo, Model model) {
		model.addAttribute("pageResult", this.moneyWithdrawService.query(qo));
		return "moneywithdraw/list";
	}

	/**
	 * 进行审核
	 * @param id
	 * @param remark
	 * @param state
	 * @return
	 */
	@RequestMapping("moneyWithdraw_audit")
	@ResponseBody
	public ResultJSON audit(Long id, String remark, int state) {
		this.moneyWithdrawService.audit(id, remark, state);
		//审核结果
		return new ResultJSON("审核结果");
	}
}
