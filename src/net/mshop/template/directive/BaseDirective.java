package net.mshop.template.directive;

import freemarker.core.Environment;
import freemarker.template.*;
import net.mshop.util.FreeMarkerUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 指令 基础类
 * Created by Panfuhao on 2016/10/24.
 */
public abstract class BaseDirective implements TemplateDirectiveModel {
    /**
     * 是否使用缓存
     */
    private static final String USER_CACHE_PARAMETER_NAME = "useCache";
    /**
     * ID 参数名称
     */
    private static final String ID_PARAMETER_NAME = "id";
    /**
     * 数量 参数名称
     */
    private static final String COUNT_PARAMETER_NAME = "count";
    /**
     * 排序 参数名称
     */
    private static final String ORDER_BY_PARAMETER_NAME = "orderBy";
    /**
     * 排序项分隔符
     */
    private static final String ORDER_BY_ITEM_SEPARATOR = "\\s*,\\s*";
    /**
     *排序字段分隔符
     */
    private static final String ORDER_BY_FIELD_SEPARATOR = "\\s+";

    /**
     * 是否使用缓存
     * @param environment
     * @param params
     * @return
     * @throws TemplateModelException
     */
    protected boolean userCache(Environment environment, Map<String, TemplateModel> params) throws TemplateModelException {
        Boolean useCache = FreeMarkerUtils.getParameter(USER_CACHE_PARAMETER_NAME, Boolean.class, params);
        return useCache != null ? useCache : true;
    }

    /**
     * 获取ID
     * @param params
     * @return
     * @throws TemplateModelException
     */
    protected Long getId(Map<String, TemplateModel> params) throws TemplateModelException {
        return FreeMarkerUtils.getParameter(ID_PARAMETER_NAME, Long.class, params);
    }

    /**
     * 获取数量
     * @param params
     * @return
     * @throws TemplateModelException
     */
    protected Integer getCount(Map<String, TemplateModel> params) throws TemplateModelException {
        return FreeMarkerUtils.getParameter(COUNT_PARAMETER_NAME, Integer.class, params);
    }

    /*    protected List<Filter> getFilter(Map<String,TemplateModel> params,String... ignoreProperties) throws TemplateModelException{

        }
        protected List<Order> getOrders(Map<String,TemplateModel> params,String... ignoreProperties) throws TemplateModelException{

        }*/

    /**
     * 设置局部变量
     * @param name
     * @param value
     * @param environment
     * @param templateDirectiveBody
     * @throws TemplateException
     * @throws IOException
     */
    protected void setLocalVariable(String name, Object value, Environment environment, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        TemplateModel templateModel = FreeMarkerUtils.getVariable(name, environment);
        try {
            FreeMarkerUtils.setVariable(name, value, environment);
            templateDirectiveBody.render(environment.getOut());
        } finally {
            FreeMarkerUtils.setVariable(name, templateModel, environment);
        }
    }

    /**
     * 设置局部变量
     * @param variables
     * @param environment
     * @param templateDirectiveBody
     * @throws TemplateException
     * @throws IOException
     */
    protected void setLocalVariables(Map<String,Object> variables,Environment environment,TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        Map<String, Object> preVariables = new HashMap<>();
        for(String name:variables.keySet()){
            TemplateModel templateModel = FreeMarkerUtils.getVariable(name,environment);
            preVariables.put(name,templateModel);
        }
        try{
            FreeMarkerUtils.setVariables(variables,environment);
            templateDirectiveBody.render(environment.getOut());
        }finally {
            FreeMarkerUtils.setVariables(preVariables,environment);
        }
    }
}
