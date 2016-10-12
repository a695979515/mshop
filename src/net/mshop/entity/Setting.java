package net.mshop.entity;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Panfuhao on 2016/10/11.
 */
@ScriptAssert(lang = "javascript", script = "_this.usernameMaxLength >= _this.usernameMinLength && _this.passwordMaxLength >= _this.passwordMinLength")
public class Setting implements Serializable {
    /**
     * 缓存名称
     */
    public static final String CACHE_NAME = "setting";
    /**
     * 分隔符
     */
    private static final String SEPARATOR = ",";

    /**
     * 水印位置  枚举
     */
    public enum WaterMarkPosition {
        no,     //无
        topLeft,    //左上
        topRight,   //又上
        center,     //中间
        bottomLeft, //左下
        bottomRight //右下
    }

    /**
     * 小数位精确  枚举
     */
    public enum RoundType {
        roundHalfUp,//四舍五入
        roundUp,    //向上取整
        roundDown   //向下取整
    }

    /**
     * 验证码  枚举
     */
    public enum CaptchaType {
        memberLogin,    //会员登录
        memberRegister, //会员注册
        adminLogin,     //管理员登录
        findPassword,   //找回密码
        resetPassword,  //重置密码
        other           //其他
    }

    /**
     * 账户锁定类型  枚举
     */
    public enum AccountLockType {
        member, //会员
        admin   //管理员
    }

    /**
     * 库存修改时间点 枚举
     */
    public enum StockAllocationTime {
        order,  //下单时
        payment,    //支付时
        ship    //发货时
    }

    /**
     * 网站名称
     */
    private String siteName;
    /**
     * 网站地址
     */
    private String siteUrl;
    /**
     * logo
     */
    private String logo;
    /**
     * 热门搜索
     */
    private String hotSearch;
    /**
     * 联系地址
     */
    private String address;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 邮政编码
     */
    private String zipCode;
    /**
     * 联系邮箱
     */
    private String email;
    /**
     * 备案号
     */
    private String recordNumber;
    /**
     * 是否开启网站
     */
    private Boolean siteEnabled;
    /**
     * 网站关闭消息
     */
    private String closeMessage;
    /**
     * 商品图片宽度  大图
     */
    private Integer largeProductImageWidth;
    /**
     * 商品图片高度  大图
     */
    private Integer largeProductImageHeight;
    /**
     * 商品图片宽度  中图
     */
    private Integer mediumProductImageWidth;
    /**
     * 商品图片高度  中图
     */
    private Integer mediumProductImageHeight;
    /**
     * 商品图片宽度  缩略图
     */
    private Integer thumbnailProductImageWidth;
    /**
     * 商品图片高度  缩略图
     */
    private Integer thumbnailProductImageHeight;
    /**
     * 默认图片 大图
     */
    private String defaultLargeProductImage;
    /**
     * 默认图片 中图
     */
    private String defaultMediumProductImage;
    /**
     * 默认图片 缩略图
     */
    private String defaultThumbnailProductImage;
    /**
     * 水印透明度
     */
    private Integer watermarkAlpha;
    /**
     * 水印图片
     */
    private String watermarkImage;
    /**
     * 水印位置
     */
    private Setting.WaterMarkPosition waterMarkPosition;
    /**
     * 价格小数点精确位数
     */
    private Integer priceScale;
    /**
     * 价格精确方式
     */
    private Setting.RoundType roundType;
    /**
     * 是否显示市场价
     */
    private Boolean showMarketPrice;
    /**
     * 默认市场价比例
     */
    private Double defaultMarketPriceScale;
    /**
     * 是否开放注册
     */
    private Boolean registerEnabled;
    /**
     * 不允许用户名
     */
    private String disabledUsername;
    /**
     * 用户名最小长度
     */
    private Integer usernameMinLength;
    /**
     * 用户名最大长度
     */
    private Integer usernameMaxLength;
    /**
     * 密码最小长度
     */
    private Integer passwordMinLength;
    /**
     * 密码最大长度
     */
    private Integer passwordMaxLength;
    /**
     * 注册送多少积分
     */
    private Long registerPoint;
    /**
     * 注册协议
     */
    private String registerAgreement;
    /**
     * 验证码类型
     */
    private Setting.CaptchaType[] captchaTypes;
    /**
     * 账户锁定类型
     */
    private Setting.AccountLockType[] accountLockTypes;
    /**
     * 连续登录失败最大次数
     */
    private Integer failureLoginCount;
    /**
     * 自动解锁时间
     */
    private Integer autoUnlockTime;
    /**
     * 安全密钥有效时间
     */
    private Integer safeKeyExpiryTime;
    /**
     * 上传文件最大限制
     */
    private Integer uploadMaxSize;
    /**
     * 允许上传图片扩展名
     */
    private String uploadImageExtension;
    /**
     * 允许上传媒体扩展名
     */
    private String uploadMediaExtension;
    /**
     * 允许上传文件扩展名
     */
    private String uploadFileExtension;
    /**
     * 图片上传路径
     */
    private String imageUploadPath;
    /**
     * 媒体上传路径
     */
    private String mediaUploadPath;
    /** 文件上传路径 */
    private String fileUploadPath;
    /**
     * SMTP地址
     */
    private String smtpHost;
    /**
     * SMTP端口
     */
    private Integer smtpPort;
    /**
     * SMPT用户名
     */
    private String smtpUsername;
    /**
     * SMPT密码
     */
    private String smtpPassword;
    /**
     * SMTP是否启用SSL
     */
    private Boolean smtpSSLEnabled;
    /**
     * 发件人邮箱
     */
    private String smtpFromMail;
    /**
     * 货币符号
     */
    private String currencySign;
    /**
     * 货币单位
     */
    private String currencyUnit;
    /**
     * 库存警告触发数
     */
    private Integer stockAlertCount;
    /**
     * 库存修改时间点
     */
    private Setting.StockAllocationTime stockAllocationTime;
    /**
     * 默认积分换算比列
     */
    private Double defaultPointScale;
    /**
     * 是否开启评论
     */
    private Boolean reviewEnabled;
    /**
     * 是否审核评论
     */
    private Boolean reviewCheck;
    /**
     * 是否开启发票功能
     */
    private Boolean invoiceEnabled;
    /**
     * 是否开启含税价
     */
    private Boolean taxPriceEnabled;
    /**
     * 税率
     */
    private Boolean taxRate;
    /**
     * cookie路径
     */
    private String cookiePath;
    /**
     * cookie作用的域名
     */
    private String cookieDomain;
    /**
     * 快递100的key
     */
    private String kuaidi100key;
    /**
     * 是否开启cnzz统计
     */
    private Boolean cnzzEnabled;
    /**
     * cnzz统计站点ID
     */
    private String cnzzSiteId;
    /**
     * cnzz统计密码
     */
    private String cnzzPassword;
    /**
     * 主题
     */
    private String theme;

    @NotEmpty
    @Length(max = 200)
    public Integer getAutoUnlockTime() {
        return autoUnlockTime;
    }

    public void setAutoUnlockTime(Integer autoUnlockTime) {
        this.autoUnlockTime = autoUnlockTime;
    }

    @NotNull
    @Min(1)
    public Integer getFailureLoginCount() {
        return failureLoginCount;
    }

    public void setFailureLoginCount(Integer failureLoginCount) {
        this.failureLoginCount = failureLoginCount;
    }

    public AccountLockType[] getAccountLockTypes() {
        return accountLockTypes;
    }

    public void setAccountLockTypes(AccountLockType[] accountLockTypes) {
        this.accountLockTypes = accountLockTypes;
    }

    @Length(max = 200)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CaptchaType[] getCaptchaTypes() {
        return captchaTypes;
    }

    public void setCaptchaTypes(CaptchaType[] captchaTypes) {
        this.captchaTypes = captchaTypes;
    }

    @NotEmpty
    public String getCloseMessage() {
        return closeMessage;
    }

    public void setCloseMessage(String closeMessage) {
        this.closeMessage = closeMessage;
    }

    @Null
    public Boolean getCnzzEnabled() {
        return cnzzEnabled;
    }

    public void setCnzzEnabled(Boolean cnzzEnabled) {
        this.cnzzEnabled = cnzzEnabled;
    }

    @Null
    public String getCnzzPassword() {
        return cnzzPassword;
    }

    public void setCnzzPassword(String cnzzPassword) {
        this.cnzzPassword = cnzzPassword;
    }

    @Null
    public String getCnzzSiteId() {
        return cnzzSiteId;
    }

    public void setCnzzSiteId(String cnzzSiteId) {
        this.cnzzSiteId = cnzzSiteId;
    }

    @Length(max = 200)
    public String getCookieDomain() {
        return cookieDomain;
    }

    public void setCookieDomain(String cookieDomain) {
        this.cookieDomain = cookieDomain;
    }

    @NotEmpty
    @Length(max = 200)
    public String getCookiePath() {
        return cookiePath;
    }

    public void setCookiePath(String cookiePath) {
        this.cookiePath = cookiePath;
    }

    @NotEmpty
    @Length(max = 200)
    public String getCurrencySign() {
        return currencySign;
    }

    public void setCurrencySign(String currencySign) {
        this.currencySign = currencySign;
    }

    @NotEmpty
    @Length(max = 200)
    public String getCurrencyUnit() {
        return currencyUnit;
    }

    public void setCurrencyUnit(String currencyUnit) {
        this.currencyUnit = currencyUnit;
    }

    @NotEmpty
    @Length(max = 200)
    @Pattern(regexp = "^(?i)(http:\\/\\/|https:\\/\\/|\\/).*$")
    public String getDefaultLargeProductImage() {
        return defaultLargeProductImage;
    }

    public void setDefaultLargeProductImage(String defaultLargeProductImage) {
        this.defaultLargeProductImage = defaultLargeProductImage;
    }

    public Double getDefaultMarketPriceScale() {
        return defaultMarketPriceScale;
    }

    public void setDefaultMarketPriceScale(Double defaultMarketPriceScale) {
        this.defaultMarketPriceScale = defaultMarketPriceScale;
    }

    @NotEmpty
    @Length(max = 200)
    @Pattern(regexp = "^(?i)(http:\\/\\/|https:\\/\\/|\\/).*$")
    public String getDefaultMediumProductImage() {
        return defaultMediumProductImage;
    }

    public void setDefaultMediumProductImage(String defaultMediumProductImage) {
        this.defaultMediumProductImage = defaultMediumProductImage;
    }

    @NotNull
    @Min(0)
    @Digits(integer = 3, fraction = 3)
    public Double getDefaultPointScale() {
        return defaultPointScale;
    }

    public void setDefaultPointScale(Double defaultPointScale) {
        this.defaultPointScale = defaultPointScale;
    }

    @NotEmpty
    @Length(max = 200)
    @Pattern(regexp = "^(?i)(http:\\/\\/|https:\\/\\/|\\/).*$")
    public String getDefaultThumbnailProductImage() {
        return defaultThumbnailProductImage;
    }

    public void setDefaultThumbnailProductImage(String defaultThumbnailProductImage) {
        this.defaultThumbnailProductImage = defaultThumbnailProductImage;
    }

    @Length(max = 200)
    public String getDisabledUsername() {
        return disabledUsername;
    }

    public void setDisabledUsername(String disabledUsername) {
        if (disabledUsername != null) {
            disabledUsername = disabledUsername.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "");
        }
        this.disabledUsername = disabledUsername;
    }

    @Email
    @Length(max = 200)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Length(max = 200)
    public String getHotSearch() {
        return hotSearch;
    }

    public void setHotSearch(String hotSearch) {
        if (hotSearch != null) {
            hotSearch = hotSearch.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "");
        }
        this.hotSearch = hotSearch;
    }

    @NotNull
    public Boolean getInvoiceEnabled() {
        return invoiceEnabled;
    }

    public void setInvoiceEnabled(Boolean invoiceEnabled) {
        this.invoiceEnabled = invoiceEnabled;
    }

    @Length(max = 200)
    public String getKuaidi100key() {
        return kuaidi100key;
    }

    public void setKuaidi100key(String kuaidi100key) {
        this.kuaidi100key = kuaidi100key;
    }

    @NotNull
    @Min(1)
    public Integer getLargeProductImageHeight() {
        return largeProductImageHeight;
    }

    public void setLargeProductImageHeight(Integer largeProductImageHeight) {
        this.largeProductImageHeight = largeProductImageHeight;
    }

    @NotNull
    @Min(1)
    public Integer getLargeProductImageWidth() {
        return largeProductImageWidth;
    }

    public void setLargeProductImageWidth(Integer largeProductImageWidth) {
        this.largeProductImageWidth = largeProductImageWidth;
    }

    @NotEmpty
    @Length(max = 200)
    @Pattern(regexp = "^(?i)(http:\\/\\/|https:\\/\\/|\\/).*$")
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @NotNull
    @Min(1)
    public Integer getMediumProductImageHeight() {
        return mediumProductImageHeight;
    }

    public void setMediumProductImageHeight(Integer mediumProductImageHeight) {
        this.mediumProductImageHeight = mediumProductImageHeight;
    }

    @NotNull
    @Min(1)
    public Integer getMediumProductImageWidth() {
        return mediumProductImageWidth;
    }

    public void setMediumProductImageWidth(Integer mediumProductImageWidth) {
        this.mediumProductImageWidth = mediumProductImageWidth;
    }

    @NotNull
    @Min(1)
    @Max(117)
    public Integer getPasswordMaxLength() {
        return passwordMaxLength;
    }

    public void setPasswordMaxLength(Integer passwordMaxLength) {
        this.passwordMaxLength = passwordMaxLength;
    }

    @NotNull
    @Min(1)
    @Max(117)
    public Integer getPasswordMinLength() {
        return passwordMinLength;
    }

    public void setPasswordMinLength(Integer passwordMinLength) {
        this.passwordMinLength = passwordMinLength;
    }

    @Length(max = 200)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @NotNull
    @Min(0)
    @Max(3)
    public Integer getPriceScale() {
        return priceScale;
    }

    public void setPriceScale(Integer priceScale) {
        this.priceScale = priceScale;
    }

    @Length(max = 200)
    public String getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
    }

    @NotEmpty
    public String getRegisterAgreement() {
        return registerAgreement;
    }

    public void setRegisterAgreement(String registerAgreement) {
        this.registerAgreement = registerAgreement;
    }

    @NotNull
    public Boolean getRegisterEnabled() {
        return registerEnabled;
    }

    public void setRegisterEnabled(Boolean registerEnabled) {
        this.registerEnabled = registerEnabled;
    }

    @NotNull
    @Min(0)
    public Long getRegisterPoint() {
        return registerPoint;
    }

    public void setRegisterPoint(Long registerPoint) {
        this.registerPoint = registerPoint;
    }

    @NotNull
    public Boolean getReviewCheck() {
        return reviewCheck;
    }

    public void setReviewCheck(Boolean reviewCheck) {
        this.reviewCheck = reviewCheck;
    }

    @NotNull
    public Boolean getReviewEnabled() {
        return reviewEnabled;
    }

    public void setReviewEnabled(Boolean reviewEnabled) {
        this.reviewEnabled = reviewEnabled;
    }

    @NotNull
    public RoundType getRoundType() {
        return roundType;
    }

    public void setRoundType(RoundType roundType) {
        this.roundType = roundType;
    }

    @NotNull
    @Min(0)
    public Integer getSafeKeyExpiryTime() {
        return safeKeyExpiryTime;
    }

    public void setSafeKeyExpiryTime(Integer safeKeyExpiryTime) {
        this.safeKeyExpiryTime = safeKeyExpiryTime;
    }

    @NotNull
    public Boolean getShowMarketPrice() {
        return showMarketPrice;
    }

    public void setShowMarketPrice(Boolean showMarketPrice) {
        this.showMarketPrice = showMarketPrice;
    }

    @NotNull
    public Boolean getSiteEnabled() {
        return siteEnabled;
    }

    public void setSiteEnabled(Boolean siteEnabled) {
        this.siteEnabled = siteEnabled;
    }

    @NotEmpty
    @Length(max = 200)
    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    @NotEmpty
    @Length(max = 200)
    @Pattern(regexp = "^(?i)(http:\\/\\/|https:\\/\\/).*$")
    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    @NotEmpty
    @Email
    @Length(max = 200)
    public String getSmtpFromMail() {
        return smtpFromMail;
    }

    public void setSmtpFromMail(String smtpFromMail) {
        this.smtpFromMail = smtpFromMail;
    }

    @NotEmpty
    @Length(max = 200)
    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    @Length(max = 200)
    public String getSmtpPassword() {
        return smtpPassword;
    }

    public void setSmtpPassword(String smtpPassword) {
        this.smtpPassword = smtpPassword;
    }

    @NotNull
    @Min(0)
    public Integer getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(Integer smtpPort) {
        this.smtpPort = smtpPort;
    }

    @NotNull
    public Boolean getSmtpSSLEnabled() {
        return smtpSSLEnabled;
    }

    public void setSmtpSSLEnabled(Boolean smtpSSLEnabled) {
        this.smtpSSLEnabled = smtpSSLEnabled;
    }

    @NotEmpty
    @Length(max = 200)
    public String getSmtpUsername() {
        return smtpUsername;
    }

    public void setSmtpUsername(String smtpUsername) {
        this.smtpUsername = smtpUsername;
    }

    @NotNull
    @Min(0)
    public Integer getStockAlertCount() {
        return stockAlertCount;
    }

    public void setStockAlertCount(Integer stockAlertCount) {
        this.stockAlertCount = stockAlertCount;
    }

    @NotNull
    public StockAllocationTime getStockAllocationTime() {
        return stockAllocationTime;
    }

    public void setStockAllocationTime(StockAllocationTime stockAllocationTime) {
        this.stockAllocationTime = stockAllocationTime;
    }

    @NotNull
    public Boolean getTaxPriceEnabled() {
        return taxPriceEnabled;
    }

    public void setTaxPriceEnabled(Boolean taxPriceEnabled) {
        this.taxPriceEnabled = taxPriceEnabled;
    }

    @NotNull
    @Min(0)
    @Digits(integer = 3, fraction = 3)
    public Boolean getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Boolean taxRate) {
        this.taxRate = taxRate;
    }

    @Null
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @NotNull
    @Min(1)
    public Integer getThumbnailProductImageHeight() {
        return thumbnailProductImageHeight;
    }

    public void setThumbnailProductImageHeight(Integer thumbnailProductImageHeight) {
        this.thumbnailProductImageHeight = thumbnailProductImageHeight;
    }

    @NotNull
    @Min(1)
    public Integer getThumbnailProductImageWidth() {
        return thumbnailProductImageWidth;
    }

    public void setThumbnailProductImageWidth(Integer thumbnailProductImageWidth) {
        this.thumbnailProductImageWidth = thumbnailProductImageWidth;
    }

    @Length(max = 200)
    public String getUploadFileExtension() {
        return uploadFileExtension;
    }

    public void setUploadFileExtension(String uploadFileExtension) {
        if (uploadFileExtension != null) {
            uploadFileExtension = uploadFileExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
        }
        this.uploadFileExtension = uploadFileExtension;
    }

    @Length(max = 200)
    public String getUploadImageExtension() {
        return uploadImageExtension;
    }

    public void setUploadImageExtension(String uploadImageExtension) {
        if (uploadImageExtension != null) {
            uploadImageExtension = uploadImageExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
        }
        this.uploadImageExtension = uploadImageExtension;
    }

    @NotNull
    @Min(0)
    public Integer getUploadMaxSize() {
        return uploadMaxSize;
    }

    public void setUploadMaxSize(Integer uploadMaxSize) {
        this.uploadMaxSize = uploadMaxSize;
    }

    @Length(max = 200)
    public String getUploadMediaExtension() {
        return uploadMediaExtension;
    }

    public void setUploadMediaExtension(String uploadMediaExtension) {
        if (uploadMediaExtension != null) {
            uploadMediaExtension = uploadMediaExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
        }
        this.uploadMediaExtension = uploadMediaExtension;
    }

    @NotNull
    @Min(1)
    @Max(117)
    public Integer getUsernameMaxLength() {
        return usernameMaxLength;
    }

    public void setUsernameMaxLength(Integer usernameMaxLength) {
        this.usernameMaxLength = usernameMaxLength;
    }

    @NotNull
    @Min(1)
    @Max(117)
    public Integer getUsernameMinLength() {
        return usernameMinLength;
    }

    public void setUsernameMinLength(Integer usernameMinLength) {
        this.usernameMinLength = usernameMinLength;
    }

    public String getWatermarkImage() {
        return watermarkImage;
    }

    public void setWatermarkImage(String watermarkImage) {
        this.watermarkImage = watermarkImage;
    }

    @NotNull
    @Min(0)
    @Max(100)
    public Integer getWatermarkAlpha() {
        return watermarkAlpha;
    }

    public void setWatermarkAlpha(Integer watermarkAlpha) {
        this.watermarkAlpha = watermarkAlpha;
    }

    @NotNull
    public WaterMarkPosition getWaterMarkPosition() {
        return waterMarkPosition;
    }

    public void setWaterMarkPosition(WaterMarkPosition waterMarkPosition) {
        this.waterMarkPosition = waterMarkPosition;
    }

    @Length(max = 200)
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    @NotEmpty
    @Length(max = 200)
    public String getImageUploadPath() {
        return imageUploadPath;
    }

    public void setImageUploadPath(String imageUploadPath) {
        if (imageUploadPath != null) {
            if (!imageUploadPath.startsWith("/")) {
                imageUploadPath = "/" + imageUploadPath;
            }
            if (!imageUploadPath.endsWith("/")) {
                imageUploadPath += "/";
            }
        }
        this.imageUploadPath = imageUploadPath;
    }
    @NotEmpty
    @Length(max = 200)
    public String getMediaUploadPath() {
        return mediaUploadPath;
    }

    public void setMediaUploadPath(String mediaUploadPath) {
        if (mediaUploadPath != null) {
            if (!mediaUploadPath.startsWith("/")) {
                mediaUploadPath = "/" + mediaUploadPath;
            }
            if (!mediaUploadPath.endsWith("/")) {
                mediaUploadPath += "/";
            }
        }
        this.mediaUploadPath = mediaUploadPath;
    }
    @NotEmpty
    @Length(max = 200)
    public String getFileUploadPath() {
        return fileUploadPath;
    }

    public void setFileUploadPath(String fileUploadPath) {
        if (fileUploadPath != null) {
            if (!fileUploadPath.startsWith("/")) {
                fileUploadPath = "/" + fileUploadPath;
            }
            if (!fileUploadPath.endsWith("/")) {
                fileUploadPath += "/";
            }
        }
        this.fileUploadPath = fileUploadPath;
    }

    /**
     * 获取热门搜索
     * @return
     */
    public String[] getHotSearches(){
        return StringUtils.split(hotSearch,SEPARATOR);
    }

    /**
     * 获取不允许用户名
     * @return
     */
    public String[] getDisabledUsernames(){
        return StringUtils.split(disabledUsername,SEPARATOR);
    }

    /**
     * 获取允许上传图片扩展名
     * @return
     */
    public String[] getUploadImageExtensions(){
        return StringUtils.split(uploadFileExtension,SEPARATOR);
    }

    /**
     * 获取允许上传媒体扩展名
     * @return
     */
    public String[] getUploadMediaExtensions(){
        return StringUtils.split(uploadMediaExtension,SEPARATOR);
    }
    /**
     * 获取允许上传文件扩展名
     * @return
     */
    public String[] getUploadFileExtensions(){
        return StringUtils.split(uploadFileExtension,SEPARATOR);
    }

    /**
     * 设置精度
     * @param amount
     * @return
     */
    public BigDecimal setScale(BigDecimal amount){
        if(amount!=null && getPriceScale()!=null && getRoundType()!=null){
            switch (getRoundType()){
                case roundUp:
                    return amount.setScale(getPriceScale(),BigDecimal.ROUND_UP);
                case roundDown:
                    return amount.setScale(getPriceScale(),BigDecimal.ROUND_DOWN);
                case roundHalfUp:
                    return amount.setScale(getPriceScale(),BigDecimal.ROUND_HALF_UP);
            }
        }
        return amount;
    }
}
