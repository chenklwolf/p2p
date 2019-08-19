package com.eloan.base.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

public class EloanRoutingDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return StringUtils.hasLength(DBContextUtil.getDB()) ? DBContextUtil
				.getDB() : DBContextUtil.DBMASTER;
	}

}
