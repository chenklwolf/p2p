package com.eloan.business.service;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyCode {

	private String phoneNumber;
	private String randomCode;
	private Date lastSendTime;
	private String content;

	public VerifyCode() {
		super();
	}

	public VerifyCode(String phoneNumber, String randomCode, Date lastSendTime,
			String content) {
		super();
		this.phoneNumber = phoneNumber;
		this.randomCode = randomCode;
		this.lastSendTime = lastSendTime;
		this.content = content;
	}

}
