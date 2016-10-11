package net.mshop.interceptor;

import net.mshop.util.WebUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by Panfuhao on 2016/10/11.
 */
public class TokenInterceptor extends HandlerInterceptorAdapter{
    private static final String TOKEN_ATTRIBUTE_NAME = "token";

    private static final String TOKEN_COOKIE_NAME="token";
    private static final String TOKEN_PARAMETER_NAME="token";
    private static final String ERROR_MESSAGE = "missing token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = WebUtils.getCookie(request,TOKEN_COOKIE_NAME);
        if(StringUtils.equalsIgnoreCase(request.getMethod(),"POST")){
            if(StringUtils.isNotEmpty(token)){
                String requestType = request.getHeader("X-Request-With");
                if(StringUtils.equalsIgnoreCase(requestType,"XMLHttpRequest")){
                    if(StringUtils.equals(token,request.getHeader(TOKEN_PARAMETER_NAME))){
                        return true;
                    }else{
                        response.addHeader("tokenStatus","accessDenied");
                    }
                }else{
                    if(StringUtils.equals(token,request.getParameter(TOKEN_PARAMETER_NAME))){
                        return true;
                    }
                }
            }else{
                WebUtils.addCookie(request,response,TOKEN_COOKIE_NAME, DigestUtils.md5Hex(UUID.randomUUID()+ RandomStringUtils.randomAlphabetic(30)));
            }
            response.sendError(HttpServletResponse.SC_FORBIDDEN,ERROR_MESSAGE);
            return false;
        }else{
            if(StringUtils.isEmpty(token)){
                token = DigestUtils.md5Hex(UUID.randomUUID()+RandomStringUtils.randomAlphabetic(30));
                WebUtils.addCookie(request,response,TOKEN_COOKIE_NAME,token);
            }
            request.setAttribute(TOKEN_ATTRIBUTE_NAME,token);
            return true;
        }
    }
}
