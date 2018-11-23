package net.mshop.config;

import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaptchaConfig {

    @Bean
    public DefaultManageableImageCaptchaService imageCaptchaService(){
        DefaultManageableImageCaptchaService imageCaptchaService = new DefaultManageableImageCaptchaService();
        imageCaptchaService.setCaptchaEngine(new net.mshop.util.CaptchaEngine());
        imageCaptchaService.setMinGuarantedStorageDelayInSeconds(600);
        return imageCaptchaService;
    }
}
