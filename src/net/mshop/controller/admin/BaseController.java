package net.mshop.controller.admin;


import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import net.mshop.util.DateEditor;
import net.mshop.util.StringEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * 基础Controller
 * Created by Panfuhao on 2016/9/23.
 */
public class BaseController {
    /**
     * 错误视图
     */
    protected static final String ERROR_VIEW="/admin/common/error";
    /**
     * "验证结果"属性名称
     */
    private static final String CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME = "constraintViolations";

    @Resource(name = "validator")
    private Validator validator;

    @InitBinder
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        webDataBinder.registerCustomEditor(Date.class, new DateEditor(true));
        webDataBinder.registerCustomEditor(String.class, "password", new StringEditor(true));
    }

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

    protected boolean isValid(Collection<Object> targets, Class<?>... groups) {
        Assert.notEmpty(targets);
        for (Object target : targets) {
            if (!isValid(target, groups)) {
                return false;
            }
        }
        return true;
    }

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

    protected boolean isValid(Class<?> type, Map<String, Object> properties, Class<?>... groups) {
        Assert.notNull(type);
        Assert.notEmpty(properties);
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            if (!isValid(type, entry.getKey(), entry.getValue(), groups)) {
                return false;
            }
        }
        return true;
    }

    protected String currency(BigDecimal amount, boolean showSign, boolean showUnit) {
        return null;
    }
}