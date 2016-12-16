package net.mshop.plugin.ossStorage;


import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import net.mshop.entity.PluginConfig;
import net.mshop.plugin.StoragePlugin;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * Plugin - 阿里云存储
 *
 */
@Component("ossStoragePlugin")
public class OssStoragePlugin extends StoragePlugin {

	@Override
	public String getName() {
		return "阿里云存储";
	}

	@Override
	public String getSiteUrl() {
		return "http://www.shopxx.net";
	}

	@Override
	public String getInstallUrl() {
		return "oss_storage/install.jhtml";
	}

	@Override
	public String getUninstallUrl() {
		return "oss_storage/uninstall.jhtml";
	}

	@Override
	public String getSettingUrl() {
		return "oss_storage/setting.jhtml";
	}

	@Override
	public void upload(String path, File file, String contentType) {
		PluginConfig pluginConfig = getPluginConfig();
		if (pluginConfig != null) {
			String endpoint = pluginConfig.getAttribute("endpoint");
			String accessId = pluginConfig.getAttribute("accessId");
			String accessKey = pluginConfig.getAttribute("accessKey");
			String bucketName = pluginConfig.getAttribute("bucketName");
			InputStream inputStream = null;
			try {
				inputStream = new BufferedInputStream(new FileInputStream(file));
				OSSClient ossClient = new OSSClient(endpoint, accessId, accessKey);
				ObjectMetadata objectMetadata = new ObjectMetadata();
				objectMetadata.setContentType(contentType);
				objectMetadata.setContentLength(file.length());
				ossClient.putObject(bucketName, StringUtils.removeStart(path, "/"), inputStream, objectMetadata);
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				IOUtils.closeQuietly(inputStream);
			}
		}
	}

	@Override
	public String getUrl(String path) {
		PluginConfig pluginConfig = getPluginConfig();
		if (pluginConfig != null) {
			String urlPrefix = pluginConfig.getAttribute("urlPrefix");
			return urlPrefix + path;
		}
		return null;
	}

}