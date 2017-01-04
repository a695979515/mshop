package net.mshop.service;


import net.mshop.entity.PaymentLog;

/**
 * Service - 支付记录
 *
 */
public interface PaymentLogService extends BaseService<PaymentLog, Long> {

	/**
	 * 根据编号查找支付记录
	 * 
	 * @param sn
	 *            编号(忽略大小写)
	 * @return 支付记录，若不存在则返回null
	 */
	PaymentLog findBySn(String sn);

	/**
	 * 支付处理
	 * 
	 * @param paymentLog
	 *            支付记录
	 */
	void handle(PaymentLog paymentLog);

}