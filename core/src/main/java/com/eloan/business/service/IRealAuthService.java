package com.eloan.business.service;

import com.eloan.base.query.PageResult;
import com.eloan.business.domain.Realauth;
import com.eloan.business.query.RealAuthQueryObject;

public interface IRealAuthService {

	Realauth get(Long id);

	/**
	 * 提交实名申请
	 * @param realAuth
	 */
	void apply(Realauth realAuth);

	PageResult query(RealAuthQueryObject qo);

	/**
	 * 实名认证审核方法
	 * @param id
	 * @param remark
	 * @param state
	 */
	void audit(Long id, String remark, int state);
}
