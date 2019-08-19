package com.eloan.business.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import org.apache.ibatis.type.Alias;

import com.eloan.base.domain.BaseDomain;
import com.eloan.business.util.BidConst;

/**
 * 一次投标对象
 *
 */
@Getter
@Setter
@Alias("Bid")
public class Bid extends BaseDomain {
	private BigDecimal actualRate = BidConst.ZERO; // 实际年利率(应该是等同于标的的利率)
	private BigDecimal availableAmount = BidConst.ZERO; // 投标有效金额(就是投标金额)

	private Long bidRequestId; // 来自于哪个借款标
	private String bidRequestTitle;//标的title
	private Logininfo bidUser; // 投标人id(loginInfo)
	private Date bidTime;//投标时间

	//冗余字段,就相当于外键关联查询一样，只是数据库没有字段
	private int bidRequestState;//这个属性不需要保存到数据库中,只是在查询的时候,关联到到bidrequest查询;

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
}
