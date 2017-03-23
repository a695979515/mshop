package net.mshop.dao.impl;

import net.mshop.dao.ShippingMethodDao;
import net.mshop.entity.ShippingMethod;
import org.springframework.stereotype.Repository;

/**
 * Created by Panfuhao on 2017/3/23.
 */
@Repository("shippingMethodDaoImpl")
public class ShippingMethodDaoImpl extends BaseDaoImpl<ShippingMethod,Long> implements ShippingMethodDao{
}
