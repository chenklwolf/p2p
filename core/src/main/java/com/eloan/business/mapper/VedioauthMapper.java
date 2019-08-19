package com.eloan.business.mapper;

import java.util.List;

import com.eloan.business.domain.Vedioauth;
import com.eloan.business.query.VedioAuthQueryObject;

public interface VedioauthMapper {
	int insert(Vedioauth record);

	Vedioauth selectByPrimaryKey(Long id);

	int updateByPrimaryKey(Vedioauth record);

	int queryForCount(VedioAuthQueryObject qo);

	List<Vedioauth> query(VedioAuthQueryObject qo);
}