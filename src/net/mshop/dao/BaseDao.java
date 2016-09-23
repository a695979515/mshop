package net.mshop.dao;

import net.mshop.entity.BaseEntity;

import java.io.Serializable;

/**
 * Created by Panfuhao on 2016/9/23.
 */
public interface BaseDao<T extends BaseEntity<ID>, ID extends Serializable> {
}
