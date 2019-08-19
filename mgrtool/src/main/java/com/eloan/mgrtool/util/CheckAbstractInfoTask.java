package com.eloan.mgrtool.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eloan.business.domain.Account;
import com.eloan.business.service.IAccountService;

/**
 * 用来检查摘要信息的任务
 */
@Component("checkAbstractInfoTask")
public class CheckAbstractInfoTask {
	@Autowired
	private IAccountService accountService;

	public void executeCheck() {
		System.out.println("===========调度任务执行=============");
		List<Account> accounts = accountService.listAll();
		/*for (Account account : accounts) {
			if(!account.checkAbstractInfo()){
				System.out.println("用户账户信息被恶意篡改,账户id:"+account.getId());
			}
		}*/
	}
}
