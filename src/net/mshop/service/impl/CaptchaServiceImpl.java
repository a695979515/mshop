package net.mshop.service.impl;

import com.octo.captcha.service.CaptchaServiceException;
import net.mshop.entity.Setting;
import net.mshop.service.CaptchaService;
import net.mshop.util.SystemUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;

/**
 * Created by Panfuhao on 2016/10/18.
 */
@Service("captchaServiceImpl")
public class CaptchaServiceImpl implements CaptchaService {

    @Resource(name = "imageCaptchaService")
    private com.octo.captcha.service.CaptchaService imageCaptchaService;

    @Override
    public BufferedImage buildImage(String captchaId) {
        Assert.hasText(captchaId);
        return (BufferedImage) imageCaptchaService.getChallengeForID(captchaId);
    }

    @Override
    public boolean isValid(Setting.CaptchaType captchaType, String captchaId, String captcha) {
        Assert.notNull(captchaType);
        Setting setting = SystemUtils.getSetting();
        if (!ArrayUtils.contains(setting.getCaptchaTypes(), captchaType)) {
            return true;
        }
        if (StringUtils.isEmpty(captchaId) || StringUtils.isEmpty(captcha)) {
            return false;
        }
        try {
            return imageCaptchaService.validateResponseForID(captchaId, captcha.toUpperCase());
        } catch (CaptchaServiceException e) {
            return false;
        }
    }
}
