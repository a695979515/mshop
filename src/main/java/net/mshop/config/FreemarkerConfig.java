package net.mshop.config;

import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
import net.mshop.template.directive.FlashMessageDirective;
import net.mshop.template.directive.PaginationDirective;
import net.mshop.template.method.AbbreviateMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

@Component
public class FreemarkerConfig {

    @Autowired
    private Configuration configuration;
    @Autowired
    private AbbreviateMethod abbreviateMethod;
    @Autowired
    private FlashMessageDirective flashMessageDirective;
    @Autowired
    private PaginationDirective paginationDirective;
    @Autowired
    private ServletContext servletContext;

    @PostConstruct
    public void setSharedVariable(){
        try {
            configuration.setSharedVariable("base",servletContext.getContextPath());//缩写 标签名与标签类
        } catch (TemplateModelException e) {
            e.printStackTrace();
        }
        configuration.setSharedVariable("abbreviate",abbreviateMethod);//缩写 标签名与标签类
        configuration.setSharedVariable("flash_message",flashMessageDirective);//消息指令 标签名与标签类
        configuration.setSharedVariable("pagination",paginationDirective);//分页指令 标签名与标签类

    }
}
