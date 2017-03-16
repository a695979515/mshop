package net.mshop.controller.admin;

import net.mshop.entity.*;
import net.mshop.operator.Pageable;
import net.mshop.service.AdminService;
import net.mshop.service.RoleService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 管理员Controller
 * Created by Panfuhao on 2016/10/24.
 */
@Controller("adminAdminController")
@RequestMapping("/admin/admin")
public class AdminController extends BaseController {
    @Resource(name = "adminServiceImpl")
    private AdminService adminService;
    @Resource(name = "roleServiceImpl")
    private RoleService roleService;

    /**
     * 转到管理员列表
     *
     * @param pageable
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model) {
        model.addAttribute("page", adminService.findPage(pageable));
        return "/admin/admin/list";
    }

    /**
     * 转到添加页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        model.addAttribute("roles", roleService.findAll());

        return "/admin/admin/add";
    }

    /**
     * 保存操作
     *
     * @param admin
     * @param roleIds
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Admin admin, Long[] roleIds, RedirectAttributes redirectAttributes) {
        admin.setRoles(new HashSet<Role>(roleService.findListByIds(roleIds)));
        admin.setIsEnabled(admin.getIsEnabled() == null ? false : admin.getIsEnabled());
        admin.setIsLocked(admin.getIsLocked() == null ? false : admin.getIsLocked());
        if (!isValid(admin)) {
            return ERROR_VIEW;
        }
        if (adminService.usernameExists(admin.getUsername())) {
            return ERROR_VIEW;
        }
        admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
        admin.setIsLocked(false);
        admin.setLoginFailureCount(0);
        admin.setLockedDate(null);
        admin.setLoginDate(null);
        admin.setLoginIp(null);
        admin.setLockKey(null);
        adminService.save(admin);
        System.out.println("id===="+admin.getId());
        redirectAttributes.addFlashAttribute("success", "成功");
        return "redirect:list.html";

    }

    /**
     * 删除操作
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    Message delete(Long[] ids) {
        if (ids.length >= adminService.count()) {
            return Message.error("删除失败，必须至少保留一项");
        }
        adminService.deleteByIds(ids);
        return SUCCESS_MESSAGE;
    }

    /**
     * 转到编辑页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model) {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("admin", adminService.findById(id));
        return "/admin/admin/edit";
    }

    /**
     * 更新操作
     *
     * @param admin
     * @param roleIds
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Admin admin, Long[] roleIds, RedirectAttributes redirectAttributes) {
        admin.setRoles(new HashSet<Role>(roleService.findListByIds(roleIds)));
        admin.setIsEnabled(admin.getIsEnabled() == null ? false : admin.getIsEnabled());
        admin.setIsLocked(admin.getIsLocked() == null ? false : admin.getIsLocked());
        if (!isValid(admin)) {
            return ERROR_VIEW;
        }
        Admin oldAdmin = adminService.findById(admin.getId());
        if (oldAdmin == null) {
            return ERROR_VIEW;
        }
        if (StringUtils.isNotEmpty(admin.getPassword())) {
            admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
        } else {
            admin.setPassword(oldAdmin.getPassword());
        }
        if (oldAdmin.getIsLocked() && !admin.getIsLocked()) {
            admin.setLoginFailureCount(0);
            admin.setLockedDate(null);
        } else {
            admin.setIsLocked(oldAdmin.getIsLocked());
            admin.setLoginFailureCount(oldAdmin.getLoginFailureCount());
            admin.setLockedDate(oldAdmin.getLockedDate());
        }
        adminService.update(admin, "username", "loginDate", "loginIp", "lockKey");
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html";
    }

    /**
     * 检查用户名是否存在
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/check_username", method = RequestMethod.GET)
    public
    @ResponseBody
    boolean checkUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return false;
        }
        return !adminService.usernameExists(username);
    }
}
