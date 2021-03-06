package net.mshop.service.impl;

import net.mshop.Principal;
import net.mshop.dao.AdminDao;
import net.mshop.entity.Admin;
import net.mshop.entity.Role;
import net.mshop.service.AdminService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Panfuhao on 2016/9/29.
 */
@Service("adminServiceImpl")
public class AdminServiceImpl extends BaseServiceImpl<Admin, Long> implements AdminService {
    @Resource(name = "adminDaoImpl")
    private AdminDao adminDao;

    /**
     * 判断用户名是否存在
     *
     * @param username
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public boolean usernameExists(String username) {
        return adminDao.usernameExists(username);
    }

    /**
     * 根据用户名查找
     *
     * @param username
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public Admin findByUsername(String username) {
        return adminDao.findByUsername(username);
    }

    /**
     * 根据ID查找权限
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<String> findAuthorities(Long id) {
        List<String> authorities = new ArrayList<>();
        Admin admin = adminDao.findById(id);
        if (admin != null && admin.getRoles() != null) {
            for (Role role : admin.getRoles()) {
                if (role != null && role.getAuthorities() != null) {
                    authorities.addAll(role.getAuthorities());
                }
            }
        }
        return authorities;
    }

    /**
     * 判断是否登录
     *
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public boolean isAuthenticated() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            return subject.isAuthenticated();
        }
        return false;
    }

    /**
     * 获取当前登录管理员
     *
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public Admin getCurrent() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            Principal principal = (Principal) subject.getPrincipal();
            if (principal != null) {
                return adminDao.findById(principal.getId());
            }
        }
        return null;
    }

    /**
     * 获取当前登录管理员账户名
     *
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public String getCurrentUsername() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            Principal principal = (Principal) subject.getPrincipal();
            if (principal != null) {
                return principal.getUsername();
            }
        }
        return null;
    }

    /**
     * 获取登录token
     *
     * @return
     */
    @Transactional(readOnly = true)
    @Cacheable(value = "loginToken")
    @Override
    public String getLoginToken() {
        return DigestUtils.md5Hex(UUID.randomUUID() + RandomStringUtils.randomAlphabetic(30));
    }

    /**
     * 保存管理员操作
     *
     * @param entity
     * @return
     */
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    @Override
    public Admin save(Admin entity) {
        return super.save(entity);
    }

    /**
     * 更新管理员操作
     *
     * @param entity
     * @return
     */
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    @Override
    public Admin update(Admin entity) {
        return super.update(entity);
    }

    /**
     * 更新管理员操作
     *
     * @param entity
     * @param ignoreProperties
     * @return
     */
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    @Override
    public Admin update(Admin entity, String... ignoreProperties) {
        return super.update(entity, ignoreProperties);
    }

    /**
     * 根据ID删除管理员
     *
     * @param aLong
     */
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }

    /**
     * 批量删除管理员
     *
     * @param longs
     */
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    @Override
    public void deleteByIds(Long... longs) {
        super.deleteByIds(longs);
    }

    /**
     * 删除管理员
     *
     * @param entity
     */
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    @Override
    public void delete(Admin entity) {
        super.delete(entity);
    }
}
