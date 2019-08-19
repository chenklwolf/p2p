package com.eloan.base.mapper;

import java.util.List;

import com.eloan.base.domain.IpLog;
import com.eloan.base.query.IpLogQueryObject;

public interface IpLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IpLog record);

    IpLog selectByPrimaryKey(Long id);

    List<IpLog> selectAll();

    int updateByPrimaryKey(IpLog record);

	int queryForCount(IpLogQueryObject qo);

	List<IpLog> query(IpLogQueryObject qo);
}