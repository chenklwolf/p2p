package com.eloan.mgrtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eloan.base.util.ResultJSON;
import com.eloan.business.query.RechargeOfflineQueryObject;
import com.eloan.business.service.ICompanyBankInfoService;
import com.eloan.business.service.IRechargeOfflineService;

/**
 * 线下充值审核
 */
@Controller
public class RechargeOfflineController extends BaseController {

	@Autowired
	private IRechargeOfflineService rechargeOfflineService;
	
	@Autowired
	private ICompanyBankInfoService companyBankInfoService;
	//线下充值审核页面
	@RequestMapping("rechargeOffline")
	public String rechageOfflineList(@ModelAttribute("qo")RechargeOfflineQueryObject qo,Model model){
		model.addAttribute("pageResult",this.rechargeOfflineService.query(qo));
		model.addAttribute("banks",this.companyBankInfoService.list());
		return "rechargeoffline/list";
	}
	
	@RequestMapping("rechargeOffline_audit")
	@ResponseBody
	public ResultJSON audit(Long id,String remark,int state){
		ResultJSON json=new ResultJSON();
		try{
			this.rechargeOfflineService.audit(id,remark,state);
			json.setSuccess(true);
		}catch(Exception e){
			json.setMsg(e.getMessage());
		}
		return json;
	}
}
