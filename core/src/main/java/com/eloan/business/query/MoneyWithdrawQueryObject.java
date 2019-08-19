package com.eloan.business.query;

import lombok.Getter;
import lombok.Setter;

/**
 * 提现申请查询对象
 * 
 * @author Administrator
 * 
 */
@Getter
@Setter
public class MoneyWithdrawQueryObject extends BaseAuditQueryObject {

	private Long applierId;
}
