package com.eloan.uiweb.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eloan.business.domain.Logininfo;
import com.eloan.base.query.PageResult;
import com.eloan.base.util.ResultJSON;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.BidRequest;
import com.eloan.business.domain.Userfile;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.query.BidQueryObject;
import com.eloan.business.query.BidRequestQueryObject;
import com.eloan.business.query.UserFileQueryObject;
import com.eloan.business.service.IAccountService;
import com.eloan.business.service.IBidRequestService;
import com.eloan.business.service.IRealAuthService;
import com.eloan.business.service.IUserFileService;
import com.eloan.business.service.IUserService;
import com.eloan.business.util.BidConst;

/**
 * 借款模块
 * @author Administrator
 *
 */
@Controller
public class BorrowController extends BaseController {

	@Autowired
	private IAccountService accountService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IBidRequestService bidRequestService;
	
	@Autowired
	private IUserFileService userFileService;
	
	@Autowired
	private IRealAuthService realAuthService;

	/**
	 * 我要借款
	 * @return
	 */
	@RequestMapping("/borrow")
	public String borrowIndex(Model model) {
		Logininfo current = UserContext.getCurrent();
		if (current == null) {
			return "redirect:borrow.html"; //返回到未登录的首页
		}
		model.addAttribute("account", this.accountService.get(current.getId()));
		model.addAttribute("userinfo", this.userService.get(current.getId()));
		model.addAttribute("creditBorrowScore", BidConst.CREDIT_BORROW_SCORE);
		return "borrow";
	}

	/**
	 * 导向到借款界面
	 */
	@RequestMapping("")
	public String borrowInfo(Model model) {
		//判断是否可以借款
		boolean canBidRequest = this.bidRequestService.canBorrow(UserContext
				.getCurrent());
		if (canBidRequest) {
			model.addAttribute("account",
					this.accountService.get(UserContext.getCurrent().getId()));
			model.addAttribute("minBidRequestAmount",
					BidConst.SMALLEST_BIDREQUEST_AMOUNT); //最小借款金额
			model.addAttribute("minBidAmount", BidConst.SMALLEST_BID_AMOUNT);//最小投标金额
			return "borrow_apply";
		} else {
			return "borrow_apply_result";
		}
	}

	/**
	 * 借款申请处理
	 */
	@RequestMapping("borrow_apply")
	public String borrowApply(BidRequest bidRequest) {
		this.bidRequestService.apply(bidRequest);
		//借款成功失败都到借款判断页面中去
		return "redirect:borrowInfo.do";
		//借款如果成功进入后台发标前审核
	}

	/**
	 * 用于显示投资列表框架页面的方法，这个我要投资展示是头+身体，
	 * 身体是另一个页面ajax请求的数据。
	 */
	@RequestMapping("invest")
	public String invest() {
		return "invest";
	}

	/**
	 * 用于显示投资列表分页内容的方法
	 */
	@RequestMapping("invest_list")
	public String investList(int bidRequestState, int currentPage, Model model) {
		BidRequestQueryObject qo = new BidRequestQueryObject();
		if (bidRequestState == -1) { //借款状态
			qo.setStates(new int[] { BidConst.BIDREQUEST_STATE_BIDDING,// 招标中
					BidConst.BIDREQUEST_STATE_APPROVE_PENDING_1, // 满标1审
					BidConst.BIDREQUEST_STATE_APPROVE_PENDING_2,// 满标2审
					BidConst.BIDREQUEST_STATE_PAYING_BACK,// 还款中
					BidConst.BIDREQUEST_STATE_COMPLETE_PAY_BACK });// 已还清
		} else {
			qo.setState(bidRequestState);//查询具体状态
		}
		qo.setCurrentPage(currentPage);
		PageResult result = this.bidRequestService.query(qo);
		model.addAttribute("pageResult", result);
		return "invest_list";
	}

	//借款详情页面，理论上说这个页面应该在管理系统中的页面，图省事
	@RequestMapping("borrow_info")
	public String borrowInfo(long id,Model model){
		//标相关内容
		BidRequest bidRequest=this.bidRequestService.get(id);
		Logininfo current=UserContext.getCurrent();
		//借款人
		Userinfo userinfo=this.userService.get(bidRequest.getCreateUser().getId());
		//如果是当前借款人用户登录self为true，其他用户false
		model.addAttribute("self",current!=null &&current.getId()==userinfo.getId());
		if(current!=null){
			model.addAttribute("account",this.accountService.get(current.getId()));
		}
		//借款人的realauth
		model.addAttribute("realAuth",this.realAuthService.get(userinfo.getRealauthId()));
		//借款人的userfiles;
		UserFileQueryObject qo=new UserFileQueryObject();
		qo.setPageSize(-1);
		qo.setState(Userfile.STATE_PASS);
		qo.setApplierId(userinfo.getId());
		model.addAttribute("userFiles",this.userFileService.queryList(qo));//风控材料
		model.addAttribute("userInfo",userinfo);
		model.addAttribute("bidRequest",bidRequest);
		
		return "borrow_info";
	}

	/*=======================投标操作===================================*/


	/*
	 * 投标操作,每次投的钱可以添加限制,50或100的整数便于计算,让钱最后能全部被投完,
	 * 每个投资人一比标不能超过20%,降低风险.
	 * 投资的时候加上密码验证,避免抢标,瞬时并发高,并增加安全性,防止误操作
	 * @param amount
	 * @param bidRequestId
	 * @return
	 */
	@RequestMapping("borrow_bid") //投标操作
	@ResponseBody
	public ResultJSON borrowBid(BigDecimal amount,Long bidRequestId){
		ResultJSON json=new ResultJSON();
		try{
			this.bidRequestService.bid(amount,bidRequestId);
			json.setSuccess(true);
		}catch(Exception e){
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	/**
	 * 个人中心-借款明细
	 */
	@RequestMapping("myborrow_list")
	public String myBorrowList(@ModelAttribute("qo")BidRequestQueryObject qo,Model model){
		qo.setCreateUserId(UserContext.getCurrent().getId());
		model.addAttribute("pageResult",this.bidRequestService.query(qo));
		model.addAttribute("bidRequestStates",this.bidRequestService.listBidRequestStates());
		return "bidRequest_list";
	}
	
	/**
	 * 个人中心-投资明细
	 */
	@RequestMapping("bid_list")
	public String bidList(@ModelAttribute("qo")BidQueryObject qo,Model model){
		qo.setBidUserId(UserContext.getCurrent().getId());
		model.addAttribute("pageResult",this.bidRequestService.queryBid(qo));
		model.addAttribute("bidRequestStates",this.bidRequestService.listBidRequestStates());
		return "bid_list";
	}
}
