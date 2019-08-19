package com.eloan.business.mapper;

import java.util.List;

import com.eloan.base.query.QueryObject;
import com.eloan.business.domain.CompanyBankInfo;

public interface CompanyBankInfoMapper {

    int insert(CompanyBankInfo record);

    CompanyBankInfo selectByPrimaryKey(Long id);
    
    List<CompanyBankInfo> selectAll();

    int updateByPrimaryKey(CompanyBankInfo record);
    
    int queryForCount(QueryObject qo);
    
    List<CompanyBankInfo> query(QueryObject qo);
}