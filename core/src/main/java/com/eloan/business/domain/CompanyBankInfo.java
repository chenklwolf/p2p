package com.eloan.business.domain;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;
import com.eloan.base.domain.BaseDomain;

/**
 * 公司银行账户,作用是用户线下充值时填写充值单，
 * 充值到公司支持的银行账户里面去
 * @author Administrator
 */
@Getter
@Setter
@Alias("CompanyBankInfo")
public class CompanyBankInfo extends BaseDomain {
	
	private static final long serialVersionUID = 1L;

	private String bankname;//银行

    private String accountname;//开户名

    private String banknumber;//账号

    private String bankforkname;//开户支行

    public String getJsonString(){
    	Map<String,Object> m=new HashMap<>();
    	m.put("bankname", bankname);
    	m.put("accountname", accountname);
    	m.put("banknumber", banknumber);
    	m.put("bankforkname", bankforkname);
    	return JSONObject.toJSONString(m);
    }

}