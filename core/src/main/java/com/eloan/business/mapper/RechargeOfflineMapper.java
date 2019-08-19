package com.eloan.business.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eloan.business.domain.RechargeOffline;
import com.eloan.business.query.RechargeOfflineQueryObject;

public interface RechargeOfflineMapper {

	int insert(RechargeOffline record);

	int update(RechargeOffline record);

	RechargeOffline selectByPrimaryKey(@Param("id") Long id);
	
	int queryForCount(RechargeOfflineQueryObject qo);
	
	List<RechargeOffline> query(RechargeOfflineQueryObject qo);

}