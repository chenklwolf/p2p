package com.eloan.mgrtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eloan.base.util.ResultJSON;
import com.eloan.business.query.RealAuthQueryObject;
import com.eloan.business.service.IRealAuthService;

@Controller
public class RealAuthController extends BaseController {
	@Autowired
	private IRealAuthService realAuthService;

	/**
	 * 页面要求，已认证查看，未认证马上认证
	 * 0 申请
	 * 1 通过
	 * 2 拒绝
	 *
	 * 前台系统发送认证请求，后台系统接收请求认证处理
	 * @param qo
	 * @param model
	 * @return
	 */
	@RequestMapping("realAuth")
	public String realAuth(@ModelAttribute("qo") RealAuthQueryObject qo,
			Model model) {
		//已经认证的数据
		model.addAttribute("pageResult", this.realAuthService.query(qo));
		return "/realAuth/list"; //跳转到审核页面
	}

	@RequestMapping("realAuth_audit")
	@ResponseBody
	public ResultJSON realAuthAudit(Long id, String remark, int state) {
		ResultJSON json = new ResultJSON();
		try {
			this.realAuthService.audit(id, remark, state);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		return json;

	}

}
