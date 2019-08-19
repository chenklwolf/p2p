package com.eloan.business.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import com.eloan.base.domain.BaseDomain;

@Getter
@Setter
abstract public class BaseAuditDomain extends BaseDomain {

	public static final int STATE_APPLY = 0;//申请状态
	public static final int STATE_PASS = 1;//审核通过
	public static final int STATE_REJECT = 2;//审核拒绝

	protected Logininfo applier;//申请人
	protected Date applyTime;//申请时间
	private Logininfo auditor;//审核人
	private Date auditTime;//审核时间
	private int state;//状态
	private String remark;//审核备注

	public String getStateDisplay() {
		switch (state) {
		case STATE_APPLY:
			return "申请状态";
		case STATE_PASS:
			return "审核通过";
		case STATE_REJECT:
			return "审核失败";
		default:
			return "异常";
		}
	}
}
