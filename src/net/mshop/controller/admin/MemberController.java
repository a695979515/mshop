package net.mshop.controller.admin;

import net.mshop.entity.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Panfuhao on 2016/9/23.
 */
@Controller("adminMemberController")
@RequestMapping("/admin/member")
public class MemberController extends BaseController{
    @RequestMapping(value = "save" ,method = RequestMethod.POST)
    public String save(Member member, Long memberRankId, HttpServletRequest request, RedirectAttributes redirectAttributes){
      //  member.setMemberRank();
        return "/admin/member/member";
    }
}
