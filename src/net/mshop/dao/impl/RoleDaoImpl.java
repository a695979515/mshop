package net.mshop.dao.impl;

import net.mshop.dao.RoleDao;
import net.mshop.entity.Role;
import org.springframework.stereotype.Repository;

/**
 * Created by Panfuhao on 2016/10/17.
 */
@Repository("roleDaoImpl")
public class RoleDaoImpl extends BaseDaoImpl<Role,Long> implements RoleDao{
}
