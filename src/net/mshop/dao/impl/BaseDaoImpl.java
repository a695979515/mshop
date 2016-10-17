package net.mshop.dao.impl;

import net.mshop.dao.BaseDao;
import net.mshop.entity.BaseEntity;
import org.springframework.core.ResolvableType;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

/**
 * Created by Panfuhao on 2016/9/23.
 */
public abstract class BaseDaoImpl<T extends BaseEntity<ID>, ID extends Serializable> implements BaseDao<T, ID> {
    /**
     * 实体类类型
     */
    private Class<T> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        ResolvableType resolvableType = ResolvableType.forClass(getClass());
        entityClass = (Class<T>) resolvableType.getSuperType().getGeneric().resolve();
    }

    public T findById(ID id) {
        if (id == null) {
            return null;
        }
        return entityManager.find(entityClass, id);
    }

    public void persist(T entity) {
        Assert.notNull(entity);

        entityManager.persist(entity);
    }

    public boolean isManaged(T entity) {
        Assert.notNull(entity);
        return entityManager.contains(entity);
    }

    public ID getIdentifier(T entity) {
        Assert.notNull(entity);
        return (ID) entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
    }

    public void remove(T entity) {
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    public T merge(T entity) {
        Assert.notNull(entity);

        return entityManager.merge(entity);
    }
}
