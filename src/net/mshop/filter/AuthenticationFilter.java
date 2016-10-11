package net.mshop.filter;

import net.mshop.entity.Admin;
import net.mshop.service.AdminService;
import net.mshop.service.RSAService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Panfuhao on 2016/10/9.
 */
public class AuthenticationFilter extends FormAuthenticationFilter{
    /**
     * 默认 加密密码参数
     */
    private static final String DEFAULT_EN_PASSWORD_PARAM = "enPassword";
    /**
     * 默认 验证码ID参数
     */
    private static final String DEFAULT_CAPTCHA_ID_PARAM = "captchaId";
    /**
     * 默认 验证码参数
     */
    private static final String DEFAULT_CAPTCHA_PARAM = "captcha";
    /**
     * 加密密码
     */
    private String enPasswordParam = DEFAULT_EN_PASSWORD_PARAM;
    /**
     * 验证码ID
     */
    private String captchaIdParam = DEFAULT_CAPTCHA_ID_PARAM;
    /**
     * 验证码
     */
    private String captchaParam = DEFAULT_CAPTCHA_PARAM;

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;
    @Resource(name = "rsaServiceImpl")
    private RSAService rsaService;

    /**
     * 创建令牌TOKEN
     * @param request
     * @param response
     * @return
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        String captchaId = getCaptchaId(request);
        String captcha = getCaptcha(request);
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        return new net.mshop.AuthenticationToken(username,password,captchaId,captcha,rememberMe,host);
    }



    /**
     * 拒绝访问处理
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if(StringUtils.equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With"),"XMLHttpRequest")){
            httpServletResponse.addHeader("loginStatus","accessDenied");
            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }
        String loginToken = net.mshop.util.WebUtils.getCookie(httpServletRequest, Admin.LOING_TOKEN_COOKIE_NAME);
        System.out.println("loginToken1=="+loginToken);
        System.out.println("loginToken2=="+adminService.getLoginToken());
        if(!StringUtils.equalsIgnoreCase(loginToken,adminService.getLoginToken())){
            WebUtils.issueRedirect(httpServletRequest,response,"/");
            return false;
        }
        return super.onAccessDenied(httpServletRequest,httpServletResponse);
    }


    /**
     * 登录成功处理
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        Session session = subject.getSession();
        Map<Object,Object> attributes = new HashMap<>();
        Collection<Object> keys = session.getAttributeKeys();
        for (Object key :keys){
            attributes.put(key,session.getAttribute(key));
        }
        session.stop();
        session = subject.getSession();
        for (Map.Entry<Object,Object> entry : attributes.entrySet()){
            session.setAttribute(entry.getKey(),entry.getKey());
        }
        return super.onLoginSuccess(token, subject, request, response);
    }

    /**
     * 获取密码
     * @param request
     * @return
     */
    @Override
    protected String getPassword(ServletRequest request) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String password  = rsaService.decryptParameter(enPasswordParam,httpServletRequest);
        rsaService.removePrivateKey(httpServletRequest);
        return password;
    }
    /**
     * 获取验证码ID
     * @param request
     * @return
     */
    protected String getCaptchaId(ServletRequest request){
        String captchaId = WebUtils.getCleanParam(request, captchaIdParam);
        if (captchaId == null) {
            captchaId = ((HttpServletRequest) request).getSession().getId();
        }
        return captchaId;
    }

    /**
     * 获取验证码
     * @param servletRequest
     * @return
     */
    protected String getCaptcha(ServletRequest servletRequest) {
        return WebUtils.getCleanParam(servletRequest, captchaParam);
    }

    public String getCaptchaIdParam() {
        return captchaIdParam;
    }

    public void setCaptchaIdParam(String captchaIdParam) {
        this.captchaIdParam = captchaIdParam;
    }

    public String getCaptchaParam() {
        return captchaParam;
    }

    public void setCaptchaParam(String captchaParam) {
        this.captchaParam = captchaParam;
    }

    public String getEnPasswordParam() {
        return enPasswordParam;
    }

    public void setEnPasswordParam(String enPasswordParam) {
        this.enPasswordParam = enPasswordParam;
    }
}
