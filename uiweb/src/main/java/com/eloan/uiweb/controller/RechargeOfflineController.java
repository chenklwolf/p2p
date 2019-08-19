package com.eloan.uiweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eloan.base.util.ResultJSON;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.RechargeOffline;
import com.eloan.business.query.RechargeOfflineQueryObject;
import com.eloan.business.service.ICompanyBankInfoService;
import com.eloan.business.service.IRechargeOfflineService;

/**
 * 前台的线下充值，前台的页面和后台的也买不应该一样，
 * 前台可以查看自己的账户明细
 *
 * jack 线下充值支付宝或者银行，充值到平台银行账户，有交易单号
 */
@Controller
public class RechargeOfflineController extends BaseController {

	@Autowired
	private ICompanyBankInfoService companyBankInfoService;

	@Autowired
	private IRechargeOfflineService rechargeOfflineService;

	//页面菜单点击账户充值
	@RequestMapping("rechargeOffline")
	public String rechargeOffline(Model model) {
		//可以充值P2P平台的账户
		model.addAttribute("banks", this.companyBankInfoService.list());
		return "recharge";
	}

	@RequestMapping("recharge_save")
	@ResponseBody
	public ResultJSON rechargeApply(RechargeOffline recharge) {
		ResultJSON json = new ResultJSON();
		//提交线下充值，设置为审核状态，等待后台审核
		this.rechargeOfflineService.apply(recharge);
		json.setSuccess(true);
		return json;
	}

	//查看充值的明细
	@RequestMapping("recharge_list")
	public String rechargeList(@ModelAttribute("qo")RechargeOfflineQueryObject qo,Model model){
		qo.setApplierId(UserContext.getCurrent().getId());
		model.addAttribute("pageResult",this.rechargeOfflineService.query(qo));
		return "recharge_list";
	}
}
