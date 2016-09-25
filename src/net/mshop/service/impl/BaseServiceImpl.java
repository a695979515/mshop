package net.mshop.service.impl;

import net.mshop.dao.BaseDao;
import net.mshop.entity.BaseEntity;
import net.mshop.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * Created by Panfuhao on 2016/9/23.
 */
@Transactional
public abstract class BaseServiceImpl<T extends BaseEntity<ID>, ID extends Serializable> implements BaseService<T, ID> {

    /** 更新忽略属性 */
    private static final String[] UPDATE_IGNORE_PROPERTIES = new String[] { BaseEntity.CREATE_DATE_PROPERTY_NAME, BaseEntity.MODIFY_DATE_PROPERTY_NAME, BaseEntity.VERSION_PROPERTY_NAME };

    /** BaseDao */
    private BaseDao<T, ID> baseDao;

    @Autowired
    protected void setBaseDao(BaseDao<T, ID> baseDao) {
        this.baseDao = baseDao;
    }

    @Transactional
    public T save(T entity) {
        Assert.notNull(entity);
        Assert.isTrue(entity.isNew());

        baseDao.persist(entity);
        return entity;
    }
}
