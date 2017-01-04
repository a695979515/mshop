package net.mshop.dao;


import net.mshop.entity.PaymentLog;

/**
 * Dao - 支付记录
 */
public interface PaymentLogDao extends BaseDao<PaymentLog, Long> {

	/**
	 * 根据编号查找支付记录
	 * 
	 * @param sn
	 *            编号(忽略大小写)
	 * @return 支付记录，若不存在则返回null
	 */
	PaymentLog findBySn(String sn);

}