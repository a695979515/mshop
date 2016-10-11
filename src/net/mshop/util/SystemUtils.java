package net.mshop.util;

import net.mshop.EnumConverter;
import net.mshop.entity.Setting;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.ArrayConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.shiro.util.Assert;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/**
 * 系统工具类
 * Created by Panfuhao on 2016/10/11.
 */
public final class SystemUtils {
    private static final CacheManager CACHE_MANAGER = CacheManager.create();

    private static final BeanUtilsBean BEAN_UTILS;

    static {
        ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean() {
            @Override
            public String convert(Object value) {
                if (value != null) {
                    Class<?> type = value.getClass();
                    if (type.isEnum() && super.lookup(type) != null) {
                        super.register(new EnumConverter(type), type);
                    } else if (type.isArray() && type.getComponentType().isEnum()) {
                        if (super.lookup(type) != null) {
                            ArrayConverter arrayConverter = new ArrayConverter(type, new EnumConverter(type.getComponentType()), 0);
                            arrayConverter.setOnlyFirstToString(false);
                            super.register(arrayConverter, type);
                        }
                        Converter converter = super.lookup(type);
                        return (converter.convert(String.class, value));
                    }
                }
                return super.convert(value);
            }

            @Override
            public Object convert(String value, Class<?> clazz) {
                if (clazz.isEnum() && super.lookup(clazz) == null) {
                    super.register(new EnumConverter(clazz), clazz);
                }
                return super.convert(value, clazz);
            }

            @Override
            public Object convert(Object value, Class<?> targetType) {
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

            @Override
            public Object convert(String[] values, Class<?> clazz) {
                if (clazz.isArray() && clazz.getComponentType().isEnum() && super.lookup(clazz.getComponentType()) == null) {
                    super.register(new EnumConverter(clazz.getComponentType()), clazz.getComponentType());
                }
                return super.convert(values, clazz);
            }
        };
        DateConverter dataConverter = new DateConverter();
        dataConverter.setPatterns(CommonAttributes.DATE_PATTERNS);
        convertUtilsBean.register(dataConverter, Date.class);
        BEAN_UTILS = new BeanUtilsBean(convertUtilsBean);
    }

    private SystemUtils() {

    }

    /**
     * 获取系统设置
     *
     * @return
     */
    public static Setting getSetting() {
        Ehcache cache = CACHE_MANAGER.getEhcache(Setting.CACHE_NAME);
        String cacheKey = "setting";
        net.sf.ehcache.Element cacheElement = cache.get(cacheKey);
        if (cacheElement == null) {
            Setting setting = new Setting();
            try {
                File xmlFile = new ClassPathResource(CommonAttributes.MSHOP_XML_PATH).getFile();
                Document document = new SAXReader().read(xmlFile);
                List<Element> elements = document.selectNodes("/mshop/setting");
                for (Element element : elements) {
                    try {
                        String name = element.attributeValue("name");
                        String value = element.attributeValue("value");
                        BEAN_UTILS.setProperty(setting, name, value);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e.getMessage(), e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e.getMessage(), e);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            } catch (DocumentException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
            cache.put(new net.sf.ehcache.Element(cacheKey, setting));
            cacheElement = cache.get(cacheKey);
        }
        return (Setting) cacheElement.getObjectKey();
    }

    /**
     * 设置系统配置
     * @param setting
     */
    public static void setSetting(Setting setting) {
        Assert.notNull(setting);
        try {
            File xmlFile = new ClassPathResource(CommonAttributes.MSHOP_XML_PATH).getFile();
            Document document = new SAXReader().read(xmlFile);
            List<Element> elements = document.selectNodes("/mshop/setting");
            for (Element element : elements) {
                try {
                    String name = element.attributeValue("name");
                    String value = BEAN_UTILS.getProperty(setting, name);
                    Attribute attribute = element.attribute("value");
                    attribute.setValue(value);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e.getMessage(), e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e.getMessage(), e);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
            XMLWriter xmlWriter = null;
            try {
            OutputFormat outputFormat = OutputFormat.createPrettyPrint();
            outputFormat.setEncoding("UTF-8");
            outputFormat.setIndent(true);
            outputFormat.setIndent("	");
            outputFormat.setNewlines(true);
            xmlWriter = new XMLWriter(new FileOutputStream(xmlFile),outputFormat);
            xmlWriter.write(document);
            xmlWriter.flush();
            }catch (FileNotFoundException e){
                throw new RuntimeException(e.getMessage(), e);
            }catch (UnsupportedEncodingException e){
                throw new RuntimeException(e.getMessage(), e);
            }catch (IOException e){
                throw new RuntimeException(e.getMessage(), e);
            }finally {
                try{
                    if(xmlWriter !=null){
                        xmlWriter.close();
                    }
                }catch (IOException e){

                }
            }
            Ehcache cache = CACHE_MANAGER.getEhcache(Setting.CACHE_NAME);
            String cacheKey = "setting";
            cache.put(new net.sf.ehcache.Element(cacheKey,setting));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (DocumentException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
