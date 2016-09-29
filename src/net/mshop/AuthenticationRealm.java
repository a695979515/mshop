package net.mshop;

import net.mshop.entity.Admin;
import net.mshop.service.AdminService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
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
        net.mshop.AuthenticationToken token = (net.mshop.AuthenticationToken) authenticationToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());
        String captchaId = token.getCaptchaId();
        String captcha = token.getCaptcha();
        String ip = token.getHost();
        Admin admin = adminService.findByUsername(username);
        if(admin !=null){
            return new SimpleAuthenticationInfo(new Principal(admin.getId(),username),password,getName());
        }
        return null;
    }
}
