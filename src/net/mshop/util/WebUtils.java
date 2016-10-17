package net.mshop.util;

import net.mshop.entity.Setting;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.omg.CORBA.Object;
import org.springframework.util.Assert;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Panfuhao on 2016/10/8.
 */
public final class WebUtils {
    private static final PoolingHttpClientConnectionManager HTTP_CLIENT_CONNECTION_MANAGER;
    private static final CloseableHttpClient HTTP_CLIENT;

    static {
        HTTP_CLIENT_CONNECTION_MANAGER = new PoolingHttpClientConnectionManager(RegistryBuilder.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", SSLConnectionSocketFactory.getSocketFactory()).build());
        HTTP_CLIENT_CONNECTION_MANAGER.setDefaultMaxPerRoute(100);
        HTTP_CLIENT_CONNECTION_MANAGER.setMaxTotal(200);
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(60000).setConnectTimeout(60000).setSocketTimeout(60000).build();
        HTTP_CLIENT = HttpClientBuilder.create().setConnectionManager(HTTP_CLIENT_CONNECTION_MANAGER).setDefaultRequestConfig(requestConfig).build();
    }

    private WebUtils() {

    }

    /**
     * add cookie
     *
     * @param request
     * @param response
     * @param name     //cookie名称
     * @param value    //cookie值
     * @param maxAge   //有效期  秒
     * @param path     //路径
     * @param domain   //域
     * @param secure   //是否启用加密
     */
    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, Integer maxAge, String path, String domain, Boolean secure) {
        Assert.notNull(request);
        Assert.notNull(response);
        Assert.hasText(name);
        Assert.hasText(value);

        try {
            name = URLEncoder.encode(name, "UTF-8");
            value = URLEncoder.encode(value, "UTF-8");
            Cookie cookie = new Cookie(name, value);
            if (maxAge != null) {
                cookie.setMaxAge(maxAge);
            }
            if (StringUtils.isNotEmpty(path)) {
                cookie.setPath(path);
            }
            if (StringUtils.isNotEmpty(domain)) {
                cookie.setDomain(domain);
            }
            if (secure != null) {
                cookie.setSecure(secure);
            }
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, Integer maxAge) {
        Assert.notNull(request);
        Assert.notNull(response);
        Assert.hasText(name);
        Assert.hasText(value);
        Setting setting = SystemUtils.getSetting();
        addCookie(request, response, name, value, maxAge, setting.getCookiePath(), setting.getCookieDomain(), null);
    }

    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value) {
        Assert.notNull(request);
        Assert.notNull(response);
        Assert.hasText(name);
        Assert.hasText(value);
        Setting setting = SystemUtils.getSetting();
        addCookie(request, response, name, value, null, setting.getCookiePath(), setting.getCookieDomain(), null);
    }

    /**
     * 获取cookie
     *
     * @param request
     * @param name
     * @return
     */
    public static String getCookie(HttpServletRequest request, String name) {
        Assert.notNull(request);
        Assert.hasText(name);

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            try {
                name = URLEncoder.encode(name, "UTF-8");
                for (Cookie cookie : cookies) {
                    if (name.equals(cookie.getName())) {
                        return URLDecoder.decode(cookie.getValue(), "UTF-8");
                    }
                }
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return null;
    }

    /**
     * 移除cookie
     *
     * @param request
     * @param response
     * @param name
     * @param path
     * @param domain
     */
    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name, String path, String domain) {
        Assert.notNull(request);
        Assert.notNull(response);
        Assert.hasText(name);
        try {
            name = URLEncoder.encode(name, "UTF-8");
            Cookie cookie = new Cookie(name, null);
            cookie.setMaxAge(0);
            if (StringUtils.isNotEmpty(path)) {
                cookie.setPath(path);
            }
            if (StringUtils.isNotEmpty(domain)) {
                cookie.setDomain(domain);
            }
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 参数解析
     *
     * @param query
     * @param encoding
     * @return
     */
    public static Map<String, String> parse(String query, String encoding) {
        Assert.hasText(query);
        Charset charset;
        if (StringUtils.isNotEmpty(encoding)) {
            charset = Charset.forName(encoding);
        } else {
            charset = Charset.forName("UTF-8");
        }
        List<NameValuePair> nameValuePairs = URLEncodedUtils.parse(query, charset);
        Map<String, String> parameterMap = new HashMap<>();
        for (NameValuePair nameValuePair : nameValuePairs) {
            parameterMap.put(nameValuePair.getName(), nameValuePair.getValue());
        }
        return parameterMap;
    }

    /**
     * 解析参数
     *
     * @param query
     * @return
     */
    public static Map<String, String> parse(String query) {
        Assert.hasText(query);

        return parse(query, null);
    }

    /**
     * post请求
     *
     * @param url
     * @param parameterMap
     * @return
     */
    public static String post(String url, Map<String, Object> parameterMap) {
        Assert.hasText(url);
        String result = null;
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            if (parameterMap != null) {
                for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
                    String name = entry.getKey();
                    String value = ConvertUtils.convert(entry.getValue());
                    if (StringUtils.isNotEmpty(name)) {
                        nameValuePairs.add(new BasicNameValuePair(name, value));
                    }
                }
            }
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            CloseableHttpResponse httpResponse = HTTP_CLIENT.execute(httpPost);
            try {
                HttpEntity httpEntity = httpResponse.getEntity();
                if (httpEntity != null) {
                    result = EntityUtils.toString(httpEntity);
                    EntityUtils.consume(httpEntity);
                }
            } finally {
                try {
                    httpResponse.close();
                } catch (IOException e) {

                }
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return result;
    }

    /**
     * get请求
     *
     * @param url
     * @param parameterMap
     * @return
     */
    public static String get(String url, Map<String, Object> parameterMap) {
        Assert.hasText(url);
        String result = null;
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            if (parameterMap != null) {
                for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
                    String name = entry.getKey();
                    String value = ConvertUtils.convert(entry.getValue());
                    if (StringUtils.isNotEmpty(name)) {
                        nameValuePairs.add(new BasicNameValuePair(name, value));
                    }
                }
            }
            HttpGet httpGet = new HttpGet(url + (StringUtils.contains(url, "?") ? "&" : "?") + EntityUtils.toString(new UrlEncodedFormEntity(nameValuePairs, "UTF-8")));
            CloseableHttpResponse httpResponse = HTTP_CLIENT.execute(httpGet);
            try {
                HttpEntity httpEntity = httpResponse.getEntity();
                if (httpEntity != null) {
                    result = EntityUtils.toString(httpEntity);
                    EntityUtils.consume(httpEntity);
                }
            } finally {
                try {
                    httpResponse.close();
                } catch (IOException e) {

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return result;
    }
}
