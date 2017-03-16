package net.mshop.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

/**
 * 物流公司
 * Created by Panfuhao on 2017/3/16.
 */
@Entity
@Table(name="m_delivery_corp")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_delivery_corp")
public class DeliveryCorp extends OrderEntity<Long>{
    private String name;
    private String url;
    private String code;

    @NotEmpty
    @Length(max = 200)
    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(max = 200)
    @Pattern(regexp = "^(?i)(http:\\/\\/|https:\\/\\/|ftp:\\/\\/|mailto:|\\/|#).*$")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Length(max = 200)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
