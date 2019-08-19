package com.eloan.business.mapper;

import java.util.List;

import com.eloan.business.domain.BidRequestAuditHistory;
import com.eloan.business.query.BidRequestAuditHistoryQueryObject;

public interface BidRequestAuditHistoryMapper {

	int insert(BidRequestAuditHistory record);

	BidRequestAuditHistory selectByPrimaryKey(Long id);

	int queryForCount(BidRequestAuditHistoryQueryObject qo);

	List<BidRequestAuditHistory> query(BidRequestAuditHistoryQueryObject qo);
}