package com.eloan.base.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.eloan.base.domain.IpLog;
import com.eloan.business.domain.Logininfo;
import com.eloan.base.mapper.IpLogMapper;
import com.eloan.base.mapper.LogininfoMapper;
import com.eloan.base.service.ILogininfoService;
import com.eloan.base.util.MD5;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Account;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.mapper.AccountMapper;
import com.eloan.business.mapper.UserinfoMapper;
import com.eloan.business.util.BidConst;

import javax.annotation.Resource;

@Service
public class LogininfoServiceImpl implements ILogininfoService {

	@Resource
	private LogininfoMapper loginInfoMapper;

	@Resource
	private AccountMapper accountMapper;

	@Resource
	private UserinfoMapper userinfoMapper;

	@Resource
	private IpLogMapper ipLogMapper;

	@Override
	public void register(String username, String password) {
		int count = this.loginInfoMapper.getCountByUsername(username,
				Logininfo.USERTYPE_NORMAL);
		if (count <= 0) {
			Logininfo logininfo = new Logininfo();
			logininfo.setUsername(username);
			logininfo.setPassword(MD5.encode(password));
			logininfo.setState(Logininfo.STATE_NORMAL);
			logininfo.setUserType(Logininfo.USERTYPE_NORMAL);
			this.loginInfoMapper.insert(logininfo);

			//初始化一个account
			Account account = Account.empty(logininfo.getId());
			accountMapper.insert(account);

			//初始化一个Userinfo
			Userinfo userinfo = Userinfo.empty(logininfo.getId());
			userinfoMapper.insert(userinfo);
		} else {
			throw new RuntimeException("用户名已经存在!");
		}
	}

	@Override
	public boolean checkUsername(String name, int userType) {
		return this.loginInfoMapper.getCountByUsername(name, userType) <= 0;
	}

	/**
	 * 登录的时候，
	 * 比对数据库密码，插入日志log
	 * @param name
	 * @param password
	 * @param userType
	 * @param ip
	 * @return
	 */
	@Override
	public Logininfo login(String name, String password, int userType, String ip) {
		IpLog log = new IpLog(name, new Date(), ip, userType, null);
		//userType如果是前台登录然后存储0，后台1
		Logininfo current = this.loginInfoMapper.login(name,
				MD5.encode(password), userType);
		if (current != null) {
			UserContext.putLogininfo(current); //放进session中
			log.setLoginInfoId(current.getId());
			log.setLoginState(IpLog.LOGINSTATE_SUCCESS); //登录成功状态1
		}
		ipLogMapper.insert(log);
		return current;
	}

	@Override
	public boolean hasAdmin() {
		return this.loginInfoMapper.getCountByUsername(
				BidConst.DEFAULT_ADMIN_NAME, Logininfo.USERTYPE_SYSTEM) > 0;
	}

	@Override
	public void createDefaultAdmin() {
		Logininfo admin = new Logininfo();
		admin.setUsername(BidConst.DEFAULT_ADMIN_NAME);
		admin.setPassword(MD5.encode(BidConst.DEFAULT_ADMIN_PASSWORD));
		admin.setUserType(Logininfo.USERTYPE_SYSTEM);
		admin.setAdmin(true);
		this.loginInfoMapper.insert(admin);
	}

	@Override
	public List<Map<String, Object>> autoComplate(String word, int userType) {
		return this.loginInfoMapper.autoComplate(word,userType);
	}

}
