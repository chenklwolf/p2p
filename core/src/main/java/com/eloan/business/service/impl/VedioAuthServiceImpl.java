package com.eloan.business.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eloan.business.domain.Logininfo;
import com.eloan.base.query.PageResult;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.domain.Vedioauth;
import com.eloan.business.mapper.VedioauthMapper;
import com.eloan.business.query.VedioAuthQueryObject;
import com.eloan.business.service.IUserService;
import com.eloan.business.service.IVedioAuthService;
import com.eloan.business.util.BitStatesUtils;

@Service
public class VedioAuthServiceImpl implements IVedioAuthService {

	@Autowired
	private VedioauthMapper vedioauthMapper;

	@Autowired
	private IUserService userService;

	@Override
	public PageResult query(VedioAuthQueryObject qo) {
		int count = this.vedioauthMapper.queryForCount(qo);
		if (count > 0) {
			List<Vedioauth> list = this.vedioauthMapper.query(qo);
			return new PageResult(count, qo.getPageSize(), qo.getCurrentPage(),
					list);
		}
		return PageResult.empty(qo.getPageSize());
	}

	@Override
	public void audit(Long loginInfoValue, String remark, int state) {
		//思路
		Vedioauth va = new Vedioauth();
		Logininfo applier = new Logininfo();
		applier.setId(loginInfoValue);//logininfo的id和userinfo的id相同

		va.setApplier(applier); //设置申请人
		va.setApplyTime(new Date());
		va.setAuditor(UserContext.getCurrent());
		va.setAuditTime(new Date());
		va.setState(state); // 视频认证状态看具体业务前端设计
		va.setRemark(remark);
		this.vedioauthMapper.insert(va);

		if (state == Vedioauth.STATE_PASS) { //现在前端state‘’ ，还没有设计值
			Userinfo userinfo = this.userService.get(loginInfoValue);
			if (!userinfo.getVedioAuth()) {
				userinfo.addState(BitStatesUtils.OP_VEDIO_AUTH); //添加视频已认证
				this.userService.update(userinfo);
			}
		}
	}
}
