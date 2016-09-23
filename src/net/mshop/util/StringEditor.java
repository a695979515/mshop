package net.mshop.util;


import org.apache.commons.lang.StringUtils;

import java.beans.PropertyEditorSupport;

/**
 * Created by Panfuhao on 2016/9/23.
 */
public class StringEditor extends PropertyEditorSupport {
    private boolean emptyAsNull;

    public StringEditor(boolean emptyAsNull) {
        this.emptyAsNull = emptyAsNull;
    }

    @Override
    public String getAsText() {
        Object value = getValue();
        return value != null ? value.toString() : StringUtils.EMPTY;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (emptyAsNull && StringUtils.isEmpty(text)) {
            setValue(null);
        } else {
            setValue(text);
        }
    }
}
