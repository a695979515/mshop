package net.mshop.plugin.localStorage;

import net.mshop.controller.admin.BaseController;
import net.mshop.entity.PluginConfig;
import net.mshop.service.PluginConfigService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * Controller - 本地文件存储
 *
 */
@Controller("adminLocalStorageController")
@RequestMapping("/admin/storage_plugin/local_storage")
public class LocalStorageController extends BaseController {

	@Resource(name = "localStoragePlugin")
	private LocalStoragePlugin localStoragePlugin;
	@Resource(name = "pluginConfigServiceImpl")
	private PluginConfigService pluginConfigService;

	/**
	 * 设置
	 */
	@RequestMapping(value = "/setting", method = RequestMethod.GET)
	public String setting(ModelMap model) {
		PluginConfig pluginConfig = localStoragePlugin.getPluginConfig();
		model.addAttribute("pluginConfig", pluginConfig);
		return "/net/shopxx/plugin/localStorage/setting";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Integer order, RedirectAttributes redirectAttributes) {
		PluginConfig pluginConfig = localStoragePlugin.getPluginConfig();
		pluginConfig.setIsEnabled(true);
		pluginConfig.setOrder(order);
		pluginConfigService.update(pluginConfig);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:/admin/storage_plugin/list.jhtml";
	}

}