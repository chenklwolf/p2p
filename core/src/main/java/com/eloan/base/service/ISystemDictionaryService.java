package com.eloan.base.service;

import java.util.List;

import com.eloan.base.domain.SystemDictionary;
import com.eloan.base.domain.SystemDictionaryItem;
import com.eloan.base.query.PageResult;
import com.eloan.base.query.SystemDictionaryQueryObject;

public interface ISystemDictionaryService {

	PageResult queryDic(SystemDictionaryQueryObject qo);

	void saveOrUpdate(SystemDictionary sd);

	PageResult queryDicItem(SystemDictionaryQueryObject qo);

	void saveOrUpdateItem(SystemDictionaryItem item);

	List<SystemDictionary> listDics();
	
	List<SystemDictionaryItem> queryBySn(String sn);

}
