package net.mshop;

import net.mshop.entity.Admin;
import net.mshop.service.AdminService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Panfuhao on 2016/9/29.
 */
public class AuthenticationRealm extends AuthorizingRealm {
    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("doGetAuthorizationInfo......................");
        Principal principal = (Principal) principalCollection.fromRealm(getName()).iterator().next();
        if(principal!=null){
            List<String> authorities = adminService.findAuthorities(principal.getId());
            if(authorities!=null){
                SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
                authorizationInfo.addStringPermissions(authorities);
                return authorizationInfo;
            }
        }

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("AuthenticationInfo......................");
     //   net.mshop.AuthenticationToken token = (net.mshop.AuthenticationToken) authenticationToken;
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());
        String ip = token.getHost();
        System.out.println("username=="+username);
        System.out.println("ip=="+ip);
     //   String captchaId = token.getCaptchaId();
     //   String captcha = token.getCaptcha();
        Admin admin = adminService.findByUsername(username);
        if(admin !=null){
            return new SimpleAuthenticationInfo(new Principal(admin.getId(),username),password,getName());
        }
        return null;
    }
}
