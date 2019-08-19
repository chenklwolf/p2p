package com.eloan.business.mapper;

import com.eloan.business.domain.SystemAccount;

/**
 * 系统账户mapper
 * 
 * @author Stef
 * 
 */
public interface SystemAccountMapper {

	/**
	 * 添加一个系统账户的信息
	 * 
	 */
	void addSystemAccount(SystemAccount systemAccount);

	/**
	 * 得到当前活动的系统账户
	 * @return
	 */
	SystemAccount getCurrentSystemAccount();

	/**
	 * 修改系统账户信息
	 */
	int updateSystemAccount(SystemAccount systemAccount);

	/**
	 * 返回第一个系统账户是否初始化，如果没有初始化，返回true，如果已经初始化，返回false
	 * @return
	 */
	boolean unInitialize();
}
