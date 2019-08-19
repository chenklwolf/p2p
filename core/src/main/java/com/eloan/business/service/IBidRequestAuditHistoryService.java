package com.eloan.business.service;

import java.util.List;

import com.eloan.base.query.PageResult;
import com.eloan.business.domain.BidRequestAuditHistory;
import com.eloan.business.query.BidRequestAuditHistoryQueryObject;

public interface IBidRequestAuditHistoryService {
	
	PageResult query(BidRequestAuditHistoryQueryObject qo);
	
	List<BidRequestAuditHistory> queryList(BidRequestAuditHistoryQueryObject qo);

}
