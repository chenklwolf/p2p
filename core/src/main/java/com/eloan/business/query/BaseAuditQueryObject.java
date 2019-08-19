package com.eloan.business.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

import com.eloan.base.query.QueryObject;
import com.eloan.base.util.DateUtil;

@Getter
@Setter
public class BaseAuditQueryObject extends QueryObject {

	private int state = -1; //借款状态
	private Date beginDate;
	private Date endDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndDate() {
		if (endDate != null) {
			return DateUtil.endOfDay(endDate);
		}
		return null;
	}
}
