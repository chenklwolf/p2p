package com.eloan.business.mapper;


import com.eloan.business.domain.MoneyWithdraw;
import com.eloan.business.query.MoneyWithdrawQueryObject;

import java.util.List;

public interface MoneyWithdrawMapper {

	int insert(MoneyWithdraw record);

	MoneyWithdraw selectByPrimaryKey(Long id);

	int updateByPrimaryKey(MoneyWithdraw record);

	int queryForCount(MoneyWithdrawQueryObject qo);

	List<MoneyWithdraw> query(MoneyWithdrawQueryObject qo);
}