package net.mshop.dao;

import net.mshop.entity.BaseEntity;

import java.io.Serializable;

/**
 * Created by Panfuhao on 2016/9/23.
 */
public interface BaseDao<T extends BaseEntity<ID>, ID extends Serializable> {
    /**
     * 根据ID查找
     *
     * @param id
     * @return
     */
    T findById(ID id);

    /**
     * 持久化
     *
     * @param entity
     */
    void persist(T entity);

    /**
     * 判断是否为托管状态
     *
     * @param entity 实体对象
     * @return 是否为托管状态
     */
    boolean isManaged(T entity);

    /**
     * 获取实体对象ID
     *
     * @param entity 实体对象
     * @return 实体对象ID
     */
    ID getIdentifier(T entity);

    /**
     * 移除实体对象
     *
     * @param entity 实体对象
     */
    void remove(T entity);

    /**
     * 合并实体对象
     *
     * @param entity 实体对象
     * @return 实体对象
     */
    T merge(T entity);

}
