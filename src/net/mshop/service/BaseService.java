package net.mshop.service;

import net.mshop.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Panfuhao on 2016/9/23.
 */
public interface BaseService<T extends BaseEntity<ID>, ID extends Serializable> {
    /**
     * 根据ID查找
     * @param id
     * @return
     */
    T findById(ID id);

    /**
     * 查找全部集合
     * @return
     */
    List<T> findAll();

    /**
     * 根据ids查找集合
     * @param ids
     * @return
     */
    List<T> findListByIds(ID... ids);

    /**
     * 根据ID判断是否存在
     * @param id
     * @return
     */
    boolean exists(ID id);

    /**
     * 保存
     * @param entity
     * @return
     */
    T save(T entity);

    /**
     * 更新
     * @param entity
     * @return
     */
    T update(T entity);

    /**
     * 忽略一些属性更新
     * @param entity
     * @param ignoreProperties
     * @return
     */
    T update(T entity , String... ignoreProperties);

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    void deleteById(ID id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    void deleteByIds(ID... ids);

    /**
     * 删除
     * @param entity
     * @return
     */
    void delete(T entity);
}
