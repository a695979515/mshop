package net.mshop.dao;

import net.mshop.entity.BaseEntity;
import net.mshop.operator.Filter;
import net.mshop.operator.Order;
import net.mshop.operator.Page;
import net.mshop.operator.Pageable;

import javax.persistence.LockModeType;
import java.io.Serializable;
import java.util.List;

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
     * 排序筛选查找集合
     *
     * @param count
     * @param filters
     * @param orders
     * @return
     */
    List<T> findList(Integer first, Integer count, List<Filter> filters, List<Order> orders);

    /**
     * 查找分页
     *
     * @param pageable
     * @return
     */
    Page<T> findPage(Pageable pageable);

    /**
     * 查询总数
     *
     * @return
     */
    long count(Filter... filters);

    /**
     * 持久化
     *
     * @param entity
     */
    void persist(T entity);
    /**
     * 刷新实体对象
     *
     * @param entity
     *            实体对象
     */
    void refresh(T entity);

    /**
     * 刷新实体对象
     *
     * @param entity
     *            实体对象
     * @param lockModeType
     *            锁定方式
     */
    void refresh(T entity, LockModeType lockModeType);

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
    /**
     * 获取锁定方式
     *
     * @param entity
     *            实体对象
     * @return 锁定方式
     */
    LockModeType getLockMode(T entity);

    /**
     * 锁定实体对象
     *
     * @param entity
     *            实体对象
     * @param lockModeType
     *            锁定方式
     */
    void lock(T entity, LockModeType lockModeType);

}
