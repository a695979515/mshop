package net.mshop.dao.impl;

import net.mshop.dao.PluginConfigDao;
import net.mshop.entity.PluginConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

/**
 * Dao - 插件配置
 * 

 */
@Repository("pluginConfigDaoImpl")
public class PluginConfigDaoImpl extends BaseDaoImpl<PluginConfig, Long> implements PluginConfigDao {

	public boolean pluginIdExists(String pluginId) {
		if (StringUtils.isEmpty(pluginId)) {
			return false;
		}
		String jpql = "select count(*) from PluginConfig pluginConfig where pluginConfig.pluginId = :pluginId";
		Long count = entityManager.createQuery(jpql, Long.class).setParameter("pluginId", pluginId).getSingleResult();
		return count > 0;
	}

	public PluginConfig findByPluginId(String pluginId) {
		if (StringUtils.isEmpty(pluginId)) {
			return null;
		}
		try {
			String jpql = "select pluginConfig from PluginConfig pluginConfig where pluginConfig.pluginId = :pluginId";
			return entityManager.createQuery(jpql, PluginConfig.class).setParameter("pluginId", pluginId).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}