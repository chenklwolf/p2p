package com.eloan.business.service;

/**
 * 短信验证码相关服务
 * @author Administrator
 *
 */
public interface ISendVerifyCodeService {

	void sendVerifyCode(String phoneNumber);

	/**
	 * 短信验证码校验
	 * @param phoneNumber
	 * @param verifyCode
	 */
	boolean verifyCode(String phoneNumber, String verifyCode);
}
