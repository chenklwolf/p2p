package com.eloan.business.service;

import java.math.BigDecimal;
import java.util.List;

import com.eloan.business.domain.Logininfo;
import com.eloan.base.query.PageResult;
import com.eloan.business.domain.BidRequest;
import com.eloan.business.query.BidQueryObject;
import com.eloan.business.query.BidRequestQueryObject;
import com.eloan.business.service.impl.BidRequestServiceImpl.EntryValue;

public interface IBidRequestService {
	
	BidRequest get(Long id);

	void update(BidRequest br);

	/**
	 * 判定一个用户是否能够借款
	 * @param current
	 * @return
	 */
	boolean canBorrow(Logininfo current);

	/**
	 * 申请借款
	 * @param bidRequest
	 */
	void apply(BidRequest bidRequest);

	/**
	 * 查询借款
	 * @param qo
	 * @return
	 */
	PageResult query(BidRequestQueryObject qo);
	
	/**
	 * 查询投资
	 * @param qo
	 * @return
	 */
	PageResult queryBid(BidQueryObject qo);

	/**
	 * 发标前审核
	 * @param id
	 * @param remark
	 * @param state
	 */
	void publishAudit(Long id, String remark, int state);

	/**
	 * @param size
	 * @return
	 */
	List<BidRequest> listIndexBidRequests(int size);

	/**
	 * 投标操作
	 * @param amount
	 * @param bidRequestId
	 */
	void bid(BigDecimal amount, Long bidRequestId);
	
	List<EntryValue<Integer,String>> listBidRequestStates();

	/**
	 * 满标一审
	 * @param id
	 * @param remark
	 * @param state
	 */
	void audit1(Long id, String remark, int state);

	/**
	 * 满标二审
	 * @param id
	 * @param remark
	 * @param state
	 */
	void audit2(Long id, String remark, int state);
}
