package net.mshop.template.directive;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import net.mshop.entity.Message;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * 消息指令
 * Created by Panfuhao on 2016/10/24.
 */
@Component("messageDirective")
public class MessageDirective extends BaseDirective {
    /**
     * 消息 属性名称
     */
    public static final String MESSAGE_ATTRIBUTE_NAME= MessageDirective.class.getName()+".FLASH_MESSAGE";
    /**
     * 变量名称
     */
    private static final String VARIABLE_NAME="flashMessage";

    /**
     * 执行方法
     * @param environment
     * @param map
     * @param templateModels
     * @param templateDirectiveBody
     * @throws TemplateException
     * @throws IOException
     */
    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        if(requestAttributes!=null){
            Message message = (Message) requestAttributes.getAttribute(MESSAGE_ATTRIBUTE_NAME,RequestAttributes.SCOPE_REQUEST);
            if(templateDirectiveBody!=null){
                setLocalVariable(VARIABLE_NAME,message,environment,templateDirectiveBody);
            }else{
                if(message!=null){
                    Writer out = environment.getOut();
                    out.write("$.message(\""+message.getType()+"\",\""+message.getContent()+"\");");
                }
            }
        }
    }
}
