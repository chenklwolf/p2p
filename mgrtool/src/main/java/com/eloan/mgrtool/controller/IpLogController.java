package com.eloan.mgrtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eloan.base.query.IpLogQueryObject;
import com.eloan.base.query.PageResult;
import com.eloan.base.service.IIpLogService;

@Controller
public class IpLogController extends BaseController {
	
	@Autowired
	private IIpLogService ipLogService;

	@RequestMapping("/iplog")
	public String ipLog(@ModelAttribute("qo") IpLogQueryObject qo, Model model) {
		qo.setLike(true);
		PageResult result = this.ipLogService.query(qo);
		model.addAttribute("pageResult", result);
		return "ipLog/list";
	}
}
