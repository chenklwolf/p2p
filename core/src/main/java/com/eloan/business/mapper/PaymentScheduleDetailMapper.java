package com.eloan.business.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eloan.business.domain.PaymentScheduleDetail;

/**
 * 分期还款明细计划mapper
 *
 * @author Stef
 *
 */
public interface PaymentScheduleDetailMapper {

	/**
	 * 添加一个分期还款计划明细信息
	 */
	void addPaymentScheduleDetail(PaymentScheduleDetail paymentScheduleDetail);

	/**
	 * 根据一个分期还款计划得到该分期还款计划下所有的还款计划明细
	 */
	List<PaymentScheduleDetail> getPaymentScheduleDetailBySchedule(
			@Param("scheduleId") Long scheduleId);

	/**
	 * 修改还款计划明细
	 *
	 * @param paymentScheduleDetail
	 */
	void update(PaymentScheduleDetail paymentScheduleDetail);

	/**
	 * 根据id查询PaymentScheduleDetail
	 * @param psdId
	 * @return
	 */
	PaymentScheduleDetail getPaymentScheduleDetailById(Long id);

}
