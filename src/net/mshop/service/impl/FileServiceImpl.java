package net.mshop.service.impl;

import net.mshop.entity.FileType;
import net.mshop.service.FileService;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

/**
 * Service - 文件
 */
@Service("fileServiceImpl")
public class FileServiceImpl implements FileService, ServletContextAware {

	/** ServletContext */
	private ServletContext servletContext;

	@Resource(name = "taskExecutor")
	private TaskExecutor taskExecutor;


	@Override
	public boolean isValid(FileType fileType, MultipartFile multipartFile) {
		return false;
	}

	@Override
	public String upload(FileType fileType, MultipartFile multipartFile, boolean async) {
		return null;
	}

	@Override
	public String upload(FileType fileType, MultipartFile multipartFile) {
		return null;
	}

	@Override
	public String uploadLocal(FileType fileType, MultipartFile multipartFile) {
		return null;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {

	}
}