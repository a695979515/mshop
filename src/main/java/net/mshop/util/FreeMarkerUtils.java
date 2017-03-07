package net.mshop.util;

import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.*;
import freemarker.template.utility.DeepUnwrap;
import net.mshop.EnumConverter;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.ArrayConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Panfuhao on 2016/9/26.
 */
@SuppressWarnings("unchecked")
public final class FreeMarkerUtils {
    private static final ConvertUtilsBean CONVERT_UTILS_BEAN;

    private static final Configuration DEFAULT_CONFIGURATION = new Configuration(Configuration.VERSION_2_3_23);
    private static final BeansWrapper DEFAULT_BEANS_WRAPPER = new BeansWrapperBuilder(Configuration.VERSION_2_3_23).build();

    static {
        CONVERT_UTILS_BEAN = new ConvertUtilsBean() {
            @Override
            public String convert(Object value) {
                if (value != null) {
                    Class<?> type = value.getClass();
                    if (type.isEnum() && super.lookup(type) == null) {
                        super.register(new EnumConverter(type), type);
                    } else if (type.isArray() && type.getComponentType().isEnum()) {
                        if (super.lookup(type) == null) {
                            ArrayConverter arrayConverter = new ArrayConverter(type, new EnumConverter(type.getComponentType()), 0);
                            arrayConverter.setOnlyFirstToString(false);
                            super.register(arrayConverter, type);
                        }
                        Converter converter = super.lookup(type);
                        return ((String) converter.convert(String.class, value));
                    }
                }
                return super.convert(value);
            }

            @SuppressWarnings("rawtypes")
            @Override
            public Object convert(String value, Class clazz) {
                if (clazz.isEnum() && super.lookup(clazz) == null) {
                    super.register(new EnumConverter(clazz), clazz);
                }
                return super.convert(value, clazz);
            }

            @SuppressWarnings("rawtypes")
            @Override
            public Object convert(String[] values, Class clazz) {
                if (clazz.isArray() && clazz.getComponentType().isEnum() && super.lookup(clazz.getComponentType()) == null) {
                    super.register(new EnumConverter(clazz.getComponentType()), clazz.getComponentType());
                }
                return super.convert(values, clazz);
            }

            @SuppressWarnings("rawtypes")
            @Override
            public Object convert(Object value, Class targetType) {
                if (super.lookup(targetType) == null) {
                    if (targetType.isEnum()) {
                        super.register(new EnumConverter(targetType), targetType);
                    } else if (targetType.isArray() && targetType.getComponentType().isEnum()) {
                        ArrayConverter arrayConverter = new ArrayConverter(targetType, new EnumConverter(targetType.getComponentType()), 0);
                        arrayConverter.setOnlyFirstToString(false);
                        super.register(arrayConverter, targetType);
                    }
                }
                return super.convert(value, targetType);
            }
        };
        DateConverter dateConverter = new DateConverter();
        dateConverter.setPatterns(CommonAttributes.DATE_PATTERNS);
        CONVERT_UTILS_BEAN.register(dateConverter, Date.class);
    }

    /**
     * 不可实例化
     */
    private FreeMarkerUtils() {
    }

    /**
     * 解析字符串模板
     *
     * @param template 字符串模板
     * @return 解析后内容
     */
    public static String process(String template) throws IOException, TemplateException {
        return process(template, null);
    }

    /**
     * 解析字符串模板
     *
     * @param template 字符串模板
     * @param model    数据
     * @return 解析后内容
     */
    public static String process(String template, Map<String, ?> model) throws IOException, TemplateException {
        Configuration configuration = null;
        ApplicationContext applicationContext = SpringUtils.getApplicationContext();
        if (applicationContext != null) {
            FreeMarkerConfigurer freeMarkerConfigurer = SpringUtils.getBean("freeMarkerConfigurer", FreeMarkerConfigurer.class);
            if (freeMarkerConfigurer != null) {
                configuration = freeMarkerConfigurer.getConfiguration();
            }
        }
        return process(template, model, configuration);
    }

    /**
     * 解析字符串模板
     *
     * @param template      字符串模板
     * @param model         数据
     * @param configuration 配置
     * @return 解析后内容
     */
    public static String process(String template, Map<String, ?> model, Configuration configuration) throws IOException, TemplateException {
        if (template == null) {
            return null;
        }
        StringWriter out = new StringWriter();
        new Template("template", new StringReader(template), configuration != null ? configuration : DEFAULT_CONFIGURATION).process(model, out);
        return out.toString();
    }

    /**
     * 获取参数
     *
     * @param name   名称
     * @param type   类型
     * @param params 参数
     * @return 参数，若不存在则返回null
     */
    public static <T> T getParameter(String name, Class<T> type, Map<String, TemplateModel> params) throws TemplateModelException {
        Assert.hasText(name);
        Assert.notNull(type);
        Assert.notNull(params);

        TemplateModel templateModel = params.get(name);
        if (templateModel != null) {
            Object value = DeepUnwrap.unwrap(templateModel);
            if (value != null) {
                return (T) CONVERT_UTILS_BEAN.convert(value, type);
            }
        }
        return null;
    }

    /**
     * 获取参数
     *
     * @param index     索引
     * @param type      类型
     * @param arguments 参数
     * @return 参数，若不存在则返回null
     */
    public static <T> T getArgument(int index, Class<T> type, List<?> arguments) throws TemplateModelException {
        Assert.notNull(type);
        Assert.notNull(arguments);

        if (index >= 0 && index < arguments.size()) {
            Object argument = arguments.get(index);
            Object value;
            if (argument != null) {
                if (argument instanceof TemplateModel) {
                    value = DeepUnwrap.unwrap((TemplateModel) argument);
                } else {
                    value = argument;
                }
                if (value != null) {
                    return (T) CONVERT_UTILS_BEAN.convert(value, type);
                }
            }
        }
        return null;
    }

    /**
     * 获取变量
     *
     * @param name 名称
     * @param env  环境变量
     * @return 变量
     */
    public static TemplateModel getVariable(String name, Environment env) throws TemplateModelException {
        Assert.hasText(name);
        Assert.notNull(env);

        return env.getVariable(name);
    }

    /**
     * 设置变量
     *
     * @param name  名称
     * @param value 变量值
     * @param env   环境变量
     */
    public static void setVariable(String name, Object value, Environment env) throws TemplateException {
        Assert.hasText(name);
        Assert.notNull(env);

        if (value instanceof TemplateModel) {
            env.setVariable(name, (TemplateModel) value);
        } else {
            env.setVariable(name, DEFAULT_BEANS_WRAPPER.wrap(value));
        }
    }

    /**
     * 设置变量
     *
     * @param variables 变量
     * @param env       环境变量
     */
    public static void setVariables(Map<String, Object> variables, Environment env) throws TemplateException {
        Assert.notNull(variables);
        Assert.notNull(env);

        for (Map.Entry<String, Object> entry : variables.entrySet()) {
            String name = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof TemplateModel) {
                env.setVariable(name, (TemplateModel) value);
            } else {
                env.setVariable(name, DEFAULT_BEANS_WRAPPER.wrap(value));
            }
        }
    }
}
