package com.eloan.business.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import org.apache.ibatis.type.Alias;

import com.eloan.base.domain.BaseDomain;
import com.eloan.business.util.BidConst;

/**
 * 账户流水
 */
@Getter
@Setter
@Alias("AccountFlow")
public class AccountFlow extends BaseDomain {
	private static final long serialVersionUID = -7829109852643418486L;
	
	private int accountActionType;// 账户流水类型（参考BidConst）
	private BigDecimal amount = BidConst.ZERO; // 发生额(流水账金额,ZERO_AMOUNT:0)
	private Account account; // 对应的账户
	private String note; // 备注
	private BigDecimal balance = BidConst.ZERO; // 可用余额
	private BigDecimal freezed = BidConst.ZERO;// 冻结金额(比如满标2审之前资金冻结，体现冻结)
	private Date actionTime; // 发生时间
}
