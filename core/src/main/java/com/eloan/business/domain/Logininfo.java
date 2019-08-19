package com.eloan.business.domain;

import com.eloan.base.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("Logininfo")
public class Logininfo extends BaseDomain {

	public static final int STATE_NORMAL = 0;
	public static final int STATE_LOCK = 1;
	public static final int STATE_DELETE = -1;

	public static final int USERTYPE_NORMAL = 0;//前端用户
	public static final int USERTYPE_SYSTEM = 1;//后台用户

	private String username;
	private String password;
	private int state = STATE_NORMAL;

	private int userType;//用户类型
	private boolean admin = false;

}
