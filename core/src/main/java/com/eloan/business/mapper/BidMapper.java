package com.eloan.business.mapper;

import java.util.List;

import com.eloan.business.domain.Bid;
import com.eloan.business.query.BidQueryObject;

public interface BidMapper {
    int insert(Bid record);

    Bid selectByPrimaryKey(Long id);
    
    int queryForCount(BidQueryObject qo);
    
    List<Bid> query(BidQueryObject qo);
}