package com.eloan.mgrtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eloan.base.util.ResultJSON;
import com.eloan.business.query.BidRequestQueryObject;
import com.eloan.business.service.IBidRequestService;
import com.eloan.business.util.BidConst;

@Controller
public class BidRequestAuditController extends BaseController {

	@Autowired
	private IBidRequestService bidRequestService;

	/**
	 * 列出发标前审核的标的信息
	 * @return
	 */
	@RequestMapping("bidrequest_publishaudit_list")
	public String publishAuditList(BidRequestQueryObject qo, Model model) {
		qo.setState(BidConst.BIDREQUEST_STATE_PUBLISH_PENDING);
		model.addAttribute("pageResult", this.bidRequestService.query(qo));
		return "bidrequest/publish_audit";
	}

	/**
	 * 发标前审核页面信息接收，审核
	 */
	@RequestMapping("bidrequest_publishaudit")
	@ResponseBody
	public ResultJSON publishAudit(Long id,String remark,int state){
		ResultJSON json=new ResultJSON();
		try{
			this.bidRequestService.publishAudit(id,remark,state);
			json.setSuccess(true);
		}catch(Exception e){
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	/**
	 * 列出处于满标一审的标的信息
	 * @return
	 */
	@RequestMapping("bidrequest_audit1_list")
	public String audit1List(BidRequestQueryObject qo, Model model) {
		qo.setState(BidConst.BIDREQUEST_STATE_APPROVE_PENDING_1);// 满标1审
		model.addAttribute("pageResult", this.bidRequestService.query(qo));//借款对象
		return "bidrequest/audit1";
	}
	
	/**
	 * 列出处于满标二审的标的信息
	 * @return
	 */
	@RequestMapping("bidrequest_audit2_list")
	public String audit2List(BidRequestQueryObject qo, Model model) {
		qo.setState(BidConst.BIDREQUEST_STATE_APPROVE_PENDING_2);
		model.addAttribute("pageResult", this.bidRequestService.query(qo));
		return "bidrequest/audit2";
	}
	
	/**
	 * 满标一审
	 */
	@RequestMapping("bidrequest_audit1")
	@ResponseBody
	public ResultJSON bidRequestAudit1(Long id,String remark,int state){
		ResultJSON json=new ResultJSON();
		try{
			this.bidRequestService.audit1(id,remark,state);
			json.setSuccess(true);
		}catch(Exception e){
			e.printStackTrace();
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	/**
	 * 满标二审
	 */
	@RequestMapping("bidrequest_audit2")
	@ResponseBody
	public ResultJSON bidRequestAudit2(Long id,String remark,int state){
		ResultJSON json=new ResultJSON();
		try{
			this.bidRequestService.audit2(id,remark,state);
			json.setSuccess(true);
		}catch(Exception e){
			e.printStackTrace();
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	
	
}
