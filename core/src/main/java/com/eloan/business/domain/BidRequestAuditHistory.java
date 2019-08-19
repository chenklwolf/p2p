package com.eloan.business.domain;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

/**
 * 借款审核历史对象
 * @author Administrator
 *
 */
@Getter
@Setter
@Alias("BidRequestAuditHistory")
public class BidRequestAuditHistory extends BaseAuditDomain {

	public static final int AUDITTYPE_PUBLISH_BIDREQUEST = 0;//发表前审核
	public static final int AUDITTYPE_FULL_AUDIT1 = 1;//满标一审
	public static final int AUDITTYPE_FULL_AUDIT2 = 2;//满标二审

	private Long bidRequestId;
	private int auditType;//审核类型

	public String getAuditTypeDisplay() {
		switch (auditType) {
		case AUDITTYPE_PUBLISH_BIDREQUEST:
			return "发标前审核";
		case AUDITTYPE_FULL_AUDIT1:
			return "满标一审";
		case AUDITTYPE_FULL_AUDIT2:
			return "满标二审";
		default:
			return "未知状态";
		}
	}
}
