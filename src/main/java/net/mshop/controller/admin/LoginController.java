package net.mshop.controller.admin;

import net.mshop.entity.Admin;
import net.mshop.entity.Message;
import net.mshop.entity.Setting;
import net.mshop.service.AdminService;
import net.mshop.service.RSAService;
import net.mshop.util.SystemUtils;
import net.mshop.util.WebUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

/**
 * 管理员登录Controller
 * Created by Panfuhao on 2016/10/8.
 */
@Controller("adminLoginController")
@RequestMapping("/admin/login")
public class LoginController extends BaseController {
    @Resource(name = "adminServiceImpl")
    private AdminService adminService;
    @Resource(name = "rsaServiceImpl")
    private RSAService rsaService;

    /**
     * 登录
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping
    public String index(HttpServletRequest request, ModelMap model) {
        String loginToken = WebUtils.getCookie(request, Admin.LOGIN_TOKEN_COOKIE_NAME);
        if (!StringUtils.equalsIgnoreCase(loginToken, adminService.getLoginToken())) {
            return "redirect:/";
        }
        if (adminService.isAuthenticated()) {
            return "redirect:common/main.html";
        }
        Message message = null;
        String loginFailure = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        if (StringUtils.isNotEmpty(loginFailure)) {
            if (loginFailure.equals("net.mshop.exception.IncorrectCaptchaException")) {
                message = Message.error("验证码输入错误");
            } else if (loginFailure.equals("org.apache.shiro.authc.UnknownAccountException")) {
                message = Message.error("帐号不存在");
            } else if (loginFailure.equals("org.apache.shiro.authc.DisabledAccountException")) {
                message = Message.error("该帐号已被禁用");
            } else if (loginFailure.equals("org.apache.shiro.authc.LockedAccountException")) {
                message = Message.error("该账户已被锁定");
            } else if (loginFailure.equals("org.apache.shiro.authc.IncorrectCredentialsException")) {
                Setting setting = SystemUtils.getSetting();
                if (ArrayUtils.contains(setting.getAccountLockTypes(), Setting.AccountLockType.admin)) {
                    message = Message.error("密码错误，若连续" + setting.getFailureLoginCount() + "次密码错误账号将被锁定");
                } else {
                    message = Message.error("用户名或密码错误");
                }
            } else if (loginFailure.equals("org.apache.shiro.authc.AuthenticationException")) {
                message = Message.error("账号认证失败");
            }
        }
        RSAPublicKey publicKey = rsaService.generateKey(request);
        model.addAttribute("modulus", Base64.encodeBase64String(publicKey.getModulus().toByteArray()));
        model.addAttribute("exponent", Base64.encodeBase64String(publicKey.getPublicExponent().toByteArray()));
        model.addAttribute("captchaId", UUID.randomUUID().toString());
        model.addAttribute("failureMessage",message);
        return "/admin/login/index";
    }


}
