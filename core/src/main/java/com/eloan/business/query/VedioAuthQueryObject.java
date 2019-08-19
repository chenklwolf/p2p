package com.eloan.business.query;

import org.springframework.util.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VedioAuthQueryObject extends BaseAuditQueryObject {

	private String keyword;

	public String getKeyword() {
		return StringUtils.hasLength(keyword) ? keyword : null;
	}
}
