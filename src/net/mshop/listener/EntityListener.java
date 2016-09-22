package net.mshop.listener;

import net.mshop.entity.BaseEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * Created by Panfuhao on 2016/9/22.
 */
public class EntityListener {
    /**
     * 保存前处理
     * @param entity
     */
    @PrePersist
    public void prePersist(BaseEntity<?> entity){
        entity.setCreateDate(new Date());
        entity.setModifyDate(new Date());
        entity.setVersion(null);
    }

    /**
     * 更新前处理
     * @param entity
     */
    @PreUpdate
    public void preUpdate(BaseEntity<?> entity){
        entity.setModifyDate(new Date());
    }
}
