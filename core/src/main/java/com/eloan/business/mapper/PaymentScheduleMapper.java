package com.eloan.business.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eloan.business.domain.PaymentSchedule;

/**
 * 分期还款计划mapper
 *
 * @author Stef
 *
 */
public interface PaymentScheduleMapper {

	/**
	 * 得到一个分期还款计划对象
	 */
	PaymentSchedule getPaymentSchedule(@Param("id") Long id);

	/**
	 * 添加一个分期还款计划信息
	 */
	void addPaymentSchedule(PaymentSchedule paymentSchedule);

	/**
	 * 修改还款计划
	 *
	 * @param paymentSchedule
	 */
	void update(PaymentSchedule paymentSchedule);

	/**
	 * 根据一个借款得到该借款下的所有还款计划
	 *
	 * @param id
	 * @return
	 */
	List<PaymentSchedule> getPaymentScheduleByBidRequest(
			@Param("bidRequestId") Long id);

//	Long getPaymentScheduleTotalCount(PaymentScheduleQueryObject qo);

//	List<PaymentSchedule> getPaymentScheduleBy(PaymentScheduleQueryObject qo);

}
