package net.mshop.entity;

import net.mshop.BaseAttributeConverter;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Entity - 插件配置
 *
 */
@Entity
@Table(name = "m_plugin_config")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_plugin_config")
public class PluginConfig extends OrderEntity<Long> {

	private static final long serialVersionUID = -3703072378069785812L;

	/** 插件ID */
	private String pluginId;

	/** 是否启用 */
	private Boolean isEnabled;

	/** 属性 */
	private Map<String, String> attributes = new HashMap<String, String>();

	/**
	 * 获取插件ID
	 * 
	 * @return 插件ID
	 */
	@Column(nullable = false, updatable = false, unique = true)
	public String getPluginId() {
		return pluginId;
	}

	/**
	 * 设置插件ID
	 * 
	 * @param pluginId
	 *            插件ID
	 */
	public void setPluginId(String pluginId) {
		this.pluginId = pluginId;
	}

	/**
	 * 获取是否启用
	 * 
	 * @return 是否启用
	 */
	@Column(nullable = false)
	public Boolean getIsEnabled() {
		return isEnabled;
	}

	/**
	 * 设置是否启用
	 * 
	 * @param isEnabled
	 *            是否启用
	 */
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	/**
	 * 获取属性
	 * 
	 * @return 属性
	 */
	@Column(length = 4000)
	@Convert(converter = MapConverter.class)
	public Map<String, String> getAttributes() {
		return attributes;
	}

	/**
	 * 设置属性
	 * 
	 * @param attributes
	 *            属性
	 */
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	/**
	 * 获取属性值
	 * 
	 * @param name
	 *            属性名称
	 * @return 属性值
	 */
	@Transient
	public String getAttribute(String name) {
		if (StringUtils.isEmpty(name)) {
			return null;
		}
		return getAttributes() != null ? getAttributes().get(name) : null;
	}

	/**
	 * 类型转换 - Map
	 * 
	 * @author SHOP++ Team
	 * @version 4.0
	 */
	@Converter
	public static class MapConverter extends BaseAttributeConverter<Map<String, String>> implements AttributeConverter<Object, String> {
	}

}
