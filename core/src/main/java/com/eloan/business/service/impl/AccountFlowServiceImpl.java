package com.eloan.business.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import com.eloan.business.domain.*;
import org.springframework.stereotype.Service;
import com.eloan.business.mapper.AccountFlowMapper;
import com.eloan.business.service.IAccountFlowService;
import com.eloan.business.util.BidConst;

import javax.annotation.Resource;

@Service
public class AccountFlowServiceImpl implements IAccountFlowService {

	@Resource
	private AccountFlowMapper accountFlowMapper;

	private AccountFlow createBaseFlow(Account account) {
		AccountFlow flow = new AccountFlow();
		flow.setAccount(account);
		flow.setActionTime(new Date());//发生时间
		flow.setBalance(account.getUsableAmount());//可用余额
		flow.setFreezed(account.getFreezedAmount());// 冻结金额
		return flow;
	}

	//给账户添加一条流水记录
	@Override
	public void addRechargeFlow(RechargeOffline recharge, Account account) {
		AccountFlow flow = createBaseFlow(account); //组装通用数据
		// 账户流水类型 资金流水类别：线下充值
		flow.setAccountActionType(BidConst.ACCOUNT_ACTIONTYPE_DEPOSIT_OFFLINE_LOCAL);
		flow.setAmount(recharge.getAmount());//流水账金额，此次交易充值的金额
		flow.setNote("线下充值成功!");
		accountFlowMapper.insert(flow);
	}

	@Override
	public void addBidFlow(Bid bid, Account account) {
		AccountFlow flow = createBaseFlow(account);//组装数据
		flow.setAccountActionType(BidConst.ACCOUNT_ACTIONTYPE_BID_FREEZED);//投标冻结金额
		flow.setAmount(bid.getAvailableAmount());
		flow.setNote("投标:" + bid.getBidRequestTitle() + ",冻结金额!");//哪个标下面的冻结金额
		accountFlowMapper.insert(flow);
	}

	@Override
	public void addReturnBidMoneyFlow(Bid bid, Account bidAccount) {
		AccountFlow flow = createBaseFlow(bidAccount);
		flow.setAccountActionType(BidConst.ACCOUNT_ACTIONTYPE_BID_UNFREEZED);// 资金流水类别：取消投标冻结金额
		flow.setAmount(bid.getAvailableAmount());
		flow.setNote("投标取消,投标冻结金额返还!");
		accountFlowMapper.insert(flow);
	}

	@Override
	public void addBorrowRecevieFlow(BidRequest bidRequest,
			Account borrowAccount) {
		AccountFlow flow = createBaseFlow(borrowAccount);
		flow.setAccountActionType(BidConst.ACCOUNT_ACTIONTYPE_BIDREQUEST_SUCCESSFUL);// 资金流水类别：成功借款
		flow.setAmount(bidRequest.getBidRequestAmount());
		flow.setNote("成功借款,借款金额到账!");
		accountFlowMapper.insert(flow);
	}

	@Override
	public void addManageChargeFeeFlow(BigDecimal manageChargeFee,
			Account borrowAccount) {
		AccountFlow flow = createBaseFlow(borrowAccount);
		flow.setAccountActionType(BidConst.ACCOUNT_ACTIONTYPE_CHARGE);
		flow.setAmount(manageChargeFee);
		flow.setNote("支付借款手续费!");
		accountFlowMapper.insert(flow);
	}

	@Override
	public void addBidSuccessFlow(Bid bid, Account bidAccount) {
		AccountFlow flow = createBaseFlow(bidAccount);
		flow.setAccountActionType(BidConst.ACCOUNT_ACTIONTYPE_BID_SUCCESSFUL);
		flow.setAmount(bid.getAvailableAmount());
		flow.setNote("借款成功,取消冻结金额!");
		accountFlowMapper.insert(flow);
	}

	/**
	 * 提现申请
	 * @param m
	 * @param account
	 */
	@Override
	public void moneyWithDrawApply(MoneyWithdraw m, Account account) {
		AccountFlow flow = createBaseFlow(account);
		//状态：提现申请资金冻结金额
		flow.setAccountActionType(BidConst.ACCOUNT_ACTIONTYPE_WITHDRAW_FREEZED);
		flow.setAmount(m.getAmount());
		flow.setNote("提现申请,冻结金额:" + m.getAmount());
		this.accountFlowMapper.insert(flow);

	}

	/**
	 * 提现申请失败，取消冻结金额
	 * @param m
	 * @param account
	 */
	@Override
	public void withDrawFailed(MoneyWithdraw m, Account account) {
		AccountFlow flow = createBaseFlow(account);
		flow.setAccountActionType(BidConst.ACCOUNT_ACTIONTYPE_WITHDRAW_UNFREEZED);
		flow.setAmount(m.getAmount());
		flow.setNote("提现申请失败,取消冻结金额:" + m.getAmount());
		this.accountFlowMapper.insert(flow);
	}

	/**
	 * 提现手续费
	 * @param m
	 * @param account
	 */
	@Override
	public void withDrawChargeFee(MoneyWithdraw m, Account account) {
		AccountFlow flow = createBaseFlow(account);
		flow.setAccountActionType(BidConst.ACCOUNT_ACTIONTYPE_WITHDRAW_MANAGE_CHARGE);
		flow.setAmount(m.getCharge());
		flow.setNote("提现成功,提现手续费:" + m.getCharge());
		this.accountFlowMapper.insert(flow);
	}

	/**
	 * 提现成功流水
	 * @param amount
	 * @param account
	 */
	@Override
	public void withDrawSuccess(BigDecimal amount, Account account) {
		AccountFlow flow = createBaseFlow(account);
		//资金流水类别：提现
		flow.setAccountActionType(BidConst.ACCOUNT_ACTIONTYPE_WITHDRAW);
		flow.setAmount(amount);
		flow.setNote("提现成功,提现金额:" + amount);
		this.accountFlowMapper.insert(flow);
	}
}
