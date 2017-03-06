package net.mshop.controller.admin;

import net.mshop.entity.Member;
import net.mshop.operator.Pageable;
import net.mshop.service.MemberRankService;
import net.mshop.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    @Resource(name="memberRankServiceImpl")
    private MemberRankService memberRankService;

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
        return "redirect:list.html";
    }

    /**
     * 会员列表
     * @param pageable
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model) {
        model.addAttribute("page", memberService.findPage(pageable));
        return "/admin/member/list";
    }

    /**
     * 查看
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(Long id, ModelMap model) {
        Member member = memberService.findById(id);
     //   model.addAttribute("genders", Member.Gender.values());
     //   model.addAttribute("memberAttributes", memberAttributeService.findList(true, true));
        model.addAttribute("member", member);
    //    model.addAttribute("loginPlugin", pluginService.getLoginPlugin(member.getLoginPluginId()));
        return "/admin/member/view";
    }
    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        model.addAttribute("genders", Member.Gender.values());
        model.addAttribute("memberRanks", memberRankService.findAll());
     //   model.addAttribute("memberAttributes", memberAttributeService.findList(true, true));
        return "/admin/member/add";
    }

}
