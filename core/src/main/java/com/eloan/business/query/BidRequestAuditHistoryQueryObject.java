package com.eloan.business.query;

import lombok.Getter;
import lombok.Setter;

/**
 * 标的审核信息查询对象
 *
 */
@Getter
@Setter
public class BidRequestAuditHistoryQueryObject extends BaseAuditQueryObject {

	private Long bidRequestId;
}
