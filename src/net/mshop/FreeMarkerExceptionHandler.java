package net.mshop;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.Writer;

/**
 * Created by Panfuhao on 2016/9/27.
 */
public class FreeMarkerExceptionHandler implements TemplateExceptionHandler{
    /**
     * 默认模版异常处理
     */
    private static final TemplateExceptionHandler DEFAULT_TEMPLATE_EXCEPTION_HANDLER=TemplateExceptionHandler.DEBUG_HANDLER;

    /**
     * 异常处理
     * @param e
     * @param environment
     * @param writer
     * @throws TemplateException
     */
    @Override
    public void handleTemplateException(TemplateException e, Environment environment, Writer writer) throws TemplateException {
       DEFAULT_TEMPLATE_EXCEPTION_HANDLER.handleTemplateException(e,environment,writer);
    }
}
