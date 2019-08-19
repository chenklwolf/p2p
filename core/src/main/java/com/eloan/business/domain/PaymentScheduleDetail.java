package com.eloan.business.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import org.apache.ibatis.type.Alias;

import com.eloan.base.domain.BaseDomain;
import com.eloan.business.util.BidConst;

/**
 * 针对一个还款计划,投资人的回款明细
 * @author Administrator
 *
 */
@Getter
@Setter
@Alias("PaymentScheduleDetail")
public class PaymentScheduleDetail extends BaseDomain{
	
	private static final long serialVersionUID = 1L;
	private BigDecimal bidAmount;		//该投标人总共投标金额,便于还款/垫付查询
	private Long bidId;					//对应的投标ID
	private BigDecimal totalAmount = BidConst.ZERO;	//本期还款总金额(=本金+利息)
	private BigDecimal principal = BidConst.ZERO;	//本期应还款本金
	private BigDecimal interest = BidConst.ZERO;	//本期应还款利息
	private int monthIndex;							//第几期（即第几个月）
	private Date deadline;							//本期还款截止时间
	private Long bidRequestId;						//所属哪个借款
	private Date payDate;							//实际付款日期
	private int returnType;							//还款方式
	private Long paymentScheduleId;					//所属还款计划
	private Logininfo fromLoginInfo;	//还款人(即发标人)
	private Logininfo toLoginInfo;		//收款人(即投标人)
}
