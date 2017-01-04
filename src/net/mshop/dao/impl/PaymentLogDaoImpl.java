package net.mshop.dao.impl;

import net.mshop.dao.PaymentLogDao;
import net.mshop.entity.PaymentLog;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

/**
 * Dao - 支付记录
 * 

 */
@Repository("paymentLogDaoImpl")
public class PaymentLogDaoImpl extends BaseDaoImpl<PaymentLog, Long> implements PaymentLogDao {

	public PaymentLog findBySn(String sn) {
		if (StringUtils.isEmpty(sn)) {
			return null;
		}
		String jpql = "select paymentLog from PaymentLog paymentLog where lower(paymentLog.sn) = lower(:sn)";
		try {
			return entityManager.createQuery(jpql, PaymentLog.class).setParameter("sn", sn).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}