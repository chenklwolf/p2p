package com.eloan.business.mapper;

import java.util.List;

import com.eloan.business.domain.BidRequest;
import com.eloan.business.query.BidRequestQueryObject;

public interface BidRequestMapper {
	int insert(BidRequest record);

	BidRequest selectByPrimaryKey(Long id);

	int updateByPrimaryKey(BidRequest record);
	
	int queryForCount(BidRequestQueryObject qo);
	
	List<BidRequest> query(BidRequestQueryObject qo);
}