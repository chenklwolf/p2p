package com.eloan.business.service.impl;

import com.eloan.base.util.UserContext;
import com.eloan.business.domain.UserBankinfo;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.mapper.UserBankinfoMapper;
import com.eloan.business.service.IUserBankinfoService;
import com.eloan.business.service.IUserService;
import com.eloan.business.util.BitStatesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserBankinfoServiceImpl implements IUserBankinfoService {

	@Resource
	private UserBankinfoMapper userBankinfoMapper;

	@Autowired
	private IUserService userinfoService;

	@Override
	public UserBankinfo getByUser(Long id) {

		return this.userBankinfoMapper.selectByUser(id);
	}

	/**
	 * 绑定银行卡
	 * @param bankInfo
	 */
	@Override
	public void bind(UserBankinfo bankInfo) {
		// 判断当前用户没有绑定;
		Userinfo current = this.userinfoService.get(UserContext.getCurrent().getId());
		if (!current.getIsBindBank() && current.getRealAuth()) {//用户绑定银行卡之前需要实名认证
			// 创建一个UserBankinfo,设置相关属性;
			UserBankinfo b = new UserBankinfo();
			b.setAccountName(current.getRealName());
			b.setAccountNumber(bankInfo.getAccountNumber());
			b.setBankForkName(bankInfo.getBankForkName());
			b.setBankName(bankInfo.getBankName());
			b.setLogininfo(UserContext.getCurrent());
			this.userBankinfoMapper.insert(b);
			// 修改用户状态码
			current.addState(BitStatesUtils.OP_BIND_BANKINFO);
			this.userinfoService.update(current);
		}
	}
}
