package com.eloan.business.service;

import java.util.List;

import com.eloan.business.domain.Account;

public interface IAccountService {

	void update(Account account);

	Account get(Long id);
	
	//void recreateAbstractInfo();
	
	List<Account> listAll();
}
