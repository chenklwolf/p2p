package com.eloan.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eloan.base.domain.SystemDictionaryItem;
import com.eloan.base.query.SystemDictionaryQueryObject;

public interface SystemDictionaryItemMapper {
	int deleteByPrimaryKey(Long id);

	int insert(SystemDictionaryItem record);

	SystemDictionaryItem selectByPrimaryKey(Long id);

	List<SystemDictionaryItem> selectAll();

	int updateByPrimaryKey(SystemDictionaryItem record);

	int queryForCount(SystemDictionaryQueryObject qo);

	List<SystemDictionaryItem> query(SystemDictionaryQueryObject qo);

	/**
	 * 按照数据字典的目录sn查所有明细
	 * @param sn
	 * @return
	 */
	List<SystemDictionaryItem> queryBySn(@Param("sn")String sn);
}