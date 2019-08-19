package com.eloan.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.mapper.UserinfoMapper;
import com.eloan.business.service.ISendVerifyCodeService;
import com.eloan.business.service.IUserService;
import com.eloan.business.util.BitStatesUtils;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {

	@Resource
	private UserinfoMapper userinfoMapper;

	@Autowired
	private ISendVerifyCodeService verifyCodeService;
	
	@Value("${db.timeout}")
	private String salt;

	@Override
	public void update(Userinfo userinfo) {
		int ret = userinfoMapper.updateByPrimaryKey(userinfo,salt);
		if (ret <= 0) {
			throw new RuntimeException("Userinfo对象:" + userinfo.getId()
					+ " 乐观锁失败!");
		}
	}

	@Override
	public Userinfo get(Long id) {
		return userinfoMapper.selectByPrimaryKey(id,salt);
	}

	@Override
	public boolean bindPhone(String phoneNumber, String verifyCode) {
		boolean ret = verifyCodeService.verifyCode(phoneNumber, verifyCode);
		if (ret) {
			Userinfo ui = this.get(UserContext.getCurrent().getId());
			ui.setPhoneNumber(phoneNumber);
			ui.addState(BitStatesUtils.OP_BIND_PHONE);
			this.update(ui);
			return true;
		}
		return false;
	}

	@Override
	public void updateBasicInfo(Userinfo userinfo) {
		Userinfo current = this.userinfoMapper.selectByPrimaryKey(UserContext
				.getCurrent().getId(),salt);
		current.setEducationBackground(userinfo.getEducationBackground()); //学历
		current.setHouseCondition(userinfo.getHouseCondition()); //居住房子
		current.setIncomeGrade(userinfo.getIncomeGrade());
		current.setKidCount(userinfo.getKidCount());
		current.setMarriage(userinfo.getMarriage());
		if (!current.getBaseInfo()) {
			current.addState(BitStatesUtils.OP_BASE_INFO);
		}
		this.update(current);
	}

}
