package net.mshop.plugin.localStorage;

import net.mshop.entity.Setting;
import net.mshop.plugin.StoragePlugin;
import net.mshop.util.SystemUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

/**
 * Plugin - 本地文件存储
 *
 */
@Component("localStoragePlugin")
public class LocalStoragePlugin extends StoragePlugin implements ServletContextAware {

	/** ServletContext */
	private ServletContext servletContext;

	/**
	 * 设置ServletContext
	 * 
	 * @param servletContext
	 *            ServletContext
	 */
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public String getName() {
		return "本地文件存储";
	}

	@Override
	public String getSiteUrl() {
		return "http://www.mshop.net";
	}

	@Override
	public String getInstallUrl() {
		return null;
	}

	@Override
	public String getUninstallUrl() {
		return null;
	}

	@Override
	public String getSettingUrl() {
		return "local_storage/setting.jhtml";
	}

	@Override
	public void upload(String path, File file, String contentType) {
		File destFile = new File(servletContext.getRealPath(path));
		try {
			FileUtils.moveFile(file, destFile);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Override
	public String getUrl(String path) {
		Setting setting = SystemUtils.getSetting();
		return setting.getSiteUrl() + path;
	}

}