package net.mshop.entity;

import net.mshop.BaseAttributeConverter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Panfuhao on 2016/9/27.
 */
@Entity
@Table(name = "m_role")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_role")
public class Role extends BaseEntity<Long> {
    private String name;        //名称
    private String isSystem;    //是否内置
    private String description; //描述
    private List<String> authorities = new ArrayList<>(); //权限
    private Set<Admin> admins = new HashSet<>();            //管理员

    @NotEmpty
    @Length(max = 200)
    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false, updatable = false)
    public String getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(String isSystem) {
        this.isSystem = isSystem;
    }

    @Length(max = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotEmpty
    @Column(nullable = false, length = 4000)
    @Convert(converter = AuthorityConverter.class)
    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    public Set<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(Set<Admin> admins) {
        this.admins = admins;
    }

    /**
     * 类型转换 - 权限
     *
     * @version 4.0
     */
    @Converter
    public static class AuthorityConverter extends BaseAttributeConverter<List<String>> implements AttributeConverter<Object, String> {
    }
}
