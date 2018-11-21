//package net.mshop.template.method;
//
//import freemarker.template.SimpleScalar;
//import freemarker.template.TemplateMethodModelEx;
//import freemarker.template.TemplateModel;
//import freemarker.template.TemplateModelException;
//import freemarker.template.utility.DeepUnwrap;
//import net.mshop.util.FreeMarkerUtils;
//import net.mshop.util.SpringUtils;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///**
// * 语言资源文件
// * Created by Panfuhao on 2016/10/24.
// */
//@Component("messageMethod")
//public class MessageMethod implements TemplateMethodModelEx{
//    @Override
//    public Object exec(List list) throws TemplateModelException {
//        String code = FreeMarkerUtils.getArgument(0,String.class,list);
//        if(StringUtils.isNotEmpty(code)){
//            String message;
//            if(list.size()>1){
//                Object[] objects = new Object[list.size()-1];
//                for(int i = 1;i<list.size();i++){
//                    Object object = list.get(i);
//                    if(object!=null && object instanceof TemplateModel){
//                        objects[i-1] = DeepUnwrap.unwrap((TemplateModel) object);
//                    }else{
//                        objects[i-1]=objects;
//                    }
//                }
//                message = SpringUtils.getMessage(code,objects);
//            }else{
//                message = SpringUtils.getMessage(code);
//            }
//            return new SimpleScalar(message);
//        }
//        return null;
//    }
//}
