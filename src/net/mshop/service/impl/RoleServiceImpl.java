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
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {
    /**
     * 保存
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public Role save(Role entity) {
        return super.save(entity);
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public Role update(Role entity) {
        return super.update(entity);
    }

    /**
     * 更新
     *
     * @param entity
     * @param ignoreProperties
     * @return
     */
    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public Role update(Role entity, String... ignoreProperties) {
        return super.update(entity, ignoreProperties);
    }

    /**
     * 批量删除
     *
     * @param longs
     */
    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public void deleteByIds(Long... longs) {
        super.deleteByIds(longs);
    }

    /**
     * 根据id删除
     *
     * @param aLong
     */
    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }

    /**
     * 删除
     *
     * @param entity
     */
    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public void delete(Role entity) {
        super.delete(entity);
    }
}
