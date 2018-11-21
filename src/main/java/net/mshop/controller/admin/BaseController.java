package net.mshop.controller.admin;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import net.mshop.entity.Message;
import net.mshop.entity.Setting;
import net.mshop.template.directive.FlashMessageDirective;
import net.mshop.util.DateEditor;
import net.mshop.util.SpringUtils;
import net.mshop.util.StringEditor;
import net.mshop.util.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 基础Controller
 * Created by Panfuhao on 2016/9/23.
 */
public class BaseController {
    /**
     * 错误视图
     */
    protected static final String ERROR_VIEW = "/admin/common/error";
    /**
     * 错误消息
     */
    protected static final Message ERROR_MESSAGE = Message.error("操作失败");

    /**
     * 成功消息
     */
    protected static final Message SUCCESS_MESSAGE = Message.success("操作成功");
    /**
     * "验证结果"属性名称
     */
    private static final String CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME = "constraintViolations";

    @Autowired
    private Validator validator;

    @InitBinder
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        webDataBinder.registerCustomEditor(Date.class, new DateEditor(true));
        webDataBinder.registerCustomEditor(String.class, "password", new StringEditor(true));
    }


    /**
     * 数据验证
     *
     * @param target 验证对象
     * @param groups 验证组
     * @return 验证结果
     */
    protected boolean isValid(Object target, Class<?>... groups) {
        Assert.notNull(target);
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(target, groups);
        if (constraintViolations.isEmpty()) {
            return true;
        }
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        requestAttributes.setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations, RequestAttributes.SCOPE_REQUEST);
        return false;
    }

    /**
     * 数据验证
     *
     * @param targets 验证对象
     * @param groups  验证组
     * @return 验证结果
     */
    protected boolean isValid(Collection<Object> targets, Class<?>... groups) {
        Assert.notEmpty(targets);

        for (Object target : targets) {
            if (!isValid(target, groups)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 数据验证
     *
     * @param type     类型
     * @param property 属性
     * @param value    值
     * @param groups   验证组
     * @return 验证结果
     */
    protected boolean isValid(Class<?> type, String property, Object value, Class<?>... groups) {
        Assert.notNull(type);
        Assert.hasText(property);

        Set<?> constraintViolations = validator.validateValue(type, property, value, groups);
        if (constraintViolations.isEmpty()) {
            return true;
        }
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        requestAttributes.setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations, RequestAttributes.SCOPE_REQUEST);
        return false;
    }

    /**
     * 获取国际化消息
     *
     * @param code 代码
     * @param args 参数
     * @return 国际化消息
     */
//    protected String message(String code, Object... args) {
//        return SpringUtils.getMessage(code, args);
//    }

    /**
     * 添加瞬时消息
     * @param redirectAttributes
     * @param message
     */
    protected void addFlashMessage(RedirectAttributes redirectAttributes,Message message){
        if(redirectAttributes!=null && message!=null){
            redirectAttributes.addFlashAttribute(FlashMessageDirective.MESSAGE_ATTRIBUTE_NAME,message);
        }
    }

    protected String currency(BigDecimal amount, boolean showSign, boolean showUnit) {
        Setting setting = SystemUtils.getSetting();
        String price = setting.setScale(amount).toString();
        if (showSign) {
            price = setting.getCurrencySign() + price;
        }
        if (showUnit) {
            price += setting.getCurrencyUnit();
        }
        return price;
    }

    /**
     * 加盐值
     * @param username
     * @param password
     * @param createDate
     * @return
     */
    public String addSalt(String username,String password,Date createDate){
        StringBuffer stringBuffer = new StringBuffer();
        char[] usernames = username.toCharArray();
        char[] passwords = password.toCharArray();
        for(int i=0;i<username.length();i++){
            stringBuffer.append(usernames[i]);
            for (int j=0;j<password.length();j++){
                stringBuffer.append(passwords[j]);
            }
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        stringBuffer.append(dateFormat.format(createDate).toString());
        return stringBuffer.toString();
    }

}