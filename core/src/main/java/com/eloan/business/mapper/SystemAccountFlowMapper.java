package com.eloan.business.mapper;

import com.eloan.business.domain.SystemAccountFlow;

/**
 * 系统账户流水mapper
 *
 * @author Stef
 *
 */
public interface SystemAccountFlowMapper {

	/**
	 * 添加一个系统账户流水
	 *
	 * @param bank
	 */
	void addSystemAccountFlow(SystemAccountFlow systemAccountFlow);

}
