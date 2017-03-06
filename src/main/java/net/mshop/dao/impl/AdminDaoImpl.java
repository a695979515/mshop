package net.mshop.dao.impl;

import net.mshop.dao.AdminDao;
import net.mshop.entity.Admin;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

/**
 * Created by Panfuhao on 2016/9/29.
 */
@Repository("adminDaoImpl")
public class AdminDaoImpl extends BaseDaoImpl<Admin, Long> implements AdminDao {
    @Override
    public boolean usernameExists(String username) {
        if (StringUtils.isEmpty(username)) {
            return false;
        }
        String jpql = "select count(*) from Admin admin where lower(admin.username) = lower(:username)";
        Long count = entityManager.createQuery(jpql, Long.class).setParameter("username", username).getSingleResult();
        return count > 0;
    }

    @Override
    public Admin findByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        try {
            String jpql = "select admin from Admin admin where lower(admin.username) = lower(:username)";
            return entityManager.createQuery(jpql, Admin.class).setParameter("username", username).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
