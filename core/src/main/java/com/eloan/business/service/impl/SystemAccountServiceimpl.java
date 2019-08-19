package com.eloan.business.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import com.eloan.business.domain.MoneyWithdraw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.eloan.business.domain.BidRequest;
import com.eloan.business.domain.SystemAccount;
import com.eloan.business.domain.SystemAccountFlow;
import com.eloan.business.mapper.SystemAccountFlowMapper;
import com.eloan.business.mapper.SystemAccountMapper;
import com.eloan.business.service.ISystemAccountService;
import com.eloan.business.util.BidConst;

import javax.annotation.Resource;

@Service
public class SystemAccountServiceimpl implements ISystemAccountService,
		ApplicationListener<ContextRefreshedEvent> {

	@Resource
	private SystemAccountMapper systemAccountMapper;

	@Resource
	private SystemAccountFlowMapper systemAccountFlowMapper;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (systemAccountMapper.unInitialize()) {
			SystemAccount account = new SystemAccount();
			account.setBeginDate(new Date());
			account.setCreateDate(new Date());
			this.systemAccountMapper.addSystemAccount(account);
		}
	}

	@Override
	public void update(SystemAccount current) {
		int ret = this.systemAccountMapper.updateSystemAccount(current);
		if (ret <= 0) {
			throw new RuntimeException("修改系统账户乐观锁失败!");
		}
	}

	/**
	 * 系统账户收取提现金额
	 * @param m
	 */
	@Override
	public void chargeWithdrawFee(MoneyWithdraw m) {
      // 1,得到当前系统账户;
		SystemAccount current = this.systemAccountMapper.getCurrentSystemAccount();
		// 2,修改账户余额;
		current.setTotalBalance(current.getTotalBalance().add(m.getCharge()));
		// 3,生成收款流水
		SystemAccountFlow flow = new SystemAccountFlow();
		flow.setAccountActionType(BidConst.SYSTEM_ACCOUNT_ACTIONTYPE_WITHDRAW_MANAGE_CHARGE);
		flow.setAmount(m.getCharge());
		flow.setBalance(current.getTotalBalance());
		flow.setCreatedDate(new Date());
		flow.setFreezedAmount(current.getFreezedAmount());//平台冻结金额
		flow.setNote("用户" + m.getApplier().getUsername() + "提现" + m.getAmount()
				+ "成功,收取提现手续费:" + m.getCharge());
		flow.setSystemAccountId(current.getId());
		this.systemAccountFlowMapper.addSystemAccountFlow(flow);
		this.update(current);
	}

	@Override
	public void chargeManageFee(BigDecimal manageChargeFee,
			BidRequest bidRequest) {
		SystemAccount current = this.systemAccountMapper
				.getCurrentSystemAccount();
		current.setTotalBalance(current.getTotalBalance().add(manageChargeFee));

		//创建收款流水
		SystemAccountFlow flow = new SystemAccountFlow();
		flow.setAccountActionType(BidConst.SYSTEM_ACCOUNT_ACTIONTYPE_MANAGE_CHARGE);
		flow.setAmount(manageChargeFee);
		flow.setBalance(current.getTotalBalance());
		flow.setFreezedAmount(current.getFreezedAmount());
		flow.setCreatedDate(new Date());
		flow.setNote("收到借款人" + bidRequest.getCreateUser().getUsername()
				+ "成功借款支付管理费!");
		flow.setSystemAccountId(current.getId());
		flow.setTargetUser(bidRequest.getCreateUser());

		this.systemAccountFlowMapper.addSystemAccountFlow(flow);
		this.update(current);
	}

}
