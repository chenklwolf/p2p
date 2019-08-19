package com.eloan.business.mapper;

import java.util.List;

import com.eloan.business.domain.Account;

public interface AccountMapper {
	int deleteByPrimaryKey(Long id);

	int insert(Account record);

	Account selectByPrimaryKey(Long id);

	List<Account> selectAll();

	int updateByPrimaryKey(Account record);
}