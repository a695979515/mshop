package net.mshop.service.impl;

import net.mshop.entity.Role;
import net.mshop.service.RoleService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Panfuhao on 2016/10/17.
 */
@Service("roleServiceImpl")
public class RoleServiceImpl extends BaseServiceImpl<Role , Long> implements RoleService{
    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public Role save(Role entity) {
        return super.save(entity);
    }

    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public Role update(Role entity) {
        return super.update(entity);
    }

    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public Role update(Role entity, String... ignoreProperties) {
        return super.update(entity, ignoreProperties);
    }

    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public void deleteByIds(Long... longs) {
        super.deleteByIds(longs);
    }

    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }

    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public void delete(Role entity) {
        super.delete(entity);
    }
}
