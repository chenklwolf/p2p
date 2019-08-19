package com.eloan.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eloan.base.domain.SystemDictionaryItem;
import com.eloan.base.service.ISystemDictionaryService;

/**
 * 数据字典工具类
 * @author Administrator
 *
 */
@Component
public class SystemDictionaryUtil {

	@Autowired
	private ISystemDictionaryService systemDictionaryService;

	public List<SystemDictionaryItem> list(String sn) {
		return systemDictionaryService.queryBySn(sn);
	}

}
