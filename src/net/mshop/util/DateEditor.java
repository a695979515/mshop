package net.mshop.util;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;

/**
 * Created by Panfuhao on 2016/9/23.
 */
public class DateEditor extends PropertyEditorSupport{
    /**
     * 默认时间格式
     */
    private static final String DEFAULT_DATE_FORMAT="yyyy-MM-dd HH:mm:ss";
    /**
     * 是否讲空的转换为null
     */
    private boolean emptyAsNull;
    /**
     * 日期格式
     */
    private String dateFormat = DEFAULT_DATE_FORMAT;

    public DateEditor(boolean emptyAsNull){
        this.emptyAsNull = emptyAsNull;
    }
    public DateEditor(boolean emptyAsNull ,String dateFormat){
        this.emptyAsNull = emptyAsNull;
        this.dateFormat = dateFormat;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(text !=null){
            String value = text.trim();
            if(emptyAsNull && StringUtils.isEmpty(text)){
                setValue(null);
            }else {
                try {
                    setValue(DateUtils.parseDate(value,CommonAttrbutes.DATE_PATTERNS));
                } catch (ParseException e) {
                    setValue(null);
                }
            }
        }else{
            setValue(null);
        }
    }
}
