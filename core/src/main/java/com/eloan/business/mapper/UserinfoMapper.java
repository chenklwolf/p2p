package com.eloan.business.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eloan.business.domain.Userinfo;

public interface UserinfoMapper {
	int deleteByPrimaryKey(Long id);

	int insert(Userinfo record);

	Userinfo selectByPrimaryKey(@Param("id") Long id, @Param("salt") String salt);

	List<Userinfo> selectAll(@Param("salt") String salt);

	int updateByPrimaryKey(@Param("ui") Userinfo record, @Param("salt") String salt);
}