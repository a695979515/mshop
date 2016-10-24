package net.mshop.controller.admin;

import net.mshop.entity.Setting;
import net.mshop.util.SystemUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Panfuhao on 2016/10/21.
 */
@Controller("settingController")
@RequestMapping("/admin/setting")
public class SettingController extends BaseController {

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(ModelMap model) {
        model.addAttribute("setting", SystemUtils.getSetting());
        model.addAttribute("watermarkPositions", Setting.WatermarkPosition.values());
        model.addAttribute("roundTypes", Setting.RoundType.values());
        model.addAttribute("captchaTypes", Setting.CaptchaType.values());
        model.addAttribute("accountLockTypes", Setting.AccountLockType.values());
        model.addAttribute("stockAllocationTimes", Setting.StockAllocationTime.values());
        return "/admin/setting/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Setting setting) {
        setting.setSmtpSSLEnabled(setting.getSmtpSSLEnabled() == null ? false : setting.getSmtpSSLEnabled());
        setting.setInvoiceEnabled(setting.getInvoiceEnabled() == null ? false : setting.getInvoiceEnabled());
        setting.setTaxPriceEnabled(setting.getTaxPriceEnabled() == null ? false : setting.getTaxPriceEnabled());
        setting.setShowMarketPrice(setting.getShowMarketPrice() == null ? false : setting.getShowMarketPrice());
        setting.setSiteEnabled(setting.getSiteEnabled() == null ? false : setting.getSiteEnabled());
        setting.setRegisterEnabled(setting.getRegisterEnabled() == null ? false : setting.getRegisterEnabled());
        setting.setReviewEnabled(setting.getReviewEnabled() == null ? false : setting.getReviewEnabled());
        setting.setReviewCheck(setting.getReviewCheck() == null ? false : setting.getReviewCheck());
        if (!isValid(setting)) {
            return ERROR_VIEW;
        }
        Setting srcSetting = SystemUtils.getSetting();
        if (StringUtils.isEmpty(setting.getSmtpPassword())) {
            setting.setSmtpPassword(srcSetting.getSmtpPassword());
        }
        setting.setCnzzEnabled(srcSetting.getCnzzEnabled());
        setting.setCnzzSiteId(srcSetting.getCnzzSiteId());
        setting.setCnzzPassword(srcSetting.getCnzzPassword());
        SystemUtils.setSetting(setting);
        return "redirect:edit.html";
    }
}
