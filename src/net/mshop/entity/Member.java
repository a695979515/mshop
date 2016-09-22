package net.mshop.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Panfuhao on 2016/9/21.
 */
@Entity
@Table(name = "m_member")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_member")
public class Member extends BaseEntity<Long> {

    /**
     * 性别
     */
    public enum Gender {
        male,   //男
        female  //女
    }

    /**
     * 排名类型
     */
    public enum RankingType {
        point,  //积分
        balance,    //余额
        amout   //消费金额
    }

    /**
     * 用户名的Cookie名称
     */
    public static final String USERNAME_COOKIE_NAME = "username";
    /**
     * 昵称的cookie名称
     */
    public static final String NICKNAME_COOKIE_NAME = "nickname";

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 积分
     */
    private Long point;
    /**
     * 账户余额
     */
    private BigDecimal balance;
    /**
     * 消费金额
     */
    private BigDecimal amount;
    /**
     * 账户是否启用
     */
    private Boolean isEnabled;
    /**
     * 账户是否被锁
     */
    private Boolean isLocked;
    /**
     * 登录错误次数
     */
    private Integer loginFailureCount;
    /**
     * 锁定时间
     */
    private Date lockedDate;
    /**
     * 注册IP
     */
    private String registerIp;
    /**
     * 登录IP
     */
    private String loginIp;
    /**
     * 登录时间
     */
    private Date loginDate;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private Member.Gender gender;
    /**
     * 生日
     */
    private Date birth;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 登录插件ID
     */
    private String loginPlugId;
    /**
     * OpenID
     */
    private String openId;
    /**
     * 锁定KEY
     */
    private String lockKey;

    @NotEmpty(groups = Save.class)
    @Pattern(regexp = "^[0-9a-zA-Z_\\u4e00-\\u9fa5]+$")
    @Column(nullable = false, updatable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotEmpty(groups = Save.class)
    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotEmpty
    @Email
    @Length(max = 200)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Length(max = 200)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Column(nullable = false)
    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    @Column(nullable = false, precision = 27, scale = 12)
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Column(nullable = false, precision = 27, scale = 12)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @NotNull
    @Column(nullable = false)
    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    @Column(nullable = false)
    public Boolean getLocked() {
        return isLocked;
    }

    public void setLocked(Boolean locked) {
        isLocked = locked;
    }

    @Column(nullable = false)
    public Integer getLoginFailureCount() {
        return loginFailureCount;
    }

    public void setLoginFailureCount(Integer loginFailureCount) {
        this.loginFailureCount = loginFailureCount;
    }

    public Date getLockedDate() {
        return lockedDate;
    }

    public void setLockedDate(Date lockedDate) {
        this.lockedDate = lockedDate;
    }

    @Column(nullable = false, updatable = false)
    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    @Length(max = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Length(max = 200)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(updatable = false)
    public String getLoginPlugId() {
        return loginPlugId;
    }

    public void setLoginPlugId(String loginPlugId) {
        this.loginPlugId = loginPlugId;
    }

    @Column(updatable = false)
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Column(nullable = false, updatable = false)
    public String getLockKey() {
        return lockKey;
    }

    public void setLockKey(String lockKey) {
        this.lockKey = lockKey;
    }
}
