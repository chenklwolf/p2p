package com.eloan.business.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;
import com.eloan.base.domain.BaseDomain;
import com.eloan.business.util.BidConst;

/**
 * 借款对象
 * @author Administrator
 */
@Getter
@Setter
@Alias("BidRequest")
public class BidRequest extends BaseDomain {

	private int version;// 版本
	private int returnType = BidConst.RETURN_TYPE_MONTH_INTEREST_PRINCIPAL; // 还款方式
	private int bidRequestType = BidConst.BIDREQUEST_TYPE_NORMAL; // 标的类型
	private int bidRequestState = BidConst.BIDREQUEST_STATE_PUBLISH_PENDING; // 这个标的状态

	private BigDecimal bidRequestAmount = BidConst.ZERO; // 借款金额
	private BigDecimal currentRate = BidConst.ZERO; // 借款利率
	private BigDecimal minBidAmount = BidConst.SMALLEST_BID_AMOUNT;// 最小投标(意思就是别人最少借出的钱,一次借款可以多个人投资筹款)
	private int monthes2Return = 1; // 借款期限(月份数1~12)
	private int bidCount = 0; // 已有投标数量（冗余字段）
	private BigDecimal totalRewardAmount = BidConst.ZERO; // 总报酬金额
	private BigDecimal currentSum = BidConst.ZERO; // 当前已经借到多少钱
	private String title = ""; // 借款标题
	private String description = ""; // 借款描述
	private String note = ""; // 风控评审意见
	private Date disableDate = new Date(); // 招标到期时间 (页面数据是招标天数，无效日期=招标天数+当前日期)
	private int disableDays = 0; // 标的有效天数
	private Logininfo createUser; // 借款人
	private List<Bid> bids = new ArrayList<Bid>(); // 这个借款已经有的标(已经收到的投标)
	private Date applyTime;// 申请时间
	private Date publishTime;// 发布时间

	public String getReturnTypeDisplay() {
		if (this.returnType == BidConst.RETURN_TYPE_MONTH_INTEREST_PRINCIPAL) {
			return "等额本息";
		} else if (this.returnType == BidConst.RETURN_TYPE_MONTH_INTEREST) {
			return "按月到期";
		} else {
			return "";
		}
	}

	public String getBidRequestStateDisplay() {
		switch (this.bidRequestState) {
		case BidConst.BIDREQUEST_STATE_PUBLISH_PENDING:
			return "待发布";
		case BidConst.BIDREQUEST_STATE_BIDDING:
			return "招标中";
		case BidConst.BIDREQUEST_STATE_UNDO:
			return "已撤销";
		case BidConst.BIDREQUEST_STATE_BIDDING_OVERDUE:
			return "流标";
		case BidConst.BIDREQUEST_STATE_APPROVE_PENDING_1:
			return "满标1审";
		case BidConst.BIDREQUEST_STATE_APPROVE_PENDING_2:
			return "满标2审";
		case BidConst.BIDREQUEST_STATE_REJECTED:
			return "满标审核拒绝";
		case BidConst.BIDREQUEST_STATE_PAYING_BACK:
			return "还款中";
		case BidConst.BIDREQUEST_STATE_COMPLETE_PAY_BACK:
			return "已还清";
		case BidConst.BIDREQUEST_STATE_PAY_BACK_OVERDUE:
			return "逾期";
		case BidConst.BIDREQUEST_STATE_PUBLISH_REFUSE:
			return "初审拒绝状态";
		default:
			return "错误状态";
		}
	}

	public String getJsonString() {
		Map<String, Object> m = new HashMap<>();
		m.put("id", getId());
		m.put("username", createUser.getUsername());
		m.put("title", title);
		m.put("bidRequestAmount", bidRequestAmount);
		m.put("currentRate", currentRate);
		m.put("monthes2Return", monthes2Return);
		m.put("totalRewardAmount", totalRewardAmount);
		m.put("returnType", getReturnTypeDisplay());
		return JSONObject.toJSONString(m);
	}
	//当前投标进度currentSum/bidRequestAmount
	public BigDecimal getPersent() {
		return currentSum.divide(bidRequestAmount, BidConst.DISPLAY_SCALE,
				RoundingMode.HALF_UP);
	}
	//还需要多少钱
	public BigDecimal getRemainAmount(){
		return bidRequestAmount.subtract(currentSum);
	}

	public static void main(String[] args) {
		BidRequest b1 = new BidRequest();
		b1.setCurrentSum(new BigDecimal(50));
		b1.setBidRequestAmount(new BigDecimal(100));
		System.out.println(b1.getPersent());
	}
}
