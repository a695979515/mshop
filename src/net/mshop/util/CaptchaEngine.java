package net.mshop.util;

import com.octo.captcha.CaptchaFactory;
import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.FunkyBackgroundGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.engine.image.ImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.awt.*;

/**
 * Created by Panfuhao on 2016/10/18.
 */
@Component("captchaEngine")
public class CaptchaEngine extends ImageCaptchaEngine implements ServletContextAware ,InitializingBean{
    private ServletContext servletContext;
    /**
     * 图片宽度
     */
    @Value("80")
    private int imageWidth;
    /**
     * 图片高度
     */
    @Value("28")
    private int imageHeight;
    /**
     * 最小字体
     */
    @Value("12")
    private int minFontSize;
    /**
     * 最大字体
     */
    @Value("16")
    private int maxFontSize;
    /**
     * 最小字符长度
     */
    @Value("4")
    private int minWordLength;
    /**
     * 最大字符长度
     */
    @Value("4")
    private int maxWordLength;
    /**
     * 随机字符串
     */
    @Value("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890")
    private String charString;


    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.state(imageHeight>0);
        Assert.state(imageWidth>0);
        Assert.state(minFontSize>0);
        Assert.state(maxFontSize>0);
        Assert.state(minWordLength>0);
        Assert.state(maxWordLength>0);
        Assert.hasText(charString);
        Font[] fonts = new Font[]{new Font("Arial",Font.BOLD,maxFontSize),new Font("Bell",Font.BOLD,maxFontSize),new Font("Credit",Font.BOLD,maxFontSize),new Font("Impact",Font.BOLD,maxFontSize) };
        FontGenerator fontGenerator = new RandomFontGenerator(minFontSize,maxFontSize,fonts);
        BackgroundGenerator backgroundGenerator = new FunkyBackgroundGenerator(imageWidth, imageHeight);
        TextPaster textPaster = new RandomTextPaster(minWordLength,maxWordLength,Color.white);
        CaptchaFactory[] captchaFactories = new CaptchaFactory[]{new GimpyFactory(new RandomWordGenerator(charString),new ComposedWordToImage(fontGenerator,backgroundGenerator,textPaster))};
        super.setFactories(captchaFactories);
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
