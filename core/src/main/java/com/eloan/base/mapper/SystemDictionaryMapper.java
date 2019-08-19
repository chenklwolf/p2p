package com.eloan.base.mapper;

import java.util.List;

import com.eloan.base.domain.SystemDictionary;
import com.eloan.base.query.SystemDictionaryQueryObject;

public interface SystemDictionaryMapper {
	int deleteByPrimaryKey(Long id);

	int insert(SystemDictionary record);

	SystemDictionary selectByPrimaryKey(Long id);

	List<SystemDictionary> selectAll();

	int updateByPrimaryKey(SystemDictionary record);

	int queryForCount(SystemDictionaryQueryObject qo);

	List<SystemDictionary> query(SystemDictionaryQueryObject qo);
}