package com.eloan.base.service;

import com.eloan.base.domain.IpLog;
import com.eloan.base.query.IpLogQueryObject;
import com.eloan.base.query.PageResult;

public interface IIpLogService {

	PageResult query(IpLogQueryObject qo);
	
	void insert(IpLog ipLog);
}
