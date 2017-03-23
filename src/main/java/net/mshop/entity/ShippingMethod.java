package net.mshop.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * 配送方式
 * Created by Panfuhao on 2017/3/20.
 */
@Entity
@Table(name = "m_shipping_method")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_shipping_method")
public class ShippingMethod extends OrderEntity<Long> {
    private static final long serialVersionUID = 1354351215645645L;
    private String name;        //名称
    private Integer firstWeight;    //首重
    private Integer countinueWeight;    //续重
    private BigDecimal defaultFirstPrice;//默认首重价格
    private BigDecimal defaultContinuePrice;//默认续重价格
    private String icon;            //图标
    private String description;     //描述
    private DeliveryCorp defaultDeliveryCorp;//默认物流公司

    @NotEmpty
    @Length(max = 200)
    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Min(0)
    @Column(nullable = false)
    public Integer getFirstWeight() {
        return firstWeight;
    }

    public void setFirstWeight(Integer firstWeight) {
        this.firstWeight = firstWeight;
    }

    @NotNull
    @Min(1)
    @Column(nullable = false)
    public Integer getCountinueWeight() {
        return countinueWeight;
    }

    public void setCountinueWeight(Integer countinueWeight) {
        this.countinueWeight = countinueWeight;
    }

    @NotNull
    @Min(0)
    @Digits(integer = 12, fraction = 3)
    @Column(nullable = false, precision = 21, scale = 6)
    public BigDecimal getDefaultFirstPrice() {
        return defaultFirstPrice;
    }

    public void setDefaultFirstPrice(BigDecimal defaultFirstPrice) {
        this.defaultFirstPrice = defaultFirstPrice;
    }

    @NotNull
    @Min(0)
    @Digits(integer = 12, fraction = 3)
    @Column(nullable = false, precision = 21, scale = 6)
    public BigDecimal getDefaultContinuePrice() {
        return defaultContinuePrice;
    }

    public void setDefaultContinuePrice(BigDecimal defaultContinuePrice) {
        this.defaultContinuePrice = defaultContinuePrice;
    }

    @Length(max = 200)
    @Pattern(regexp = "^(?i)(http:\\/\\/|https:\\/\\/|\\/).*$")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Length(max = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public DeliveryCorp getDefaultDeliveryCorp() {
        return defaultDeliveryCorp;
    }

    public void setDefaultDeliveryCorp(DeliveryCorp defaultDeliveryCorp) {
        this.defaultDeliveryCorp = defaultDeliveryCorp;
    }
}
