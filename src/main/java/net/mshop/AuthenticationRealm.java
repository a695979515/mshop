package net.mshop;

import net.mshop.entity.Admin;
import net.mshop.entity.Setting;
import net.mshop.exception.IncorrectCaptchaException;
import net.mshop.service.AdminService;
import net.mshop.service.CaptchaService;
import net.mshop.util.SystemUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Panfuhao on 2016/9/29.
 */
public class AuthenticationRealm extends AuthorizingRealm {
    @Resource(name = "adminServiceImpl")
    private AdminService adminService;
    @Resource(name = "captchaServiceImpl")
    private CaptchaService captchaService;


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken authenticationToken){
        AuthenticationToken token = (AuthenticationToken) authenticationToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());
        String captcha = token.getCaptcha();
        String ip = token.getHost();
        String captchaId = token.getCaptchaId();
        if (!captchaService.isValid(Setting.CaptchaType.adminLogin, captchaId, captcha)) {
            throw new IncorrectCaptchaException();
        }
        if (username != null && password != null) {
            Admin admin = adminService.findByUsername(username);
            if (admin == null) {
                throw new UnknownAccountException();
            }
            if (!admin.getIsEnabled()) {
                throw new DisabledAccountException();
            }
            Setting setting = SystemUtils.getSetting();
            if (admin.getIsLocked()) {
                if (ArrayUtils.contains(setting.getAccountLockTypes(), Setting.AccountLockType.admin)) {
                    int loginFailureLockTime = setting.getAutoUnlockTime();
                    if (loginFailureLockTime == 0) {
                        throw new LockedAccountException();
                    }
                    Date lockedDate = admin.getLockedDate();
                    Date unlockDate = DateUtils.addMinutes(lockedDate, loginFailureLockTime);
                    if (new Date().after(unlockDate)) {
                        admin.setLoginFailureCount(0);
                        admin.setIsLocked(false);
                        admin.setLockedDate(null);
                        adminService.update(admin);
                    } else {
                        throw new LockedAccountException();
                    }
                } else {
                    admin.setLoginFailureCount(0);
                    admin.setIsLocked(false);
                    admin.setLockedDate(null);
                    adminService.update(admin);
                }
            }
            if (!DigestUtils.md5Hex(addSalt(admin.getUsername(),password,admin.getCreateDate())).equals(admin.getPassword())) {
                int loginFailureCount = admin.getLoginFailureCount() + 1;
                if (loginFailureCount >= setting.getFailureLoginCount()) {
                    admin.setIsLocked(true);
                    admin.setLockedDate(new Date());
                }
                admin.setLoginFailureCount(loginFailureCount);
                adminService.update(admin);
                throw new IncorrectCredentialsException();
            }
            admin.setLoginIp(ip);
            admin.setLoginDate(new Date());
            admin.setLoginFailureCount(0);
            adminService.update(admin);
            return new SimpleAuthenticationInfo(new Principal(admin.getId(), username), password, getName());
        }
        throw new UnknownAccountException();
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Principal principal = (Principal) principalCollection.fromRealm(getName()).iterator().next();
        if (principal != null) {
            List<String> authorities = adminService.findAuthorities(principal.getId());
            if (authorities != null) {
                SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
                authorizationInfo.addStringPermissions(authorities);
                return authorizationInfo;
            }
        }
        return null;
    }

    /**
     * 加盐值
     * @param username
     * @param password
     * @param createDate
     * @return
     */
    public String addSalt(String username,String password,Date createDate){
        StringBuffer stringBuffer = new StringBuffer();
        char[] usernames = username.toCharArray();
        char[] passwords = password.toCharArray();
        for(int i=0;i<username.length();i++){
            stringBuffer.append(usernames[i]);
            for (int j=0;j<password.length();j++){
                stringBuffer.append(passwords[j]);
            }
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        stringBuffer.append(dateFormat.format(createDate).toString());
        return stringBuffer.toString();
    }




}
