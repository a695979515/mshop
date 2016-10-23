package net.mshop.controller.admin;

import net.mshop.entity.Setting;
import net.mshop.util.SystemUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static net.mshop.controller.admin.BaseController.ERROR_VIEW;

/**
 * Created by Panfuhao on 2016/10/21.
 */
@Controller("settingController")
@RequestMapping("/admin/setting")
public class SettingController extends BaseController{

    @RequestMapping(value="/edit",method = RequestMethod.GET)
    public String edit(ModelMap model){
        model.addAttribute("setting", SystemUtils.getSetting());
        model.addAttribute("watermarkPositions", Setting.WatermarkPosition.values());
        model.addAttribute("roundTypes", Setting.RoundType.values());
        model.addAttribute("captchaTypes", Setting.CaptchaType.values());
        model.addAttribute("accountLockTypes", Setting.AccountLockType.values());
        model.addAttribute("stockAllocationTimes", Setting.StockAllocationTime.values());
        return "/admin/setting/edit";
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(Setting setting , RedirectAttributes redirectAttributes){
        if (!isValid(setting)) {
            System.out.println("错误");
            return ERROR_VIEW;
        }
        Setting srcSetting = SystemUtils.getSetting();
        if(StringUtils.isEmpty(setting.getSmtpPassword())){
            setting.setSmtpPassword(srcSetting.getSmtpPassword());
        }
        setting.setWatermarkImage(srcSetting.getWatermarkImage());
        setting.setCnzzEnabled(srcSetting.getCnzzEnabled());
        setting.setCnzzSiteId(srcSetting.getCnzzSiteId());
        setting.setCnzzPassword(srcSetting.getCnzzPassword());
        System.out.println("44444");
        SystemUtils.setSetting(setting);
        System.out.println("55555");
        return "redirect:edit.html";
    }
}
