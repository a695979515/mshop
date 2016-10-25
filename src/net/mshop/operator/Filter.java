package net.mshop.operator;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * 查询过滤类
 * Created by Panfuhao on 2016/10/25.
 */
public class Filter implements Serializable {
    /**
     * 查询运算枚举
     */
    public enum Operator {
        eq, //等于
        ne,//不等于
        gt,//大于
        lt,//小于
        ge,//大于等于
        le,//小于等于
        like,//模糊查询
        in,//包含
        isNull,//为Null
        isNotNull//不为Null
    }

    /**
     * 默认是否忽略大小写
     */
    private static final boolean DEFAULT_LGNORE_GASE = false;
    /**
     * 属性
     */
    private String property;
    /**
     * 运算符
     */
    private Operator operator;
    /**
     * 值
     */
    private Object value;
    /**
     * 是否忽略大小写
     */
    private Boolean ignoreCase = DEFAULT_LGNORE_GASE;

    public Filter() {
    }


    public Filter(String property, Operator operator, Object value) {
        this.property = property;
        this.operator = operator;
        this.value = value;
    }

    public Filter(String property, Operator operator, Object value, Boolean ignoreCase) {
        this.property = property;
        this.operator = operator;
        this.value = value;
        this.ignoreCase = ignoreCase;
    }

    public static Filter eq(String property, Object value) {
        return new Filter(property, Operator.eq, value);
    }

    public static Filter eq(String property, Object value, boolean ignoreCase) {
        return new Filter(property, Operator.eq, value, ignoreCase);
    }

    public static Filter ne(String property, Object value) {
        return new Filter(property, Operator.ne, value);
    }

    public static Filter ne(String property, Object value, boolean ignoreCase) {
        return new Filter(property, Operator.ne, value, ignoreCase);
    }

    public static Filter gt(String property, Object value) {
        return new Filter(property, Operator.gt, value);
    }

    public static Filter lt(String property, Object value) {
        return new Filter(property, Operator.lt, value);
    }

    public static Filter ge(String property, Object value) {
        return new Filter(property, Operator.ge, value);
    }

    public static Filter le(String property, Object value) {
        return new Filter(property, Operator.le, value);
    }

    public static Filter like(String property, Object value) {
        return new Filter(property, Operator.like, value);
    }

    public static Filter in(String property, Object value) {
        return new Filter(property, Operator.in, value);
    }

    public static Filter isNull(String property) {
        return new Filter(property, Operator.in, null);
    }

    public static Filter isNotNull(String property) {
        return new Filter(property, Operator.in, null);
    }

    public Filter ignoreCase() {
        this.ignoreCase = true;
        return this;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Boolean getIgnoreCase() {
        return ignoreCase;
    }

    public void setIgnoreCase(Boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getProperty()).append(getOperator()).append(getValue()).toHashCode();
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
        Filter filter = (Filter) obj;
        return new EqualsBuilder().append(getProperty(), filter.getProperty()).append(getOperator(), filter.getOperator()).append(getValue(), filter.getValue()).isEquals();
    }
}
