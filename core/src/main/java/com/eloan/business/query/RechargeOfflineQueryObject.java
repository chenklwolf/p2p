package com.eloan.business.query;

import org.springframework.util.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RechargeOfflineQueryObject extends BaseAuditQueryObject {
	//后台线下充值交易审核查询对象
	private Long applierId;//申请人
	private long bankInfoId = -1;//开户行 ，如果有开户行对应的有1-20，就是bank.js中对应的
	private String tradeCode;//交易号

	public String getTradeCode(){
		return StringUtils.hasLength(tradeCode)?tradeCode:null;
	}
}
