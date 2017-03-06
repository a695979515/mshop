package net.mshop.template.method;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import net.mshop.util.FreeMarkerUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

/**
 * 缩写
 * Created by Panfuhao on 2016/10/28.
 */
@Component("abbreviateMethod")
public class AbbreviateMethod implements TemplateMethodModelEx {
    private static final Pattern PATTERN = Pattern.compile("[\\u4e00-\\u9fa5\\ufe30-\\uffa0]");

    @Override
    public Object exec(List list) throws TemplateModelException {
        String str = FreeMarkerUtils.getArgument(0, String.class, list);
        Integer width = FreeMarkerUtils.getArgument(1, Integer.class, list);
        String ellipsis = FreeMarkerUtils.getArgument(2, String.class, list);
        if (StringUtils.isEmpty(str) || width == null) {
            return str;
        }
        int i = 0;
        for (int strWidth = 0; i < str.length(); i++) {
            strWidth = PATTERN.matcher(String.valueOf(str.charAt(i))).find() ? strWidth + 2 : strWidth + 1;
            if (strWidth == width) {
                break;
            } else if (strWidth > width) {
                i--;
                break;
            }
        }
        return ellipsis != null && i < str.length() - 1 ? StringUtils.substring(str, 0, i + 1) + ellipsis : StringUtils.substring(str, 0, i + 1);
    }
}
