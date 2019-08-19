package com.eloan.business.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import org.apache.ibatis.type.Alias;

import com.eloan.base.domain.BaseDomain;
import com.eloan.business.util.BidConst;

/**
 * 系统账户流水，专门用于记录系统账户的流水变化
 *
 * @author Stef
 *
 */
@Getter
@Setter
@Alias("SystemAccountFlow")
public class SystemAccountFlow extends BaseDomain {

	private static final long serialVersionUID = 6745295563470056376L;

	private Date createdDate;										//创建时间
	private int accountActionType = BidConst.SYSTEM_ACCOUNT_NONE;	// 系统账户流水类型（参考SystemConst）
	private BigDecimal amount = BidConst.ZERO;						// 发生额(流水账金额,ZERO_AMOUNT:0)
	private String note;											// 流水账备注
	private BigDecimal balance = BidConst.ZERO;						// 系统账户（或者风险控制账户）可用余额
	private BigDecimal freezedAmount = BidConst.ZERO;				// 冻结余额(只有系统账户有)
	private Long systemAccountId;									// 所属账户区间(及这个流水是属于哪个区间的，以后系统优化会按照这个分表)
	private Logininfo targetUser;									// 该系统账户流水对应的产生目标用户对象
}
