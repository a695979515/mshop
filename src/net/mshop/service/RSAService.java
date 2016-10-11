package net.mshop.service;

import javax.servlet.http.HttpServletRequest;
import java.security.interfaces.RSAPublicKey;

/**
 * Created by Panfuhao on 2016/10/10.
 */
public interface RSAService {
    /**
     * 生成密钥  （添加私钥至Seesion并返回公钥）
     * @param request
     * @return
     */
    RSAPublicKey generateKey(HttpServletRequest request);

    /**
     * 移除私钥
     * @param request
     */
    void removePrivateKey(HttpServletRequest request);

    /**
     * 解密参数
     * @param name
     * @param request
     * @return
     */
    String decryptParameter(String name,HttpServletRequest request);
}
