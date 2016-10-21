package net.mshop.controller.admin;

import net.mshop.entity.MemberRank;
import net.mshop.service.MemberRankService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * 会员等级Controller
 * Created by Panfuhao on 2016/9/26.
 */
@Controller("adminMemberRankController")
@RequestMapping("/admin/member_rank")
public class MemberRankController extends BaseController {
    @Resource(name = "memberRankServiceImpl")
    private MemberRankService memberRankService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(MemberRank memberRank, RedirectAttributes redirectAttributes) {
        if (memberRank.getIsSpecial()) {
            memberRank.setAmount(null);
        } else if (memberRank.getAmount() == null || memberRankService.amountExists(memberRank.getAmount())) {
            return "error";
        }
        memberRank.setMembers(null);
        memberRankService.save(memberRank);
        return "/admin/memberRank/member_rank";
    }
}
