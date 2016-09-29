package net.mshop;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by Panfuhao on 2016/9/29.
 */
public class AuthenticationToken extends UsernamePasswordToken {

    private String captchaId;
    private String captcha;

    public AuthenticationToken(String username, String password, String captchaId, String captcha, boolean rememberMe, String host) {
        super(username, password, rememberMe, host);
        this.captchaId = captchaId;
        this.captcha = captcha;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }
}
