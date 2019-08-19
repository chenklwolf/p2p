package com.eloan.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eloan.base.query.PageResult;
import com.eloan.base.query.QueryObject;
import com.eloan.business.domain.CompanyBankInfo;
import com.eloan.business.mapper.CompanyBankInfoMapper;
import com.eloan.business.service.ICompanyBankInfoService;

@Service
public class CompanyBankInfoServiceImpl implements ICompanyBankInfoService {

	@Autowired
	private CompanyBankInfoMapper bankInfoMapper;

	@Override
	public List<CompanyBankInfo> list() {
		return this.bankInfoMapper.selectAll();
	}

	@Override
	public PageResult query(QueryObject qo) {
		int count = this.bankInfoMapper.queryForCount(qo);
		if (count > 0) {
			List<CompanyBankInfo> list = this.bankInfoMapper.query(qo);
			return new PageResult(count, qo.getPageSize(), qo.getCurrentPage(),
					list);
		}
		return PageResult.empty(qo.getPageSize());
	}

	@Override
	public void save(CompanyBankInfo c) {
		if (c.getId() != null) {
			this.bankInfoMapper.updateByPrimaryKey(c);
		} else {
			this.bankInfoMapper.insert(c);
		}
	}

}
