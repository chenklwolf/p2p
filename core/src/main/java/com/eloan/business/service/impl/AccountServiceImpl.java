package com.eloan.business.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.eloan.business.domain.Account;
import com.eloan.business.mapper.AccountMapper;
import com.eloan.business.service.IAccountService;
import javax.annotation.Resource;

@Service
public class AccountServiceImpl implements IAccountService {

	@Resource
	private AccountMapper accountMapper;

	@Override
	public void update(Account account) {
		int ret = accountMapper.updateByPrimaryKey(account);
		if (ret <= 0) {
			throw new RuntimeException("Account对象:" + account.getId()
					+ " 乐观锁失败!");
		}
	}

	@Override
	public Account get(Long id) {
		Account account = accountMapper.selectByPrimaryKey(id);
		/*if (!account.checkAbstractInfo()) {
			throw new RuntimeException("账户信息被篡改:" + id);
		}*/
		return account;
	}

	/**
	 * 重建account表的摘要信息
	 */
	/*@Override
	public void recreateAbstractInfo() {
		List<Account> accounts = this.accountMapper.selectAll();
		for (Account account : accounts) {
			this.accountMapper.updateByPrimaryKey(account);
		}
	}*/

	@Override
	public List<Account> listAll() {
		return this.accountMapper.selectAll();
	}

}
