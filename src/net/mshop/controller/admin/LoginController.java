package net.mshop.controller.admin;

import net.mshop.entity.Admin;
import net.mshop.service.AdminService;
import net.mshop.util.WebUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Panfuhao on 2016/10/8.
 */
@Controller("adminLoginController")
@RequestMapping("/admin/login")
public class LoginController extends BaseController{
    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    @RequestMapping
    public String index(HttpServletRequest request, ModelMap model){
        String loginToken = WebUtils.getCookie(request, Admin.LOING_TOKEN_COOKIE_NAME);
        /*if(!StringUtils.equalsIgnoreCase(loginToken,adminService.getLoginToken())){
            return "redirect:/";
        }*/

        if(adminService.isAuthenticated()){
            return "redirect:common/main";
        }
        String loginFailure = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        if(StringUtils.isNotEmpty(loginFailure)){

        }
        return "/admin/login/index";
    }

}
