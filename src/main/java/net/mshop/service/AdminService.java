package net.mshop.service;

import net.mshop.entity.Admin;

import java.util.List;

/**
 * Created by Panfuhao on 2016/9/29.
 */
public interface AdminService extends BaseService<Admin, Long> {
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

    /**
     * 根据ID查找权限
     *
     * @param id
     * @return
     */
    List<String> findAuthorities(Long id);

    /**
     * 判断是否登录
     *
     * @return
     */
    boolean isAuthenticated();

    /**
     * 获取当前登录管理员
     *
     * @return
     */
    Admin getCurrent();

    /**
     * 获取当前登录管理员账户名
     *
     * @return
     */
    String getCurrentUsername();

    /**
     * 获取登录Token
     *
     * @return
     */
    String getLoginToken();
}
