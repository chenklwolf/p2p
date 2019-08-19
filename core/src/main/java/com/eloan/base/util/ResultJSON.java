package com.eloan.base.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultJSON {
	private Boolean success = false;
	private String msg;

	public ResultJSON() {
		super();
	}

	public ResultJSON(Boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	public ResultJSON(String msg) {
		super();
		this.msg = msg;
	}

}
