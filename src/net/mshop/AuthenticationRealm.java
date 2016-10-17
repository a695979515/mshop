package net.mshop;

import net.mshop.entity.Admin;
import net.mshop.entity.Setting;
import net.mshop.service.AdminService;
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
import java.util.Date;
import java.util.List;

/**
 * Created by Panfuhao on 2016/9/29.
 */
public class AuthenticationRealm extends AuthorizingRealm {
    @Resource(name = "adminServiceImpl")
    private AdminService adminService;


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken authenticationToken) throws AuthenticationException {
        AuthenticationToken token = (AuthenticationToken) authenticationToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());
        String captcha = token.getCaptcha();
        String ip = token.getHost();
        String captchaId = token.getCaptchaId();
        if (username != null && password != null) {
            Admin admin = adminService.findByUsername(username);
            if (admin == null) {
                throw new UnknownAccountException();
            }
            if (!admin.getIsEnabled()) {
                throw new DisabledAccountException();
            }
            Setting setting = SystemUtils.getSetting();
            if (admin.getIsEnabled()) {
                if(ArrayUtils.contains(setting.getAccountLockTypes(),Setting.AccountLockType.admin)){
                    int loginFailureLockTime = setting.getAutoUnlockTime();
                    if(loginFailureLockTime==0){
                        throw new LockedAccountException();
                    }
                    Date lockedDate = admin.getLockedDate();
                    Date unlockDate = DateUtils.addMinutes(lockedDate,loginFailureLockTime);
                    if(new Date().after(unlockDate)){
                        admin.setLoginFailureCount(0);
                        admin.setIsLocked(false);
                        admin.setLockedDate(null);
                        adminService.update(admin);
                    }else{
                        throw  new LockedAccountException();
                    }
                }else{
                    admin.setLoginFailureCount(0);
                    admin.setIsLocked(false);
                    admin.setLockedDate(null);
                    adminService.update(admin);
                }
            }
            if (!DigestUtils.md5Hex(password).equals(admin.getPassword())) {
                int loginFailureCount = admin.getLoginFailureCount()+1;
                if(loginFailureCount>=setting.getFailureLoginCount()){
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


}
