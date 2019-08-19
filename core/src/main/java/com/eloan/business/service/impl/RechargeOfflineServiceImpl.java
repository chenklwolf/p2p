package com.eloan.business.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eloan.base.query.PageResult;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Account;
import com.eloan.business.domain.RechargeOffline;
import com.eloan.business.mapper.RechargeOfflineMapper;
import com.eloan.business.query.RechargeOfflineQueryObject;
import com.eloan.business.service.IAccountFlowService;
import com.eloan.business.service.IAccountService;
import com.eloan.business.service.IRechargeOfflineService;

@Service
public class RechargeOfflineServiceImpl implements IRechargeOfflineService {

	@Autowired
	private RechargeOfflineMapper rechargeOfflineMapper;
	
	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private IAccountFlowService accountFlowService;

	@Override
	public void apply(RechargeOffline recharge) {
		recharge.setApplier(UserContext.getCurrent());
		recharge.setApplyTime(new Date());
		recharge.setState(RechargeOffline.STATE_APPLY);
		this.rechargeOfflineMapper.insert(recharge);
	}

	@Override
	public PageResult query(RechargeOfflineQueryObject qo) {
		int count = this.rechargeOfflineMapper.queryForCount(qo);
		if (count > 0) {
			List<RechargeOffline> list = this.rechargeOfflineMapper.query(qo);
			return new PageResult(count, qo.getPageSize(), qo.getCurrentPage(),
					list);
		}
		return PageResult.empty(qo.getPageSize());
	}

	@Override
	public void audit(Long id, String remark, int state) {
		RechargeOffline recharge = this.rechargeOfflineMapper
				.selectByPrimaryKey(id);
		if (recharge != null
				&& recharge.getState() == RechargeOffline.STATE_APPLY) {
			recharge.setAuditor(UserContext.getCurrent());
			//BaseAuditDomain继承关系，所以设置审核关系
			recharge.setAuditTime(new Date());
			recharge.setState(state);
			recharge.setRemark(remark);
			if (state == RechargeOffline.STATE_PASS) {//审核成功
				//找到充值用户的账户;
				Account account=this.accountService.get(recharge.getApplier().getId());
				//增加账户的可用余额;
				account.addUseableAmount(recharge.getAmount());
				//给账户添加一条账户流水;
				accountFlowService.addRechargeFlow(recharge,account);
				//修改账户
				this.accountService.update(account);
			}
			this.rechargeOfflineMapper.update(recharge);
		}
	}

}
