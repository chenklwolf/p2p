package com.eloan.business.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

import com.eloan.base.domain.BaseDomain;

@Alias("EmailActive")
@Getter
@Setter
public class EmailActive extends BaseDomain {

	private String uuidcode;
	private Long logininfoId;
	private String email;
	private Date sendDate;
}
