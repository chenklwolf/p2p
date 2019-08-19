package com.eloan.business.domain;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSONObject;

/**
 * 线下充值记录
 */
@Getter
@Setter
@Alias("RechargeOffline")
public class RechargeOffline extends BaseAuditDomain {
	private static final long serialVersionUID = -6066507421501780625L;

	private CompanyBankInfo bankInfo;// 平台账号
	private String tradeCode; // 交易号
	private Date tradeTime; // 交易时间
	private BigDecimal amount; // 交易金额
	private String note; // 备注
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getJsonString() {
		Map<String, Object> m = new HashMap<>();
		m.put("id", id);
		m.put("username", this.getApplier().getUsername());
		m.put("tradeCode", tradeCode);
		m.put("amount", amount);
		m.put("tradeTime", DateFormat.getDateInstance().format(this.getApplyTime()));//申请时间
		return JSONObject.toJSONString(m);
	}

	public static void main(String[] args) {
		//jack awesome
		System.out.println(DateFormat.getDateInstance().format(new Date()));
	}
}
