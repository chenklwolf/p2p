package com.eloan.business.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eloan.business.domain.Userfile;
import com.eloan.business.query.UserFileQueryObject;

public interface UserfileMapper {

	int insert(Userfile record);

	Userfile selectByPrimaryKey(Long id);

	int updateByPrimaryKey(Userfile record);

	List<Userfile> listUnSetTypeFiles(@Param("applierId") Long id,
			@Param("unselected") boolean unselected);

	int queryForCount(UserFileQueryObject qo);

	List<Userfile> query(UserFileQueryObject qo);
}