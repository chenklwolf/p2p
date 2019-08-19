package com.eloan.base.domain;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;

/**
 * 数据字典
 * 
 * @author Stef
 * 
 */
@Getter
@Setter
@Alias("SystemDictionary")
public class SystemDictionary extends BaseDomain {
	private static final long serialVersionUID = 3382007784095246946L;
	private String sn; // 编码
	private String title; // 名称
	private String intro; // 简介

	/**
	 * 返回当前的json字符串，页面中修改可以用到
	 * ,调用的时候vo.jsonString 就可以使用这个字段
	 * data-str = '${vo.jsonString}'
	 * @return
	 */
	public String getJsonString() {
		Map<String, Object> m = new HashMap<>();
		m.put("id", getId());
		m.put("sn", sn);
		m.put("title", title);
		m.put("intro", intro);
		return JSONObject.toJSONString(m);
	}
}
