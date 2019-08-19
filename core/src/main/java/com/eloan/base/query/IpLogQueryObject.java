package com.eloan.base.query;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;

import com.eloan.base.util.DateUtil;

/**
 * IpLog查询对象
 * @author Administrator
 *
 */
@Setter
@Getter
public class IpLogQueryObject extends QueryObject {

	private Date beginDate;
	private Date endDate;
	private String username;
	private int userType=-1;
	private boolean like;
	private int state=-1;

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
