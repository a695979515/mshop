package net.mshop.dao;

import net.mshop.entity.Admin;

/**
 * Created by Panfuhao on 2016/9/29.
 */
public interface AdminDao extends BaseDao<Admin, Long> {
    /**
     * 判断用户名是否存在
     *
     * @param username
     * @return
     */
    boolean usernameExists(String username);

    /**
     * 根据用户名查找
     *
     * @param username
     * @return
     */
    Admin findByUsername(String username);
}
