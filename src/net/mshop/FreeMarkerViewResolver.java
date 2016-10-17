package net.mshop;

import net.mshop.util.FreeMarkerUtils;
import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

/**
 * Created by Panfuhao on 2016/9/26.
 */
public class FreeMarkerViewResolver extends AbstractTemplateViewResolver {
    /**
     *
     */
    public FreeMarkerViewResolver() {
        setViewClass(requiredViewClass());
    }

    @Override
    protected Class<FreeMarkerView> requiredViewClass() {
        return FreeMarkerView.class;
    }

    @Override
    protected AbstractUrlBasedView buildView(String viewName) throws Exception {
        return super.buildView(FreeMarkerUtils.process(viewName));
    }
}
