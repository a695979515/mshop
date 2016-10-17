package net.mshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Panfuhao on 2016/10/17.
 */
@Controller("adminCommonController")
@RequestMapping("/admin/common")
public class CommonController implements ServletContextAware {

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main() {
        return "/admin/common/main";
    }

    /**
     * 权限错误
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/unauthorized")
    public String unauthorizedUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestType = request.getHeader("X-Requested-With");
        if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
            response.addHeader("loginStatus", "unauthorized");
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
        return "/admin/common/unauthorized";
    }
}
