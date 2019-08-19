package com.eloan.business.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eloan.base.query.PageResult;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Realauth;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.mapper.RealauthMapper;
import com.eloan.business.query.RealAuthQueryObject;
import com.eloan.business.service.IRealAuthService;
import com.eloan.business.service.IUserService;
import com.eloan.business.util.BitStatesUtils;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RealAuthServiceImpl implements IRealAuthService {
	@Autowired
	private RealauthMapper realAuthMapper;

	@Autowired
	private IUserService userService;

	@Override
	public Realauth get(Long id) {
		return this.realAuthMapper.selectByPrimaryKey(id);
	}

	@Override
	public void apply(Realauth realAuth) {
		realAuth.setApplyTime(new Date());
		realAuth.setApplier(UserContext.getCurrent());
		realAuth.setState(Realauth.STATE_APPLY);
		realAuthMapper.insert(realAuth);
		//userinfo存入信息，目前的状态是审核
		Userinfo current = this.userService.get(UserContext.getCurrent()
				.getId());
		current.setRealauthId(realAuth.getId());
		this.userService.update(current);
	}

	@Override
	public PageResult query(RealAuthQueryObject qo) {
		int count = this.realAuthMapper.queryForCount(qo);
		if (count > 0) {
			List<Realauth> list = this.realAuthMapper.query(qo);
			return new PageResult(count, qo.getPageSize(), qo.getCurrentPage(),
					list);
		}
		return PageResult.empty(qo.getPageSize());
	}

	@Transactional
	@Override
	public void audit(Long id, String remark, int state) {
		//思路:
		Realauth realAuth = this.realAuthMapper.selectByPrimaryKey(id);
		//未认证
		if (realAuth != null && realAuth.getState() == Realauth.STATE_APPLY) {
			realAuth.setAuditor(UserContext.getCurrent()); //当前登录的管理员设为处理人
			realAuth.setAuditTime(new Date());
			realAuth.setState(state); //处理人给的状态
			//得到处理人的信息
			Userinfo userinfo = this.userService.get(realAuth.getApplier()
					.getId());
			if (state == Realauth.STATE_REJECT) { //拒绝state置为空
				userinfo.setRealauthId(null);
			} else if (state == Realauth.STATE_PASS && !userinfo.getRealAuth()) {
				//处理状态通过且处理人不为空,用户信息更新
				userinfo.setRealName(realAuth.getRealname());
				userinfo.setIdNumber(realAuth.getIdNumber());
				userinfo.addState(BitStatesUtils.OP_REAL_AUTH);
			}
			//更改用户信息，认证信息
			this.userService.update(userinfo);
			this.realAuthMapper.updateByPrimaryKey(realAuth);
		}
	}

}
