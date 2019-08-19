package com.eloan.business.mapper;

import java.util.List;

import com.eloan.business.domain.EmailActive;

public interface EmailActiveMapper {
	int deleteByPrimaryKey(Long id);

	int insert(EmailActive record);

	EmailActive selectByPrimaryKey(Long id);

	List<EmailActive> selectAll();

	int updateByPrimaryKey(EmailActive record);
	
	EmailActive selectByCode(String code);
}