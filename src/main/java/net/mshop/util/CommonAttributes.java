package net.mshop.util;

/**
 * Created by Panfuhao on 2016/9/23.
 */
public final class CommonAttributes {
    /**
     * 日期格式匹配
     */
    public static final String[] DATE_PATTERNS = new String[]{"yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss"};
    /**
     * mshop.xml 路径
     */
    public static final String MSHOP_XML_PATH = "/mshop.xml";
    /**
     * mshop.properties 路径
     */
    public static final String MSHOP_PROPERTIES_PATH = "/mshop.properties";

    /**
     * 不可实例化
     */
    private CommonAttributes() {
    }
}
