package com.eloan.business.service;

import com.eloan.base.query.PageResult;
import com.eloan.business.query.VedioAuthQueryObject;

public interface IVedioAuthService {

	PageResult query(VedioAuthQueryObject qo);

	/**
	 * 视频审核
	 * @param loginInfoValue
	 * @param remark
	 * @param state
	 */
	void audit(Long loginInfoValue, String remark, int state);

}
