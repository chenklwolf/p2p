package com.eloan.business.domain;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import com.alibaba.fastjson.JSONObject;
import com.eloan.base.domain.SystemDictionaryItem;

/**
 * 风控材料
 */
@Getter
@Setter
public class Userfile extends BaseAuditDomain {

	private String file; //风控材料文件，可能是图片，其他
	private SystemDictionaryItem fileType;
	private int score;

	public String getJsonString() {
		Map<String, Object> m = new HashMap<>();
		m.put("id", getId());
		m.put("username", this.getApplier().getUsername());
		m.put("fileType", fileType.getTitle());
		m.put("file", file);
		return JSONObject.toJSONString(m);
	}
}
