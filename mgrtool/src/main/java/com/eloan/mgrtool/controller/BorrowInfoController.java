package com.eloan.mgrtool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eloan.business.domain.BidRequest;
import com.eloan.business.domain.BidRequestAuditHistory;
import com.eloan.business.domain.Realauth;
import com.eloan.business.domain.Userfile;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.query.BidRequestAuditHistoryQueryObject;
import com.eloan.business.query.UserFileQueryObject;
import com.eloan.business.service.IBidRequestAuditHistoryService;
import com.eloan.business.service.IBidRequestService;
import com.eloan.business.service.IRealAuthService;
import com.eloan.business.service.IUserFileService;
import com.eloan.business.service.IUserService;

/**
 * 用于显示借款明细
 * @author Administrator
 *
 */
@Controller
public class BorrowInfoController extends BaseController {

	@Autowired
	private IBidRequestService bidRequestService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IRealAuthService realAuthService;

	@Autowired
	private IBidRequestAuditHistoryService auditHistoryService;
	
	@Autowired
	private IUserFileService userFileService;
	
	@RequestMapping("borrow_info")
	public String borrowInfo(Long id, Model model) {
		//需要标的信息
		BidRequest bidRequest = this.bidRequestService.get(id);
		//借款人的userinfo信息
		Userinfo userinfo = this.userService.get(bidRequest.getCreateUser()
				.getId());
		//借款人的realAuth信息
		Realauth realAuth = this.realAuthService.get(userinfo.getRealauthId());
		//借款的所有审核信息
		BidRequestAuditHistoryQueryObject audiQO = new BidRequestAuditHistoryQueryObject();
		audiQO.setPageSize(-1);
		audiQO.setBidRequestId(id);
		List<BidRequestAuditHistory> history=this.auditHistoryService.queryList(audiQO);
		//借款人的所有通过审核的风控材料
		UserFileQueryObject ufQO=new UserFileQueryObject();
		ufQO.setPageSize(-1);
		ufQO.setState(Userfile.STATE_PASS);
		ufQO.setApplierId(userinfo.getId());
		List<Userfile> files=this.userFileService.queryList(ufQO);
		
		model.addAttribute("bidRequest",bidRequest);
		model.addAttribute("userInfo",userinfo);
		model.addAttribute("audits",history);
		model.addAttribute("realAuth",realAuth);
		model.addAttribute("userFiles",files);
		return "bidrequest/borrow_info";
	}

}
