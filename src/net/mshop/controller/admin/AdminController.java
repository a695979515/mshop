package net.mshop.controller.admin;

import net.mshop.operator.Pageable;
import net.mshop.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by Panfuhao on 2016/10/24.
 */
@Controller("adminAdminController")
@RequestMapping("/admin/admin")
public class AdminController extends BaseController {
    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model) {
        model.addAttribute("page", adminService.findPage(pageable));
        return "/admin/admin/list";
    }
}
