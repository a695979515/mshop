package net.mshop.util;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.util.Assert;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by Panfuhao on 2016/10/10.
 */
public final class RSAUtils {
    /**
     * 密钥算法
     */
    private static final String KEY_ALGORITHM = "RSA";
    /**
     * 加密/解密算法
     */
    private static final String TRANSFORMATION = "RSA/ECB/PKCS1Padding";
    /**
     * RSA加密算法提供商
     */
    private static final Provider PROVIDER = new BouncyCastleProvider();

    /**
     * 不可实例化
     */
    private RSAUtils() {

    }

    /**
     * 生成密钥对
     *
     * @param keySize
     * @return
     */
    public static KeyPair generateKeyPair(int keySize) {
        Assert.state(keySize > 0);
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM, PROVIDER);
            keyPairGenerator.initialize(keySize);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 生成私钥
     *
     * @param encodedKey
     * @return
     */
    public static PrivateKey generatePrivateKey(byte[] encodedKey) {
        Assert.notNull(encodedKey);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM, PROVIDER);
            return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 生成私钥
     *
     * @param keyString
     * @return
     */
    public static PrivateKey generatePrivateKey(String keyString) {
        Assert.hasText(keyString);
        return generatePrivateKey(Base64.decodeBase64(keyString));
    }

    /**
     * 生成公钥
     *
     * @param encodedKey
     * @return
     */
    public static PublicKey generatePublicKey(byte[] encodedKey) {
        Assert.notNull(encodedKey);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM, PROVIDER);
            return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 生成公钥
     *
     * @param keyString
     * @return
     */
    public static PublicKey generatePublicKey(String keyString) {
        Assert.hasText(keyString);
        return generatePublicKey(Base64.decodeBase64(keyString));
    }

    /**
     * 获取密钥字符串
     *
     * @param key
     * @return
     */
    public static String getKeyString(Key key) {
        Assert.notNull(key);
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     * 获取密钥
     *
     * @param type
     * @param inputStream
     * @param password
     * @return
     */
    public static Key getKey(String type, InputStream inputStream, String password) {
        Assert.hasText(type);
        Assert.notNull(inputStream);
        try {
            KeyStore keyStore = KeyStore.getInstance(type, PROVIDER);
            keyStore.load(inputStream, password != null ? password.toCharArray() : null);
            String alias = keyStore.aliases().hasMoreElements() ? keyStore.aliases().nextElement() : null;
            return keyStore.getKey(alias, password != null ? password.toCharArray() : null);
        } catch (KeyStoreException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (CertificateException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (UnrecoverableKeyException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 获取证书
     *
     * @param type
     * @param inputStream
     * @return
     */
    public static java.security.cert.Certificate getCertificate(String type, InputStream inputStream) {
        Assert.hasText(type);
        Assert.notNull(inputStream);
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance(type, PROVIDER);
            return certificateFactory.generateCertificate(inputStream);
        } catch (CertificateException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 生成签名
     *
     * @param algorithm
     * @param privateKey
     * @param data
     * @return
     */
    public static byte[] sign(String algorithm, PrivateKey privateKey, byte[] data) {
        Assert.hasText(algorithm);
        Assert.notNull(privateKey);
        Assert.notNull(data);
        try {
            Signature signature = Signature.getInstance(algorithm, PROVIDER);
            signature.initSign(privateKey);
            signature.update(data);
            return signature.sign();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (SignatureException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 验证签名
     *
     * @param algorithm
     * @param publicKey
     * @param sign
     * @param data
     * @return
     */
    public static boolean verify(String algorithm, PublicKey publicKey, byte[] sign, byte[] data) {
        Assert.hasText(algorithm);
        Assert.notNull(publicKey);
        Assert.notNull(sign);
        Assert.notNull(data);
        try {
            Signature signature = Signature.getInstance(algorithm, PROVIDER);
            signature.initVerify(publicKey);
            signature.update(data);
            return signature.verify(sign);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (SignatureException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 验证签名
     *
     * @param algorithm
     * @param certificate
     * @param sign
     * @param data
     * @return
     */
    public static boolean verify(String algorithm, Certificate certificate, byte[] sign, byte[] data) {
        Assert.hasText(algorithm);
        Assert.notNull(certificate);
        Assert.notNull(sign);
        Assert.notNull(data);
        try {
            Signature signature = Signature.getInstance(algorithm, PROVIDER);
            signature.initVerify(certificate);
            signature.update(data);
            return signature.verify(sign);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (SignatureException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 加密
     *
     * @param publicKey
     * @param data
     * @return
     */
    public static byte[] encrypt(PublicKey publicKey, byte[] data) {
        Assert.notNull(publicKey);
        Assert.notNull(data);
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION, PROVIDER);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 解密
     *
     * @param privateKey
     * @param data
     * @return
     */
    public static byte[] decrypt(PrivateKey privateKey, byte[] data) {
        Assert.notNull(privateKey);
        Assert.notNull(data);
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION, PROVIDER);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
