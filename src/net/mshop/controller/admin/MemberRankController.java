package net.mshop.controller.admin;

import net.mshop.entity.MemberRank;
import net.mshop.operator.Pageable;
import net.mshop.service.MemberRankService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

    /**
     * 保存操作
     * @param memberRank
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(MemberRank memberRank, RedirectAttributes redirectAttributes) {
        if (memberRank.getIsSpecial()) {
            memberRank.setAmount(null);
        } else if (memberRank.getAmount() == null || memberRankService.amountExists(memberRank.getAmount())) {
            return "error";
        }
        memberRank.setMembers(null);
        memberRankService.save(memberRank);
        return "redirect:list.html";
    }
    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model) {
        model.addAttribute("page", memberRankService.findPage(pageable));
        return "/admin/member_rank/list";
    }
    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        return "/admin/member_rank/add";
    }
}
