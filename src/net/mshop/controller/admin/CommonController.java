package net.mshop.controller.admin;

import net.mshop.service.CaptchaService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Panfuhao on 2016/10/17.
 */
@Controller("adminCommonController")
@RequestMapping("/admin/common")
public class CommonController implements ServletContextAware {

    private ServletContext servletContext;
    @Resource(name = "captchaServiceImpl")
    private CaptchaService captchaService;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main() {
        return "/admin/common/main";
    }

    /**
     * 验证码
     *
     * @param captchaId
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "captcha", method = RequestMethod.GET)
    public void captcha(String captchaId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (StringUtils.isEmpty(captchaId)) {
            captchaId = request.getSession().getId();
        }
        String pragma = new StringBuilder().append("yB").append("-").append("der").append("ewoP").reverse().toString();
        String value = new StringBuilder().append("ten").append(".").append("xxp").append("ohs").reverse().toString();
        response.addHeader(pragma, value);
        response.addHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        OutputStream outputStream = response.getOutputStream();
        BufferedImage bufferedImage = captchaService.buildImage(captchaId);
        ImageIO.write(bufferedImage, "jpeg", outputStream);
        outputStream.flush();
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
