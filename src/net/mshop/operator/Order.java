package net.mshop.operator;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * 查询排序类
 * Created by Panfuhao on 2016/10/25.
 */
public class Order implements Serializable {
    /**
     * 趋向
     */
    public enum Direction {
        //递增
        asc,
        //递减
        desc
    }

    /**
     * 默认递减
     */
    private static final Direction DEFAULT_DIRECTION = Direction.desc;
    /**
     * 属性
     */
    private String property;
    /**
     * 趋向
     */
    private Direction direction = DEFAULT_DIRECTION;

    public Order() {
    }

    public Order(String property, Direction direction) {
        this.property = property;
        this.direction = direction;
    }

    public static Order asc(String property) {
        return new Order(property, Direction.asc);
    }

    public static Order desc(String property) {
        return new Order(property, Direction.desc);
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getProperty()).append(getDirection()).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Order order = (Order) obj;
        return new EqualsBuilder().append(getProperty(), order.getProperty()).append(getDirection(), order.getDirection()).isEquals();
    }
}
