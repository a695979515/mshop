package net.mshop.service;

import net.mshop.entity.Setting;

import java.awt.image.BufferedImage;

/**
 * Created by Panfuhao on 2016/10/18.
 */
public interface CaptchaService {
    /**
     * 生成验证码图片
     *
     * @param captchaId
     * @return
     */
    BufferedImage buildImage(String captchaId);

    /**
     * 验证 验证码
     * @param captchaType
     * @param captchaId
     * @param captcha
     * @return
     */
    boolean isValid(Setting.CaptchaType captchaType, String captchaId, String captcha);
}
