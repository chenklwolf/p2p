package com.eloan.business.service;

import java.util.List;

import com.eloan.base.query.PageResult;
import com.eloan.base.query.QueryObject;
import com.eloan.business.domain.CompanyBankInfo;

public interface ICompanyBankInfoService {
	
	List<CompanyBankInfo> list(); //平台的所有账户

	PageResult query(QueryObject qo); //根据条件查询
	
	void save(CompanyBankInfo c);
}
