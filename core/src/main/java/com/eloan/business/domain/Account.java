package com.eloan.business.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

import org.apache.ibatis.type.Alias;

import com.eloan.base.domain.BaseDomain;
import com.eloan.base.util.MD5;
import com.eloan.business.util.BidConst;

/**
 * 用户的帐户信息账户 一个LoginInfo 对应一个UserInfo对应一个Account
 * 
 * @author stef
 */
@Getter
@Setter
@Alias("Account")
public class Account extends BaseDomain {
	private static final long serialVersionUID = 6760287512112252557L;
	private int version;
	private String tradePassword; // 交易密码
	private BigDecimal usableAmount = BidConst.ZERO; // 可用余额
	private BigDecimal freezedAmount = BidConst.ZERO; // 冻结金额
	private BigDecimal unReceiveInterest = BidConst.ZERO; // 账户待收利息
	private BigDecimal unReceivePrincipal = BidConst.ZERO; // 账户待收本金
	private BigDecimal unReturnAmount = BidConst.ZERO; // 账户待还金额
	private BigDecimal remainBorrowLimit = BidConst.ZERO; // 账户剩余授信额度
	private BigDecimal borrowLimitAmount; // 授信额度（当前还可以信用借款额度）

	private String abstractInfo;  //摘要信息用于防篡改检查;

	/*public String getAbstractInfo() {
		return MD5.encode(usableAmount.add(freezedAmount)
				.add(remainBorrowLimit).toString());
	}*/

	/*public boolean checkAbstractInfo() {
		return MD5.encode(
				usableAmount.add(freezedAmount).add(remainBorrowLimit)
						.toString()).equals(abstractInfo);
	}*/

	public BigDecimal getTotalAmount() {
		return usableAmount.add(freezedAmount).add(unReceivePrincipal);
	}

	public void addUseableAmount(BigDecimal amount) {
		//账户可用余额
		this.usableAmount = this.usableAmount.add(amount);
	}

	public void addFreezedAmount(BigDecimal amount) {
		this.freezedAmount = this.freezedAmount.add(amount);
	}

	//针对初始注册用户给的一个初始账户
	public static Account empty(Long id) {
		Account account = new Account();
		account.setId(id);
		//账户授信额度
		account.setBorrowLimitAmount(BidConst.DEFALUT_BORROWLIMITAMOUNT);
		//账户剩余授信额度
		account.setRemainBorrowLimit(BidConst.DEFALUT_BORROWLIMITAMOUNT);
		return account;
	}
}
