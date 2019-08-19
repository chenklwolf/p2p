package com.eloan.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eloan.base.domain.IpLog;
import com.eloan.base.mapper.IpLogMapper;
import com.eloan.base.query.IpLogQueryObject;
import com.eloan.base.query.PageResult;
import com.eloan.base.service.IIpLogService;
import com.eloan.base.util.DBContextUtil;

@Service
public class IpLogServiceImpl implements IIpLogService {
	
	@Autowired
	private IpLogMapper ipLogMapper;

	@Override
	public PageResult query(IpLogQueryObject qo) {
		DBContextUtil.setDB(DBContextUtil.DBREAD);
		int count=this.ipLogMapper.queryForCount(qo);
		if(count>0){
			List<IpLog> list=this.ipLogMapper.query(qo);
			return new PageResult(count, qo.getPageSize(),qo.getCurrentPage(),list);
		}
		return PageResult.empty(qo.getPageSize());
	}

	@Override
	public void insert(IpLog ipLog) {
		this.ipLogMapper.insert(ipLog);
	}

}
