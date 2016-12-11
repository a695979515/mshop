package net.mshop.controller.admin;

import net.mshop.entity.Member;
import net.mshop.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * 用户管理 Controller
 * Created by Panfuhao on 2016/9/23.
 */
@Controller("adminMemberController")
@RequestMapping("/admin/member")
public class MemberController extends BaseController {

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Member member, Long memberRankId, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        member.setPoint(0L);
        member.setBalance(BigDecimal.ZERO);
        member.setAmount(BigDecimal.ZERO);
        member.setLoginFailureCount(0);
        member.setLockedDate(null);
        member.setRegisterIp(request.getRemoteAddr());
        member.setLoginIp(null);
        member.setLoginDate(null);
        member.setOpenId(null);
        member.setLockKey(null);
        memberService.save(member);
        return "/admin/member/member";
    }

}
