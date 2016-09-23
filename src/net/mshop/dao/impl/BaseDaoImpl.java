package net.mshop.dao.impl;

import net.mshop.dao.BaseDao;
import net.mshop.entity.BaseEntity;

import java.io.Serializable;

/**
 * Created by Panfuhao on 2016/9/23.
 */
public abstract class BaseDaoImpl<T extends BaseEntity<ID> ,ID extends Serializable> implements BaseDao<T,ID> {
}
