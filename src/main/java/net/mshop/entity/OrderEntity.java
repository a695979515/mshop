package net.mshop.entity;

import org.apache.commons.lang.builder.CompareToBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * 排序基类
 * Created by Panfuhao on 2016/10/25.
 */
@MappedSuperclass
public abstract class OrderEntity<ID extends Serializable> extends BaseEntity<ID> implements Comparable<OrderEntity<ID>> {
    public static final String ORDER_PROPERTY_NAME = "order";
    private Integer order;


    @Min(0)
    @Column(name = "orders")
    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public int compareTo(OrderEntity<ID> o) {
        if (o == null) {
            return 1;
        }
        return new CompareToBuilder().append(getOrder(), o.getOrder()).append(getId(), o.getId()).toComparison();
    }
}
