package net.mshop.service.impl;

import net.mshop.dao.PaymentLogDao;
import net.mshop.entity.PaymentLog;
import net.mshop.service.MemberService;
import net.mshop.service.PaymentLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.persistence.LockModeType;

/**
 * Service - 支付记录
 * 

 */
@Service("paymentLogServiceImpl")
public class PaymentLogServiceImpl extends BaseServiceImpl<PaymentLog, Long> implements PaymentLogService {

	@Resource(name = "paymentLogDaoImpl")
	private PaymentLogDao paymentLogDao;
//	@Resource(name = "snDaoImpl")
//	private SnDao snDao;
	@Resource(name = "memberServiceImpl")
	private MemberService memberService;
//	@Resource(name = "orderServiceImpl")
//	private OrderService orderService;

	@Transactional(readOnly = true)
	public PaymentLog findBySn(String sn) {
		return paymentLogDao.findBySn(sn);
	}

	public void handle(PaymentLog paymentLog) {
		/*Assert.notNull(paymentLog);

		if (!LockModeType.PESSIMISTIC_WRITE.equals(paymentLogDao.getLockMode(paymentLog))) {
			paymentLogDao.refresh(paymentLog, LockModeType.PESSIMISTIC_WRITE);
		}

		Assert.notNull(paymentLog.getType());

		if (!PaymentLog.Status.wait.equals(paymentLog.getStatus())) {
			return;
		}

		switch (paymentLog.getType()) {
		case recharge:
			Member member = paymentLog.getMember();
			if (member != null) {
				memberService.addBalance(member, paymentLog.getEffectiveAmount(), DepositLog.Type.recharge, null, null);
			}
			break;
		case payment:
			Order order = paymentLog.getOrder();
			if (order != null) {
				Payment payment = new Payment();
				payment.setMethod(Payment.Method.online);
				payment.setPaymentMethod(paymentLog.getPaymentPluginName());
				payment.setFee(paymentLog.getFee());
				payment.setAmount(paymentLog.getAmount());
				payment.setOrder(order);
				orderService.payment(order, payment, null);
			}
			break;
		}
		paymentLog.setStatus(PaymentLog.Status.success);*/
	}

	@Override
	@Transactional
	public PaymentLog save(PaymentLog paymentLog) {
		/*Assert.notNull(paymentLog);

		paymentLog.setSn(snDao.generate(Sn.Type.paymentLog));*/

		return super.save(paymentLog);
	}

}