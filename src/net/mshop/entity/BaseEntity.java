package net.mshop.entity;

import net.mshop.listener.EntityListener;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;


import javax.persistence.*;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Panfuhao on 2016/9/21.
 */
@EntityListeners(EntityListener.class)
@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> implements Serializable {
    /**
     * ID 属性名称
     */
    public static final String ID_PROPERTY_NAME = "id";
    /**
     * 创建日期 属性名称
     */
    public static final String CREATE_DATE_PROPERTY_NAME = "createDate";
    /**
     * 修改日期  属性名称
     */
    public static final String MODIFY_DATE_PROPERTY_NAME = "modifyDate";
    /**
     * 版本 属性名称
     */
    public static final String VERSION_PROPERTY_NAME = "version";

    /**
     * 保存验证组
     */
    public interface Save extends Default {

    }

    /**
     * 更新验证组
     */
    public interface Update extends Default {

    }

    /**
     * id
     */
    private ID id;
    /**
     * 创建日期
     */
    private Date createDate;
    /**
     * 修改时间
     */
    private Date modifyDate;
    /**
     * 版本
     */
    private Long version;

    @DocumentId
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequenceGenerator")
    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
    @DateBridge(resolution = Resolution.SECOND)
    @Column(nullable = false, updatable = false)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
    @DateBridge(resolution = Resolution.SECOND)
    @Column(nullable = false)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Version
    @Column(nullable = false)
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * 判断是否为新建对象
     *
     * @return
     */
    @Transient
    public boolean isNew() {
        return getId() == null;
    }

    /**
     * 重写equals方法
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!BaseEntity.class.isAssignableFrom(o.getClass())) {
            return false;
        }
        BaseEntity<?> other = (BaseEntity<?>) o;
        return getId() != null ? getId().equals(other.getId()) : false;

    }

    /**
     * 重写hashCode方法
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hashCode = 17;
        hashCode += getId() != null ? getId().hashCode() * 31 : 0;
        return hashCode;
    }
}
