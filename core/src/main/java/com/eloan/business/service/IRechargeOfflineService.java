package com.eloan.business.service;

import com.eloan.base.query.PageResult;
import com.eloan.business.domain.RechargeOffline;
import com.eloan.business.query.RechargeOfflineQueryObject;

/**
 * 线下充值
 * @author Administrator
 *
 */
public interface IRechargeOfflineService {

	/**
	 * 提交线下充值
	 * @param recharge
	 */
	void apply(RechargeOffline recharge);
	
	PageResult query(RechargeOfflineQueryObject qo);

	/**
	 * 审核
	 * @param id
	 * @param remark
	 * @param state
	 */
	void audit(Long id, String remark, int state);

}
