package net.mshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity - 序列号
 *
 */
@Entity
@Table(name = "xx_sn")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_sn")
public class Sn extends BaseEntity<Long> {

	private static final long serialVersionUID = -5463407053026432038L;

	/**
	 * 类型
	 */
	public enum Type {

		/** 货品 */
		goods,

		/** 订单 */
		order,

		/** 支付记录 */
		paymentLog,

		/** 收款单 */
		payment,

		/** 退款单 */
		refunds,

		/** 发货单 */
		shipping,

		/** 退货单 */
		returns
	}

	/** 类型 */
	private Sn.Type type;

	/** 末值 */
	private Long lastValue;

	/**
	 * 获取类型
	 *
	 * @return 类型
	 */
	@Column(nullable = false, updatable = false, unique = true)
	public Sn.Type getType() {
		return type;
	}

	/**
	 * 设置类型
	 *
	 * @param type
	 *            类型
	 */
	public void setType(Sn.Type type) {
		this.type = type;
	}

	/**
	 * 获取末值
	 * 
	 * @return 末值
	 */
	@Column(nullable = false)
	public Long getLastValue() {
		return lastValue;
	}

	/**
	 * 设置末值
	 * 
	 * @param lastValue
	 *            末值
	 */
	public void setLastValue(Long lastValue) {
		this.lastValue = lastValue;
	}

}
