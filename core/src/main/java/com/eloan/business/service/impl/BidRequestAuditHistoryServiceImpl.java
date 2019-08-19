package com.eloan.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eloan.base.query.PageResult;
import com.eloan.business.domain.BidRequestAuditHistory;
import com.eloan.business.mapper.BidRequestAuditHistoryMapper;
import com.eloan.business.query.BidRequestAuditHistoryQueryObject;
import com.eloan.business.service.IBidRequestAuditHistoryService;

@Service
public class BidRequestAuditHistoryServiceImpl implements
		IBidRequestAuditHistoryService {

	@Autowired
	private BidRequestAuditHistoryMapper bidRequestAuditHistoryMapper;

	@Override
	public PageResult query(BidRequestAuditHistoryQueryObject qo) {
		int count = this.bidRequestAuditHistoryMapper.queryForCount(qo);
		if (count > 0) {
			List<BidRequestAuditHistory> list = this.bidRequestAuditHistoryMapper
					.query(qo);
			return new PageResult(count, qo.getPageSize(), qo.getCurrentPage(),
					list);
		}
		return PageResult.empty(qo.getPageSize());
	}

	@Override
	public List<BidRequestAuditHistory> queryList(
			BidRequestAuditHistoryQueryObject qo) {
		return this.bidRequestAuditHistoryMapper.query(qo);
	}

}
