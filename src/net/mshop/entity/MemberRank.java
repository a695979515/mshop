package net.mshop.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Panfuhao on 2016/9/23.
 */
@Entity
@Table(name = "m_member_rank")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_member_rank")
public class MemberRank extends BaseEntity<Long> {
    private String name;    //名称
    private String scale;   //优惠比列
    private BigDecimal amount;  //消费金额
    private Boolean isDefault;  //是否默认
    private Boolean isSpecial;  //是否特殊
    private Set<Member> members = new HashSet<>();    //会员

    @NotEmpty
    @Length(max = 200)
    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Min(0)
    @Digits(integer = 3, fraction = 3)
    @Column(nullable = false, precision = 12, scale = 6)
    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    @Min(0)
    @Digits(integer = 12, fraction = 3)
    @Column(precision = 21, scale = 6)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @NotNull
    @Column(nullable = false)
    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    @NotNull
    @Column(nullable = false)
    public Boolean getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(Boolean special) {
        isSpecial = special;
    }

    @OneToMany(mappedBy = "memberRank", fetch = FetchType.LAZY)
    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }
}
