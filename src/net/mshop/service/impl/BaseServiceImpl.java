package net.mshop.service.impl;

import net.mshop.dao.BaseDao;
import net.mshop.entity.BaseEntity;
import net.mshop.service.BaseService;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Panfuhao on 2016/9/23.
 */
@Transactional
public abstract class BaseServiceImpl<T extends BaseEntity<ID>, ID extends Serializable> implements BaseService<T, ID> {

    /**
     * 更新忽略属性
     */
    private static final String[] UPDATE_IGNORE_PROPERTIES = new String[]{BaseEntity.CREATE_DATE_PROPERTY_NAME, BaseEntity.MODIFY_DATE_PROPERTY_NAME, BaseEntity.VERSION_PROPERTY_NAME};

    /**
     * BaseDao
     */
    private BaseDao<T, ID> baseDao;

    @Autowired
    protected void setBaseDao(BaseDao<T, ID> baseDao) {
        this.baseDao = baseDao;
    }

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public T findById(ID id) {
        return baseDao.findById(id);
    }

    /**
     * 查找全部集合
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return null;
    }

    /**
     * 根据ids查找集合
     *
     * @param ids
     * @return
     */
    @Transactional(readOnly = true)
    public List<T> findListByIds(ID... ids) {
        List<T> result = new ArrayList<>();
        if (ids != null) {
            for (ID id : ids) {
                T entity = findById(id);
                if (entity != null) {
                    result.add(entity);
                }
            }
        }
        return result;
    }

    /**
     * 根据ID判断是否存在
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public boolean exists(ID id) {
        return baseDao.findById(id) != null;
    }


    @Transactional
    public T save(T entity) {
        Assert.notNull(entity);
        Assert.isTrue(entity.isNew());
        baseDao.persist(entity);
        return entity;
    }

    @Transactional
    public T update(T entity) {
        Assert.notNull(entity);
        Assert.isTrue(!entity.isNew());
        if (!baseDao.isManaged(entity)) {
            T persistant = baseDao.findById(baseDao.getIdentifier(entity));
            if (persistant != null) {
                copyProperties(entity, persistant, UPDATE_IGNORE_PROPERTIES);
            }
            return persistant;
        }
        return entity;
    }

    @Transactional
    public T update(T entity, String... ignoreProperties) {
        Assert.notNull(entity);
        Assert.isTrue(!entity.isNew());
        Assert.isTrue(!baseDao.isManaged(entity));
        T persistant = baseDao.findById(baseDao.getIdentifier(entity));
        if (persistant != null) {
            copyProperties(entity, persistant, (String[]) ArrayUtils.addAll(ignoreProperties, UPDATE_IGNORE_PROPERTIES));
        }
        return update(persistant);
    }

    @Transactional
    public void deleteById(ID id) {
        delete(baseDao.findById(id));
    }

    @Transactional
    public void deleteByIds(ID... ids) {
        if (ids != null) {
            for (ID id : ids) {
                delete(baseDao.findById(id));
            }
        }
    }
    @Transactional
    public void delete(T entity) {
       if(entity!=null){
           baseDao.remove(baseDao.isManaged(entity)?entity:baseDao.merge(entity));
        }
    }

    /**
     * 拷贝对象属性
     *
     * @param source           源
     * @param target           目标
     * @param ignoreProperties 忽略属性
     */
    protected void copyProperties(T source, T target, String... ignoreProperties) {
        Assert.notNull(source);
        Assert.notNull(target);

        PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(target);
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertyName = propertyDescriptor.getName();
            Method readMethod = propertyDescriptor.getReadMethod();
            Method writeMethod = propertyDescriptor.getWriteMethod();
            if (ArrayUtils.contains(ignoreProperties, propertyName) || readMethod == null || writeMethod == null) {
                continue;
            }
            try {
                Object sourceValue = readMethod.invoke(source);
                writeMethod.invoke(target, sourceValue);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e.getMessage(), e);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(e.getMessage(), e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }
}
