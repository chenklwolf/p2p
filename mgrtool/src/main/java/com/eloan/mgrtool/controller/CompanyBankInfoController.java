package com.eloan.mgrtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eloan.base.query.QueryObject;
import com.eloan.business.domain.CompanyBankInfo;
import com.eloan.business.service.ICompanyBankInfoService;

/**
 * 管理公司银行平台账户
 */
@Controller
public class CompanyBankInfoController extends BaseController {
	
	@Autowired
	private ICompanyBankInfoService bankInfoService;
	
	@RequestMapping("companyBank_list")
	public String companyBankList(@ModelAttribute("qo")QueryObject qo,Model model){
		model.addAttribute("pageResult",this.bankInfoService.query(qo));
		return "companybank/list";
	}
	
	@RequestMapping("companyBank_update")
	public String companyBankUpdate(CompanyBankInfo info){
		this.bankInfoService.save(info);
		return "redirect:companyBank_list.do";
	}

}
